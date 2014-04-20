package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class BetweenDatesSelector implements Selector<StatementLine> {

    private Date lowerDate;
    private Date upperDate;

    public BetweenDatesSelector(Date date1, Date date2) {
        if (date1.compareTo(date2) > 0) {
            this.lowerDate = date2;
            this.upperDate = date1;
        } else {
            this.lowerDate = date1;
            this.upperDate = date2;
        }
    }

    @Override
    public boolean isSelected(StatementLine item) {
        return item.getDate().compareTo(lowerDate) >= 0
                && item.getDate().compareTo(upperDate) <= 0;
    }

}
