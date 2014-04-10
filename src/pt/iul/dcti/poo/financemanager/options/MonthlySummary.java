package pt.iul.dcti.poo.financemanager.options;

import pt.iul.dcti.poo.financemanager.PersonalFinanceManager;

public class MonthlySummary implements Option {

    private PersonalFinanceManager pfm;
    
    public MonthlySummary(PersonalFinanceManager pfm)
    {
        this.pfm = pfm;
    }
    
    @Override
    public void executeOption()
    {
        
    }

}
