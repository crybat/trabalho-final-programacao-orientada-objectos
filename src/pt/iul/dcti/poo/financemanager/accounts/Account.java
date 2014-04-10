package pt.iul.dcti.poo.financemanager.accounts;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.accounts.parsers.FileAccountParser;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.statements.parsers.ScannerStatementLineParser;

public abstract class Account {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static Account newAccount(File file) throws IOException,
            ParseException
    {
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

    private long                id;
    private String              name;
    private String              additionalInfo = "";
    private double              currentBalance;
    private Date                startDate;
    private Date                endDate;
    private List<StatementLine> statements     = new LinkedList<>();
    private String              currency;
    private Date                lastActivityDate;

    public Account(long id, String name)
    {
        this.id = id;
        setName(name);
    }

    public void addStatementLine(StatementLine statementLine)
    {
        if (statementLine == null || statementExists(statementLine))
            return;

        if (startDate == null || startDate.compareTo(statementLine.getDate()) > 0) {
            startDate = statementLine.getDate();
        }
        if (endDate == null || endDate.compareTo(statementLine.getDate()) < 0) {
            endDate = statementLine.getDate();
            currentBalance = statementLine.getAvailableBalance();
        }

        statements.add(statementLine);
    }

    private boolean statementExists(StatementLine statementLine)
    {
        for (StatementLine statement : statements) {
            if (statement.equals(statementLine)) {
                return true;
            }
        }

        return false;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setLastActivityDate(Date lastActivityDate)
    {
        this.lastActivityDate = lastActivityDate;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String additionalInfo()
    {
        return additionalInfo;
    }

    public double currentBalance()
    {
        return currentBalance;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }
    
    public double getCurrentBalance()
    {
        return currentBalance;
    }

    public List<StatementLine> statements()
    {
        return statements;
    }

    public abstract double getInterestRate();

    public abstract double estimatedAverageBalance();

}
