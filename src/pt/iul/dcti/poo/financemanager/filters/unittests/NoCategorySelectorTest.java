package pt.iul.dcti.poo.financemanager.filters.unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.NoCategorySelector;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class NoCategorySelectorTest {

    private static StatementLine stt1;
    private static StatementLine stt2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        stt1 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, new Category("A"));
        stt2 = new StatementLine(new Date(2, 1, 2014), new Date(2, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
    }

    @Test
    public void testIsSelected() {
        NoCategorySelector selector = new NoCategorySelector();
        assertFalse(selector.isSelected(stt1));
        assertTrue(selector.isSelected(stt2));

    }

}
