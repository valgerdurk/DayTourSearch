package DayTour;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *  
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class DayTourController implements Initializable {
                 
    private final InfoCache tours = new InfoCache();
    
    @FXML
    private ComboBox<String> regionCombo;
    @FXML
    private ListView<String> list;    
   
    private BookingController bookingController;
    private InfoView viewTours;
    @FXML
    private TextField searchInput;
    private List<TourInfo> currentList;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private ComboBox<String> sortByCombo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize regionCombo box
        regionCombo.getItems().removeAll(regionCombo.getItems());
        regionCombo.getItems().addAll("Westfjords","East","North","Capital area","South","West");
        
        // Initialize typeCombo box
        typeCombo.getItems().removeAll(typeCombo.getItems());
        typeCombo.getItems().addAll("Hiking","Sailing","Glacier","Food","Sights");
        
        //Initialize sortByCombo box
        sortByCombo.getItems().removeAll(sortByCombo.getItems());
        sortByCombo.getItems().addAll("Price","Duration");
        
        // Load initial list of tours
        tours.LoadFromDisk("tourdata.dat");
        currentList = tours.AllTours();   
        showTours(currentList); 
    }
   
    /**
     * Make current list of tours appear
     */
    private void showTours(List<TourInfo> currentList) {
        // Clear previous list
        list.getItems().clear();
        
        // Observable list with the tour information
        ObservableList<String> items = FXCollections.observableArrayList();
        for(int i = 0; i < currentList.size(); i++) {
            items.add(currentList.get(i).title +
                    "\nPrice: " + Integer.toString(currentList.get(i).priceISK) +
                    " ISK    Duration: " + Integer.toString(currentList.get(i).durationHours)
                    + " hours");
        }
        
        list.getItems().addAll(items);
        
        // ImageView for tour images added for every matching cell in the ListView
        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name, boolean empty) {
                for(int i = 0; i < currentList.size(); i++) {
                    super.updateItem(name, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        try {
                            Image image = new Image(new FileInputStream(currentList.get(i).img),150,150,false,false);
                            if(name.equals(items.get(i))) {
                                imageView.setImage(image);
                            }
                            setText(name);
                            setFont(Font.font(20));
                            setGraphic(imageView);
                        } catch (FileNotFoundException e) {
                            System.out.println("Image not found for tour: " + currentList.get(i).title);
                        }
                    }
                }
            }
        });
    }
   
    /**
     * Opens booking dialog for chosen tour
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
     * Handler for the search window 
     * Returns a list of tours that contain the input string
     * in the tour title or as a tag word
     * @param event 
     */
    @FXML
    private void handleSearch(javafx.event.ActionEvent event) {
        viewTours = new InfoView();
        viewTours.init();
       
        List<TourInfo> displayedTours = viewTours.SearchByText(searchInput.getText());
        currentList = displayedTours;
        showTours(currentList);
    }
    
    /**
     * Handler for when a tour is clicked 
     * Opens up a booking dialog for the chosen tour
     * @param event 
     */
    @FXML
    private void chooseTour(MouseEvent event) {
        tourDialog(currentList);
    }
    
    /**
     * Handler for the Region combo box
     * Returns a list of tours in the chosen region
     * @param event 
     */
    @FXML
    private void searchByRegion(javafx.event.ActionEvent event) {
        String region = regionCombo.getValue().toLowerCase();
     
        viewTours = new InfoView();
        viewTours.init();
        List<TourInfo> displayedTours = viewTours.SearchByRegion(Region.Interpret(region));
        
        currentList = displayedTours;
        showTours(currentList);
    }
    
    /**
     * Handler for the Type combo box
     * Returns a list of tours for the chosen type
     * @param event 
     */
    @FXML
    private void searchByType(javafx.event.ActionEvent event) {
        String type = typeCombo.getValue().toUpperCase();
     
        viewTours = new InfoView();
        viewTours.init();
        List<TourInfo> displayedTours = viewTours.SearchByType(TourType.Interpret(type));
        
        currentList = displayedTours;
        showTours(currentList);
    }
    
    /**
     * Handler for the SortBy combo box
     * Returns list of tours sorted by 
     * price or duration depending on what was chosen
     * @param event 
     */
    @FXML
    private void handleSort(javafx.event.ActionEvent event) {
        String sort = sortByCombo.getValue();
        
        viewTours = new InfoView();
        viewTours.init();
        
        if ("Price".equals(sort)) {
            List<TourInfo> displayedTours = viewTours.SortByPrice(currentList);
            currentList = displayedTours;
        } else if("Duration".equals(sort)) {
            List<TourInfo> displayedTours = viewTours.SortByDuration(currentList);
            currentList = displayedTours;
        } 
        showTours(currentList);
    }
}
