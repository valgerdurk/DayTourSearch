/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DayTour;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class DayTourMainController implements Initializable {

    @FXML
    private AnchorPane jAnchor;
    private InfoCache info;
    
    @FXML
    private ListView<TourInfo> list;
    private int index = 0;
    ObservableList<TourInfo> listv = FXCollections.observableArrayList();

    Image img = new Image(" ");
    @FXML
    private DatePicker dagurAfmarka;
    @FXML
    private ComboBox<TourInfo> verdAfmarka;
    @FXML
    private ComboBox<TourInfo> region;
    
    static class Cell extends ListCell<TourInfo> {
        HBox hbox = new HBox();
        Label title = new Label("");
        Image img = new Image(" ");
        
        ImageView pic = new ImageView(img);
        GridPane pane = new GridPane();
        private String TourInfo;
        
    public Cell() {
        super();
        hbox.getChildren().addAll(pic,title,pane);
        hbox.setHgrow(pane, Priority.ALWAYS);
    }
    public void updateItem(ListView<TourInfo> list, boolean empty) {
        super.updateListView(list);
        setText(null);
        setGraphic(null);
        if(list != null && !empty) {
            list.setVisible(true);
            setGraphic(hbox);
        }
    }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.setItems(listv);
        GridPane pane = new GridPane();
        Label title = new Label("h");
        pane.add(list,    0, 0);
        
        list.setCellFactory(param -> new ListCell<TourInfo>(){
            ImageView pic = new ImageView();
            public void updateItem(ListView<TourInfo> list, boolean empty) {
                super.updateListView(list);
                if(title != null) {
                    list.setItems(listv);
                    pic.setImage(img);
                }
            }
        });
    }

    
    
/*           
    private static final String REGION = "Region";
    
        void chooseRegion(ObservableList<TourInfo> chosenReligion) {
        list.setItems(chosenReligion);
    }   
*/
           



 }
    

