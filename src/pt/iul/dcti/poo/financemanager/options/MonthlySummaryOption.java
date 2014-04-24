package pt.iul.dcti.poo.financemanager.options;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class MonthlySummaryOption implements Option {

    private Map<String, Account> accounts;

    public MonthlySummaryOption(PersonalFinanceManager pfm) {
        this.accounts = pfm.getAccounts();
    }

    private static void addToMonth(Map<Date, Double> months, StatementLine sttmt) {
        Date month = Date.firstOfMonth(sttmt.getDate());
        if (months.containsKey(month)) {
            months.put(month, months.get(month) + sttmt.getDraft());
        } else {
            months.put(month, sttmt.getDraft());
        }
    }

    @Override
    public void executeOption() {
        Map<Date, Double> monthly = new HashMap<>();

        for (Account account : accounts.values()) {
            for (StatementLine statement : account.getStatements()) {
                addToMonth(monthly, statement);
            }
        }

        Date[] months = monthly.keySet().toArray(new Date[0]);
        Arrays.sort(months);
        StringBuilder b = new StringBuilder().append(
                PersonalFinanceManagerUserInterface.OPT_MONTHLY_SUMMARY)
                .append("\n");

        for (Date month : months) {
            b.append("Monthly total spent in ").append(month.getMonth())
                    .append(" ").append(month.getYear()).append(" = ")
                    .append(Math.abs(monthly.get(month))).append("\n");
        }

        System.out.println(b);
    }

}
