package pt.iul.dcti.poo.persistence.unittests;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.persistence.Serializer;

public class SerializerTest {
    
    private static final String TEST_DIR = "testAssets";
    private static final String TEST_FILE = TEST_DIR + File.separator + "categoriesSerialization";
    
    @Before
    public void setUp() throws Exception {
        File dir = new File(TEST_DIR);
        if (!dir.exists())
            dir.mkdir();
    }

    @Test
    public void serialization() throws IOException {
        Serializer<List<Category>> s = new Serializer<>();
        List<Category> categories = new LinkedList<>();
        categories.add(new Category("Something"));
        categories.add(new Category("Something else"));
        categories.get(1).addDescription("A description");
        categories.get(1).addDescription("Another Description");
        s.serialize(categories, TEST_FILE);
    }

}
