package pt.iul.dcti.poo.financemanager.filters.unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.DraftAccount;
import pt.iul.dcti.poo.financemanager.filters.AccountNameSelector;

public class AccountNameSelectorTest {

    private Account a1;
    private Account a2;

    @Before
    public void setUp() throws Exception
    {
        a1 = new DraftAccount(12345, "DRAFT_ACC");
        a2 = new DraftAccount(67890, "SOMETHING_ELSE");
    }

    @Test
    public void testAccountNameSelector()
    {
        AccountNameSelector nameSelector = new AccountNameSelector("DRAFT_ACC");
        assertTrue(nameSelector.isSelected(a1));
        assertFalse(nameSelector.isSelected(a2));
    }

}
