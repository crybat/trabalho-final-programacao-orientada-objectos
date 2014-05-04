package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class AnualInterestAccountFormat implements AccountFormat {

    @Override
    public String format(Account acc) {
        return acc.getId() + "\t\t" + acc.yearlyInterestEstimate();
    }

    @Override
    public String fields() {
        return "Id\t\tPredicted Interest";
    }

}
