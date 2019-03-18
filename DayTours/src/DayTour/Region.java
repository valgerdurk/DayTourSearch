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
public enum Region {
    UNDEFINED,
    CAPITAL,
    NORTH,
    SOUTH,
    EAST,
    WESTFJORDS,
    WESTCOAST,
    HIGHLANDS;
    
    public static Region Interpret(String reg) {
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
}
