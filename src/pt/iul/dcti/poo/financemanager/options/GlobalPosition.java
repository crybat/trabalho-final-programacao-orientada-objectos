package pt.iul.dcti.poo.financemanager.options;

import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class GlobalPosition implements Option {

    private PersonalFinanceManager pfm;

    public GlobalPosition(PersonalFinanceManager pfm)
    {
        this.pfm = pfm;
    }

    @Override
    public void executeOption()
    {
        double totalBalance = 0.0;
        StringBuilder b = new StringBuilder();

        b.append(PersonalFinanceManagerUserInterface.OPT_GLOBAL_POSITION)
                .append("\nAccount Number\t\tBalance\n");

        for (Account acc : pfm.getAccounts().values()) {
            b.append(acc.getId()).append("\t\t").append(acc.getCurrentBalance())
                    .append("\n");
            totalBalance += acc.getCurrentBalance();
        }

        b.append("Total balance:\t\t").append(totalBalance);
        System.out.println(b);
    }

}
