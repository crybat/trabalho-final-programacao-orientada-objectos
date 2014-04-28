package pt.iul.dcti.poo.financemanager.commands;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.categories.CategoryIndex;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class ListCategoriesCommand implements Command {

    private final CategoryIndex categoryIndex;

    public ListCategoriesCommand(PersonalFinanceManager pfm) {
        this.categoryIndex = pfm.getCategoryIndex();
    }

    @Override
    public void executeCommand() {
        System.out
                .println(PersonalFinanceManagerUserInterface.OPT_LIST_CATEGORIES
                        + "\n" + categoryIndex.toString());

    }

}
