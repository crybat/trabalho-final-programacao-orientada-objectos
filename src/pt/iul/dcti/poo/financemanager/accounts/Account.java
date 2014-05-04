package pt.iul.dcti.poo.financemanager.accounts;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.SortedSet;
import java.util.TreeSet;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.accounts.parsers.FileAccountParser;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.accounts.statements.parsers.ScannerStatementLineParser;
import pt.iul.dcti.poo.financemanager.date.Date;

public abstract class Account {

    public static Account newAccount(File file) throws IOException,
            ParseException, ClassNotFoundException {

        Account acc = new FileAccountParser().parseAccount(file);
        ScannerStatementLineParser.populateAccount(acc, file);

        String baseName = file.getName();
        String statementsFileName = Configuration.getDirStatements()
                + baseName.substring(0, baseName.indexOf(".csv")) + "_1.csv";

        File additionalStatements = new File(statementsFileName);
        if (additionalStatements.canRead()) {
            ScannerStatementLineParser.populateAccount(acc,
                    additionalStatements);
        }

        return acc;
    }

    private long id;
    private String name;
    private String additionalInfo = "";
    private SortedSet<StatementLine> statements = new TreeSet<>();
    private String currency;
    private double interestRate = 1.0;

    public double getInterestRate() {
        return interestRate;
    }

    public abstract double estimatedAverageBalance();
    
    public abstract double yearlyInterestEstimate();

    public Account(long id, String name) throws IllegalArgumentException {
        setId(id);
        setName(name);
    }

    private void setId(long id) throws IllegalArgumentException {
        validateId(id);
        this.id = id;
    }

    public void addStatementLine(StatementLine statementLine)
            throws IllegalArgumentException {
        validateStatementLine(statementLine);
        statements.add(statementLine);
    }

    public void setName(String name) throws IllegalArgumentException {
        validateName(name);
        this.name = name;
    }

    public void setCurrency(String currency) throws IllegalArgumentException {
        validateCurrency(currency);
        this.currency = currency;
    }
    
    public void setInterestRate(double interestRate) throws IllegalArgumentException {
        validateInterestRate(interestRate);
        this.interestRate = interestRate;
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

    public SortedSet<StatementLine> getStatements() {
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
    
    private void validateInterestRate(double interestRate) {
        if (interestRate < 0.0)
            throw new IllegalArgumentException("Interest rate must be positive");
    }

    private void validateStatementLine(StatementLine statementLine)
            throws IllegalArgumentException {
        if (statementLine == null)
            throw new IllegalArgumentException(
                    "Statement Line must not be null");
    }

    private void validateId(long id) throws IllegalArgumentException {
        if (id < 0)
            throw new IllegalArgumentException("ID must be a positive long");
    }

}
