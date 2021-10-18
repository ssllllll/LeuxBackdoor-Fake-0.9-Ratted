// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.Iterator;
import java.util.ArrayList;
import me.sazked.leux.client.util.Notification;
import me.sazked.leux.client.util.NotificationUtil;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Notifications extends Pinnable
{
    public Notifications() {
        super("Notifications", "notifications", 1.0f, 0, 0);
        this.set_width(125);
        this.set_height(42);
    }
    
    @Override
    public void render() {
        NotificationUtil.update();
        final ArrayList<Notification> notifications = NotificationUtil.get_notifications();
        int a = 0;
        for (final Notification n : notifications) {
            final int messageWidth = this.get(n.getMessage(), "width") + 25;
            final int nWidth = Math.max(messageWidth, 125);
            this.create_rect(this.get_width() - nWidth, a, nWidth, a + 40, 0, 0, 0, 69);
            this.create_rect(this.get_width() - nWidth, a, this.get_width() - nWidth + 5, a + 40, n.getR(), n.getG(), n.getB(), 255);
            this.create_line(n.getMessage(), this.get_width() - nWidth + 10, a + (42 - this.get(n.getMessage(), "height")) / 2);
            a += 42;
        }
    }
}
