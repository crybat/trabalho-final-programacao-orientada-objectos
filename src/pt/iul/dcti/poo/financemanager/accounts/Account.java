package pt.iul.dcti.poo.financemanager.accounts;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.accounts.statements.parsers.ScannerStatementLineParser;
import pt.iul.dcti.poo.financemanager.accounts.utils.AccountUtils;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.date.utils.DateUtils;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;
import pt.iul.dcti.poo.financemanager.filters.BeforeDateSelector;
import pt.iul.dcti.poo.financemanager.filters.BetweenDatesSelector;
import pt.iul.dcti.poo.financemanager.filters.CategorySelector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;

public abstract class Account {

    public static Account newAccount(File file) throws IOException,
            ParseException, ClassNotFoundException, BadFormatException {

        Account acc = AccountUtils.parseAccount(file);
        ScannerStatementLineParser.populateAccount(acc, file);

        return acc;
    }

    private long id;
    private String name;
    private String additionalInfo = "";
    private NavigableSet<StatementLine> statements = new TreeSet<>();
    private String currency;

    public abstract double getInterestRate();

    public abstract double estimatedAverageBalance();

    public abstract double yearlyInterestEstimate();

    public double totalDraftsForCategorySince(Category cat, Date date) {
        StatementLineFilter categoryFilter = new StatementLineFilter(
                new CategorySelector(cat));
        NavigableSet<StatementLine> sttmts = (NavigableSet<StatementLine>) categoryFilter
                .apply(statements);
        
        if (sttmts.isEmpty())
            return 0.0;
        
        Date startDate = DateUtils.max(Date.firstOfMonth(date), sttmts.first().getDate());
        Date endDate = sttmts.last().getDate();
        StatementLineFilter dateFilter = new StatementLineFilter(
                new BetweenDatesSelector(startDate, endDate));
        sttmts = (NavigableSet<StatementLine>) dateFilter.apply(sttmts);
        double total = 0.0;

        for (StatementLine sttmt : sttmts)
            total += sttmt.getDraft();

        return total;
    }

    public void removeStatementLinesBefore(Date date) {
        StatementLineFilter beforeFilter = new StatementLineFilter(
                new BeforeDateSelector(date));
        statements = (NavigableSet<StatementLine>) beforeFilter
                .apply(statements);
    }

    public double totalForMonth(int month, int year) {
        Date firstOfMonth = new Date(1, month, year);
        Date lastOfMonth = Date.firstOfNextMonth(firstOfMonth);
        StatementLineFilter dateFilter = new StatementLineFilter(
                new BetweenDatesSelector(firstOfMonth, lastOfMonth));
        NavigableSet<StatementLine> sttmts = (NavigableSet<StatementLine>) dateFilter
                .apply(statements);
        double total = 0.0;

        for (StatementLine sttmt : sttmts) {
            total += sttmt.getDraft();
        }

        return total;
    }

    public void autoCategorizeStatements(List<Category> categories) {

        for (StatementLine sttmt : statements)
            for (Category c : categories)
                if (c.hasDescription(sttmt.getDescription())) {
                    sttmt.setCategory(c);
                    break;
                }
    }

    public Account(long id, String name) throws IllegalArgumentException {
        setId(id);
        setName(name);
    }

    private void setId(long id) throws IllegalArgumentException {
        validateId(id);
        this.id = id;
    }

    public void setName(String name) throws IllegalArgumentException {
        validateName(name);
        this.name = name;
    }

    public void setCurrency(String currency) throws IllegalArgumentException {
        validateCurrency(currency);
        this.currency = currency;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void addStatementLine(StatementLine statementLine)
            throws IllegalArgumentException {
        validateStatementLine(statementLine);
        statements.add(statementLine);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String additionalInfo() {
        return additionalInfo;
    }

    public double currentBalance() {
        return hasStatements() ? statements.last().getAvailableBalance() : 0.0;
    }

    public Date getStartDate() {
        return hasStatements() ? statements.first().getDate() : null;
    }

    public Date getEndDate() {
        return hasStatements() ? statements.last().getDate() : null;
    }

    public double getCurrentBalance() {
        return hasStatements() ? statements.last().getAvailableBalance() : 0.0;
    }

    public Date getLastActivityDate() {
        return getEndDate();
    }

    public NavigableSet<StatementLine> getStatements() {
        return statements;
    }

    public boolean hasStatements() {
        return !statements.isEmpty();
    }

    private void validateCurrency(String currency)
            throws IllegalArgumentException {
        if (currency == null || currency.trim().isEmpty())
            throw new IllegalArgumentException("Currency must not be empty");
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Currency must not be empty");
    }

    private void validateStatementLine(StatementLine statementLine)
            throws IllegalArgumentException {
        if (statementLine == null)
            throw new IllegalArgumentException(
                    "Statement Line must not be null");

//        if (!hasStatements())
//            return;
//
//        double balance = getCurrentBalance() + statementLine.getDraft()
//                + statementLine.getCredit();
//
//        if (Math.abs(balance - statementLine.getAvailableBalance()) > 0.01) {
//            throw new IllegalArgumentException(
//                    "Error with statement line balance:\n" + statementLine
//                            + "\n" + "sould be " + balance + " was "
//                            + statementLine.getAvailableBalance()
//                            + " current balance" + getCurrentBalance());
//        }
    }

    private void validateId(long id) throws IllegalArgumentException {
        if (id < 0)
            throw new IllegalArgumentException("ID must be a positive long");
    }

}
