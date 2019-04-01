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

    
    // NOTE: This method assumes a list of VALID bookings,
    //        e.g. all bookings refer to a valid day for
    //        the given tour, and reserve a number of seats
    //        which are in fact available.
    //      If this is not true, the method will fail on
    //        the first invalid booking, and process the list
    //        no further.
    //      Note that all bookings confirmed PRIOR to the
    //        invalid booking will still be confirmed after
    //        failure.
    public boolean Confirm(List<BookingInfo> pending) {
        for (BookingInfo b : pending) {
            if (!b.tour.ReserveSeats(b.day, b.seats)) {
                return false;
                
                // NOTE: If the desired behaviour is that
                //  booking list continues processing after
                //  an invalid booking, use the below line
                //  instead of the above.
                
                //continue;
            }
            bookings.add(b);
        }
        return true;
    }
    
    public List<TourInfo> AllTours() {
        return tours;
    }
    
    public TourInfo TourFromID(int ID) {
        for (TourInfo t : tours) {
            if (t.ID == ID) {
                return t;
            }
        }
        
        return null;
    }
    
    public void Debug() {
        Iterator<TourInfo> i = tours.iterator();
        while (i.hasNext()) {
            System.out.println(i.next().title);
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
            
            n = dat.readInt();
            for (int i = 0; i < n; i++) {
                BookingInfo b = new BookingInfo(this, dat);
                bookings.add(b);
            }
            
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
            
            dat.writeInt(bookings.size());
            for (BookingInfo b : bookings) {
                b.WriteToStream(dat);
            }
            
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
