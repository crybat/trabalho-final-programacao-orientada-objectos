package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.VIPAccount;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;

public class FileAccountFormat {

    private static final String NL = System.getProperty("line.separator");

    public String format(Account a) {

        String output;

        output = "Account Info - " + new Date() + NL;
        // header
        output += "Account  ;" + a.getId() + " ; " + a.getCurrency() + "  ;"
                + a.getName() + " ;" + a.getClass().getSimpleName() + " ;";

        if (a instanceof VIPAccount)
            output += a.getInterestRate();

        output += NL;
        output += "Start Date ;" + a.getStartDate() + NL;
        output += "End Date ;" + a.getEndDate() + NL;
        output += statements(a.getStatements());

        return output;
    }

    private static String statements(Iterable<StatementLine> sttmts) {
        StringBuilder sb = new StringBuilder();

        sb.append("Date ;Value Date ;Description ;Draft ;Credit ;Accounting balance ;Available balance")
            .append(NL);
        for (StatementLine statementLine : sttmts) {
            sb.append(statement(statementLine)).append(NL);
        }

        return sb.toString();
    }

    private static String statement(StatementLine sttmt) {
        return sttmt.getDate() + " ;" + sttmt.getValueDate() + " ;"
                + sttmt.getDescription() + " ;" + sttmt.getDraft() + " ;"
                + sttmt.getCredit() + " ;" + sttmt.getAccountingBalance()
                + " ;" + sttmt.getAvailableBalance();
    }
}
