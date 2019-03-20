/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class for tour information page
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class BookingController implements Initializable {
    
    @FXML
    private AnchorPane booking;
    @FXML
    private ImageView image;
    @FXML
    private Button bookNowButton;
    @FXML
    private Font x1;
    @FXML
    private Label price;
    @FXML
    private Label duration;
    @FXML
    private Label title;
    @FXML
    private Label about;
    @FXML
    private ComboBox<String> nTravelers;
    @FXML
    private DatePicker datePick;
    @FXML
    private Label updatedPrice;
    @FXML
    private CheckBox hotelPickup;
    @FXML
    private CartController cartController;
    
    private InfoCache tours = new InfoCache();

    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nTravelers.getItems().removeAll(nTravelers.getItems());
        nTravelers.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        DatePicker datePicker = new DatePicker();
        
        datePicker.setOnAction(event -> {
        LocalDate date = datePicker.getValue();
        System.out.println("Selected date: " + date);
        });
        
        initTest();
        
        // Modification via dff
        // cartController = new CartController();
    }
    
    public void initTest() {
        // Test tour for booking system
        
        tours.LoadFromDisk("tourdata.dat");
    
        System.out.println(tours.AllTours().get(0).title);
     
        title.setText(tours.AllTours().get(0).title);
        price.setText(Integer.toString(tours.AllTours().get(0).priceISK));
        duration.setText(Integer.toString(tours.AllTours().get(0).durationHours));
        about.setText(tours.AllTours().get(0).description);
        
        //Image img = new Image(tours.AllTours().get(0).img);
        //image.setImage(img); 
    }
    

    @FXML
    private void getSeatQt(ActionEvent event) {
        int travelers = Integer.valueOf((String)nTravelers.getValue());
        int total = Integer.parseInt(price.getText())*travelers;
        updatedPrice.setText(Integer.toString(total));
        System.out.println(updatedPrice);
    }    
        
    /**
     * Adds booking to cart
     * Booking is made with current input information
     * @param event 
     */
    @FXML
    private void handleBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cart.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            cartController = fxmlLoader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window" + e.getMessage());
        }
        
        cartController.makeBooking(title.getText(), datePick.getValue(), updatedPrice.getText(), nTravelers.getValue());
    } 

    @FXML
    private void hotelPickup(MouseEvent event) {
    }
    
}
