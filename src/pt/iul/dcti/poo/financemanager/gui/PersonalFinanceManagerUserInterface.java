package pt.iul.dcti.poo.financemanager.gui;

import pt.iul.dcti.poo.comands.Command;
import pt.iul.dcti.poo.comands.CommandReceiver;
import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.utils.Menu;

public class PersonalFinanceManagerUserInterface {

    public static final String OPT_GLOBAL_POSITION = "Posi��o Global";
    public static final String OPT_ACCOUNT_STATEMENT = "Movimentos Conta";
    public static final String OPT_LIST_CATEGORIES = "Listar categorias";
    public static final String OPT_ANALISE = "An�lise";
    public static final String OPT_EXIT = "Sair";

    public static final String OPT_MONTHLY_SUMMARY = "Evolu��o global por m�s";
    public static final String OPT_PREDICTION_PER_CATEGORY = "Previs�o gastos totais do m�s por categoria";
    public static final String OPT_ANUAL_INTEREST = "Previs�o juros anuais";

    public static final String[] OPTIONS_ANALYSIS = { OPT_MONTHLY_SUMMARY,
            OPT_PREDICTION_PER_CATEGORY, OPT_ANUAL_INTEREST };

    private final String[] OPTIONS = { OPT_GLOBAL_POSITION,
            OPT_ACCOUNT_STATEMENT, OPT_LIST_CATEGORIES, OPT_ANALISE, OPT_EXIT };

    private CommandReceiver<String, Command> opts;

    public PersonalFinanceManagerUserInterface(
            CommandReceiver<String, Command> opts) {
        this.opts = opts;
    }

    public void execute() {
        while (true) {
            opts.executeCommand(Menu.requestSelection(
                    Configuration.getAppTitle(), OPTIONS));
        }
    }

}
