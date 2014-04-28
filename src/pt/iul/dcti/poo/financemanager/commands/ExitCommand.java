package pt.iul.dcti.poo.financemanager.commands;

import java.io.IOException;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.utils.Menu;

public class ExitCommand implements Command {

    private static final int STATUS_OK = 1;
    private final PersonalFinanceManager pfm;

    public ExitCommand(PersonalFinanceManager pfm) {
        this.pfm = pfm;
    }

    @Override
    public void executeCommand() {

        if (Menu.yesOrNoInput("Deseja salvar?")) {
            saveCategories();
        }

        System.exit(STATUS_OK);
    }

    private void saveCategories() {
        try {
            Category.saveCategories(pfm.getCategoryIndex().getCategories(),
                    Configuration.getCategoriesFile());
        } catch (IOException e) {
            System.out.println("Houve um erro ao tentar salvar as categorias!");
        }
    }

}
