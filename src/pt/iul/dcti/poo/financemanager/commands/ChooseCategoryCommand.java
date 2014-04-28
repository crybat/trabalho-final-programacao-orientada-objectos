package pt.iul.dcti.poo.financemanager.commands;

import java.util.ArrayList;
import java.util.List;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.categories.CategoryIndex;
import pt.iul.dcti.poo.utils.Menu;

public class ChooseCategoryCommand implements Command {

    private final CategoryIndex categoryIndex;
    private final PersonalFinanceManager pfm;
    private Category chosenCategory;
    private String description;

    public ChooseCategoryCommand(PersonalFinanceManager pfm) {
        this.pfm = pfm;
        this.categoryIndex = pfm.getCategoryIndex();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getChosenCategory() {
        return chosenCategory;
    }

    @Override
    public void executeCommand() {

        chosenCategory = null;

        if (categoryIndex.getCategories().size() == 0) {
            insertCategory();
        } else {
            chooseCategory();
        }

        System.out.println(categoryIndex);
    }

    private void insertCategory() {
        String choice;
        while (chosenCategory == null) {
            choice = Menu.requestInput("Insert a category " + description);
            if (choice == null) {
                // se cancelou, sair
                new ExitCommand(pfm).executeCommand();
            } else {
                try {

                    if (categoryIndex.hasCategoryByName(choice)) {
                        chosenCategory = categoryIndex
                                .getCategoryByName(choice);
                    } else {
                        chosenCategory = new Category(choice);
                        categoryIndex.addCategory(chosenCategory);
                    }

                    chosenCategory.addDescription(description);

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    chosenCategory = null;
                }
            }
        }
    }

    private void chooseCategory() {
        String choice = Menu.requestSelection("Category for " + description,
                options());

        if (choice == null) {
            // Se cancelou, introduzir manualmente
            insertCategory();
        } else {
            chosenCategory = categoryIndex.getCategoryByName(choice);
            chosenCategory.addDescription(description);
        }
    }

    private String[] options() {
        List<String> options = new ArrayList<>();
        for (Category category : categoryIndex.getCategories())
            options.add(category.getName());
        return options.toArray(new String[options.size()]);
    }

}
