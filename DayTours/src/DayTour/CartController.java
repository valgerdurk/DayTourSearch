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
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Valgerdur Kristinsdottir <vak9@hi.is>
 */
public class CartController implements Initializable {

    @FXML
    private AnchorPane cart;
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
    private CheckBox checkRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * 
     * @param btitle 
     * @param bdate 
     * @param bprice 
     * @param btravelers 
     */
    public void makeBooking(String btitle,LocalDate bdate,String bprice,Object btravelers) {
        title.setText(btitle);
        date.setText(bdate.toString());
        price.setText(bprice);
        noTravelers.setText(btravelers.toString());
    }

    @FXML
    private void removeBooking(ActionEvent event) {
    }
    
}
