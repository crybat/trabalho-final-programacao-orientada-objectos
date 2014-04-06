package pt.iul.dcti.poo.financemanager.filters.unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.DraftAccount;
import pt.iul.dcti.poo.financemanager.filters.AccountIdSelector;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class AccountIdSelectorTest {

    private static Account a1;
    private static Account a2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception
    {
        a1 = new DraftAccount(12345, "DRAFT_ACC");
        a2 = new DraftAccount(67890, "DRAFT_ACC");
    }

    @Test
    public void testAccountIdSelector()
    {
        AccountIdSelector idSelector = new AccountIdSelector(12345);
        assertTrue(idSelector.isSelected(a1));
        assertFalse(idSelector.isSelected(a2));
    }

}
