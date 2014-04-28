package pt.iul.dcti.poo.financemanager.accounts.statements.parsers.unittests;

import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class ScannerStatementLineParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testNewStatementLine() {
        Pattern pattern = Pattern.compile("(\\d{2}-\\d{2}-\\d{4})\\s*;"
                + "(\\d{2}-\\d{2}-\\d{4})\\s*;" + "([\\w\\s]*);"
                + "(-\\d+\\.?\\d*)*\\s*;" + "(\\d+\\.?\\d*)*\\s*;"
                + "(-?\\d+\\.?\\d*)*\\s*;" + "(-?\\d+\\.?\\d*)*\\s*;?");
        String in = "03-02-2014;03-02-2014;LOW VALUE ;-150.65;;3301.52;3301.52";
        Matcher m = pattern.matcher(in);
        assertTrue(m.matches());
    }

}
