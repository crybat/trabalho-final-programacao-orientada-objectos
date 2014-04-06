package pt.iul.dcti.poo.utils;

import javax.swing.JOptionPane;

/**
 * 
 * @author POO 2014
 * 
 *         ...
 * 
 */
public class Menu {

    public static boolean yesOrNoInput(String message)
    {
        return JOptionPane.showConfirmDialog(null, message) == JOptionPane.YES_OPTION;
    }

    public static String requestInput(String message)
    {
        return JOptionPane.showInputDialog(message);
    }

    public static String requestSelection(String name, String[] options)
    {
        String option = ((String) JOptionPane.showInputDialog(null,
                "Escolha uma opção", name, JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]));
        return option;
    }

}
