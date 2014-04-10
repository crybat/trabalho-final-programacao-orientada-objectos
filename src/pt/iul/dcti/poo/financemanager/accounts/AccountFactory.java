package pt.iul.dcti.poo.financemanager.accounts;

public class AccountFactory {

    public Account createAccount(String type, long number, String name) {
	switch (type) {
	case "DraftAccount":
	    return new DraftAccount(number, name);

	case "SavingsAccount":
	    return new SavingsAccount(number, name);

	case "VIPAccount":
	    return new VIPAccount(number, name);
	}

	return null;
    }

}
