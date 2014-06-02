package pt.iul.dcti.poo.financemanager.accounts.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.DraftAccount;
import pt.iul.dcti.poo.financemanager.accounts.SavingsAccount;
import pt.iul.dcti.poo.financemanager.accounts.VIPAccount;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class AccountUtils {

    private static final Pattern ACCOUNT_HEADER_PATTERN = Pattern
            .compile("Account\\s*;\\s*" + "(\\d+)\\s*;\\s*" // id
                    + "(\\w{3})\\s*;\\s*" // Currency
                    + "(\\w+)\\s*;\\s*" // Nome
                    + "(\\w+)\\s*;?\\s*" // Type
                    + "(\\d+\\.?\\d*)?\\s*;?" // interest rate
            );

    public static long accountNumber(File f) throws IOException {
        MatchResult m = findAccountInfo(f);

        if (m == null)
            throw new IOException("Couldn't find account info for "
                    + f.getName());

        return Long.parseLong(m.group(1));
    }

    public static Account parseAccount(File f) throws IOException,
            ParseException, ClassNotFoundException, BadFormatException {

        MatchResult m = findAccountInfo(f);

        if (m == null)
            throw new IOException("Couldn't find account info for "
                    + f.getName());

        Account acc = null;
        long id = Long.parseLong(m.group(1).trim());
        String name = m.group(3).trim();

        switch (m.group(4).trim()) {
        case "DraftAccount":
            acc = new DraftAccount(id, name);
            break;

        case "SavingsAccount":
            acc = new SavingsAccount(id, name);
            break;

        case "VIPAccount":
            if (m.group(5) == null)
                throw new BadFormatException();
            acc = new VIPAccount(id, name, Double.parseDouble(m.group(5)));
            acc.setAdditionalInfo(m.group(5));
            break;
        }

        if (acc == null)
            throw new ClassNotFoundException("Account type not found");

        acc.setCurrency(m.group(2));

        return acc;
    }

    public static MatchResult findAccountInfo(File f) throws IOException {
        MatchResult m = null;
        BufferedReader r = new BufferedReader(new FileReader(f));
        String line;

        while ((line = r.readLine()) != null) {
            Matcher mt = ACCOUNT_HEADER_PATTERN.matcher(line);
            if (mt.find()) {
                m = mt.toMatchResult();
                break;
            }
        }
        r.close();

        return m;
    }

}
