package pt.iul.dcti.poo.financemanager;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import pt.iul.dcti.poo.financemanager.accounts.Account;
import pt.iul.dcti.poo.financemanager.accounts.statements.StatementLine;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.categories.CategoryIndex;
import pt.iul.dcti.poo.financemanager.commands.ChooseCategoryCommand;
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

    /*
     * Pelo teste testNewStatementLine nao podemos preencher a categoria, e
     * temos que usar os filtros
     */
    private void fillInCategories() {

        for (Account account : accounts.values()) {

            final StatementLineFilter filter = new StatementLineFilter(
                    new NoCategorySelector());
            SortedSet<StatementLine> sttmts = (SortedSet<StatementLine>) filter
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
                statementLine.setCategory(category);

                if (!categoryIndex.hasCategory(category)) {
                    // actualização, é necessário imprimir categorias
                    categoryIndex.addCategory(category);
                    System.out.println(categoryIndex);
                }
            }
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

    private void loadCategoryIndex() {
        try {
            Set<Category> categories = new Deserializer<Set<Category>>()
                    .deserialize(Configuration.getCategoriesFile());
            categoryIndex = new CategoryIndex(categories);
        } catch (ClassNotFoundException | IOException e) {
            categoryIndex = new CategoryIndex();
        }
    }

    private void loadAccounts() {
        File accountsDir = new File(Configuration.getDirAccounts());

        for (File file : accountsDir.listFiles(accountFileNameFilter)) {
            Account acc;
            try {
                acc = Account.newAccount(file);
                accounts.put(String.valueOf(acc.getId()), acc);
            } catch (ClassNotFoundException | IOException | ParseException e) {
                System.out.println("Ocorreu um erro ao processar o ficheiro '"
                        + file.getName() + "'");
                // e.printStackTrace();
            }
        }
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public CategoryIndex getCategoryIndex() {
        return categoryIndex;
    }

    private static final FilenameFilter accountFileNameFilter = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".csv");
        }
    };

}
