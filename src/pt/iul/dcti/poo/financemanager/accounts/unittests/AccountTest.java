package pt.iul.dcti.poo.financemanager.accounts.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.BanksConstants;
import pt.iul.dcti.poo.financemanager.accounts.DraftAccount;
import pt.iul.dcti.poo.financemanager.accounts.SavingsAccount;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class AccountTest {

    private static List<Category> categories;
    private static Account a1;
    @SuppressWarnings("unused")
    private static Account a2;
    @SuppressWarnings("unused")
    private Account a3;
    @SuppressWarnings("unused")
    private Account a4;
    private Account a5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        a1 = new DraftAccount(12345, "DRAFT_ACC");
        a2 = new DraftAccount(67890, "DRAFT_ACC");
        //categories = Category.readCategories("account_info/categories");
        categories = new ArrayList<Category>();
        Category c = new Category("SUMMARY");
        c.addDescription("SUMMARY");
        categories.add(c);
        c = new Category("SAVINGS");
        c.addDescription("TRANSF");
        categories.add(c);
        c = new Category("HOME");
        c.addDescription("PURCHASE");
        c.addDescription("INSTALMENT");
        categories.add(c);
        c = new Category("CAR");
        c.addDescription("LOW_VALUE");
        categories.add(c);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        a3 = a1;
        a4 = new DraftAccount(67890, "DRAFT_ACC");
        a5 = Account.newAccount(new File("account_info/1234567890989.csv"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAccount1() {
        Account acc = new DraftAccount(1, "NAME");
        assertEquals(acc.getId(), 1);
        assertEquals(acc.getName(), "NAME");
        assertEquals(acc.getClass(), DraftAccount.class);
        assertEquals(acc.additionalInfo(), "");
        assertEquals(acc.currentBalance(), 0.0, 0.001);
        assertEquals(acc.estimatedAverageBalance(), 0.0, 0.001);
        assertNull(acc.getStartDate());
        assertNull(acc.getEndDate());
        assertEquals(acc.getInterestRate(),
                BanksConstants.normalInterestRate(), 0.001);
    }

    @Test
    public void testAccount2() throws ParseException, BadFormatException, ClassNotFoundException, IOException {
        Account acc = Account.newAccount(new File(
                "account_info/1234567890989.csv"));
        assertEquals(acc.getId(), 1234567890989L);
        assertEquals(acc.getName(), "POUPANCA");
        assertEquals(acc.getClass(), SavingsAccount.class);
        assertEquals(acc.additionalInfo(), "");
        assertEquals(acc.currentBalance(), 3900.0, 0.001);
        assertEquals(acc.estimatedAverageBalance(), 3900.0, 0.001);
        assertEquals(acc.getStartDate(), new Date(31, 10, 2013));
        assertEquals(acc.getEndDate(), new Date(3, 1, 2014));
        assertEquals(acc.getInterestRate(),
                BanksConstants.savingsInterestRate(), 0.001);
    }

    @Test
    public void testAddStatementLine() {
        a1.addStatementLine(new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", 0.0, 22, 1520, 1542, null));
        a1.addStatementLine(new StatementLine(new Date(2, 1, 2014), new Date(2,
                1, 2014), "description", 0.0, 0.0, 1542, 1542, null));
        assertEquals(a1.currentBalance(), 1542.0, 0.001);
        assertEquals(a1.estimatedAverageBalance(), 1542.0, 0.001);
        assertEquals(a1.getStartDate(), new Date(1, 1, 2014));
        assertEquals(a1.getEndDate(), new Date(2, 1, 2014));
    }

    @Test
    public void testRemoveStatementLinesBefore() {
        a5.removeStatementLinesBefore(new Date(1, 12, 2013));
        assertEquals(0.0, a5.totalDraftsForCategorySince(categories.get(0),
                new Date(1, 11, 2013)), 0.001);
    }

    @Test
    public void testTotalForMonth() {
        assertEquals(a5.totalForMonth(12, 2013), 0.0, 0.001);
    }

    @Test
    public void testTotalForMonth2() {
        a1.addStatementLine(new StatementLine(new Date(1, 12, 2013), new Date(
                1, 12, 2013), "description", -22.0, 0.0, 1542, 1542, null));
        a1.addStatementLine(new StatementLine(new Date(2, 12, 2013), new Date(
                2, 12, 2013), "description", -2.0, 0.0, 1540, 1540, null));
        assertEquals(a1.totalForMonth(12, 2013), -24.0, 0.001);
    }

    @Test
    public void testAutoCategorizeStatements1() throws ParseException,
            BadFormatException, ClassNotFoundException, IOException {
        Account a6 = Account.newAccount(new File(
                "account_info/1234567890987.csv"));
        // Assuming categories (in this order):
        // SUMMARY with description SUMMARY
        // SAVINGS with description TRANSF
        // HOME with descriptions PURCHASE, INSTALMENT
        // CAR with description LOW_VALUE
        assertEquals(a6.totalDraftsForCategorySince(categories.get(0),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(1),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(2),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(3),
                new Date(1, 11, 2013)), 0.0, 0.001);
        a6.autoCategorizeStatements(categories);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(0),
                new Date(1, 11, 2013)), -5576.63, 0.01);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(1),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(2),
                new Date(1, 11, 2013)), -450, 0.001);
        assertEquals(a6.totalDraftsForCategorySince(categories.get(3),
                new Date(1, 11, 2013)), -150.65, 0.001);
    }

    @Test
    public void testAutoCategorizeStatements2() throws FileNotFoundException,
            EOFException, ParseException, BadFormatException {
        // Savings accounts only have SAVINGS statement-lines ... and they are
        // all credits
        assertEquals(a5.totalDraftsForCategorySince(categories.get(0),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a5.totalDraftsForCategorySince(categories.get(1),
                new Date(1, 11, 2013)), 0.0, 0.001);
        a5.autoCategorizeStatements(categories);
        assertEquals(a5.totalDraftsForCategorySince(categories.get(0),
                new Date(1, 11, 2013)), 0.0, 0.001);
        assertEquals(a5.totalDraftsForCategorySince(categories.get(1),
                new Date(1, 11, 2013)), 0.0, 0.001);
    }

    @Test
    public void testSetName() {
        a5.setName("OTHER");
        assertEquals(a5.getName(), "OTHER");
    }

}
