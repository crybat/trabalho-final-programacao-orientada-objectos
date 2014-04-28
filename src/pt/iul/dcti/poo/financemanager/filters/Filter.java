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

    protected Selector<T> getSelector() {
        return selector;
    }

    @SuppressWarnings("unchecked")
    public Collection<T> apply(Collection<T> list) {
        Collection<T> selected = null;
        try {
            selected = list.getClass().newInstance();
            for (T item : list) {
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
}
