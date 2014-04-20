package pt.iul.dcti.poo.financemanager.accounts.formats.unittests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.accounts.formats.LongStatementFormat;
import pt.iul.dcti.poo.financemanager.categories.Category;
import pt.iul.dcti.poo.financemanager.date.Date;
import pt.iul.dcti.poo.financemanager.statements.StatementLine;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class LongStatementFormatTest {

    private StatementLine s1;
    private StatementLine s2;
    private LongStatementFormat f;

    @Before
    public void setUp() throws Exception {
        s1 = new StatementLine(new Date(1, 1, 2014), new Date(1, 1, 2014),
                "description", 0.0, 22, 1520, 1542, null);
        s2 = new StatementLine(new Date(2, 1, 2014), new Date(3, 1, 2014),
                "description ...", -10.0, 220, 1500, 1730, new Category(
                        "CATEGORY"));
        f = new LongStatementFormat();
    }

    @Test
    public void testFormat() {
        assertEquals(f.format(s1),
                "01-01-2014 \t01-01-2014 \tdescription \t0.0 \t22.0 \t1520.0 \t1542.0");
        assertEquals(f.format(s2),
                "02-01-2014 \t03-01-2014 \tdescription ... \t-10.0 \t220.0 \t1500.0 \t1730.0");
    }

    @Test
    public void testFields() {
        assertEquals(
                f.fields(),
                "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance ");
    }

}
