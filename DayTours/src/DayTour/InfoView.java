
package DayTour;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ylfa Hafsteinsdóttir, Háskóli Íslands, ylh1@hi.is
 */
public class InfoView {
    private InfoCache tours;
    
    public InfoView() {
        tours = new InfoCache();
    }
    public boolean init() {
        return false;
    }
    /*public List<TourInfo> SearchByType(TourType type) {
        
    }
    public List<TourInfo> SearchByText(String txt) {
        List<TourInfo> text = new ArrayList<TourInfo>(); 
        for (TourInfo t : tours.AllTours()) { 
            if (t.title.matches(txt)) {
                text.add(t);
            }        
        }
        return text;
    }
    public List<TourInfo> SearchByRegion (Region r) {
        List<TourInfo> region = new ArrayList<TourInfo>(); 
        for ()
    }*/
    /*public List<TourInfo> SearchByPrice (int max) {
        
    }
    public List<TourInfo> SearchByPrice (int max) {
        
    }
    public List<TourInfo> SearchByDuration  (int max) {
        
    }
    public List<TourInfo> SearchByInterval  (Date start, Date end) {   
        
    }
    public List<TourInfo> SortByRating  (List<TourInfo> inList) {   
        
    }
    public List<TourInfo> SortByPrice  (List<TourInfo> inList) {   
        
    }
    public List<TourInfo> SortByDuration  (List<TourInfo> inList) {   
        
    }*/   
}

