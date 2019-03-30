/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public enum TourType {
    UNDEFINED,
    HIKING,
    SAILING,
    GLACIER,
    FOOD,
    SIGHTS;
    
    public static TourType Interpret(String type) {
        switch (type) {
            case "hike":
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
}
