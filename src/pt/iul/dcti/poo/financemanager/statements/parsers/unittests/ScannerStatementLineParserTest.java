package pt.iul.dcti.poo.financemanager.statements.parsers.unittests;

import static org.junit.Assert.assertTrue;

import java.io.EOFException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import pt.iul.dcti.poo.financemanager.exceptions.BadFormatException;

public class ScannerStatementLineParserTest {

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testNewStatementLine() throws EOFException, ParseException,
	    BadFormatException {
	/*String pattern = "(\\d{2}-\\d{2}-\\d{4})\\s*;" // Date
	    	+ "(\\d{2}-\\d{2}-\\d{4})\\s*;" // Value Date
	    	+ "(\\w)*\\s*;" // Description
	    	+ "(-\\d+)*\\s*;" // Draft
	    	+ "(\\d+)*\\s*;" // Credit
	    	+ "(-\\d+)*\\s*;" // Accounting balance
	    	+ "(-\\d+)*\\s*;"; // Available Balance
	String in = "03-02-2014;03-02-2014;LOW VALUE ;-150.65;333.5;3301.52;3301.52";*/
	Pattern pattern = Pattern.compile("(\\d{2}-\\d{2}-\\d{4})\\s*;"
		+ "(\\d{2}-\\d{2}-\\d{4})\\s*;"
		+ "([\\w\\s]*);"
		+ "(-\\d+\\.?\\d*)*\\s*;"
		+ "(\\d+\\.?\\d*)*\\s*;"
		+ "(-?\\d+\\.?\\d*)*\\s*;"
		+ "(-?\\d+\\.?\\d*)*\\s*");
	String in = "03-02-2014;03-02-2014;LOW VALUE ;-150.65;;3301.52;3301.52";
	Matcher m = pattern.matcher(in);
	assertTrue(m.matches());
	System.out.println(m.group(5));
    }

}
