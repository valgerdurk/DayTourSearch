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
        // TODO code application logic here
        launch(args);
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
        
        /**c.LoadFromDisk("tourdata.dat");
        for (TourInfo t : c.AllTours()) {
            System.out.println(t.title);
        }**/
        
    }
    
    private static void GenBookingSet() {
        // TODO: Generate sample bookings for some
        //          tours for testing purposes.
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DayTour.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }   
}