/*
 *Guðrún Stella Ágústsdóttir, Háskóli Íslands
 */
package DayTour;

import java.util.LinkedHashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Klasi sem heldur utan um keppnisdagsrká riðlanna sem notandi getur valið úr
 * Riðlarnir eru fengnir úr gögnum um fótboltadagskrá
 * 
 * @author Guðrún Stella Ágústsdóttir, Háskóli Íslands, gsa15@hi.is
 */
public class regionModel {
private ObservableList<String> regionItems;


    /**
     * Smiður fyrir flokkurModel. Tekur inn alla keppni
     * Síar út mengi af riðlum
     * @param dLeikir
     */
 public regionModel(ObservableList<TourInfo> tRegion) {
        
        // Náum í dagskrá
        LinkedHashSet tour = new LinkedHashSet();
        tour.add("Region");
        for (TourInfo r:tRegion) {    
                Region u = r.region;
                tour.add(u);
        }  
   
        regionItems = FXCollections.observableArrayList();
        // Setjum dagskrár í módelið 
        for (Object l:tour)
            regionItems.add(l.toString());
       
    }

 
    /*
     *Aðferðir getter og setter til að sækja dagskráliði keppninnar
     *og birta þá
    */
 
    public ObservableList<String> getItems() {
       return regionItems;
    }
    
    public void setItems(ObservableList<String> o) {
       regionItems = o;
    }
}
