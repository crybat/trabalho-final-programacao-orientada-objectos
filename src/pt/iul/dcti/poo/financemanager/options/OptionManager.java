package pt.iul.dcti.poo.financemanager.options;

import java.util.HashMap;
import java.util.Map;

public class OptionManager<K, V extends Option> {

    private Map<K, V> options;

    public OptionManager(Map<K, V> options) {
	this.options = options;
    }

    public OptionManager() {
	options = new HashMap<>();
    }

    public void executeOption(K key) {
	if (options.containsKey(key)) {
	    options.get(key).executeOption();
	} else {
	    System.out.println("'" + key + "' not implemented yet!");
	}
    }

    public void put(K key, V option) {
	options.put(key, option);
    }

}
