
package DayTour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        return tours.LoadFromDisk("tourdata.dat");
    }
    /**
     * Aðferð sem leitar að ferðum eftir tegund ferðar
     * @param type heitir á tegund ferðar 
     * @return list af ferðum 
     */
    public List<TourInfo> SearchByType(TourType type) {
        List<TourInfo> typeList = new ArrayList<TourInfo>();
        for (TourInfo t : tours.AllTours()) {
            if (t.IsType(type)) {
                typeList.add(t);
            }
        }
        return typeList;
    }
    public List<TourInfo> SearchByText(String txt) {
       List<TourInfo> txtList = new ArrayList<TourInfo>();

       String regex = ".*" + txt.toLowerCase() + ".*";

       for (TourInfo t : tours.AllTours()) {
           if (t.title.toLowerCase().matches(regex)) {
               txtList.add(t);
           }
           else if (t.HasTag(txt)) {
               txtList.add(t);
           }
       }
       return txtList;
    }
    
    
    
    public List<TourInfo> SearchByRegion (Region r) {
        List<TourInfo> rList = new ArrayList<TourInfo>(); 
        for (TourInfo t : tours.AllTours()) {
            if (t.region == r) {
                rList.add(t);
            }
        }
        return rList;
    }
    public List<TourInfo> SearchByPrice (int max) {
        List<TourInfo> pList = new ArrayList<TourInfo>(); 
        for (TourInfo t : tours.AllTours()) {
            if (t.priceISK <= max) {
                pList.add(t);
            }
        }
        return pList;
    }
    
    public List<TourInfo> SearchByDuration  (int max) {
        List<TourInfo> dList = new ArrayList<TourInfo>(); 
        for (TourInfo t : tours.AllTours()) {
            if (t.durationHours <= max) {
                dList.add(t);
            }
        }
        return dList;
    }
    /*public List<TourInfo> SearchByInterval  (Date start, Date end) {   
        List<TourInfo> itvList = new ArrayList<TourInfo>(); 
        for (TourInfo t : tours.AllTours()) {
            //TODO
        }
        return itvList;
    }*/
    
    public List<TourInfo> SortByRating  (List<TourInfo> inList) {
        List<TourInfo> rateList = new ArrayList<TourInfo>(); 
        rateList.addAll(inList);
        Collections.sort(rateList, new Comparator<TourInfo>() {
            @Override
            public int compare(TourInfo t1, TourInfo t2) {
                if (t1.rating > t2.rating)
                    return 1;
                if (t1.rating < t2.rating)
                    return -1;
                return 0;
            }
        });
        return rateList;
    }
    public List<TourInfo> SortByPrice  (List<TourInfo> inList) {   
       List<TourInfo> priceList = new ArrayList<TourInfo>(); 
        priceList.addAll(inList);
        Collections.sort(priceList, new Comparator<TourInfo>() {
            @Override
            public int compare(TourInfo t1, TourInfo t2) {
                if (t1.priceISK > t2.priceISK)
                    return 1;
                if (t1.priceISK < t2.priceISK)
                    return -1;
                return 0;
            }
        });
        return priceList; 
    }
    public List<TourInfo> SortByDuration  (List<TourInfo> inList) {   
        List<TourInfo> durationList = new ArrayList<TourInfo>(); 
        durationList.addAll(inList);
        Collections.sort(durationList, new Comparator<TourInfo>() {
            @Override
            public int compare(TourInfo t1, TourInfo t2) {
                if (t1.durationHours > t2.durationHours)
                    return 1;
                if (t1.durationHours < t2.durationHours)
                    return -1;
                return 0;
            }
        });
        return durationList; 
    }
} 

