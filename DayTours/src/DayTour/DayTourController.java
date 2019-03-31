package DayTour;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class DayTourController implements Initializable {

    private final int id = 0;                        //fáum núverandi stöðu á dagskrálið
   
    @FXML
    private ComboBox<String> regionCombo;
    @FXML
    private ComboBox<String> priceCombo;
    
    private MultipleSelectionModel msl;                 //módel fyrir val á listann
    private final InfoCache tours = new InfoCache();
    
    @FXML
    private DatePicker datePicker;
    
    private final Label title = new Label();
    private final Label price = new Label(); 
    private final Label duration = new Label();
    
    private final HBox hbox = new HBox();
    private final Pane pane = new Pane();
    private final String img = new String();

    //for regionCombo window
    private static final String REGION = "Region";
    private static final String PRICE = "Price";
    
    @FXML
    private ListView<String> list;    
   
    // Added by Vala
    private BookingController bookingController;
    private InfoView viewTours;
    @FXML
    private TextField searchInput;
    private List<TourInfo> currentList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeRegionCombo();
        initializePriceCombo();
        regionCombo.getItems().removeAll(regionCombo.getItems());
        regionCombo.getItems().addAll("Westfjords","East","North","Capital area","South","West" );
        tours.LoadFromDisk("tourdata.dat");
        
        datePicker.setOnAction(event -> {
        LocalDate date = datePicker.getValue();
        System.out.println("Selected date: " + date);
        });
        
        String price = new String();                                //?????????????
        int indexOfPrice = price.indexOf(id); //?????????????
        priceCombo.getItems().add(price);                          //?????????????
        priceCombo.getItems().removeAll(priceCombo.getItems());
        
        // Bætt við af Völu
        currentList = tours.AllTours();   // Make initial list of tours
        showTours(currentList); 
    }
    
    // Breytt af Völu (áður appearList)
    /**
     * Make current list of tours appear
     */
    private void showTours(List<TourInfo> currentList) {
        // Clear previous list
        list.getItems().clear();
        
        for(int i = 0; i < currentList.size(); i++) {
            String tourInfo = currentList.get(i).title 
                    + " - " + Integer.toString(currentList.get(i).priceISK) + " ISK - " 
                    + Integer.toString(currentList.get(i).durationHours) + " hours"; 
            list.getItems().add(tourInfo);
        }
    }
    
    // Bætt við af völu
    /**
     * Open booking dialog for chosen tour
     */
    private void tourDialog(List<TourInfo> currentList) {
        
        int tourId = list.getSelectionModel().getSelectedIndex();
                
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Booking.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            bookingController = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window" + e.getMessage());
        }
        
        bookingController.showTour(currentList, tourId);
    }
    
    /***
     * Handler to take a look at a specific tour
     * @param event 
     */
    /*@FXML
    private void lookAtTours(ActionEvent event) {
        System.out.println("ID " + id);
        List<TourInfo> allTours = tours.AllTours();
        if (id != -1) {
            dDialogController.dagskraDialogBirta(virkurIndex, allirDagskrarlidir.get(virkurIndex));
            System.out.println(listiDagskra.getItems().get(virkurIndex));
        }
    }
*/
    /***
     * Handler to delete specific tour
     */
    private void deleteList(ActionEvent event) {
        if (id != -1) {
            TourInfo n = tours.AllTours()
                    .get(id);
            tours.AllTours().remove(id);
        }
    }    
    /**
     * Upgrade listView depending on list
     * @param selectedItems 
     */
    void listControle(ObservableList<TourInfo> selectedItems) {
        tours.AllTours().addAll(id, selectedItems);
    }
    
    /**
     * fetch list from InfoCache
     */
    ObservableList<TourInfo> AllTours() {
        ObservableList<TourInfo> listTours = FXCollections.observableArrayList(tours.AllTours());
        return listTours;
    }
    
    private void initializeRegionCombo() {
      DayTourController d = new DayTourController();
      SingleSelectionModel<String> tsl = regionCombo.getSelectionModel();      
      //SingleSelectionModel tsl = regionCombo.getSelectionModel();
      tsl.select(REGION); 
      //tsl.selectedItemProperty().addListener(new ChangeListener<String> () {
       /*     @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 ObservableList<TourInfo> obl = d.AllTours();
                 ObservableList<TourInfo> selectedRegion = null;
                 if (newValue == null) {
                    return;
                }
                if (newValue.equals(REGION))  {
                    selectedRegion = obl;
                } else {
                     Predicate<TourInfo> predicate = null;
                    selectedRegion = AllTours().filtered(predicate);
                }
                d.filterByRegion(selectedRegion);
            }
        });  */  
         
    }
   
    private void filterByRegion(ObservableList<TourInfo> selectedRegion) {
        tours.AllTours().addAll(selectedRegion);            //???????????
    }
    
          
   /* @FXML
    private void handleSearch(javafx.event.ActionEvent event) {
        String search = ((TextField)event.getSource()).getText();
        ObservableList<TourInfo> newList = null;
        ObservableList<TourInfo> r = (ObservableList<TourInfo>) list.getSelectionModel().getSelectedItems();
        if (search.equals(" ")) {
            newList = r;
        } 
        /*else {
            newList = veljaLid(r, leitarstrengur);
        }
        filterByRegion(newList);
    }*/

    /***
     * Chooses by value of price
     * @param pricepick
     * @param priceI
     * @return 
     */
    private ObservableList<TourInfo> searchByPrice(ObservableList<TourInfo> pricepick, int priceI) {
        ObservableList<TourInfo> newVector = FXCollections.observableArrayList();
        
        pricepick.stream().filter((s) -> ( priceP(s.priceISK).equals(priceI))).forEachOrdered((s) -> {
            newVector.add(s);
        });
        return newVector;
    }
    
    /**
     * returns price
     * @param group
     * @return 
     */
    private Object priceP(int group) {
        return group;
    }

    private void initializePriceCombo() {
    DayTourController d = new DayTourController();    
    SingleSelectionModel tsl = priceCombo.getSelectionModel();
        tsl.selectedItemProperty().addListener(new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 ObservableList<TourInfo> obl = d.AllTours();
                 ObservableList<TourInfo> selectedItems = null;
                 if (newValue == null) {
                    return;
                }
                if (newValue.equals(PRICE))  {
                    selectedItems = obl;
                } else {
                     Predicate<TourInfo> predicate = null;
                    selectedItems = AllTours().filtered(predicate);
                }
                d.filterByPrice(selectedItems);
            }          
        });    
         
    }
    

    private void filterByPrice(ObservableList<TourInfo> selected) {
        tours.AllTours().get(id);                                    //???????????
       
    }
    
    // Bætt við af Völu
    @FXML
    private void handleSearch(javafx.event.ActionEvent event) {
        viewTours = new InfoView();
        viewTours.init();
        
        List<TourInfo> displayedTours = viewTours.SearchByText(searchInput.getText());
        currentList = displayedTours;
        showTours(currentList);
    }
    
    // Bætt við af Völu
    @FXML
    private void chooseTour(MouseEvent event) {
        tourDialog(currentList);
    }
}
