/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public class InfoCache {
    private List<TourInfo>          tours;
    private List<BookingInfo>       bookings;
    
    public List<TourInfo> AllTours() {
        return tours;
    }
    
    private String StripKey(String in) {
        // Strips strings of the form "xxxxxxxx"
        return in.substring(1, in.length() - 1); 
    }
    
    private String Strip(String in) {
        // Strips strings of the form "xxxxxxxx",
        return in.substring(1, in.length() - 2); 
    }
    
    public void Debug() {
        Iterator<TourInfo> i = tours.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().title);
        }
    }
    
    private void PrintList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
    
    private int ParseSingleMonth(String month) {
        switch (month) {
            case "Jan":     return 0;
            case "March":   return 2;
            case "April":   return 3;
            case "May":     return 4;
            case "June":    return 5;
            case "August":  return 7;
            case "Sept":    return 8;
            case "Oct":     return 9;
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
    
    private String[] ParseList(String line) {
        String[] tokens = line.split("\\[");
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
    
    public static Region ParseRegion(String reg) {
        // Ugly switch taking advantage of fall-through,
        //  whoop whoop!
        switch (reg) {
            case "south":
            case "SOUTH":           
                return Region.SOUTH;
                
            case "west":
            case "WEST":            
                return Region.WESTCOAST;
                
            case "westfjords":      
            case "WESTFJORDS":      
                return Region.WESTFJORDS;
                
            case "capital area":    
            case "CAPITAL":         
                return Region.CAPITAL;
                
            case "east":            
            case "EAST":            
                return Region.EAST;
                
            case "north":           
            case "NORTH":           
                return Region.NORTH;
                
            default:                
                return Region.UNDEFINED;
        }
    }
    
    public static TourType ParseType(String type) {
        switch (type) {
            case "hiking":
            case "HIKING":
                return TourType.HIKING;
                
            case "boat":
            case "SAILING":
                return TourType.SAILING;
                
            case "glacier":
            case "GLACIER":
                return TourType.GLACIER;
                
            case "food":
            case "FOOD":
                return TourType.FOOD;
                
            case "sightseeing":
            case "SIGHTS":
                return TourType.SIGHTS;
                
            default:
                return TourType.UNDEFINED;
        }
    }
    
    public boolean LoadFromDisk(String fileName) {
        // Start by clearing any current cache.
        tours.clear();
        bookings.clear();
        
        try {
            FileInputStream in = new FileInputStream(fileName);
            DataInputStream dat = new DataInputStream(in);
            
            int n = dat.readInt();
            for (int i = 0; i < n; i++) {
                TourInfo t = new TourInfo(dat);
                tours.add(t);
            }
            
            // TODO:    Read BookingInfo list.
            
            dat.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException.");
            return false;
        } catch (IOException e) {
            System.out.println("IOException.");
            return false;
        }
        
        return true;
    }
    
    public boolean WriteToDisk(String fileName) {
        try {
            FileOutputStream out = new FileOutputStream(fileName);
            DataOutputStream dat = new DataOutputStream(out);
            
            dat.writeInt(tours.size());
            for (TourInfo t : tours) {
                t.WriteToStream(dat);
            }
            
            // TODO:    Write BookingInfo list.
            
            dat.close();
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException.");
            return false;
        } catch (IOException e) {
            System.out.println("IOException.");
            return false;
        }
        
        return true;
    }
    
    // NOTE: This is an ugly barebones text parser for the
    //          specific JSON data source.  This method should
    //          NOT be used in general, prefer LoadFromDisk(..)
    //          using tourdata.dat
    public boolean LoadFromText(String fileName) {
        File inFile = new File(fileName);
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(inFile));
            
            boolean[] ab = new boolean[12];
            boolean pick = false;
            Region reg = Region.UNDEFINED;
            int duration = 0;
            int price = 0;
            String title = "";
            String img = "";
            String about = "";
            String desc = "";
            String avail = "No information.";
            int rating = 0;
            List<TourType> types = new ArrayList<>();
        
            String line = br.readLine();
            while (line != null) {
                line = line.trim();
                
                String[] tokens = line.split("\\s", 3);
                TourInfo obj = null;
                
                // All tour definitions have 3 or more tokens.
                if (tokens.length >= 3) {
                    switch (StripKey(tokens[0])) {
                        case "title":
                            title = tokens[2];
                            break;
                            
                        case "duration":
                            duration = Integer.parseInt(Strip(tokens[2]));
                            break;
                            
                        case "price":
                            price = Integer.parseInt(Strip(tokens[2])) * 100;
                            break;
                            
                        case "availability":
                            avail = Strip(tokens[2]);
                            if (avail.equals("All year")) {
                                for (int i = 0; i < 12; i++) {
                                    ab[i] = true;
                                }
                            }
                            else {
                                int[] m = ParseMonths(avail);
                                for (int i = 0; i < 12; i++) {
                                    if (i >= m[0] && i <= m[1]) {
                                        ab[i] = true;
                                    }
                                    else {
                                        ab[i] = false;
                                    }
                                }
                            }
                            break;
                            
                        case "type":
                            if (line.matches("\\[*\\],")) {
                                String[] list = ParseList(line);
                                for (int i = 0; i < list.length; i++) {
                                    types.add(ParseType(list[i]));
                                }
                            }
                            else {
                                types.add(ParseType(Strip(tokens[2])));
                            }
                            break;
                        
                        case "region":
                            reg = ParseRegion(Strip(tokens[2]));
                            System.out.println(reg.name());
                            break;
                            
                        case "description":
                            desc = Strip(tokens[2]);
                            break;
                            
                        case "about":
                            about = Strip(tokens[2]);
                            break;
                            
                        case "image":
                            img = Strip(tokens[2]);
                            break;
                            
                        case "keywords":
                            // Note, the tour definition ends here.
                            String[] tags = ParseList(line);
                            // PrintList(ParseList(line));
                            obj = new TourInfo(ab, pick, reg, duration, price, title, img, about, avail, rating);
                            for (int i = 0; i < tags.length; i++) {
                                obj.AddTag(tags[i]);
                            }
                            Iterator<TourType> it = types.iterator();
                            while (it.hasNext()) {
                                obj.AddActivity(it.next());
                            }
                            obj.Complete();
                            tours.add(obj);
                            types.clear();
                            break;
                    }
                }
                
                line = br.readLine();
            }
                
        } catch (IOException e) {
            System.out.println("IOException.");
        }
        
        return true;
    }
    
    public InfoCache() {
        tours = new ArrayList<>();
        bookings = new ArrayList<>();
    }
}
