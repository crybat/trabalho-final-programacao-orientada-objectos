package pt.iul.dcti.poo.financemanager.options;

import java.util.HashMap;
import java.util.Map;

public class OptionManager<T, O extends Option> {

    private Map<T, O> options;

    public OptionManager(Map<T, O> options) {
	this.options = options;
    }

    public OptionManager() {
	options = new HashMap<>();
    }

    public void executeOption(T key) {
	if (options.containsKey(key)) {
	    options.get(key).executeOption();
	} else {
	    System.out.println("'" + key + "' not implemented yet!");
	}
    }

    public void put(T key, O option) {
	options.put(key, option);
    }

}
