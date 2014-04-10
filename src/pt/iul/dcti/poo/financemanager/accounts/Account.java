package pt.iul.dcti.poo.financemanager.accounts;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.SortedSet;
import java.util.TreeSet;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.accounts.parsers.FileAccountParser;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.statements.parsers.ScannerStatementLineParser;

public abstract class Account {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Account newAccount(File file) throws IOException,
	    ParseException {
	Account acc = new FileAccountParser().parseAccount(file);
	ScannerStatementLineParser.populateAccount(acc, file);
	String baseName = file.getName();
	String statementsFileName = Configuration.DIR_STATEMENTS
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

    public Account(long id, String name) {
	this.id = id;
	setName(name);
    }

    public void addStatementLine(StatementLine statementLine) {
	if (statementLine == null)
	    return;

	statements.add(statementLine);
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
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

    public abstract double getInterestRate();

    public abstract double estimatedAverageBalance();

}
