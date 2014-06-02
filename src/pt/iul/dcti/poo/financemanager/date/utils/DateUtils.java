package pt.iul.dcti.poo.financemanager.date.utils;

import pt.iul.dcti.poo.financemanager.date.Date;

public class DateUtils {

    public static Date max(Date date1, Date date2) {
        return date1.compareTo(date2) > 0 ? date1 : date2;
    }
    
    public static Date min(Date date1, Date date2) {
        return date1.compareTo(date2) > 0 ? date2 : date1;
    }
    
}
