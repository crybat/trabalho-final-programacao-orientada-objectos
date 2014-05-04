package pt.iul.dcti.poo.financemanager.accounts;

import java.util.Calendar;
import java.util.SortedSet;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.BetweenDatesSelector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;

public class DraftAccount extends Account {

    public DraftAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double yearlyInterestEstimate() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        SortedSet<StatementLine> sttmts = (SortedSet<StatementLine>) new StatementLineFilter(
                new BetweenDatesSelector(new Date(1, 1, year), new Date(31, 12,
                        year))).apply(getStatements());

        double sum = 0.0;
        int count = 1;

        for (StatementLine sttmt : sttmts) {
            sum += count * sttmt.getAvailableBalance();
            count++;
        }

        return sum * getInterestRate();
    }

    @Override
    public double estimatedAverageBalance() {
        double sum = 0.0;
        int count = 1;

        for (StatementLine sttmt : getStatements()) {
            sum += count * sttmt.getAvailableBalance();
            count++;
        }

        return sum / count;
    }

}
