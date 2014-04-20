/**
 * 
 */
package pt.iul.dcti.poo.financemanager.date.unittests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.date.Month;

/**
 * @author lmmn
 * 
 */
public class DateTest {

    private static Date d0;
    private static Date d1;
    private static Date d2;
    private static Date d3;
    @SuppressWarnings("unused")
    private Date d4;
    @SuppressWarnings("unused")
    private Date d5;
    @SuppressWarnings("unused")
    private Date d6;
    @SuppressWarnings("unused")
    private Date d7;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        d0 = new Date(1, Month.DECEMBER, 2013);
        d1 = new Date(1, Month.JANUARY, 2014);
        d2 = new Date(2, Date.intToMonth(2), 2014);
        d3 = new Date(1, Month.FEBRUARY, 2015);
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        d4 = new Date(1, Month.DECEMBER, 2013);
        d5 = new Date(1, Month.JANUARY, 2014);
        d6 = new Date(2, Month.JANUARY, 2014);
        d7 = new Date(1, Date.intToMonth(2), 2015);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link pt.iul.dcti.poo.financemanager.date.Date#Date()}.
     */
    @Test
    public void testDate() {
        java.util.Date date = new java.util.Date();
        Date d = new Date();
        assertEquals(d.toDate(), date);
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#Date(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testDateDate() {
        java.util.Date date = new java.util.Date();
        Date d = new Date(date);
        assertEquals(d.toDate(), date);
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#Date(java.util.Date)}.
     */
    @Test
    public void testDateDate1() {
        java.util.Date date = new java.util.Date();
        Date d = new Date(date);
        Calendar c = Calendar.getInstance();
        assertEquals(d.getDay(), c.get(Calendar.DAY_OF_MONTH));
        assertEquals(d.getMonth(), Date.intToMonth(c.get(Calendar.MONTH) + 1));
        assertEquals(d.getYear(), c.get(Calendar.YEAR));
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#Date(int, int, int)}.
     */
    @Test
    public void testDateIntIntInt() {
        Date d = new Date(1, Date.intToMonth(2), 2014);
        assertEquals(d.getDay(), 1);
        assertEquals(d.getMonth(), Date.intToMonth(2));
        assertEquals(d.getYear(), 2014);
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#lastDayOf(int, int)}.
     */
    @Test
    public void testLastDayOf() {
        assertEquals(Date.lastDayOf(Date.intToMonth(1), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(2), 2014), 28);
        assertEquals(Date.lastDayOf(Date.intToMonth(2), 2012), 29);
        assertEquals(Date.lastDayOf(Date.intToMonth(3), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(4), 2014), 30);
        assertEquals(Date.lastDayOf(Date.intToMonth(5), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(6), 2014), 30);
        assertEquals(Date.lastDayOf(Date.intToMonth(7), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(8), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(9), 2014), 30);
        assertEquals(Date.lastDayOf(Date.intToMonth(10), 2014), 31);
        assertEquals(Date.lastDayOf(Date.intToMonth(11), 2014), 30);
        assertEquals(Date.lastDayOf(Date.intToMonth(12), 2014), 31);
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#before(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testBefore() {
        Date da = new Date();
        Date db = new Date(1, Date.intToMonth(2), 2014);
        assertTrue(db.before(da));

        assertTrue(d0.before(d1));
        assertTrue(d1.before(d2));
        assertTrue(d2.before(d3));

        assertFalse(d1.before(d1));

    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#after(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testAfter() {
        Date da = new Date();
        Date db = new Date(1, Date.intToMonth(2), 2014);
        assertTrue(da.after(db));

        assertTrue(d1.after(d0));
        assertTrue(d2.after(d1));
        assertTrue(d3.after(d2));

        assertFalse(d1.after(d1));

    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#compareTo(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testCompareTo() {
        assertTrue(d0.compareTo(d1) < 0);
        assertTrue(d1.compareTo(d2) < 0);
        assertTrue(d2.compareTo(d3) < 0);

        assertEquals(d1.compareTo(d1), 0);

        assertTrue(d1.compareTo(d0) > 0);
        assertTrue(d2.compareTo(d1) > 0);
        assertTrue(d3.compareTo(d2) > 0);
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#firstOfMonth(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testFirstOfMonth() {
        Date d = new Date(2, 2, 2014);
        assertEquals(Date.firstOfMonth(d), new Date(1, 2, 2014));
        d = new Date(1, 1, 2014);
        assertEquals(Date.firstOfMonth(d), new Date(1, 1, 2014));
        d = new Date(31, 12, 2013);
        assertEquals(Date.firstOfMonth(d), new Date(1, 12, 2013));
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#endOfMonth(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testEndOfMonth() {
        Date d = new Date(2, 2, 2014);
        assertEquals(Date.endOfMonth(d), new Date(28, 2, 2014));
        d = new Date(1, 1, 2014);
        assertEquals(Date.endOfMonth(d), new Date(31, 1, 2014));
        d = new Date(31, 12, 2013);
        assertEquals(Date.endOfMonth(d), new Date(31, 12, 2013));
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#endOfNextMonth(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testEndOfNextMonth() {
        Date d = new Date(2, 2, 2014);
        assertEquals(Date.endOfNextMonth(d), new Date(31, 3, 2014));
        d = new Date(1, 1, 2014);
        assertEquals(Date.endOfNextMonth(d), new Date(28, 2, 2014));
        d = new Date(31, 12, 2013);
        assertEquals(Date.endOfNextMonth(d), new Date(31, 1, 2014));
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#firstOfNextMonth(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testFirstOfNextMonth() {
        Date d = new Date(2, 2, 2014);
        assertEquals(Date.firstOfNextMonth(d), new Date(1, 3, 2014));
        d = new Date(1, 1, 2014);
        assertEquals(Date.firstOfNextMonth(d), new Date(1, 2, 2014));
        d = new Date(31, 12, 2013);
        assertEquals(Date.firstOfNextMonth(d), new Date(1, 1, 2014));
    }

    /**
     * Test method for
     * {@link pt.iul.dcti.poo.financemanager.date.Date#lastDayOfPreviousMonth(pt.iul.dcti.poo.financemanager.date.Date)}
     * .
     */
    @Test
    public void testLastDayOfPreviousMonth() {
        Date d = new Date(2, 2, 2014);
        assertEquals(Date.lastDayOfPreviousMonth(d), new Date(31, 1, 2014));
        d = new Date(1, 1, 2014);
        assertEquals(Date.lastDayOfPreviousMonth(d), new Date(31, 12, 2013));
        d = new Date(31, 12, 2013);
        assertEquals(Date.lastDayOfPreviousMonth(d), new Date(30, 11, 2013));
    }

    @Test
    public void testDifferenceInDays() {
        Date d1 = new Date(2, 2, 2014);
        Date d2 = new Date(4, 2, 2014);
        assertEquals(d1.diffInDays(d2), 2);
        d2 = new Date(2, 3, 2014);
        assertEquals(d1.diffInDays(d2), 28);
        Date d3 = new Date(2, 4, 2014);
        assertEquals(d2.diffInDays(d3), 31);
        d2 = new Date(2, 4, 2014);
        assertEquals(d1.diffInDays(d2), 28 + 31);
        d2 = new Date(2, 2, 2015);
        assertEquals(d1.diffInDays(d2), 365);
        d2 = new Date(2, 4, 2015);
        assertEquals(d1.diffInDays(d2), 28 + 31 + 365);

        d2 = new Date(2, 4, 2014);
        assertEquals(d2.diffInDays(d1), 28 + 31);

    }

    @Test
    public void testDifferenceInDays2() {
        Date d1 = new Date(2, 3, 2014);
        Date d2 = new Date(2, 3, 2014);
        for (int i = 0; i != 365; ++i) {
            assertEquals(d1.diffInDays(d2), i);
            d2 = new Date(new java.util.Date(
                    d2.toDate().getTime() + 3600 * 1000 * 24));
        }
    }

}
