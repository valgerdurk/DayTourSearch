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
    
    public void Debug() {
        Iterator<TourInfo> i = tours.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().title);
        }
    }
    
    /*
    private void PrintList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }
    */
    
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
        
        try {
            Parser p = new Parser(fileName);
            Iterator<TourInfo> i = p.Data();
            while (i.hasNext()) {
                tours.add(i.next());
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
