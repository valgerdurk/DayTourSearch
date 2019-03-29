/*
 * This source code is provided as-is.  No responsibility is taken
 *  for its usage or maintenance.  All queries should be directed
 *  to the author.
 */
package DayTour;

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
    
    public InfoViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of init method, of class InfoView.
     */
    @Test
    public void testInit() {
    }

    /**
     * Test of SearchByType method, of class InfoView.
     */
    @Test
    public void testSearchByType() {
    }

    /**
     * Test of SearchByText method, of class InfoView.
     */
    @Test
    public void testSearchByText() {
        InfoView iv = new InfoView();
        assertEquals(true, iv.init());
        
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
    }

    /**
     * Test of SearchByPrice method, of class InfoView.
     */
    @Test
    public void testSearchByPrice() {
    }

    /**
     * Test of SearchByDuration method, of class InfoView.
     */
    @Test
    public void testSearchByDuration() {
    }

    /**
     * Test of SortByRating method, of class InfoView.
     */
    @Test
    public void testSortByRating() {
    }

    /**
     * Test of SortByPrice method, of class InfoView.
     */
    @Test
    public void testSortByPrice() {
    }

    /**
     * Test of SortByDuration method, of class InfoView.
     */
    @Test
    public void testSortByDuration() {
    }
    
}
