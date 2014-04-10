package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public interface AccountFormat extends Format<Account> {

    String fields();
    
}
