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
public class D1Core {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        InfoCache c = new InfoCache();
        c.LoadFromDisk("trips.txt");
        c.Debug();
        
        String[] test = new String("\"abc\"").split("\"");
        System.out.println(test.length);
        for (int i = 0; i < test.length; i++) {
            System.out.println(test[i]);
        }
    }
    
}
