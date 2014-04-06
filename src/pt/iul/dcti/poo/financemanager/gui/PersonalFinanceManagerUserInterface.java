package pt.iul.dcti.poo.financemanager.gui;

import pt.iul.dcti.poo.financemanager.Configuration;
import pt.iul.dcti.poo.financemanager.options.Option;
import pt.iul.dcti.poo.financemanager.options.OptionManager;
import pt.iul.dcti.poo.utils.Menu;

public class PersonalFinanceManagerUserInterface {

    public PersonalFinanceManagerUserInterface(
            OptionManager<String, Option> options)
    {
        this.options = options;
    }

    public static final String             OPT_GLOBAL_POSITION         = "Posição Global";
    public static final String             OPT_ACCOUNT_STATEMENT       = "Movimentos Conta";
    public static final String             OPT_LIST_CATEGORIES         = "Listar categorias";
    public static final String             OPT_ANALISE                 = "Análise";
    public static final String             OPT_EXIT                    = "Sair";

    private static final String            OPT_MONTHLY_SUMMARY         = "Evolução global por mês";
    private static final String            OPT_PREDICTION_PER_CATEGORY = "Previsão gastos totais do mês por categoria";
    private static final String            OPT_ANUAL_INTEREST          = "Previsão juros anuais";

    @SuppressWarnings("unused")
    private static final String[]          OPTIONS_ANALYSIS            = {
            OPT_MONTHLY_SUMMARY, OPT_PREDICTION_PER_CATEGORY,
            OPT_ANUAL_INTEREST                                        };

    private static final String[]          OPTIONS                     = {
            OPT_GLOBAL_POSITION, OPT_ACCOUNT_STATEMENT, OPT_LIST_CATEGORIES,
            OPT_ANALISE, OPT_EXIT                                     };

    private OptionManager<String, Option> options;

    public void execute()
    {
        while (true) {
            options.executeOption(Menu.requestSelection(Configuration.APP_TITLE, OPTIONS));
        }
    }

}
