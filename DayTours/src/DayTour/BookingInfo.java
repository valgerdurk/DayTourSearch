/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.io.*;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public class BookingInfo {
    public final int        day;
    public final int        seats;
    public final String     name;
    public final boolean    pickup;
    public final String     pickLocation;
    public final TourInfo   tour;
    
    public boolean WriteToStream(DataOutputStream out)
            throws IOException
    {
        out.writeInt(day);
        out.writeInt(seats);
        out.writeUTF(name);
        out.writeBoolean(pickup);
        out.writeUTF(pickLocation);
        out.writeInt(tour.ID);
        
        return true;
    }
    
    public BookingInfo(InfoCache cache, DataInputStream in)
            throws IOException
    {
        day = in.readInt();
        seats = in.readInt();
        name = in.readUTF();
        pickup = in.readBoolean();
        pickLocation = in.readUTF();
        
        int id = in.readInt();
        tour = cache.TourFromID(id);
    }
    
    public BookingInfo(int day, int seats, String name, boolean pickup, String location, TourInfo tour) {
        this.day = day;
        this.seats = seats;
        this.name = name;
        this.pickup = pickup;
        this.pickLocation = location;
        this.tour = tour;
    }
}
