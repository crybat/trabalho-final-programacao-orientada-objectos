package pt.iul.dcti.poo.financemanager;

import pt.iul.dcti.poo.financemanager.accounts.formats.BalanceAccountFormat;
import pt.iul.dcti.poo.financemanager.accounts.formats.SimpleStatementFormat;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.financemanager.options.AccountStatementOption;
import pt.iul.dcti.poo.financemanager.options.ExitOption;
import pt.iul.dcti.poo.financemanager.options.GlobalPositionOption;
import pt.iul.dcti.poo.financemanager.options.MetricsOption;
import pt.iul.dcti.poo.financemanager.options.MonthlySummaryOption;
import pt.iul.dcti.poo.financemanager.options.Option;
import pt.iul.dcti.poo.financemanager.options.OptionManager;

/**
 * 
 * @author Mauro Pinto - 65175 - <mauro.s.pinto@gmail.com>
 * 
 */
public class Main {

    /*
     * TODO SavingsAccount::estimatedAverageBalance TODO Statements em TreeSet?
     * TODO Categories TODO Save
     */

    public static void main(String[] args) {
        PersonalFinanceManager personalFinanceManager = new PersonalFinanceManager();

        OptionManager<String, Option> options = new OptionManager<>();
        OptionManager<String, Option> metricsOptions = new OptionManager<>();

        Option exit = new ExitOption();
        options.put(null, exit);
        options.put(PersonalFinanceManagerUserInterface.OPT_EXIT, exit);

        options.put(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION,
                new GlobalPositionOption(personalFinanceManager,
                        new BalanceAccountFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_ACCOUNT_STATEMENT,
                new AccountStatementOption(personalFinanceManager,
                        new SimpleStatementFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_ANALISE,
                new MetricsOption(metricsOptions));

        metricsOptions.put(
                PersonalFinanceManagerUserInterface.OPT_MONTHLY_SUMMARY,
                new MonthlySummaryOption(personalFinanceManager));

        new PersonalFinanceManagerUserInterface(options).execute();
    }

}
