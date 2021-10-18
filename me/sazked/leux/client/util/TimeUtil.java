// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.Calendar;

public class TimeUtil
{
    public static int get_month() {
        return Calendar.getInstance().get(2);
    }
    
    public static int get_second() {
        return Calendar.getInstance().get(13);
    }
    
    public static int get_day() {
        return Calendar.getInstance().get(5);
    }
    
    public static int get_minuite() {
        return Calendar.getInstance().get(12);
    }
    
    public static int get_hour() {
        return Calendar.getInstance().get(11);
    }
}
