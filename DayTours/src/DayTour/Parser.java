/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

// import static DayTour.InfoCache.ParseRegion;
// import static DayTour.InfoCache.ParseType;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public class Parser {
    private class TextData {
        public boolean[]       months;
        public int             seats;
        public boolean         doesPickup;
        public Region          region;
        public int             durationHours;
        public int             priceISK;
        public String          title;
        public String          image;
        public String          desc;
        public String          about;
        public String          availDesc;
        public int             rating;
        public List<TourType>  types;
        public List<String>    tags;
        
        public TextData() {
            months = new boolean[12];
            seats = 0;
            doesPickup = false;
            region = Region.UNDEFINED;
            durationHours = 0;
            priceISK = 0;
            title = "Placeholder title";
            image = "Placeholder image";
            desc = "Placeholder description";
            about = "Placeholder about";
            availDesc = "Placeholder availability";
            rating = 0;
            types = new ArrayList<>();
            tags = new ArrayList<>();
        }
    }
    
    private final List<TourInfo>      data;
    
    public Iterator<TourInfo> Data() {
        return data.iterator();
    }
    
    public Parser(String fileName)
            throws IOException
    {
        data = new ArrayList<>();
        
        File inFile = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(inFile));
        
        String line = br.readLine();
        TextData obj = new TextData();
        
        while (line != null) {
            line = line.trim();
            
            String[] tokens = line.split("\\s", 3);
            if (tokens.length == 3) {
                if (ProcessLine(obj, tokens[0], tokens[2])) {
                    obj = new TextData();
                }
            }
            
            line = br.readLine();
        }
    }
    
    private boolean ProcessLine(TextData obj, String key, String value) {
        key = StripQuotes(key);
        
        switch (key) {
            case "title":
                obj.title = StripQuotes(StripComma(value));
                break;

            case "duration":
                obj.durationHours = Integer.parseInt(StripQuotes(StripComma(value)));
                break;

            case "price":
                obj.priceISK = Integer.parseInt(StripQuotes(StripComma(value))) * 100;
                break;

            case "availability":
                obj.availDesc = StripQuotes(StripComma(value));
                if (obj.availDesc.equals("All year")) {
                    for (int i = 0; i < 12; i++) {
                        obj.months[i] = true;
                    }
                }
                else {
                    int[] m = ParseMonths(obj.availDesc);
                    for (int i = 0; i < 12; i++) {
                        if (i >= m[0] && i <= m[1]) {
                            obj.months[i] = true;
                        }
                        else {
                            obj.months[i] = false;
                        }
                    }
                }
                break;
                
            case "seats":
                obj.seats = Integer.parseInt(StripQuotes(StripComma(value)));
                break;

            case "type":
                if (value.matches("\\[.*\\],")) {
                    String[] list = List(value);
                    
                    for (String s : list) {
                        obj.types.add(TourType.Interpret(s));
                    }
                }
                else {
                    obj.types.add(TourType.Interpret(StripQuotes(StripComma(value))));
                }
                break;

            case "region":
                obj.region = Region.Interpret(StripQuotes(StripComma(value)));
                // System.out.println(reg.name());
                break;

            case "description":
                obj.desc = StripQuotes(StripComma(value));
                break;

            case "about":
                obj.about = StripQuotes(StripComma(value));
                break;

            case "image":
                // Ugly cutting method on image file string.
                // Strings of form "File:xxxxxxxx",
                obj.image = StripQuotes(StripComma(value)).split(":")[1];
                break;

            case "keywords":
                // Note, the tour definition ends here.
                String[] tags = List(value);
                
                for (String s : tags) {
                    obj.tags.add(s);
                }
                
                data.add(Construct(obj));
                return true;
        }
        // False return indicates object is not yet complete.
        return false;
    }
    
    // This method translates a TextData structure into a
    //  TourInfo object.
    private TourInfo Construct(TextData obj) {
        TourInfo t = new TourInfo(
                obj.months,
                obj.doesPickup, 
                obj.region,
                obj.durationHours,
                obj.priceISK,
                obj.title,
                obj.image,
                obj.desc,
                obj.availDesc,
                obj.rating,
                obj.seats);
        
        for (TourType type : obj.types) {
            t.AddActivity(type);
        }
        
        for (String s : obj.tags) {
            t.AddTag(s);
        }
        
        return t;
    }
    
    private int ParseSingleMonth(String month) {
        switch (month) {
            case "Jan":     return 0;
            // February not present in dataset.
            case "March":   return 2;
            case "April":   return 3;
            case "May":     return 4;
            case "June":    return 5;
            // July not present in dataset.
            case "August":  return 7;
            case "Sept":    return 8;
            case "Oct":     return 9;
            // Novemeber not present in dataset.
            case "Dec":     return 11;
        }
        return -1;
    }
    
    private int[] ParseMonths(String args) {
        String[] tokens = args.split("-");
        int[] out = new int[2];
        out[0] = ParseSingleMonth(tokens[0]);
        out[1] = ParseSingleMonth(tokens[1]);
        return out;
    }
    
    // This method breaks a JSON list into an array of strings.
    private String[] List(String in) {
        String[] tokens = in.split("\\[");
        tokens = tokens[1].split("\\]");
        tokens = tokens[0].split("\"");
        
        List<String> list = new ArrayList<>();
        for (int i = 1; i < tokens.length; i += 2) {
            list.add(tokens[i]);
        }
        
        tokens = new String[list.size()];
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = list.get(i);
        }
        
        return tokens;
    }
    
    // This method strips a trailing comma from a string.
    private String StripComma(String in) {
        return in.substring(0, in.length() - 1);
    }
    
    // This method strips surrounding quotation marks from a string.
    private String StripQuotes(String in) {
        return in.substring(1, in.length() - 1);
    }
}
