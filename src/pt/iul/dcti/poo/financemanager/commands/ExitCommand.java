package pt.iul.dcti.poo.financemanager.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.formats.FileAccountFormat;
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
            saveAccounts();
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

    private void saveAccounts() {
        File dir = new File(Configuration.getDirUpdatedAccounts());
        if (!dir.exists() && !dir.mkdir()) {
            System.out.println("Couldn't create " + dir.getName() + "!");
            return;
        }

        FileAccountFormat af = new FileAccountFormat();
        for (Account acc : pfm.getAccounts().values()) {

            try (PrintWriter pw = new PrintWriter(Configuration.getDirUpdatedAccounts()
                    + acc.getId() + ".csv")) {
                pw.write(af.format(acc));
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't write " + acc.getName() + " to "
                        + dir.getAbsolutePath() + acc.getId() + ".csv"
                        + " account!");
            }
        }
    }
}
