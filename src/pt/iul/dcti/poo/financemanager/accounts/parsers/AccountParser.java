package pt.iul.dcti.poo.financemanager.accounts.parsers;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public interface AccountParser<T> {

    public Account parseAccount(T data) throws Exception;
    
}
