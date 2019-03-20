/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTourFx;

import DayTour.InfoCache;
import DayTour.TourInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class DayTour extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("DayTourMain.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         // TODO code application logic here
        InfoCache c = new InfoCache();
        
        // NOTE: the following line should not be called in general,
        //          prefer to load from .dat file.
        // c.LoadFromText("trips.txt");
        
        
        // Demonstration:
        c.LoadFromDisk("tourdata.dat");
        for (TourInfo t : c.AllTours()) {
            System.out.println(t.title);
        }
    }
    
}
