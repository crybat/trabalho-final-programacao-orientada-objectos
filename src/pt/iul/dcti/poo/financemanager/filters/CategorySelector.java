package pt.iul.dcti.poo.financemanager.filters;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;

public class CategorySelector implements Selector<StatementLine> {

    private Category cat;

    public CategorySelector(Category cat) {
        this.cat = cat;
    }
    
    @Override
    public boolean isSelected(StatementLine item) {
        return cat.equals(item.getCategory());
    }

}
