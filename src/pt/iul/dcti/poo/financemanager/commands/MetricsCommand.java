package pt.iul.dcti.poo.financemanager.commands;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.comands.CommandReceiver;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.utils.Menu;

public class MetricsCommand implements Command {

    private final CommandReceiver<String, Command> options;

    public MetricsCommand(CommandReceiver<String, Command> options) {
        this.options = options;
    }

    @Override
    public void executeCommand() {
        String choice = Menu.requestSelection(
                PersonalFinanceManagerUserInterface.OPT_ANALISE,
                PersonalFinanceManagerUserInterface.OPTIONS_ANALYSIS);

        options.executeCommand(choice);
    }
}
