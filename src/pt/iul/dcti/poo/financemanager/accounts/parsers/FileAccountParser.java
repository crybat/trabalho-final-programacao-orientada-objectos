package pt.iul.dcti.poo.financemanager.accounts.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.AccountFactory;

public class FileAccountParser implements AccountParser<File> {

    private static final String SPLIT_STRING = ";";

    @Override
    public Account parseAccount(File data) throws IOException, ParseException,
            ClassNotFoundException {

        BufferedReader r = new BufferedReader(new FileReader(data));
        r.readLine();
        String[] header = r.readLine().split(SPLIT_STRING);
        r.close();

        Account acc = new AccountFactory().createAccount(header[4].trim(),
                Long.parseLong(header[1].trim()), header[3].trim());
        acc.setCurrency(header[2].trim());

        return acc;
    }

}
