/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class CartController implements Initializable {

    @FXML
    private Button checkout;
    @FXML
    private Button remove;
    @FXML
    private VBox bookings;
    @FXML
    private Label cartEmptyLabel;
    @FXML
    private Label totalPrice;
    
    @FXML
    private List<Pane> bookingList;
    @FXML
    private List<Label> titleList;
    @FXML
    private List<Label> priceList;
    @FXML
    private List<Label> dateList;
    @FXML
    private List<Label> seatsList;
    @FXML
    private List<CheckBox> boxList;
    
    private boolean removeTour[] = new boolean[10];
    
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Presents the bookings currently in Cart.java
     */
    public void makeBookings() {
        List<BookingInfo> bookings = BookingController.cart.pending;
        //int total = 0;
        
        for(int i = 0; i < bookings.size(); i++) {
            bookingList.get(i).setVisible(true);

            titleList.get(i).setText(bookings.get(i).tour.title);
            //dateList.get(i).setText(Integer.toString(bookings.get(i).day));
            priceList.get(i).setText(Integer.toString(bookings.get(i).tour.priceISK*bookings.get(0).seats));
            seatsList.get(i).setText(Integer.toString(bookings.get(i).seats));
            //total += bookings.get(i).tour.priceISK*bookings.get(i).seats;
        }
        //totalPrice.setText("Total: " + Integer.toString(total) + " ISK");
    }
    
    /*
    * Removes all bookings marked for removal from Cart
    */
    @FXML
    private void removeBooking(ActionEvent event) {
        List<BookingInfo> allBookings = BookingController.cart.pending;
        
        for(int i = 0; i < allBookings.size(); i++) {
            if(removeTour[i] == true) {
                bookings.getChildren().remove(bookingList.get(i));
                BookingController.cart.removeBooking(i);
            }
        } 
    } 
    
    /**
     * Checked tours are marked for removal
     * @param event 
     */
    @FXML
    private void checkRemove(ActionEvent event) {
        for(int i = 0; i < boxList.size(); i++) {
            if(event.getSource() == boxList.get(i)) {
                removeTour[i] = true;
            } 
        }
    }
    
    /**
     * Checkout button does nothing but exit the program
     * @param event 
     */
    @FXML
    private void checkout(ActionEvent event) {
        Platform.exit();
    }
}
