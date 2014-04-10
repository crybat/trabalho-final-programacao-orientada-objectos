package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class BalanceAccountFormat implements AccountFormat {

    @Override
    public String format(Account acc) {
	return acc.getId() + "\t\t" + acc.getCurrentBalance();
    }

    @Override
    public String fields() {
	return "Account Number\t\tBalance";
    }

}
