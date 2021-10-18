// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.OnlineFriends;
import me.sazked.leux.Leux;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class FriendList extends Pinnable
{
    int passes;
    public static ChatFormatting bold;
    
    public FriendList() {
        super("Friends", "Friends", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorR").get_value(1);
        final int nl_g = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorG").get_value(1);
        final int nl_b = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorB").get_value(1);
        final int nl_a = Leux.get_setting_manager().get_setting_with_tag("HUD", "ColorA").get_value(1);
        final String line1 = FriendList.bold + "active_gang: ";
        this.passes = 0;
        this.create_line(line1, this.docking(1, line1), 2, nl_r, nl_g, nl_b, nl_a);
        if (!OnlineFriends.getFriends().isEmpty()) {
            for (final Entity e : OnlineFriends.getFriends()) {
                ++this.passes;
                this.create_line(e.getName(), this.docking(1, e.getName()), this.get(line1, "height") * this.passes, nl_r, nl_g, nl_b, nl_a);
            }
        }
        this.set_width(this.get(line1, "width") + 2);
        this.set_height(this.get(line1, "height") + 2);
    }
    
    static {
        FriendList.bold = ChatFormatting.BOLD;
    }
}
