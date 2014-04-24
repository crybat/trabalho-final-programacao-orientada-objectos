package pt.iul.dcti.poo.financemanager.accounts.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class AccountTest {

    private static Account a1;
    private Account a5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        a1 = new DraftAccount(12345, "DRAFT_ACC");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
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
    public void testAccount2() throws ParseException, BadFormatException,
            IOException {
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
    public void testSetName() {
        a5.setName("OTHER");
        assertEquals(a5.getName(), "OTHER");
    }

}
