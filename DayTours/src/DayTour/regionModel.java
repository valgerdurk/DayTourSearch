/*
 *Guðrún Stella Ágústsdóttir, Háskóli Íslands
 */
package DayTour;

import DayTour.TourInfo;
import java.util.LinkedHashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A class that includes all tours by specyfic region that the user selects
 * Region is a getter from a database from InfoCache/TourInfo?
 * 
 * @author Guðrún Stella Ágústsdóttir, Háskóli Íslands, gsa15@hi.is
 */
public class regionModel {
private ObservableList<TourInfo> regionItems;


    /**
     * Constructor for regionModel. Gets all tours
     * Filters out set of region
     * @param regionTours
     */
 public regionModel(ObservableList<TourInfo> regionTours) {
        
        /*
        LinkedHashSet region = new LinkedHashSet();
        region.add("Region");
        for (TourInfo r: regionTours) {    
                String u = r.getRegion();
                region.add(u);
        }  
   
        regionItems = FXCollections.observableArrayList();
        for (Object l: region)
            regionItems.add(l.toString());
   */    
    }

 
    /*
     *Methods getter and setter to fetch tours by region
     *and appear it
    */
 
    public ObservableList<TourInfo> getItems() {
       return regionItems;
    }
    
    public void setItems(ObservableList<TourInfo> o) {
       regionItems = o;
    }

    
}
