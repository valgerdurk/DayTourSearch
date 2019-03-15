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
    
    private String[] ParseList(String line) {
        // String[] tokens
        
        return null;
    }
    
    public boolean LoadFromDisk(String fileName) {
        File inFile = new File(fileName);
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(inFile));
            
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
        
            String line = br.readLine();
            while (line != null) {
                // System.out.println("line.");
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
                            break;
                        case "type":
                            // HANDLE
                            break;
                        case "region":
                            // HANDLE
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
                            obj = new TourInfo(pick, reg, duration, price, title, img, about, avail, rating);
                            tours.add(obj);
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
