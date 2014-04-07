package pt.iul.dcti.poo.financemanager;

import java.io.IOException;
import java.text.ParseException;

import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.financemanager.options.Exit;
import pt.iul.dcti.poo.financemanager.options.GlobalPosition;
import pt.iul.dcti.poo.financemanager.options.Option;
import pt.iul.dcti.poo.financemanager.options.OptionManager;

public class Main {

    public static void main(String[] args) throws IOException, ParseException
    {
        PersonalFinanceManager personalFinanceManager = new PersonalFinanceManager();

        OptionManager<String, Option> options = new OptionManager<>();
        Option exit = new Exit();
        options.put(PersonalFinanceManagerUserInterface.OPT_EXIT, exit);
        options.put(null, exit);

        options.put(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION,
                new GlobalPosition(personalFinanceManager));

        new PersonalFinanceManagerUserInterface(options).execute();
    }

}
