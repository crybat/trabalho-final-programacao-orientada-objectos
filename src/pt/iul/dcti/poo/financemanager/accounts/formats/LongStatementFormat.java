package pt.iul.dcti.poo.financemanager.accounts.formats;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;

public class LongStatementFormat implements StatementLineFormat {

    @Override
    public String fields() {
        return "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance ";
    }

    @Override
    public String format(StatementLine sttLine) {
        return sttLine.getDate() + " \t" + sttLine.getValueDate() + " \t"
                + sttLine.getDescription() + " \t" + sttLine.getDraft() + " \t"
                + sttLine.getCredit() + " \t" + sttLine.getAccountingBalance()
                + " \t" + sttLine.getAvailableBalance();
    }

}
