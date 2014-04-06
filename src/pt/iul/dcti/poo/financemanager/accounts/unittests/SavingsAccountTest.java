package pt.iul.dcti.poo.financemanager.accounts.unittests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.BanksConstants;
import pt.iul.dcti.poo.financemanager.accounts.SavingsAccount;

public class SavingsAccountTest {

    private Account a5;

    @Before
    public void setUp() throws Exception
    {
        a5 = Account.newAccount(new File("account_info/1234567890989.csv"));
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void testSavingsAccount()
    {
        Account a = new SavingsAccount(1, "SAV");
        assertEquals(a.getInterestRate(), BanksConstants.savingsInterestRate(),
                0.001);
    }

    @Test
    public void testGetType()
    {
        assertEquals(a5.getClass(), SavingsAccount.class);
    }

}
