package pt.iul.dcti.poo.financemanager;

import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.financemanager.options.Exit;
import pt.iul.dcti.poo.financemanager.options.Option;
import pt.iul.dcti.poo.financemanager.options.OptionManager;

public class Main {

    public static void main(String[] args)
    {
        OptionManager<String, Option> options = new OptionManager<>();
        options.put(PersonalFinanceManagerUserInterface.OPT_EXIT, new Exit());

        PersonalFinanceManager personalFinanceManager = new PersonalFinanceManager();
        PersonalFinanceManagerUserInterface gui = new PersonalFinanceManagerUserInterface(
                options);
        gui.execute();

    }

}
