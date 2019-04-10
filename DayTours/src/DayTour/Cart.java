/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class Cart {
    
    public List<BookingInfo> pending = new ArrayList<BookingInfo>();
    private BookingInfo booking;
    
    /**
     * Adds booking to list of bookings in Cart
     */
    public void makeBooking(int day, int seats, String name, boolean pickup, String pickLocation, TourInfo tour) {
        booking = new BookingInfo(day, seats, name, pickup, pickLocation, tour);
        pending.add(booking);
    }
    
    /**
     * Removes booking from list of bookings in Cart
     * @param target 
     */
    public void removeBooking(int target) {
        pending.remove(target);
    }          
}
