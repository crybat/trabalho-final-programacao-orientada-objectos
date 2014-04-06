package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.accounts.Account;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class AccountIdSelector implements Selector<Account> {

    private long id;

    public AccountIdSelector(long id)
    {
        this.id = id;
    }

    @Override
    public boolean isSelected(Account item)
    {
        return item.getId() == id;
    }

}
