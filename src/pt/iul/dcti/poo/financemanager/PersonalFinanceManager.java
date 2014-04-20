package pt.iul.dcti.poo.financemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class PersonalFinanceManager {

    private Map<String, Account> accounts = new HashMap<>();

    public PersonalFinanceManager() {
        loadAccounts();
    }

    private void loadAccounts() {
        File accountsDir = new File(Configuration.getDirAccounts());

        for (File file : accountsDir.listFiles(accountFileNameFilter)) {
            Account acc;
            try {
                acc = Account.newAccount(file);
                accounts.put(String.valueOf(acc.getId()), acc);
            } catch (IOException | ParseException e) {
                System.out.println("Ocorreu um erro ao processar o ficheiro '"
                        + file.getName() + "'");
                // e.printStackTrace();
            }
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    private static final FilenameFilter accountFileNameFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".csv");
        }
    };

}
