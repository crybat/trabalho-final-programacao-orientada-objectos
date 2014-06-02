package pt.iul.dcti.poo.financemanager.categories;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pt.iul.dcti.poo.persistence.Deserializer;
import pt.iul.dcti.poo.persistence.Serializer;

public class Category implements Serializable {
    
    private String name;
    private Set<String> descriptions = new HashSet<>();

    public static List<Category> readCategories(String fileName)
            throws ClassNotFoundException, IOException {
        return new Deserializer<List<Category>>().deserialize(fileName);
    }

    public static void saveCategories(List<Category> categories, String fileName)
            throws IOException {
        new Serializer<List<Category>>().serialize(categories, fileName);
    }

    public Category(String name) throws IllegalArgumentException {
        setName(name);
    }

    private void setName(String name) throws IllegalArgumentException {
        validateName(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Set<String> getDescriptions() {
        return descriptions;
    }

    public void addDescription(String description)
            throws IllegalArgumentException {
        validateDescription(description);
        descriptions.add(description.toUpperCase());
    }

    public boolean hasDescription(String description) {
        return descriptions.contains(description.toUpperCase());
    }

    /*
     * VALIDATION
     */

    private void validateState() throws IllegalArgumentException {
        validateDescriptions(descriptions);
        validateName(name);
    }

    private void validateDescriptions(Set<String> descriptions)
            throws IllegalArgumentException {
        for (String description : descriptions)
            validateDescription(description);
    }

    private void validateDescription(String description)
            throws IllegalArgumentException {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Description must not be empty");
    }

    private void validateName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name must not be empty");
    }

    /*
     * SERIALIZATION
     */
    private static final long serialVersionUID = 7379892715140836581L;
    
    
    private void readObject(ObjectInputStream inputStream)
            throws ClassNotFoundException, IOException {
        inputStream.defaultReadObject();
        validateState();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        
        Category o = (Category) obj;
        
        return name.equals(o.name);
    }

}
