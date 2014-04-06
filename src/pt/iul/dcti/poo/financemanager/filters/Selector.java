package pt.iul.dcti.poo.financemanager.filters;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public interface Selector<T> {
    public boolean isSelected(T item);
}
