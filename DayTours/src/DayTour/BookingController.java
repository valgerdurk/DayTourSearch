/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private Pane bDialog;
    @FXML
    private Font x2;
    @FXML
    private CartController cartController;
    @FXML
    private Font x1;
    @FXML
    private TextField bname;
    @FXML
    private TextField bhotel;
    
    // TO-DO gera snyrtilegra og skjala betur
    
    //private Cart cart = new Cart();
    
    private boolean pickup = false;
    

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
        });
    }
    
    /*
     * Presents the chosen tour from the main page
     */
    public void showTour(List<TourInfo> currentList, int id) {
        title.setText(currentList.get(id).title);
        price.setText(Integer.toString(currentList.get(id).priceISK));
        duration.setText(Integer.toString(currentList.get(id).durationHours));
        about.setText(currentList.get(id).description);
        
        try {
            Image bimage = new Image(new FileInputStream(currentList.get(id).img));
            image.setImage(bimage);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found for tour: " + currentList.get(id).title);
        }
    }
    
    /*
     * A dialog for name and hotel information
     * Adds booking to cart 
     */
    private void bookingDialog () {
        DialogPane p = new DialogPane();
        bDialog.setVisible(true);
        
        p.setContent(bDialog);
        
        Dialog d = new Dialog();
        
        d.setDialogPane(p);
        
        // Add to cart button made
        ButtonType addToCart = new ButtonType("Add to cart", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(addToCart);
        
        final Node cart = p.lookupButton(addToCart);
        cart.disableProperty()
                .bind(bname.textProperty().isEmpty());
        
        Optional<ButtonType> result = d.showAndWait();
        if (result.isPresent() && (result.get()
                .getButtonData() == ButtonBar.ButtonData.OK_DONE)) {
        } else {
            // villumelding
        }
        addToCart();
    }
    
    
    /*
    * Handler for the choose travelers ComboBox
    * Updates total price according to chosen quantity
    */
    @FXML
    private void chooseSeats(ActionEvent event) {
        int travelers = getSeatQt();
        int total = Integer.parseInt(price.getText())*travelers;
        updatedPrice.setText(Integer.toString(total));
    }
     
    /**
     * Extracts the number of travelers chosen
     * @return number of travelers
     */
    private int getSeatQt() {
        int travelers = Integer.valueOf((String)nTravelers.getValue());
        return travelers;
    }
        
    /**
     * Handler for the "Book now" button
     * Opens the booking dialog if tour is available on chosen date
     * @param event 
     */
    @FXML
    private void handleBook() {
       bookingDialog();
       
       // TO-DO villumeldingar
    } 
    
    /**
     * Handler for hotel pickup check-box
     * If checked pickup is set as true
     * @param event 
     */
     @FXML
    private void hotelPickup(MouseEvent event) {
        pickup = true;
    }
    
    /**
     * 
     * @param date
     * @return 
     */
    private int parseDate(LocalDate date) {
        return 0;
    }
    
    /**
     * TO-DO make booking in Cart.java
     */
    private void addToCart() {
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
         
        int date = parseDate(datePick.getValue());
        int travelers = getSeatQt();
        
        cartController.makeBooking(title.getText(), datePick.getValue(), updatedPrice.getText(), nTravelers.getValue());
    }
}
