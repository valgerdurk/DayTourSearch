/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTourFx;

import DayTour.InfoCache;
import DayTour.TourInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class RegionComboController implements Initializable {
    
    private static final String REGION = "Region";
    
    private DayTourMainController dayTourMainController; // Aðalglugginn
    private MultipleSelectionModel msl;
    @FXML
    private ComboBox<String> regionResults;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
/*
    public void initializeRegionResults(DayTourMainController d) {
        dayTourMainController = d;    
        SingleSelectionModel tsl = regionResults.getSelectionModel();
        tsl.selectedItemProperty().addListener(new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 ObservableList<TourInfo> obl = dayTourMainController.AllTours();
                 ObservableList<TourInfo> selectedRegion;
                 if (newValue == null) {
                    return;
                }
                if (newValue.equals(REGION))  {
                    selectedRegion = obl;
                } else {
                   selectedRegion = selectByRegion(obl, newValue);
                }
                dayTourMainController.parseRegion(selectedRegion);
            }          
        });
        putDayTourMainController();
        regionResults.getSelectionModel().selectFirst();
    }
*/   
    /**
     * Put specific region in the combobox
     */
    /*
    public void setdayTourMainController() {
        regionModel region = new regionModel().select(null);
        regionResults.setItems(region.getItems());
    }
*/
    /**
     * Find specific region and return them as a specific in a selection stick
     * @param event 
     */
    /*
    private void searchHandler(ActionEvent event) {
        String searchString = ((TextField)event.getSource()).getText();
        ObservableList<TourInfo> newList;
        ObservableList<TourInfo> r = dayTourMainController.allurTours();
    }
*/
    /***
     * Select trips that belong to specific region
     * Select from a list
     * @param tour
     * @param trip trips by region
     * @return list of tours by region
     */
    /*
    private ObservableList<TourInfo> selectRegion(ObservableList<TourInfo> tour, String region) {
        ObservableList<TourInfo> newVector = FXCollections.observableArrayList();
        
        for (TourInfo s: tour) {
            if ( title(s.getGroup()).equals(region)) {
                newVector.add(s);
            }
        }
        return newVector;
    }
    */
    /**
     * Returns region as as a string
     * @param regionTitle
     * @return String 
     */
    /*
    private Object title(String regionTitle) {
        return group;
    }
*/
    
}    
    

