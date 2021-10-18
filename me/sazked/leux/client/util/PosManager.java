// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.client.Minecraft;

public class PosManager
{
    public static double x;
    public static double z;
    public static double y;
    public static Minecraft mc;
    public static boolean onground;
    
    static {
        PosManager.mc = Minecraft.getMinecraft();
    }
    
    public static void restorePosition() {
        PosManager.mc.player.posX = PosManager.x;
        PosManager.mc.player.posY = PosManager.y;
        PosManager.mc.player.posZ = PosManager.z;
        PosManager.mc.player.onGround = PosManager.onground;
    }
    
    public static void updatePosition() {
        PosManager.x = PosManager.mc.player.posX;
        PosManager.y = PosManager.mc.player.posY;
        PosManager.z = PosManager.mc.player.posZ;
        PosManager.onground = PosManager.mc.player.onGround;
    }
}
