package pt.iul.dcti.poo.financemanager;

import java.io.IOException;
import java.text.ParseException;

import pt.iul.dcti.poo.financemanager.accounts.formats.BalanceAccountFormat;
import pt.iul.dcti.poo.financemanager.accounts.formats.SimpleStatementFormat;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.financemanager.options.AccountStatement;
import pt.iul.dcti.poo.financemanager.options.Exit;
import pt.iul.dcti.poo.financemanager.options.GlobalPosition;
import pt.iul.dcti.poo.financemanager.options.Metrics;
import pt.iul.dcti.poo.financemanager.options.MonthlySummary;
import pt.iul.dcti.poo.financemanager.options.Option;
import pt.iul.dcti.poo.financemanager.options.OptionManager;

public class Main {

    /*
     * TODO SavingsAccount::estimatedAverageBalance
     * TODO Statements em TreeMap?
     * TODO Categories
     * TODO Save
     */

    public static void main(String[] args) throws IOException, ParseException
    {
        PersonalFinanceManager personalFinanceManager = new PersonalFinanceManager();

        OptionManager<String, Option> options = new OptionManager<>();
        OptionManager<String, Option> metricsOptions = new OptionManager<>();
        
        Option exit = new Exit();
        options.put(null, exit);
        options.put(PersonalFinanceManagerUserInterface.OPT_EXIT, exit);

        options.put(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION,
                new GlobalPosition(personalFinanceManager, new BalanceAccountFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_ACCOUNT_STATEMENT,
                new AccountStatement(personalFinanceManager, new SimpleStatementFormat()));
        options.put(PersonalFinanceManagerUserInterface.OPT_ANALISE,
                new Metrics(metricsOptions));
        
        metricsOptions.put(PersonalFinanceManagerUserInterface.OPT_MONTHLY_SUMMARY,
                new MonthlySummary(personalFinanceManager));

        new PersonalFinanceManagerUserInterface(options).execute();
    }

}
