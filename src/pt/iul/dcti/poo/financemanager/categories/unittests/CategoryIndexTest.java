package pt.iul.dcti.poo.financemanager.categories.unittests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.categories.CategoryIndex;

public class CategoryIndexTest {

    private CategoryIndex ci;
    private Category c;
    
    @Before
    public void setUp() throws Exception {
        ci = new CategoryIndex();
        c = new Category("HOME");
    }

    @Test
    public void hasCategoryByName() {
        ci.addCategory(c);
        assertTrue(ci.hasCategoryByName("HOME"));
    }

}
