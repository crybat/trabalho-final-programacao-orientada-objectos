package pt.iul.dcti.poo.financemanager.options;

import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.utils.Menu;

public class Metrics implements Option {

    private OptionManager<String, Option> options;

    public Metrics(OptionManager<String, Option> options)
    {
        this.options = options;
    }

    @Override
    public void executeOption()
    {
        String choice = Menu.requestSelection(
                PersonalFinanceManagerUserInterface.OPT_ANALISE,
                PersonalFinanceManagerUserInterface.OPTIONS_ANALYSIS);

        options.executeOption(choice);
    }
}
