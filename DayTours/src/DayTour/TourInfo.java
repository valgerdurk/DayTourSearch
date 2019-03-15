/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.util.Date;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public class TourInfo {
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
    
    public boolean ReserveSeats(int day, int seats) {
        return false;
    }
    
    public int[] IsAvailable(Date begin, Date end) {
        return null;
    }
    
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
    }
}
