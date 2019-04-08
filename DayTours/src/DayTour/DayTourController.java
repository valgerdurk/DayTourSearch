package DayTour;

import static DayTour.Region.Interpret;
import static DayTour.TourType.Interpret;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class DayTourController implements Initializable {

    private final int id = 0; //id for tours
   
    @FXML
    private ComboBox<Region> regionCombo;
    @FXML
    private ComboBox<String> priceCombo;
    
    //private MultipleSelectionModel msl;    //model for choise of list
    private final InfoCache tours = new InfoCache();
    
    @FXML
    private DatePicker datePicker;
    private final Label title = new Label();
    private final Label price = new Label(); 
    private final Label duration = new Label();
    
    //private final HBox hbox = new HBox(); this is something that can be done if we want to get cellFactory and make a list appear with images       
    //private final Pane pane = new Pane(); this would be used with the hbox above for same reason I think
    //private final String img = new String(); this also, but it would make the work harder

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
        
        tours.LoadFromDisk("tourdata.dat");
        regionCombo.getItems().removeAll(regionCombo.getItems());
        regionCombo.getItems().addAll(
                    Region.CAPITAL, 
                    Region.EAST, 
                    Region.HIGHLANDS, 
                    Region.NORTH, 
                    Region.SOUTH, 
                    Region.WESTCOAST, 
                    Region.WESTFJORDS, 
                    Region.UNDEFINED
        );
        
        /*regionCombo.setCellFactory(new Callback<ListView<Region>, Listcell<Region>() {
            @Override public ListCell<Region> call(ListView<Region> r) {
                final ListCall<Region> cell = new ListCell<Region>() {
                    {
                        super.getListView();
                    }
                    @Override public void updateItem(Region reg, boolean empty) {
                        super.updateItem(reg, empty);
                        if(reg != 0) {
                            setText(reg);
                        } else {
                            setText(null);
                        }
                    };
                    return cell;        
                }
        });*/
        
        
        datePicker.setOnAction(event -> {
     
        LocalDate date = datePicker.getValue();
        System.out.println("Selected date: " + date);
        });
        
        
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
                    + Integer.toString(currentList.get(i).durationHours) + " hours "
                    + currentList.get(i).region; 
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
        
    /**
     * fetch list from InfoCache
     */
    ObservableList<TourInfo> AllTours() {
        ObservableList<TourInfo> listTours = FXCollections.observableArrayList(tours.AllTours());
        return listTours;
    }
    
    /**
     * Upgrade listView depending on list
     * @param selectedItems 
     */
    void listControle(ObservableList<TourInfo> selectedItems) {
        tours.AllTours().addAll(selectedItems);//currentList
    }
   
    private void filterByRegion(javafx.event.ActionEvent event) {
        viewTours = new InfoView();
        viewTours.init();
        
        List<TourInfo> displayedRegion = viewTours.SearchByRegion(regionCombo.getValue());
        currentList = displayedRegion;
        
        showTours(currentList);
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
