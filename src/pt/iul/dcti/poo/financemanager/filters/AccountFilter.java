package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class AccountFilter extends Filter<Account, Selector<Account>> {

    public AccountFilter(Selector<Account> selector) {
        setSelector(selector);
    }

}
