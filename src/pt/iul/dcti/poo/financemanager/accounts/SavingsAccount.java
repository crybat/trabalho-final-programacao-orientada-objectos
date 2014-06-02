package pt.iul.dcti.poo.financemanager.accounts;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;

public class SavingsAccount extends Account {

    public static Category savingsCategory = new Category("SAVINGS");

    public SavingsAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
        return getCurrentBalance();
    }

    @Override
    public double yearlyInterestEstimate() {
        return getCurrentBalance() * getInterestRate();
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.savingsInterestRate();
    }

    @Override
    public void addStatementLine(StatementLine statementLine)
            throws IllegalArgumentException {
/*
        if (!savingsCategory.hasDescription(statementLine.getDescription()))
            savingsCategory.addDescription(statementLine.getDescription());*/

        statementLine.setCategory(savingsCategory);

        super.addStatementLine(statementLine);
    }

}
