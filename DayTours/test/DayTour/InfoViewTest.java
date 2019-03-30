/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Francis <dff1@hi.is>
 */
public class InfoViewTest {
    
    private final InfoView iv;
    
    public InfoViewTest() {
        iv = new InfoView();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // The InfoView object is initialized here -- this will
        //  erase any previously held data in its InfoCache, and
        //  reload the cache from disk.
        // This is done to ensure that side-effects of any given
        //  method of InfoView are not propogated between tests.
        iv.init();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of SearchByType method, of class InfoView.
     */
    @Test
    public void testSearchByType() {
        // Testing negative result
        List<TourInfo> negResult = iv.SearchByType(TourType.UNDEFINED);
        
        assertEquals(0, negResult.size());
        
        // Testing of tour type: food
        List<TourInfo> foodResult = iv.SearchByType(TourType.FOOD);
        
        // Ensure size of result set is correct.
        assertEquals(6, foodResult.size());
        
        // Ensure that each result has the type, FOOD.
        for (TourInfo t : foodResult) {
            assertTrue(null, t.IsType(TourType.FOOD));
        }
        
        // Ensure that the results agree with the ORACLE:
        assertEquals("Kaldi Beer Spa", foodResult.get(0).title);
        assertEquals("Icelandic Beer Tasting and River Rafting", foodResult.get(1).title);
        assertEquals("Reykjavík Food Walk", foodResult.get(2).title);
        assertEquals("Fisherman's Food Trail", foodResult.get(3).title);
        assertEquals("The Story of Skyr", foodResult.get(4).title);
        assertEquals("Reykjavík Bar Crawl", foodResult.get(5).title);
        
        // Ensure that there are no tours that have the FOOD type,
        //  not represented in foodResult.
        for (TourInfo t0 : iv.SearchByText(".*")) {
            if (t0.IsType(TourType.FOOD)) {
                assertTrue(null, foodResult.contains(t0));
            }
            else {
                assertFalse(null, foodResult.contains(t0));
            }
        }
    }

    /**
     * Test of SearchByText method, of class InfoView.
     */
    @Test
    public void testSearchByText() {
        // Test the negative result.
        List<TourInfo> negResult = iv.SearchByText("Bork");
        assertEquals(0, negResult.size());
        
        // Test tag matching.
        List<TourInfo> tagResult = iv.SearchByText("glacier");
        assertEquals(7, tagResult.size());
        assertEquals("Þórsmörk Volcano Hike", tagResult.get(0).title);
        assertEquals("Amphibian Boat Tour on Jökulsárlón", tagResult.get(1).title);
        assertEquals("Sólheimajökull Glacial Experience", tagResult.get(2).title);
        assertEquals("Tröllakrókar Canyon Tour", tagResult.get(3).title);
        assertEquals("Langjökull Glacial Wonderland", tagResult.get(4).title);
        assertEquals("Snæfellsjökull Glacial Summit Hike", tagResult.get(5).title);
        assertEquals("Glacier Snowmoiling from Dalvík", tagResult.get(6).title);
        
        // Test title matching.
        List<TourInfo> titleResult = iv.SearchByText("Lake Mývatn Classic Tour");
        assertEquals(1, titleResult.size());
        assertEquals("Lake Mývatn Classic Tour", titleResult.get(0).title);
        
        // Test case-independent partial title matching.
        List<TourInfo> fragResult = iv.SearchByText("oN");
        assertEquals(5, fragResult.size());
        assertEquals("Amphibian Boat Tour on Jökulsárlón", fragResult.get(0).title);
        assertEquals("Tröllakrókar Canyon Tour", fragResult.get(1).title);
        assertEquals("Langjökull Glacial Wonderland", fragResult.get(2).title);
        assertEquals("Reykjavík Hop On Hop Off Bus Tour", fragResult.get(3).title);
        assertEquals("Golden Circle and Secret Lagoon", fragResult.get(4).title);
    }

    /**
     * Test of SearchByRegion method, of class InfoView.
     */
    @Test
    public void testSearchByRegion() {
        //Test negative result
        List<TourInfo> negResult = iv.SearchByRegion(Region.UNDEFINED);
        assertEquals(0, negResult.size());
        
        //Test Region enum matches
        List<TourInfo> regionResult = iv.SearchByRegion(Region.WESTCOAST);
        assertEquals(6, regionResult.size());
        
        // Verify against ORACLE:
        assertEquals("Glymur Waterfall Hike", regionResult.get(0).title);
        assertEquals("Kayaking Under Mt. Kirkjufell", regionResult.get(1).title);
        assertEquals("Ferry Trip tp Grímsey", regionResult.get(2).title);
        assertEquals("Langjökull Glacial Wonderland", regionResult.get(3).title);
        assertEquals("Snæfellsjökull Glacial Summit Hike", regionResult.get(4).title);
        assertEquals("The Story of Skyr", regionResult.get(5).title);
        
        // Ensure that no tours with the region WESTCOAST are not
        //  represented in regionResult.
        for (TourInfo t0 : iv.SearchByText(".*")) {
            if (t0.region == Region.WESTCOAST) {
                assertTrue(null, regionResult.contains(t0));
            }
            else {
                assertFalse(null, regionResult.contains(t0));
            }
        }
    }

    /**
     * Test of SearchByPrice method, of class InfoView.
     */
    @Test
    public void testSearchByPrice() {
        //Test negative result
        List<TourInfo> negResult = iv.SearchByPrice(-1);
        assertEquals(0, negResult.size());
        
        // ???
        /*
        List<TourInfo> priceResult = iv.SearchByPrice(2);
        assertEquals(8, priceResult.size());
        assertEquals("Tröllakrókar Canyon Tour", priceResult.get(0).title);
        assertEquals("Glacier Snowmoiling from Dalvík", priceResult.get(1).title);
        assertEquals("The Highland Circle", priceResult.get(2).title);
        assertEquals("Fox Watching in Hornstrandir", priceResult.get(3).title);
        assertEquals("Hornstrandir Hiking Tour", priceResult.get(4).title);
        assertEquals("Þórsmörk Volcano Hike", priceResult.get(5).title);
        assertEquals("The Story of Skyr", priceResult.get(6).title);
        assertEquals("Langjökull Glacial Wonderland", priceResult.get(4).title);    
        */
        
        List<TourInfo> priceResult = iv.SearchByPrice(5000);
        
        // Ensure the size of the result set is correct.
        assertEquals(5, priceResult.size());
        
        // Check against the ORACLE:
        assertEquals("Amphibian Boat Tour on Jökulsárlón", priceResult.get(0).title);
        assertEquals("Ferry Trip tp Grímsey", priceResult.get(1).title);
        assertEquals("Fisherman's Food Trail", priceResult.get(2).title);
        assertEquals("Reykjavík Hop On Hop Off Bus Tour", priceResult.get(3).title);
        assertEquals("Puffins of Borgarfjordur Eystri – Guided visit to Hafnarhólmi", priceResult.get(4).title);
        
        // Ensure deterministically that the result set is
        //  correct.
        for (TourInfo t0 : iv.SearchByText(".*")) {
            if (t0.priceISK <= 5000) {
                assertTrue(null, priceResult.contains(t0));
            }
            else {
                assertFalse(null, priceResult.contains(t0));
            }
        }
    }

    /**
     * Test of SearchByDuration method, of class InfoView.
     */
    @Test
    public void testSearchByDuration() {
        //Test negative result
        List<TourInfo> negResult = iv.SearchByDuration(-1);
        assertEquals(0, negResult.size());
        
        //Test duration matching
        List<TourInfo> durationResult = iv.SearchByDuration(2);
        assertEquals(5, durationResult.size());
        
        // Compare to ORACLE:
        assertEquals("Amphibian Boat Tour on Jökulsárlón", durationResult.get(0).title);
        assertEquals("Kayaking Under Mt. Kirkjufell", durationResult.get(1).title);
        assertEquals("Westman Island Rib Boat Tour", durationResult.get(2).title);
        assertEquals("Kaldi Beer Spa", durationResult.get(3).title);
        assertEquals("Fisherman's Food Trail", durationResult.get(4).title);
        
        // Verify deterministically that the result set agrees
        //  with our expectations.
        for (TourInfo t0 : iv.SearchByText(".*")) {
            if (t0.durationHours <= 2) {
                assertTrue(null, durationResult.contains(t0));
            }
            else {
                assertFalse(null, durationResult.contains(t0));
            }
        }
    }

    /**
     * Test of SortByRating method, of class InfoView.
     */
    @Test
    public void testSortByRating() {
        List<TourInfo> all = iv.SearchByText(".*");
        assertEquals(30, all.size());
        
        // Sort the list, ensure the size is retained.
        List<TourInfo> sorted = iv.SortByRating(all);
        assertEquals(30, sorted.size());
        
        // Ensure that no entries have been lost or changed.
        for (TourInfo t : sorted) {
            assertTrue(null, all.contains(t));
        }
        
        // Ensure that the tours have been sorted by rating
        //  in ascending order.
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(null, sorted.get(i).rating <= sorted.get(i+1).rating);
        }
    }

    /**
     * Test of SortByPrice method, of class InfoView.
     */
    @Test
    public void testSortByPrice() {
        List<TourInfo> all = iv.SearchByText(".*");
        assertEquals(30, all.size());
        
        // Run the sort, ensure the size is retained.
        List<TourInfo> sorted = iv.SortByPrice(all);
        assertEquals(30, sorted.size());
        
        // Ensure that no entries have been lost or changed.
        for (TourInfo t : sorted) {
            assertTrue(null, all.contains(t));
        }
        
        // Ensure that the tours have been sorted by price
        //  in ascending order.
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(null, sorted.get(i).priceISK <= sorted.get(i+1).priceISK);
        }
    }

    /**
     * Test of SortByDuration method, of class InfoView.
     */
    @Test
    public void testSortByDuration() {
        List<TourInfo> all = iv.SearchByText(".*");
        assertEquals(30, all.size());
        
        // Run the sort, ensure the size is retained.
        List<TourInfo> sorted = iv.SortByDuration(all);
        assertEquals(30, sorted.size());
        
        // Ensure that no entries have been lost or changed.
        for (TourInfo t : sorted) {
            assertTrue(null, all.contains(t));
        }
        
        // Ensure that the tours have been sorted by duration
        //  in ascending order.
        for (int i = 0; i < sorted.size() - 1; i++) {
            assertTrue(null, sorted.get(i).durationHours <= sorted.get(i+1).durationHours);
        }
    }
    
}
