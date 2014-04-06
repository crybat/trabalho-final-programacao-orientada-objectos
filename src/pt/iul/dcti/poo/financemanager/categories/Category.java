package pt.iul.dcti.poo.financemanager.categories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Category {

    private String name;
    private Set<String> descriptions = new HashSet<>();
    
    public Category(String name)
    {
        this.name = name;
    }

    public static List<Category> readCategories(String fileName)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasDescription(String description)
    {
        return descriptions.contains(description);
    }

    public void addDescription(String description)
    {
        descriptions.add(description);
    }

    public String getName()
    {
        return name;
    }

}
