package pt.iul.dcti.poo.comands;

import java.util.HashMap;
import java.util.Map;

public class CommandReceiver<K, V extends Command> {

    private Map<K, V> options;

    public CommandReceiver(Map<K, V> options) {
        this.options = options;
    }

    public CommandReceiver() {
        options = new HashMap<>();
    }

    public void executeCommand(K key) {
        if (options.containsKey(key))
            options.get(key).executeCommand();
    }

    public void putCommand(K key, V option) {
        options.put(key, option);
    }

}
