package pt.iul.dcti.poo.financemanager.accounts;

public class VIPAccount extends Account {

    public VIPAccount(long id, String name) {
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
