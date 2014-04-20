package pt.iul.dcti.poo.financemanager.accounts;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class DraftAccount extends Account {

    public DraftAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
        double sum = 0.0;
        int count = 1;

        for (StatementLine statement : getStatements()) {
            sum += count * statement.getAvailableBalance();
            count++;
        }

        return sum / count;
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.normalInterestRate();
    }

}
