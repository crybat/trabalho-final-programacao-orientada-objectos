package pt.iul.dcti.poo.financemanager.categories;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CategoryIndex {

    private Set<Category> categories;

    public CategoryIndex() {
        categories = new HashSet<>();
    }

    public CategoryIndex(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public Set<Category> getCategories() {
        return categories;
    }

    /*
     * SEARCHES
     */

    public boolean hasCategoryByDescription(String description) {

        for (Category category : categories)
            if (category.hasDescription(description))
                return true;

        return false;
    }

    public boolean hasCategoryByName(String name)
            throws IllegalArgumentException {
        return categories.contains(new Category(name));
    }

    /*
     * GETTERS
     */

    public Category getCategoryByDescription(String description)
            throws NoSuchElementException {

        for (Category category : categories)
            if (category.hasDescription(description))
                return category;

        throw new NoSuchElementException("No category found for description: "
                + description);
    }

    public Category getCategoryByName(String name)
            throws IllegalArgumentException, NoSuchElementException {

        Category c = new Category(name);
        for (Category category : categories)
            if (c.equals(category))
                return category;

        throw new NoSuchElementException("No category found for name: " + name);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder().append("[ ");

        for (Category category : categories) {
            b.append(category.getName()).append(", ");
        }

        int comaIndex = b.lastIndexOf(",");
        b.delete(comaIndex, comaIndex + 1).append("]");

        return b.toString();
    }

}
