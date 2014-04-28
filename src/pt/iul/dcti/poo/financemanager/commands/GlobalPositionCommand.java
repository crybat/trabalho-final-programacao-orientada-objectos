package pt.iul.dcti.poo.financemanager.commands;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.formats.AccountFormat;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class GlobalPositionCommand implements Command {

    private final PersonalFinanceManager pfm;
    private final AccountFormat formatter;

    public GlobalPositionCommand(PersonalFinanceManager pfm,
            AccountFormat formatter) {
        this.pfm = pfm;
        this.formatter = formatter;
    }

    @Override
    public void executeCommand() {
        double totalBalance = 0.0;
        StringBuilder b = new StringBuilder();

        b.append(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION)
                .append("\n").append(formatter.fields()).append("\n");

        for (Account acc : pfm.getAccounts().values()) {
            b.append(formatter.format(acc)).append("\n");
            totalBalance += acc.getCurrentBalance();
        }

        b.append("Total balance:\t\t").append(totalBalance);
        System.out.println(b);
    }

}
