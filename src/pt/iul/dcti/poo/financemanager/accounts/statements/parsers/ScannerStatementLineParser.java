package pt.iul.dcti.poo.financemanager.accounts.statements.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class ScannerStatementLineParser implements StatementLineParser<Scanner> {

    private static final Pattern PATTERN = Pattern
            .compile("(\\d{2}-\\d{2}-\\d{4})\\s*;" // Date
                    + "(\\d{2}-\\d{2}-\\d{4})\\s*;" // Value Date
                    + "([\\w\\s]+);" // Description
                    + "(-\\d+\\.?\\d*|0\\.?0?)*\\s*;" // Draft
                    + "(\\d+\\.?\\d*)*\\s*;" // Credit
                    + "(-?\\d+\\.?\\d*)*\\s*;" // Accounting balance
                    + "(-?\\d+\\.?\\d*)*\\s*;?" // Available Balance
            );
    private static final DateFormat DATE_PARSER = new SimpleDateFormat(
            Configuration.getDateFormat());

    public static void populateAccount(Account acc, File file) {

        ScannerStatementLineParser p = new ScannerStatementLineParser();

        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = r.readLine()) != null) {
                Scanner s = new Scanner(line);
                StatementLine sttmt;
                try {
                    sttmt = p.parseStatementLine(s);
                    acc.addStatementLine(sttmt);
                } catch (BadFormatException | ParseException e) {
                }
            }
        } catch (IOException e) {
        }
    }

    private static double parseMoney(String money) {
        return money == null ? 0.0 : Double.parseDouble(money);
    }

    @Override
    public StatementLine parseStatementLine(Scanner line)
            throws BadFormatException, ParseException {

        if (line.findInLine(PATTERN) == null) {
            throw new BadFormatException();
        }

        MatchResult m = line.match();

        Date date = new Date(DATE_PARSER.parse(m.group(1)));
        Date dateValue = new Date(DATE_PARSER.parse(m.group(2)));
        String description = m.group(3).trim();
        Double draft = parseMoney(m.group(4));
        Double credit = parseMoney(m.group(5));
        Double accountingBalance = parseMoney(m.group(6));
        Double availableBalance = parseMoney(m.group(7));

        return new StatementLine(date, dateValue, description, draft, credit,
                accountingBalance, availableBalance, null);
    }

}
