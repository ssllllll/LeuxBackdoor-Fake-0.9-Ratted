// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import org.lwjgl.input.Mouse;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.modules.Module;

public class MiddleClickGang extends Module
{
    public boolean clicked;
    public static ChatFormatting reset;
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting bold;
    
    static {
        MiddleClickGang.red = ChatFormatting.RED;
        MiddleClickGang.green = ChatFormatting.GREEN;
        MiddleClickGang.bold = ChatFormatting.BOLD;
        MiddleClickGang.reset = ChatFormatting.RESET;
    }
    
    @Override
    public void update() {
        if (MiddleClickGang.mc.currentScreen != null) {
            return;
        }
        if (!Mouse.isButtonDown(2)) {
            this.clicked = false;
            return;
        }
        if (!this.clicked) {
            this.clicked = true;
            final RayTraceResult obf = MiddleClickGang.mc.objectMouseOver;
            if (obf == null || obf.typeOfHit != RayTraceResult.Type.ENTITY) {
                return;
            }
            if (!(obf.entityHit instanceof EntityPlayer)) {
                return;
            }
            final Entity obf2 = obf.entityHit;
            if (FriendUtil.isFriend(obf2.getName())) {
                final FriendUtil.Friend obf3 = FriendUtil.friends.stream().filter(MiddleClickGang::lambda$update$0).findFirst().get();
                FriendUtil.friends.remove(obf3);
                MessageUtil.send_client_message("Player " + MiddleClickGang.red + MiddleClickGang.bold + obf2.getName() + MiddleClickGang.reset + " is now not your friend :(");
            }
            else {
                final FriendUtil.Friend obf3 = FriendUtil.get_friend_object(obf2.getName());
                FriendUtil.friends.add(obf3);
                MessageUtil.send_client_message("Player " + MiddleClickGang.green + MiddleClickGang.bold + obf2.getName() + MiddleClickGang.reset + " is now your friend :D");
            }
        }
    }
    
    public MiddleClickGang() {
        super(Category.misc);
        this.clicked = false;
        this.name = "MiddleClickGang";
        this.tag = "MiddleClickGang";
        this.description = "you press button and the world becomes a better place :D";
    }
    
    public static boolean lambda$update$0(final Entity obf, final FriendUtil.Friend obf) {
        return obf.getUsername().equalsIgnoreCase(obf.getName());
    }
}
