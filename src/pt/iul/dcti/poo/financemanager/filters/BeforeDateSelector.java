package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

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
