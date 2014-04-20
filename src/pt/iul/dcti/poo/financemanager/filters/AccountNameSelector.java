package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class AccountNameSelector implements Selector<Account> {

    private String name;

    public AccountNameSelector(String name) {
        this.name = name;
    }

    @Override
    public boolean isSelected(Account item) {
        return name.equals(item.getName());
    }

}
