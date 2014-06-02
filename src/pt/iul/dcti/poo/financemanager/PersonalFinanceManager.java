package pt.iul.dcti.poo.financemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.SavingsAccount;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.accounts.statements.parsers.ScannerStatementLineParser;
import pt.iul.dcti.poo.financemanager.accounts.utils.AccountUtils;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.categories.CategoryIndex;
import pt.iul.dcti.poo.financemanager.commands.ChooseCategoryCommand;
import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;
import pt.iul.dcti.poo.financemanager.filters.NoCategorySelector;
import pt.iul.dcti.poo.financemanager.filters.StatementLineFilter;
import pt.iul.dcti.poo.persistence.Deserializer;

public class PersonalFinanceManager {

    private Map<String, Account> accounts = new HashMap<>();
    private CategoryIndex categoryIndex;

    public PersonalFinanceManager() {
        loadAccounts();
        loadCategoryIndex();
        fillInCategories();
    }

    /**
     * Devido à alteração na versão 3, e para não fazer isto tudo de novo, faço
     * o loop duas vezes...
     * 
     * @see {@link pt.iul.dcti.poo.financemanager.accounts.Account#autoCategorizeStatements(List)}
     */
    private void fillInCategories() {

        for (Account account : accounts.values()) {

            final StatementLineFilter filter = new StatementLineFilter(
                    new NoCategorySelector());
            NavigableSet<StatementLine> sttmts = (NavigableSet<StatementLine>) filter
                    .apply(account.getStatements());

            for (StatementLine statementLine : sttmts) {

                final ChooseCategoryCommand cco = new ChooseCategoryCommand(
                        this);
                String description = statementLine.getDescription();

                // encontrar ou criar categoria para a descrição
                Category category = findOrCreateCategoryForDescription(
                        description, cco, categoryIndex);

                // adicionar descrição à categoria
                category.addDescription(description);
                // relacionar com statement line
                // statementLine.setCategory(category); Tem que ser feito por
                // Account#autoCategorizeStatements ...

                if (!categoryIndex.hasCategory(category)) {
                    // actualização, é necessário imprimir categorias
                    categoryIndex.addCategory(category);
                    System.out.println(categoryIndex);
                }
            }
        }

        for (Account acc : accounts.values()) {
            acc.autoCategorizeStatements(categoryIndex.getCategories());
        }
    }

    private static Category findOrCreateCategoryForDescription(
            String description, ChooseCategoryCommand cmd, CategoryIndex ci) {

        if (ci.hasCategoryByDescription(description))
            // Descrição já existe no índice
            return ci.getCategoryByDescription(description);

        // Descrição não existe no índice
        cmd.setDescription(description);
        cmd.executeCommand();
        return cmd.getChosenCategory();
    }

    /**
     * Loads categories to the category index. Defaults to a new category index
     * with preset categories, if existant.
     */
    private void loadCategoryIndex() {
        try {
            List<Category> categories = new Deserializer<List<Category>>()
                    .deserialize(Configuration.getCategoriesFile());
            categoryIndex = new CategoryIndex(categories);
        } catch (ClassNotFoundException | IOException e) {
            categoryIndex = new CategoryIndex();
            categoryIndex.addCategory(SavingsAccount.savingsCategory);
        }
    }

    private void loadAccounts() {
        File accountsDir = new File(Configuration.getDirAccounts());
        File statementsDir = new File(Configuration.getDirStatements());

        for (File file : accountsDir.listFiles(accountFileNameFilter)) {
            setUpNewAccount(file, statementsDir);
        }

        for (File file : statementsDir.listFiles(accountFileNameFilter)) {
            String accId = null;
            try {
                accId = String.valueOf(AccountUtils.accountNumber(file));
                if (!accounts.containsKey(accId)) {
                    setUpNewAccount(file, statementsDir);
                }
            } catch (IOException e) {
                e.printStackTrace();
                /*System.out.println("Nao foi possivel ler '" + file.getName()
                        + "'");*/
            }
        }
    }

    private void setUpNewAccount(File file, File sttmts) {
        try {
            Account acc = Account.newAccount(file);
            accounts.put(String.valueOf(acc.getId()), acc);
            for (File sttmtsFile : sttmts.listFiles(PersonalFinanceManager.accountFileNameFilter)) {
                if (acc.getId() == AccountUtils.accountNumber(sttmtsFile))
                    ScannerStatementLineParser.populateAccount(acc, sttmtsFile);
            }
        } catch (ClassNotFoundException | IOException | ParseException
                | BadFormatException e) {
            e.printStackTrace();
            /*
            System.out.println("Ocorreu um erro ao processar o ficheiro '"
                    + file.getName() + "'");*/
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public CategoryIndex getCategoryIndex() {
        return categoryIndex;
    }

    public static final FilenameFilter accountFileNameFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".csv");
        }
    };

}
