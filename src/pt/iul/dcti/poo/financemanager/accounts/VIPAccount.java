package pt.iul.dcti.poo.financemanager.accounts;

public class VIPAccount extends Account {

    private double interestRate;

    public VIPAccount(long id, String name, double interestRate) {
        super(id, name);
        setInterestRate(interestRate);
    }

    private void setInterestRate(double interestRate)
            throws IllegalArgumentException {
        validateInterestRate(interestRate);
        this.interestRate = interestRate;
    }

    private void validateInterestRate(double interestRate)
            throws IllegalArgumentException {
        if (interestRate < 0.0)
            throw new IllegalArgumentException(
                    "Interest Rate must be positive!");
    }

    @Override
    public double estimatedAverageBalance() {
        return getCurrentBalance();
    }

    @Override
    public double yearlyInterestEstimate() {
        return getCurrentBalance() * getInterestRate();
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }
}
