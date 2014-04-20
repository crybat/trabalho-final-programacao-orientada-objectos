package pt.iul.dcti.poo.financemanager;

import java.io.File;

public class Configuration {

    private static final String DIR_ACCOUNTS = "account_info" + File.separator;
    private static final String DIR_STATEMENTS = "statements" + File.separator;
    private static final String APP_TITLE = "Personal Finance Manager";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static String getAppTitle() {
	return APP_TITLE;
    }

    public static String getDirAccounts() {
	return DIR_ACCOUNTS;
    }

    public static String getDirStatements() {
	return DIR_STATEMENTS;
    }

    public static String getDateFormat() {
	return DATE_FORMAT;
    }

}
