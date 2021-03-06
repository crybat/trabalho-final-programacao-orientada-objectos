package pt.iul.dcti.poo.financemanager.accounts.statements;

import java.text.ParseException;
import java.util.Scanner;

import pt.iul.dcti.poo.financemanager.accounts.formats.LongStatementFormat;
import pt.iul.dcti.poo.financemanager.accounts.formats.StatementLineFormat;
import pt.iul.dcti.poo.financemanager.accounts.statements.parsers.ScannerStatementLineParser;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class StatementLine implements Comparable<StatementLine> {

    private static final StatementLineFormat formatter = new LongStatementFormat();

    public static StatementLine newStatementLine(Scanner s)
            throws ParseException, BadFormatException {
        return new ScannerStatementLineParser().parseStatementLine(s);
    }

    private Date date;
    private Date valueDate;
    private String description;
    private double draft;
    private double credit;
    private double accountingBalance;
    private double availableBalance;
    private Category category;

    public StatementLine(Date date, Date valueDate, String description,
            double draft, double credit, double accountingBalance,
            double availableBalance, Category category)
            throws IllegalArgumentException {
        setDate(date);
        setValueDate(valueDate);
        setDescription(description);
        setDraft(draft);
        setCredit(credit);
        setAccountingBalance(accountingBalance);
        setAvailableBalance(availableBalance);
        setCategory(category);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(Date date) throws IllegalArgumentException {
        validateDate(date);
        this.date = date;
    }

    public void setValueDate(Date valueDate) throws IllegalArgumentException {
        validateValueDate(valueDate);
        this.valueDate = valueDate;
    }

    public void setDescription(String description)
            throws IllegalArgumentException {
        validateDescription(description);
        this.description = description.trim();
    }

    private void setCredit(double credit) throws IllegalArgumentException {
        validateCredit(credit);
        this.credit = credit;
    }

    private void setDraft(double draft) throws IllegalArgumentException {
        validateDraft(draft);
        this.draft = draft;
    }

    private void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    private void setAccountingBalance(double accountingBalance) {
        this.accountingBalance = accountingBalance;
    }

    /*
     * GETTERS
     */

    public Date getDate() {
        return date;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public String getDescription() {
        return description;
    }

    public double getCredit() {
        return credit;
    }

    public double getDraft() {
        return draft;
    }

    public double getAccountingBalance() {
        return accountingBalance;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public Category getCategory() {
        return category;
    }

    /**
     * UNIQUITY
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(accountingBalance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(availableBalance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(credit);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        temp = Double.doubleToLongBits(draft);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result
                + ((valueDate == null) ? 0 : valueDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        StatementLine o = (StatementLine) obj;

        return accountingBalance == o.accountingBalance
                && availableBalance == o.availableBalance && credit == o.credit
                && draft == o.draft && date.compareTo(o.date) == 0
                && valueDate.compareTo(o.valueDate) == 0
                && description.equals(o.description);
    }

    @Override
    public int compareTo(StatementLine o) {
        if (equals(o)) {
            return 0;
        }

        if (valueDate.compareTo(o.valueDate) > 0)
            return valueDate.compareTo(o.valueDate);
        else if (date.compareTo(o.date) > 0)
            return date.compareTo(o.date);
        else if (Math.abs(o.getAvailableBalance() - (getAvailableBalance() + o.credit + o.draft)) < 0.001) {
            return -1;
        }
        
        return 1;
    }

    /*
     * VALIDATION
     */

    private void validateCredit(double credit) throws IllegalArgumentException {
        if (credit < 0.0)
            throw new IllegalArgumentException("Credit must be positive");
    }

    private void validateDraft(double draft) throws IllegalArgumentException {
        if (draft > 0.0)
            throw new IllegalArgumentException("Draft must be negative");
    }

    private void validateDescription(String description)
            throws IllegalArgumentException {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException(
                    "Description must be a non empty String");
    }

    private void validateDate(Date date) throws IllegalArgumentException {
        if (date == null)
            throw new IllegalArgumentException("Date must not be null");
    }

    private void validateValueDate(Date valueDate)
            throws IllegalArgumentException {
        if (valueDate == null)
            throw new IllegalArgumentException("Value Date must not be null");
    }

    @Override
    public String toString() {
        return formatter.format(this);
    }

}
