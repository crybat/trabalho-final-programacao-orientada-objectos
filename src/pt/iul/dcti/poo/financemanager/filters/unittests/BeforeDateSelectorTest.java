package pt.iul.dcti.poo.financemanager.filters.unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.filters.BeforeDateSelector;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class BeforeDateSelectorTest {

    private static StatementLine stt1;
    private static StatementLine stt2;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	stt1 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
		"description", 0.0, 22, 1520, 1542, new Category("A"));
	stt2 = new StatementLine(new Date(1, 2, 2014), new Date(1, 2, 2014),
		"description", 0.0, 22, 1520, 1542, null);
    }

    @Test
    public void testIsSelected() {
	BeforeDateSelector selector = new BeforeDateSelector(new Date(15, 1,
		2014));
	assertTrue(selector.isSelected(stt1));
	assertFalse(selector.isSelected(stt2));

    }
}
