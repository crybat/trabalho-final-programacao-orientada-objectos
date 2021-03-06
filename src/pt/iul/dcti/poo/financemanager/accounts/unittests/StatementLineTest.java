package pt.iul.dcti.poo.financemanager.accounts.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.io.EOFException;
import java.text.ParseException;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class StatementLineTest {

    private static StatementLine stt1;
    @SuppressWarnings("unused")
    private static StatementLine stt2;

    private StatementLine stt3;
    private StatementLine stt4;
    private StatementLine stt5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        stt1 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
        stt2 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        stt3 = stt1;
        stt4 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
        stt5 = stt3;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStatementLineBadDate() {
        @SuppressWarnings("unused")
        StatementLine stt = new StatementLine(null, new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStatementLineEmptyDescription() {
        @SuppressWarnings("unused")
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "", 0.0, 22, 1520, 1542, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStatementLineNullDescription() {
        @SuppressWarnings("unused")
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), null, 0.0, 22, 1520, 1542, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStatementLineBadDraft() {
        @SuppressWarnings("unused")
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 10.0, 22, 1520, 1542, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStatementLineBadCredit() {
        @SuppressWarnings("unused")
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 0.0, -22, 1520, 1542, null);
    }

    @Test
    public void testStatementLineNullCategory() {
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 0.0, 22, 1520, 1542, null);
        assertNull(stt.getCategory());
    }

    @Test
    public void testStatementLineNonNullCategory() {
        Category cat = new Category("A_CATEGORY");
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 0.0, 22, 1520, 1542, cat);
        assertSame(stt.getCategory(), cat);
    }

    @Test
    public void testStatementLine() {
        Date d = new Date(1, 1, 2014);
        String desc = "description";
        double draft = -1.2;
        double credit = 2340;
        double balance = 1467;
        Category cat = new Category("A_CAT");

        StatementLine stt = new StatementLine(d, d, desc, draft, credit,
                balance, balance, cat);
        assertEquals(stt.getDate(), d);
        assertEquals(stt.getValueDate(), d);
        assertEquals(stt.getDescription(), desc);
        assertEquals(stt.getDraft(), draft, 0.001);
        assertEquals(stt.getCredit(), credit, 0.001);
        assertEquals(stt.getAvailableBalance(), balance, 0.001);
        assertEquals(stt.getAccountingBalance(), balance, 0.001);
        assertSame(stt.getCategory(), cat);

    }

    @Test
    public void testEqualsObject() {
        assertEquals(stt1, stt3);
        assertSame(stt5, stt3);
        assertSame(stt1, stt3);
        // assertEquals(stt3, stt4); // removido na V2.3 (Lu�s Nunes)
        assertNotSame(stt4, stt3);
    }

    @Test
    public void testNewStatementLine() throws EOFException, ParseException,
            BadFormatException {
        String in = "03-02-2014;03-02-2014;LOW VALUE ;-150.65;;3301.52;3301.52";
        Scanner s = new Scanner(in);
        StatementLine stt = StatementLine.newStatementLine(s);
        Date d = new Date(3, 2, 2014);
        assertEquals(stt.getDate(), d);
        assertEquals(stt.getValueDate(), d);
        assertEquals(stt.getDescription(), "LOW VALUE");
        assertEquals(stt.getDraft(), -150.65, 0.001);
        assertEquals(stt.getCredit(), 0.0, 0.001);
        assertEquals(stt.getAvailableBalance(), 3301.52, 0.001);
        assertEquals(stt.getAccountingBalance(), 3301.52, 0.001);
        assertNull(stt.getCategory());
    }

    @Test
    public void testSetCategory() {
        StatementLine stt = new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 0.0, 22, 1520, 1542, null);
        assertNull(stt.getCategory());
        Category cat = new Category("A_CAT");
        stt.setCategory(cat);
        assertSame(stt.getCategory(), cat);
    }

}
