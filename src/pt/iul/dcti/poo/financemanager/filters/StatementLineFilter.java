package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class StatementLineFilter extends
        Filter<StatementLine, Selector<StatementLine>> {

    public StatementLineFilter(Selector<StatementLine> selector) {
        setSelector(selector);
    }

}
