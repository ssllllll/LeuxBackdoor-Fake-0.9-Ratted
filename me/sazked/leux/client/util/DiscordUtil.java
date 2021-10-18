// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import me.obsidian.DiscordEventHandlers;
import net.minecraft.client.Minecraft;
import me.obsidian.DiscordRPC;
import me.obsidian.DiscordRichPresence;

public class DiscordUtil
{
    public static DiscordRichPresence presence;
    public static DiscordRPC rpc;
    public static String state;
    public static String details;
    public static Minecraft mc;
    public static String ClientId;
    
    public static void lambda$init$1() {
        while (!Thread.currentThread().isInterrupted()) {
            Label_0528: {
                Exception ex;
                try {
                    DiscordUtil.rpc.Discord_RunCallbacks();
                    DiscordUtil.details = "IGN: " + DiscordUtil.mc.player.getName();
                    DiscordUtil.state = "";
                    if (DiscordUtil.mc.isIntegratedServerRunning()) {
                        DiscordUtil.state = "Playing on Singleplayer";
                    }
                    else if (DiscordUtil.mc.getCurrentServerData() != null) {
                        if (!DiscordUtil.mc.getCurrentServerData().serverIP.equals("")) {
                            DiscordUtil.state = "Playing on " + DiscordUtil.mc.getCurrentServerData().serverIP;
                        }
                    }
                    else {
                        DiscordUtil.state = "Main Menu";
                    }
                    if (!DiscordUtil.details.equals(DiscordUtil.presence.details) || !DiscordUtil.state.equals(DiscordUtil.presence.state)) {
                        DiscordUtil.presence.startTimestamp = System.currentTimeMillis() / ((long)1429961916 ^ 0x553B7F54L);
                    }
                    DiscordUtil.presence.details = DiscordUtil.details;
                    DiscordUtil.presence.state = DiscordUtil.state;
                    DiscordUtil.rpc.Discord_UpdatePresence(DiscordUtil.presence);
                    break Label_0528;
                }
                catch (Exception obf) {
                    ex = obf;
                }
                ex.printStackTrace();
                try {
                    Thread.sleep((long)(-1044349645) ^ 0xFFFFFFFFC1C06EBBL);
                }
                catch (InterruptedException obf2) {
                    obf2.printStackTrace();
                }
            }
        }
    }
    
    public static void shutdown() {
        DiscordUtil.rpc.Discord_Shutdown();
    }
    
    public static void init() {
        final DiscordEventHandlers obf = new DiscordEventHandlers();
        obf.disconnected = DiscordUtil::lambda$init$0;
        DiscordUtil.rpc.Discord_Initialize("799204685139542057", obf, true, "");
        DiscordUtil.presence.startTimestamp = System.currentTimeMillis() / ((long)838154135 ^ 0x31F5347FL);
        DiscordUtil.presence.details = "IGN: " + DiscordUtil.mc.player.getName();
        DiscordUtil.presence.state = "Main Menu";
        DiscordUtil.presence.largeImageKey = "logo";
        DiscordUtil.presence.largeImageText = "0.9";
        DiscordUtil.rpc.Discord_UpdatePresence(DiscordUtil.presence);
        new Thread(DiscordUtil::lambda$init$1, "Discord-RPC-Callback-Handler").start();
    }
    
    public static void lambda$init$0(final int obf, final String obf) {
        System.out.println("Discord RPC disconnected, var1: " + obf + ", var2: " + obf);
    }
    
    static {
        DiscordUtil.ClientId = "799204685139542057";
        DiscordUtil.mc = Minecraft.getMinecraft();
        DiscordUtil.rpc = DiscordRPC.INSTANCE;
        DiscordUtil.presence = new DiscordRichPresence();
    }
}
