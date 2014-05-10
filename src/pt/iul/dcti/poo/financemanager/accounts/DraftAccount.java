package pt.iul.dcti.poo.financemanager.accounts;

import java.util.Calendar;
import java.util.Iterator;
import java.util.SortedSet;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.BetweenDatesSelector;
import pt.iul.dcti.poo.financemanager.filters.Selector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;

public class DraftAccount extends Account {

    public DraftAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double yearlyInterestEstimate() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Selector<StatementLine> selector = new BetweenDatesSelector(new Date(1,
                1, year), new Date(31, 12, year));
        StatementLineFilter filter = new StatementLineFilter(selector);
        SortedSet<StatementLine> sttmts = (SortedSet<StatementLine>) filter
                .apply(getStatements());

        return estimatedAverageBalance(sttmts) * getInterestRate();
    }

    @Override
    public double estimatedAverageBalance() {
        return estimatedAverageBalance(getStatements());
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.normalInterestRate();
    }

    private double estimatedAverageBalance(SortedSet<StatementLine> statements) {

        if (statements.isEmpty())
            return 0.0;

        double sum = 0.0;
        int dayCount = statements.first().getDate()
                .diffInDays(statements.last().getDate()) + 1;

        Iterator<StatementLine> iterator = statements.iterator();
        StatementLine previous = (StatementLine) iterator.next();
        StatementLine current = previous;
        while (iterator.hasNext()) {
            current = (StatementLine) iterator.next();
            sum += sum(previous, current);
        }

        sum += sum(current, current);

        return sum / dayCount;
    }

    private static double sum(StatementLine previous, StatementLine current) {
        int dayDifference;

        if (current.getDate().diffInDays(previous.getDate()) == 0)
            dayDifference = 1;
        else
            dayDifference = current.getDate().diffInDays(previous.getDate());

        return dayDifference * previous.getAvailableBalance();
    }

}
