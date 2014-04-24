package pt.iul.dcti.poo.financemanager.filters;

import java.util.Collection;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class Filter<T, F extends Selector<T>> {
    F selector;

    protected Filter() {

    }

    public Filter(F selector) {
        this.selector = selector;
    }

    protected void setSelector(F selector) {
        this.selector = selector;
    }

    @SuppressWarnings("unchecked")
    public Collection<T> apply(Collection<T> collection) {
        Collection<T> selected = null;
        try {
            selected = collection.getClass().newInstance();
            for (T item : collection) {
                if (selector.isSelected(item))
                    selected.add(item);
            }
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return selected;
    }

    // public List<T> apply(List<T> list) {
    // //List<T> selected = newList();
    // List<T> selected = new LinkedList<T>();
    // for (T item: list) {
    // if (getSelector().isSelected(item)) {
    // selected.add(item);
    // }
    // }
    // return selected;
    // }

    // protected abstract List<T> newList();

    protected Selector<T> getSelector() {
        return selector;
    }
}
