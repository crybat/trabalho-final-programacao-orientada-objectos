package pt.iul.dcti.poo.financemanager.accounts;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class DraftAccount extends Account {

    public DraftAccount(long id, String name) {
	super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
	double estimation = 0.0;
	int days = 1;

	for (StatementLine statement : getStatements()) {
	    estimation += days * statement.getAvailableBalance();
	    days++;
	}

	return estimation / days;
    }

    @Override
    public double getInterestRate() {
	return BanksConstants.normalInterestRate();
    }

}
