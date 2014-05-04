package pt.iul.dcti.poo.financemanager.accounts;

public class SavingsAccount extends Account {

    public SavingsAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
        return getCurrentBalance();
    }

    @Override
    public double yearlyInterestEstimate() {
        return getCurrentBalance() * getInterestRate();
    }

}
