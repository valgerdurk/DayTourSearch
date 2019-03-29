/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class CartController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Font x1;
    @FXML
    private Label date;
    @FXML
    private Label price;
    @FXML
    private Label noTravelers;
    @FXML
    private Button checkout;
    @FXML
    private Button remove;
    @FXML
    private AnchorPane booking;
    @FXML
    private VBox bookings;
    @FXML
    private Label cartEmptyLabel;
    
    
    private boolean removeTour = false;
  
    //private Cart cart;

    // TO-DO gera snyrtilegra og skjala betur
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Presents booking in cart
     * TO-DO implement for more than one booking
     */
    public void makeBooking(String btitle,LocalDate bdate,String bprice,Object btravelers) {
        booking.setVisible(true);
        
        title.setText(btitle);
        date.setText(bdate.toString());
        price.setText(bprice);
        noTravelers.setText(btravelers.toString());
    }
    
    /*
    * Removes all bookings marked for removal
    * TO-DO implement for more than one booking
    */
    @FXML
    private void removeBooking(ActionEvent event) {
        if (removeTour) {
            bookings.getChildren().remove(booking);
            //cart.removeBooking(0);
            cartEmptyLabel.setVisible(true);
        }
    } 

    @FXML
    private void checkRemove(ActionEvent event) {
        removeTour = true;
    }
}
