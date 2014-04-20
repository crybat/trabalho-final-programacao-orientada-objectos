package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class SimpleStatementFormat implements StatementLineFormat {

    @Override
    public String fields() {
        return "Date \tDescription \tDraft \tCredit \tAvailable balance ";
    }

    @Override
    public String format(StatementLine sttLine) {
        return sttLine.getDate() + " \t" + sttLine.getDescription() + " \t"
                + sttLine.getDraft() + " \t" + sttLine.getCredit() + " \t"
                + sttLine.getAvailableBalance();
    }

}
