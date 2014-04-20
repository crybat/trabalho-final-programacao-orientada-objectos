package pt.iul.dcti.poo.financemanager.options;

import java.util.Map;

import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.formats.StatementLineFormat;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;
import pt.iul.dcti.poo.utils.Menu;

public class AccountStatementOption implements Option {

    private StatementLineFormat formatter;
    private Map<String, Account> accounts;

    public AccountStatementOption(PersonalFinanceManager pfm,
            StatementLineFormat formatter) {
        this.formatter = formatter;
        accounts = pfm.getAccounts();
    }

    @Override
    public void executeOption() {
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
        String[] accountOptions = accounts.keySet().toArray(new String[0]);
        return Menu.requestSelection("Choose an Account", accountOptions);
    }
}
