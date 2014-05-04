package pt.iul.dcti.poo.financemanager;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.comands.CommandReceiver;
import pt.iul.dcti.poo.financemanager.accounts.formats.BalanceAccountFormat;
import pt.iul.dcti.poo.financemanager.accounts.formats.SimpleStatementFormat;
import pt.iul.dcti.poo.financemanager.commands.AccountStatementCommand;
import pt.iul.dcti.poo.financemanager.commands.AnualInterestCommand;
import pt.iul.dcti.poo.financemanager.commands.ExitCommand;
import pt.iul.dcti.poo.financemanager.commands.GlobalPositionCommand;
import pt.iul.dcti.poo.financemanager.commands.ListCategoriesCommand;
import pt.iul.dcti.poo.financemanager.commands.MetricsCommand;
import pt.iul.dcti.poo.financemanager.commands.MonthlySummaryCommand;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

/**
 * 
 * @author Mauro Pinto - 65175 - <mauro.s.pinto@gmail.com>
 * 
 */
public class Main {

    /*
     * TODO Resto da análise TODO Guardar contas
     */

    public static void main(String[] args) {
        PersonalFinanceManager pfm = new PersonalFinanceManager();

        CommandReceiver<String, Command> options = new CommandReceiver<>();
        CommandReceiver<String, Command> metricsOptions = new CommandReceiver<>();

        Command exit = new ExitCommand(pfm);
        options.put(null, exit); // cancel
        options.put(PersonalFinanceManagerUserInterface.OPT_EXIT, exit);

        options.put(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION,
                new GlobalPositionCommand(pfm, new BalanceAccountFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_ACCOUNT_STATEMENT,
                new AccountStatementCommand(pfm, new SimpleStatementFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_LIST_CATEGORIES,
                new ListCategoriesCommand(pfm));
        options.put(PersonalFinanceManagerUserInterface.OPT_ANALISE,
                new MetricsCommand(metricsOptions));

        metricsOptions.put(
                PersonalFinanceManagerUserInterface.OPT_MONTHLY_SUMMARY,
                new MonthlySummaryCommand(pfm));
        metricsOptions.put(
                PersonalFinanceManagerUserInterface.OPT_ANUAL_INTEREST,
                new AnualInterestCommand(pfm));

        new PersonalFinanceManagerUserInterface(options).execute();
    }

}
