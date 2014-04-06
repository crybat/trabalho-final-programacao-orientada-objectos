package pt.iul.dcti.poo.financemanager.accounts.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.AccountFactory;
import pt.iul.dcti.poo.financemanager.date.Date;

public class FileAccountParser implements AccountParser<File> {

    private static final String     SPLIT_CHARACTER = ";";
    private static final DateFormat DF              = new SimpleDateFormat(
                                                            Account.DATE_FORMAT);

    @Override
    public Account parseAccount(File data) throws IOException, ParseException
    {
        BufferedReader r = new BufferedReader(new FileReader(data));
        String lastActivity = r.readLine();
        String[] header = r.readLine().split(SPLIT_CHARACTER);
        String[] startDate = r.readLine().split(SPLIT_CHARACTER);
        String[] endDate = r.readLine().split(SPLIT_CHARACTER);
        r.close();

        
        Account acc = new AccountFactory().createAccount(header[4].trim(),
                Long.parseLong(header[1].trim()), header[3].trim());
        acc.setCurrency(header[2].trim());
        lastActivity = lastActivity.substring(
                lastActivity.indexOf('-') + 2, lastActivity.length());
        
        acc.setEndDate(new Date(DF.parse(endDate[1])));
        acc.setStartDate(new Date(DF.parse(startDate[1])));
        acc.setLastActivityDate(new Date(DF.parse(lastActivity)));
        
        return acc;
    }

}
