/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author David Francis <dff1@hi.is>
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class D1Core extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
        
        
        // Temporary testing code below, confirming that GenBookingSet(..)
        //  is behaving as expected.
        InfoCache c = new InfoCache();
        // c.LoadFromText("trips.txt");
        // c.WriteToDisk("tourdata.dat");
        c.LoadFromDisk("tourdata.dat");
        
        TourInfo t0 = c.AllTours().get(0);
        
        Calendar c0 = Calendar.getInstance();
        c0.set(2019, 0, 1);
        
        Calendar c1 = Calendar.getInstance();
        c1.set(2019, 11, 31);
        
        int[] d = t0.IsAvailable(c0.getTime(), c1.getTime());
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int r = 0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < days[i]; j++) {
                System.out.print(" " + d[r]);
                r++;
            }
            System.out.println("");
        }
    }
    
    // NOTE: Please do NOT call this method.  I left this here
    //  for my convenience in case I need to regenerate the dummy
    //  booking set later, but there are some additional steps that
    //  need to be taken for this to behave correctly.
    //      - dff
    private static void GenBookingSet() {
        InfoCache c = new InfoCache();
        c.LoadFromDisk("tourdata.dat");
        
        List<BookingInfo> pending = new ArrayList<>();
        
        for (TourInfo t : c.AllTours()) {
            for (int i = 0; i < 365; ++i) {
                // Generate a random number of booked seats for each day
                //  such that there is a 1/6 chance that the tour is
                //  completely booked.
                double mod = t.seatsPerDay;
                mod *= 1.2;
                int x = (int) Math.floor(Math.random() * mod);
                if (x > t.seatsPerDay) {
                    x = t.seatsPerDay;
                }
                
                BookingInfo bi = new BookingInfo(i, x, "__SYSTEM", false, "__UNKNOWN", t);
                pending.add(bi);
            }
        }
        
        c.Confirm(pending);
        c.WriteToDisk("tourdata.dat");
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DayTour.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }   
}