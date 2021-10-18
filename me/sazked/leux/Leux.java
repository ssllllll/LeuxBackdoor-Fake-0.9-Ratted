// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux;

import org.apache.logging.log4j.LogManager;
import me.sazked.leux.client.util.DiscordUtil;
import me.sazked.leux.client.event.EventRegister;
import org.lwjgl.opengl.Display;
import me.sazked.leux.client.manager.CommandManager;
import me.sazked.leux.client.manager.EventManager;
import me.sazked.leux.client.event.EventHandler;
import java.awt.Font;
import org.reflections.reflections;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraft.client.Minecraft;
import me.sazked.leux.client.guiandfont.NewGUI;
import me.sazked.leux.client.manager.HUDManager;
import me.sazked.leux.client.guiandfont.CFontRenderer;
import me.sazked.leux.client.manager.ConfigManager;
import me.zero.alpine.fork.bus.EventBus;
import me.sazked.leux.client.guiscreen.GUI;
import me.sazked.turok.Turok;
import org.apache.logging.log4j.Logger;
import me.sazked.leux.client.manager.ModuleManager;
import me.sazked.leux.client.manager.SettingManager;
import com.mojang.realmsclient.gui.ChatFormatting;
import me.sazked.leux.client.guiscreen.HUD;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "leux", version = "0.9")
public class Leux
{
    public static HUD click_hud;
    public static ChatFormatting g;
    public static SettingManager setting_manager;
    public static ModuleManager module_manager;
    public static Logger register_log;
    @Mod.Instance
    public static Leux MASTER;
    public static ChatFormatting r;
    public static int KEY_DELETE;
    public static String CLIENT_NAME;
    public static Turok turok;
    public static int client_g;
    public static int client_r;
    public static GUI click_gui;
    public static EventBus EVENT_BUS;
    public static int KEY_GUI_ESCAPE;
    public static String VERSION;
    public static int client_b;
    public static String SIGN;
    public static ConfigManager config_manager;
    public static CFontRenderer fontRenderer;
    public static HUDManager hud_manager;
    public static NewGUI new_gui;
    public static int KEY_GUI;
    
    public static String get_actual_user() {
        return Minecraft.getMinecraft().getSession().getUsername();
    }
    
    public static void set_client_name(final String client_NAME) {
        Leux.CLIENT_NAME = client_NAME;
    }
    
    public static String get_version() {
        return "0.9";
    }
    
    public static String get_name() {
        return Leux.CLIENT_NAME;
    }
    
    public static ModuleManager get_module_manager() {
        return Leux.module_manager;
    }
    
    @Mod.EventHandler
    public void Starting(final FMLInitializationEvent obf) {
        new reflections();
        Leux.fontRenderer = new CFontRenderer(new Font("Arial", 0, 18), true, false);
        this.init_log(Leux.CLIENT_NAME);
        EventHandler.INSTANCE = new EventHandler();
        send_minecraft_log("initialising managers");
        Leux.setting_manager = new SettingManager();
        Leux.config_manager = new ConfigManager();
        Leux.module_manager = new ModuleManager();
        Leux.hud_manager = new HUDManager();
        final EventManager obf2 = new EventManager();
        final CommandManager obf3 = new CommandManager();
        send_minecraft_log("done");
        send_minecraft_log("initialising guis");
        Display.setTitle(Leux.CLIENT_NAME + " v" + "0.9");
        Leux.click_gui = new GUI();
        Leux.new_gui = new NewGUI();
        Leux.click_hud = new HUD();
        send_minecraft_log("done");
        send_minecraft_log("initialising skidded framework");
        Leux.turok = new Turok("Turok");
        send_minecraft_log("done");
        send_minecraft_log("initialising commands and events");
        EventRegister.register_command_manager(obf3);
        EventRegister.register_module_manager(obf2);
        send_minecraft_log("done");
        send_minecraft_log("loading settings");
        Leux.config_manager.load_settings();
        send_minecraft_log("done");
        if (Leux.module_manager.get_module_with_tag("GUI").is_active()) {
            Leux.module_manager.get_module_with_tag("GUI").set_active(false);
        }
        if (Leux.module_manager.get_module_with_tag("HUD").is_active()) {
            Leux.module_manager.get_module_with_tag("HUD").set_active(false);
        }
        if (get_hack_manager().get_module_with_tag("RichPresence").is_active()) {
            DiscordUtil.init();
        }
        send_minecraft_log("client started");
        send_minecraft_log("brrrrrr");
    }
    
    static {
        Leux.VERSION = "0.9";
        Leux.SIGN = " ";
        Leux.KEY_GUI = 54;
        Leux.KEY_GUI_ESCAPE = 1;
        Leux.KEY_DELETE = 211;
        Leux.CLIENT_NAME = "LeuxBackdoor";
        Leux.EVENT_BUS = new me.zero.alpine.fork.bus.EventManager();
        Leux.g = ChatFormatting.GRAY;
        Leux.r = ChatFormatting.RESET;
        Leux.client_r = 0;
        Leux.client_g = 0;
        Leux.client_b = 0;
    }
    
    public static void send_minecraft_log(final String s) {
        Leux.register_log.info(s);
    }
    
    public static EventHandler get_event_handler() {
        return EventHandler.INSTANCE;
    }
    
    public static HUDManager get_hud_manager() {
        return Leux.hud_manager;
    }
    
    public static String smoth(final String base) {
        return me.sazked.turok.task.Font.smoth(base);
    }
    
    public static ConfigManager get_config_manager() {
        return Leux.config_manager;
    }
    
    public void init_log(final String s) {
        Leux.register_log = LogManager.getLogger(s);
        send_minecraft_log("daily reminder that tater owns u");
    }
    
    public static ModuleManager get_hack_manager() {
        return Leux.module_manager;
    }
    
    public static SettingManager get_setting_manager() {
        return Leux.setting_manager;
    }
}
