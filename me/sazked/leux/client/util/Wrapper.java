// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import org.lwjgl.input.Keyboard;
import net.minecraft.world.World;
import me.sazked.leux.client.modules.ui.ClickGUI;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;

public class Wrapper
{
    public static Minecraft mc;
    public static FontRenderer fontRenderer;
    
    static {
        Wrapper.mc = Minecraft.getMinecraft();
    }
    
    public static FontRenderer getFontRenderer() {
        return Wrapper.fontRenderer;
    }
    
    public static Minecraft getMinecraft() {
        return Minecraft.getMinecraft();
    }
    
    public static EntityPlayerSP GetPlayer() {
        return Wrapper.mc.player;
    }
    
    public static void init() {
        Wrapper.fontRenderer = ClickGUI.mc.fontRenderer;
    }
    
    public static World getWorld() {
        return (World)getMinecraft().world;
    }
    
    public static int getKey(final String s) {
        return Keyboard.getKeyIndex(s.toUpperCase());
    }
    
    public static EntityPlayerSP getPlayer() {
        return getMinecraft().player;
    }
}
