package pt.iul.dcti.poo.financemanager.filters.unittests;

import static org.junit.Assert.assertEquals;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.NoCategorySelector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;

public class StatementLineFilterTest {

    @Test
    public void noCategoryFilter() {
        SortedSet<StatementLine> ss = new TreeSet<>();
        ss.add(new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null));
        ss.add(new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, new Category("Something")));

        StatementLineFilter filter = new StatementLineFilter(
                new NoCategorySelector());
        SortedSet<StatementLine> filtered = (SortedSet<StatementLine>) filter
                .apply(ss);
        assertEquals(1, filtered.size());
    }

}
