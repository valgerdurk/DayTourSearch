package DayTour;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * FXML Controller class
 *
 * @author Guðrún Stella Ágústsdóttir Háskóli Íslands gsa15@hi.is
 */
public class DayTourController implements Initializable {

    @FXML
    private ListView<TourInfo> listTour;        //listi yfir fótboltadagskrá
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     //   tours.LoadFromDisk("tourinfo.dat") = new DagskraKatalogur();         //hlutur til að geta náð í gögnin    
        initializeRegionCombo();      
        //appearList(tours.AllTours().get(id));       
        //TourInfo e = null;
        datePicker.setOnAction(event -> {
        LocalDate date = datePicker.getValue();
        System.out.println("Selected date: " + date);
        });
        String region = new String();                                //?????????????
        region.indexOf("tourdata.dat");                              //?????????????
        regionCombo.getItems().add(region);                          //?????????????
        regionCombo.getItems().removeAll(regionCombo.getItems());
        tours.AllTours();            //get all list from InfoCache     ????????????????
        initializeVariables();       //initialize variables
}
    /**
     * make data appear when we choose a tour
     * @param r one tour with many elements 
     */
    private void appearList(TourInfo g) {
        System.out.println("All Tours:");
        System.out.println("Title " + g.ID);        
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
     * Handler to dlete specific tour
     */
    @FXML
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
     * initialize data and actionEvent for list of tours 
     */
    private void initializeVariables() {
        //TourInfo ti = tours.TourFromID(id); //To change body of generated methods, choose Tools | Templates.
       // ti.AddActivity(TourType.SIGHTS);
 //       id = ti.ID;
        tours.LoadFromDisk("tourdata.dat");
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
    SingleSelectionModel tsl = regionCombo.getSelectionModel();
        tsl.selectedItemProperty().addListener(new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 ObservableList<TourInfo> obl = d.AllTours();
                 ObservableList<TourInfo> selectedItems = null;
                 if (newValue == null) {
                    return;
                }
                if (newValue.equals(REGION))  {
                    selectedItems = obl;
                } else {
                     Predicate<TourInfo> predicate = null;
                    selectedItems = AllTours().filtered(predicate);
                }
                d.filterByRegion(selectedItems);
            }          
        });    
        
//        setDayTourController();
        //regionCombo.getSelectionModel().selectFirst();
    }

    private void filterByRegion(ObservableList<TourInfo> selected) {
        tours.AllTours().get(id);                                    //???????????
    }

    /**
     * puts specific regions in the combobox
     */
    private void setDayTourController() {
        regionModel regions = new regionModel((ObservableList<TourInfo>) tours.AllTours());
        regionCombo.getSelectionModel().select(null);
        regionCombo.setItems(regions.getItems());
    }
    }

            /***
     * Velur fótboltadagskrárliði sem tilheyra ákveðnum riðli
     * Valið úr heildarlista 
     * @param dagskra
     * @param lid riðlar
     * @return listi af fótboltadagskrárliðum
     */    
    /*private ObservableList<TourInfo> chooseRegion(String s) {
        ObservableList<TourInfo> newVector = FXCollections.observableArrayList();
        Iterable<TourInfo> list = null;
        for (TourInfo s: list) {
            if(s.equals(list) {
                newVector.add(s);
            } else {
            }
        }
        return newVector;
    }
}*/
    
    /**
     * Skilar út riðli sem streng
     * @param group
     * @return String 
     */
  /*  private Object Nofn(String group) {
        return group;
        }
    }
}
    /***
     * Handler fyrir virkni á Horfa í beinni hjá RÚV
     * @param event 
     */
   // @FXML
   /* private void horfaiBeinniHandler(ActionEvent event) {
        try {
            Desktop.getDesktop().browse(new URI("http://www.ruv.is/ithrottavidburdir-i-beinni-utsendingu"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }  
    }
    */
//action fyrir region combo                                                              //????????????????????????
    /**
     * Finnur flokka landanna og skilar þeim sem streng í valstiku
     * @param event 
     */
    /*@FXML
    private void searchHandler(javafx.event.ActionEvent event) {
        String leitarstrengur = ((TextField)event.getSource()).getText();
        ObservableList<Root.Results> nyrListi;
        ObservableList<Root.Results> r = dController.allurListi();
    }
*/
/*
    /**
     * Initializes the controller class.
     */
    /*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        final SwingNode swingnode = new SwingNode();
        createAndSetSwing(swingnode);
        
        datePicker.setOnAction(event -> {
        LocalDate date = datePicker.getValue();
        System.out.println("Selected date: " + date);
        });
        //jPanel panel = new
        //textLabel
        //radiobutton
        //generata      
        initTest(); 
    }  
    /*
    public void initTest() {
        tours.LoadFromDisk("tourdata.dat");
        list.getCellFactory();
        System.out.println(tours.AllTours().get(0).title);
        title.setText(tours.AllTours().get(0).title);
        price.setText(Integer.toString(tours.AllTours().get(0).priceISK));
        duration.setText(Integer.toString(tours.AllTours().get(0).durationHours));
        hbox.getChildren().addAll(title,price,pane);
        HBox.setHgrow(pane,Priority.ALWAYS);
        Boolean empty = true;
        if(list != null && !empty) {
            list.setVisible(true);
  //          setGraphic(hbox);
       
        }
        gridpane.add(list,    0, 0);
    }
    private void createAndSetSwing(SwingNode swingnode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel panel = new JPanel();
                panel.add(img, panel);
                swingnode.setContent(panel);
                regionCombo.buttonCellProperty().addListener(listener);
            }
    });
        
    }
}*/