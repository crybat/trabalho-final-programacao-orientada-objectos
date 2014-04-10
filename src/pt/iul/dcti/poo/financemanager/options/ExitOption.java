package pt.iul.dcti.poo.financemanager.options;

public class ExitOption implements Option {

    private static final int STATUS_OK = 1;

    @Override
    public void executeOption() {
	System.exit(STATUS_OK);
    }

}
