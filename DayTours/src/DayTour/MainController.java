/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class MainController implements Initializable {
    
    private BookingController bookingController;
    
    private InfoView tours;
  
    @FXML
    private TextField searchInput;
    @FXML
    private AnchorPane rootPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Simply a test method for opening the booking window
     * @param event 
     */
    @FXML
    private void handleTest(ActionEvent event) {
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
        
        tours = new InfoView();
        tours.init();
        
        List<TourInfo> displayedTours = tours.SearchByText(searchInput.getText());
        
        String title = displayedTours.get(0).title;
        int price = displayedTours.get(0).priceISK;
        int duration = displayedTours.get(0).durationHours;
        String about = displayedTours.get(0).description;
        
        bookingController.showTour(title, price, duration, about);
    }
     
}
