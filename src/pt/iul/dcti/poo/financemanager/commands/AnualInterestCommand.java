package pt.iul.dcti.poo.financemanager.commands;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.formats.AnualInterestAccountFormat;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class AnualInterestCommand implements Command {

    private PersonalFinanceManager pfm;

    public AnualInterestCommand(PersonalFinanceManager pfm) {
        this.pfm = pfm;
    }

    @Override
    public void executeCommand() {

        AnualInterestAccountFormat f = new AnualInterestAccountFormat();
        double total = 0.0;
        StringBuilder sb = new StringBuilder()
                .append(PersonalFinanceManagerUserInterface.OPT_ANUAL_INTEREST)
                .append("\n")
                .append(f.fields())
                .append("\n");

        for (Account acc : pfm.getAccounts().values()) {
            total += acc.yearlyInterestEstimate();
            sb.append(f.format(acc))
            .append("\n");
        }

        sb.append("Total: " + total);
        System.out.println(sb);

    }

}
