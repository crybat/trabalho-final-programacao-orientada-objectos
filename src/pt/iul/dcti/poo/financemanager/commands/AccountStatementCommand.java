package pt.iul.dcti.poo.financemanager.commands;

import java.util.Map;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.formats.StatementLineFormat;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.utils.Menu;

public class AccountStatementCommand implements Command {

    private StatementLineFormat formatter;
    private Map<String, Account> accounts;

    public AccountStatementCommand(PersonalFinanceManager pfm,
            StatementLineFormat formatter) {
        this.formatter = formatter;
        accounts = pfm.getAccounts();
    }

    @Override
    public void executeCommand() {
        String accountID = chooseAccount();
        StringBuilder b = new StringBuilder();
        b.append(PersonalFinanceManagerUserInterface.OPT_ACCOUNT_STATEMENT)
                .append("\n").append(formatter.fields()).append("\n");
        for (StatementLine statement : accounts.get(accountID).getStatements()) {
            b.append(formatter.format(statement)).append("\n");
        }

        System.out.println(b);
    }

    private String chooseAccount() {
        String[] accountOptions = accounts.keySet().toArray(new String[accounts.size()]);
        return Menu.requestSelection("Choose an Account", accountOptions);
    }
}
