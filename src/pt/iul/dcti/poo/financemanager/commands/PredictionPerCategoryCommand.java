package pt.iul.dcti.poo.financemanager.commands;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.BetweenDatesSelector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;
import pt.iul.dcti.poo.financemanager.gui.PersonalFinanceManagerUserInterface;

public class PredictionPerCategoryCommand implements Command {

    private Map<String, Account> accounts;

    public PredictionPerCategoryCommand(PersonalFinanceManager pfm) {
        accounts = pfm.getAccounts();
    }

    @Override
    public void executeCommand() {
        String accountID = PersonalFinanceManagerUserInterface
                .chooseAccount(accounts);

        if (accountID == null)
            return;

        Date curDate = new Date(Calendar.getInstance().getTime());
        //Date curDate = new Date(26, 01, 2014);
        Date startDate = Date.firstOfMonth(curDate);
        int daysInMonth = Date.lastDayOf(startDate.getMonth(),
                startDate.getYear());
        int passedDays = curDate.getDay();
        StatementLineFilter f = new StatementLineFilter(
                new BetweenDatesSelector(startDate, curDate));
        NavigableSet<StatementLine> sttmts = (NavigableSet<StatementLine>) f
                .apply(accounts.get(accountID).getStatements());

        Map<Category, Double> totals = new HashMap<>();
        for (StatementLine sttmt : sttmts) {
            sum(totals, sttmt);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(
                PersonalFinanceManagerUserInterface.OPT_PREDICTION_PER_CATEGORY)
                .append("\n").append("Category\tCurrent Predicted")
                .append("\n");
        for (Map.Entry<Category, Double> totalEntry : totals.entrySet()) {
            Double total = Math.abs(totalEntry.getValue() / passedDays
                    * daysInMonth);
            if (total > 0)
                sb.append(totalEntry.getKey().getName()).append("\t")
                        .append(total).append("\n");
        }
        System.out.println(sb);
    }

    private static void sum(Map<Category, Double> map, StatementLine sttmt) {
        Category c = sttmt.getCategory();
        double value = sttmt.getDraft();
        if (map.containsKey(c)) {
            map.put(c, value + map.get(c));
        } else {
            map.put(c, value);
        }
    }

}
