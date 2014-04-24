package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;

public class BeforeDateSelector implements Selector<StatementLine> {

    private Date date;

    public BeforeDateSelector(Date date) {
        this.date = date;
    }

    @Override
    public boolean isSelected(StatementLine item) {
        return date.compareTo(item.getDate()) > 0;
    }

}
