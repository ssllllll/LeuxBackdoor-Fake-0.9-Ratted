// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import me.sazked.leux.Leux;
import java.util.function.Predicate;
import java.util.ArrayList;

public class NotificationUtil
{
    public static ArrayList<Notification> active_notifications;
    
    public static void update() {
        NotificationUtil.active_notifications.removeIf(NotificationUtil::lambda$update$0);
        final int obf = Leux.get_setting_manager().get_setting_with_tag("HUD", "MaxNotifications").get_value(1);
        if (obf < NotificationUtil.active_notifications.size()) {
            NotificationUtil.active_notifications.remove(obf - 1);
        }
    }
    
    public static void send_notification(final Notification obf) {
        NotificationUtil.active_notifications.add(0, obf);
    }
    
    public static ArrayList<Notification> get_notifications() {
        return NotificationUtil.active_notifications;
    }
    
    static {
        NotificationUtil.active_notifications = new ArrayList<Notification>();
    }
    
    public static boolean lambda$update$0(final Notification obf) {
        return System.currentTimeMillis() - obf.getTimeCreated() > ((long)804846250 ^ 0x2FF8E922L);
    }
}
