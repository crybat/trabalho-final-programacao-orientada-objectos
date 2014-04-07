package pt.iul.dcti.poo.financemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import pt.iul.dcti.poo.financemanager.accounts.Account;

public class PersonalFinanceManager {
    
    private Map<Long, Account> accounts = new HashMap<>();
    
    public PersonalFinanceManager() throws IOException, ParseException
    {
        loadAccounts();
    }
    
    private void loadAccounts() throws IOException, ParseException
    {
        File accountsDir = new File(Configuration.DIR_ACCOUNTS);
        
        if (!accountsDir.canRead()) return;
        
        for (File file : accountsDir.listFiles(accountFileNameFilter)) {
            Account acc = Account.newAccount(file);
            accounts.put(acc.getId(), acc);
        }
    }
    
    public Map<Long, Account> getAccounts()
    {
        return accounts;
    }
    
    private static final FilenameFilter accountFileNameFilter = new FilenameFilter() {
        
        @Override
        public boolean accept(File dir, String name)
        {
            return name.endsWith(".csv");
        }
    };
    
}
