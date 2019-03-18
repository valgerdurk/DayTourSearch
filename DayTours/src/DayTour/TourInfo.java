/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public final class TourInfo {
    private boolean[]       monthsAvailable;
    private int             seatsPerDay;
    private int[]           freeSeats;
    public final boolean    doesPickup;
    public final Region     region;
    public final int        durationHours;
    public final int        priceISK;
    public final String     title;
    public final String     img;
    public final String     description;
    private List<String>    tags;
    private List<TourType>  activity;
    public final String     availableDesc;
    public final int        rating;
    
    // These value is used for serial-/deserialization:
    public final int        ID;
    private static int      seed = 0;
    
    private static int GenID() {
        seed++;
        return seed - 1;
    }
    
    // Construct TourInfo from data stream.
    public TourInfo(DataInputStream in)
            throws IOException
    {
        monthsAvailable = new boolean[12];
        for (int i = 0; i < 12; i++) {
            monthsAvailable[i] = in.readBoolean();
        }
        
        seatsPerDay = in.readInt();
        
        freeSeats = new int[365];
        for (int i = 0; i < 365; i++) {
            freeSeats[i] = in.readInt();
        }
        
        doesPickup = in.readBoolean();
        region = Region.Interpret(in.readUTF());
        durationHours = in.readInt();
        priceISK = in.readInt();
        title = in.readUTF();
        img = in.readUTF();
        description = in.readUTF();
        
        tags = new ArrayList<>();
        int n = in.readInt();
        for (int i = 0; i < n; i++) {
            AddTag(in.readUTF());
        }
        
        activity = new ArrayList<>();
        n = in.readInt();
        for (int i = 0; i < n; i++) {
            AddActivity(TourType.Interpret(in.readUTF()));
        }
        
        availableDesc = in.readUTF();
        rating = in.readInt();
        
        // NOTE: If we're loading a file, we update
        //          the ID generator seed to reflect
        //          the highest ID yet seen.
        ID = in.readInt();
        if (seed <= ID) {
            seed = ID + 1;
        }
    }
    
    // Serialize the TourInfo object to a data stream.
    public boolean WriteToStream(DataOutputStream out)
            throws IOException
    {
        for (int i = 0; i < 12; i++) {
            out.writeBoolean(monthsAvailable[i]);
        }
        
        out.writeInt(seatsPerDay);
        
        for (int i = 0; i < 365; i++) {
            out.writeInt(freeSeats[i]);
        }
        
        out.writeBoolean(doesPickup);
        out.writeUTF(region.name());
        out.writeInt(durationHours);
        out.writeInt(priceISK);
        out.writeUTF(title);
        out.writeUTF(img);
        out.writeUTF(description);
        
        out.writeInt(tags.size());
        for (String s : tags) {
            out.writeUTF(s);
        }
        
        out.writeInt(activity.size());
        for (TourType t : activity) {
            out.writeUTF(t.name());
        }
        
        out.writeUTF(availableDesc);
        out.writeInt(rating);
        out.writeInt(ID);
        
        return true;
    }
    
    public boolean ReserveSeats(int day, int seats) {
        // Input validation:
        if (day < 0 || day >= 365 || seats <= 0) {
            return false;
        }
        
        // If sufficient seats are free on the specified day,
        //  reduce the number of free seats by the desired count.
        if (freeSeats[day] >= seats) {
            freeSeats[day] -= seats;
            return true;
        }
        
        return false;
    }
    
    // NOTE:    This method assumes both dates are from
    //           the SAME year.  Will misfire horribly
    //           if they are not.
    public int[] IsAvailable(Date begin, Date end) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(begin);
        
        int d0 = cal.get(Calendar.DAY_OF_YEAR) - 1;
        
        cal.setTime(end);
        int d1 = cal.get(Calendar.DAY_OF_YEAR) - 1;
        
        // NOTE:    This method does NOT check whether
        //           begin is BEFORE end.
        int[] out = new int[(d1 - d0) + 1];
        
        for (int i = d0; i <= d1; i++) {
            out[i - d0] = freeSeats[i];
        }
        
        return out;
    }
    
    // This method fills out the freeSeats array based on
    //  monthsAvailable and seatsPerDay.
    public void Complete() {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int r = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < days[i]; j++) {
                if (monthsAvailable[i]) {
                    freeSeats[r] = seatsPerDay;
                }
                else {
                    freeSeats[r] = -1;
                }
                r++;
            }
        }
    }
    
    public boolean IsType(TourType act) {
        Iterator<TourType> it = activity.iterator();
        while(it.hasNext()) {
            if (act.equals(it.next()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public void AddActivity(TourType act) {
        activity.add(act);
    }
    
    public boolean HasTag(String tag) {
        Iterator<String> it = tags.iterator();
        while(it.hasNext()) {
            if (tag.equals(it.next()))
            {
                return true;
            }
        }
        
        return false;
    }
    
    public void AddTag(String tag) {
        tags.add(tag);
    }
    
    public TourInfo(boolean[] av,
            boolean dp,
            Region reg,
            int dur,
            int p,
            String t,
            String i,
            String d,
            String a,
            int rat) {
        doesPickup = dp;
        region = reg;
        durationHours = dur;
        priceISK = p;
        title = t;
        img = i;
        description = d;
        availableDesc = a;
        rating = rat;
        
        monthsAvailable = new boolean[12];
        for (int k = 0; k < 12; k++) {
            monthsAvailable[k] = av[k];
        }
        
        seatsPerDay = 10;
        freeSeats = new int[365];
        
        tags = new ArrayList<>();
        activity = new ArrayList<>();
        
        ID = GenID();
    }
}
