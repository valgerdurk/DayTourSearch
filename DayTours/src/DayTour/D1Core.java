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
        
        // NOTE: the following line should not be called in general,
        //          prefer to load from .dat file.
        
        /* 
        c.LoadFromText("trips.txt");
        c.Debug();
        System.out.println("----------------------------------");
        c.WriteToDisk("tourdata.dat");
        c.LoadFromDisk("tourdata.dat");
        c.Debug();
        */
        
        
        // Demonstration:
        c.LoadFromDisk("tourdata.dat");
        for (TourInfo t : c.AllTours()) {
            System.out.println(t.title);
        }
    }
    
}