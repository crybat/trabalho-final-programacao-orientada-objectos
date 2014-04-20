package pt.iul.dcti.poo.financemanager.statements.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.exceptions.BadDate;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

public class ScannerStatementLineParser implements StatementLineParser<Scanner> {

    private static final String DELIMITER = ";";
    private static final String PATTERN = "(\\d{2}-\\d{2}-\\d{4})\\s*;" // Date
    	+ "(\\d{2}-\\d{2}-\\d{4})\\s*;" // Value Date
    	+ "(\\w)*\\s*;" // Description
    	+ "(-\\d+)*\\s*;" // Draft
    	+ "(\\d+)*\\s*;" // Credit
    	+ "(-\\d+)*\\s*;" // Accounting balance
    	+ "(-\\d+)*\\s*;"; // Available Balance
    private static final DateFormat DATE_PARSER = new SimpleDateFormat(
	    Configuration.getDateFormat());

    public static void populateAccount(Account acc, File file)
	    throws IOException {
	ScannerStatementLineParser p = new ScannerStatementLineParser();
	BufferedReader r = new BufferedReader(new FileReader(file));
	String line;

	while ((line = r.readLine()) != null) {
	    try {
		Scanner s = new Scanner(line);
		StatementLine sttmt = p.parseStatementLine(s);
		acc.addStatementLine(sttmt);
	    } catch (BadDate | BadFormatException | NoSuchElementException
		    | IllegalStateException e) {
		// System.out.println("Didn't process: \n" + line);
	    }
	}

	r.close();
    }

    private static String next(Scanner s) {
	return s.hasNext() ? s.next().trim() : "";
    }

    @Override
    public StatementLine parseStatementLine(Scanner line)
	    throws BadFormatException {
	line.useDelimiter(DELIMITER);
	String token;

	Date date;
	Date dateValue;
	try {
	    // Date
	    token = next(line);
	    date = new Date(DATE_PARSER.parse(token));

	    // Value Date
	    token = next(line);
	    dateValue = new Date(DATE_PARSER.parse(token));
	} catch (ParseException e) {
	    throw new BadDate();
	}

	// Description
	token = next(line);
	if (token.isEmpty())
	    throw new BadFormatException();
	String description = token;

	Double draft;
	Double credit;
	Double accountingBalance;
	Double availableBalance;
	try {
	    // Draft
	    token = next(line);
	    draft = token.isEmpty() ? 0.0 : Double.parseDouble(token);

	    // Credit
	    token = next(line);
	    credit = token.isEmpty() ? 0.0 : Double.parseDouble(token);

	    // Accounting Balance
	    token = next(line);
	    accountingBalance = token.isEmpty() ? 0.0 : Double
		    .parseDouble(token);

	    // Credit
	    token = next(line);
	    availableBalance = token.isEmpty() ? 0.0 : Double
		    .parseDouble(token);
	} catch (NumberFormatException e) {
	    throw new BadFormatException();
	}

	return new StatementLine(date, dateValue, description, draft, credit,
		accountingBalance, availableBalance, null);
    }

}
