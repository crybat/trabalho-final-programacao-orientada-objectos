package pt.iul.dcti.poo.financemanager.accounts.unittests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.VIPAccount;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;

public class VIPAccountTest {

    private static Account a1;
    @SuppressWarnings("unused")
    private static Account a2;
    @SuppressWarnings("unused")
    private Account a3;
    private Account a4;
    private Account a5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        a1 = new VIPAccount(12345, "My VIP Acc", 0.05);
        a2 = new VIPAccount(67890, "My VIP Acc", 0.1);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        a3 = a1;
        a4 = new VIPAccount(67890, "My VIP Acc", 0.05);
        a5 = Account.newAccount(new File("account_info/1234567890985.csv"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testVIPAccount() {
        Account a = new VIPAccount(1, "VIP", 0.1);
        assertEquals(a.getInterestRate(), 0.1, 0.001);
        assertEquals(a5.additionalInfo(), "0.05");
    }

    @Test
    public void testAddStatementLine() {
        Category cat = new Category("HOME");
        a4.addStatementLine(new StatementLine(new Date(1, 1, 2014), new Date(1,
                1, 2014), "description", -12.0, 0.0, 1542, 1542, cat));
        assertEquals(a4.currentBalance(), 1542.0, 0.0001);
        assertEquals(
                a4.totalDraftsForCategorySince(cat, new Date(31, 12, 2013)),
                -12.0, 0.0001);
    }

    @Test
    public void testEstimatedAverageBalance() {
        assertEquals(a4.estimatedAverageBalance(), a4.currentBalance(), 0.001);
    }

}
