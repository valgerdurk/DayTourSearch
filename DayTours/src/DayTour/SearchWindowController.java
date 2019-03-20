/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import DayTour.TourInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class SearchWindowController implements Initializable {

    @FXML
    private Button leitaHandle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      /***
     * Method that search for trips by string 
     * @param tour list of trips in Iceland 
     * @param search SearchString
     * @return return results as a string 
     */
     /**private ObservableList<TourInfo> chooseRegion(ObservableList<TourInfo> tour, String search) {
        ObservableList<TourInfo> newVector = FXCollections.observableArrayList();
      
        for (TourInfo s : tour) {
            String a = s.getRegion().toLowerCase();
            if (a.contains(search)||s.getRegion().contains(search) ) {
                
                newVector.add(s);
            }
        }
}**/
}