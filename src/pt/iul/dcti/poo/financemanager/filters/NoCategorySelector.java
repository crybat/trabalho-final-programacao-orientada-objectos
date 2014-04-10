package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class NoCategorySelector implements Selector<StatementLine> {

    @Override
    public boolean isSelected(StatementLine item) {
	return item.getCategory() == null;
    }

}
