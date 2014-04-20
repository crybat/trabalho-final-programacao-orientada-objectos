package pt.iul.dcti.poo.financemanager.accounts;

public class SavingsAccount extends Account {

    public SavingsAccount(long id, String name) {
        super(id, name);
    }

    @Override
    public double estimatedAverageBalance() {
        // TODO ?
        return getCurrentBalance();
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.savingsInterestRate();
    }

}
