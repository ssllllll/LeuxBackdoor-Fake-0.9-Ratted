// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.play.server.SPacketBlockBreakAnim;
import java.util.Objects;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import me.sazked.leux.client.util.FriendUtil;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import net.minecraft.item.ItemTool;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import java.util.List;
import me.sazked.leux.client.guiscreen.settings.Setting;
import net.minecraft.util.math.Vec3d;
import java.util.HashMap;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import java.util.Set;
import me.sazked.leux.client.modules.Module;

public class Notifications extends Module
{
    public Set<EntityPlayer> str;
    public static ChatFormatting bold;
    public int numForgetTicks;
    public HashMap<String, Vec3d> tpdPlayers;
    public Setting visualrange;
    public int numTicks;
    public Setting coordexploit;
    public List<String> people;
    public static ChatFormatting red;
    public static ChatFormatting green;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> packetReceiveListener;
    public Setting strength;
    public Setting chatDelay;
    public HashMap<Entity, Vec3d> knownPlayers;
    public Setting totempop;
    public Setting distanceToDetect;
    public static ChatFormatting reset;
    public static Minecraft mc;
    @EventHandler
    public Listener<EventPacket.ReceivePacket> packet_event;
    public static HashMap<String, Integer> totem_pop_counter;
    public Setting breakwarner;
    
    public String getPlayer() {
        final List obf = (List)Notifications.mc.world.playerEntities.stream().filter(Notifications::lambda$getPlayer$2).collect(Collectors.toList());
        for (final EntityPlayer obf3 : obf) {
            if (!obf3.isDead) {
                if (obf3.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.9057078E38f) ^ 0x7F0F5E99)) {
                    continue;
                }
                if (obf3.getName().equals(Notifications.mc.player.getName())) {
                    continue;
                }
                if (obf3.getHeldItemMainhand().getItem() instanceof ItemTool) {
                    return obf3.getName();
                }
                continue;
            }
        }
        return "";
    }
    
    public Notifications() {
        super(Category.chat);
        this.breakwarner = this.create("BreakWarner", "BreakWarner", true);
        this.totempop = this.create("TotemPop", "TotemPop", true);
        this.coordexploit = this.create("CoordExploit", "CoordExploit", false);
        this.visualrange = this.create("VisualRange", "VisualRange", false);
        this.strength = this.create("Strength", "Strength", false);
        this.distanceToDetect = this.create("Max Break Distance", "WarnerMaxDistance", 2, 1, 5);
        this.chatDelay = this.create("Chat Delay", "WarnerChatDelay", 18, 14, 25);
        this.knownPlayers = new HashMap<Entity, Vec3d>();
        this.tpdPlayers = new HashMap<String, Vec3d>();
        this.numTicks = 0;
        this.numForgetTicks = 0;
        this.packetReceiveListener = new Listener<EventPacket.ReceivePacket>(this::lambda$new$0, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.packet_event = new Listener<EventPacket.ReceivePacket>(this::lambda$new$1, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.str = Collections.newSetFromMap(new WeakHashMap<EntityPlayer, Boolean>());
        this.name = "Notifications";
        this.tag = "Notifications";
        this.description = "got yo ass drinking sum";
    }
    
    static {
        Notifications.mc = Minecraft.getMinecraft();
        Notifications.totem_pop_counter = new HashMap<String, Integer>();
        Notifications.red = ChatFormatting.RED;
        Notifications.green = ChatFormatting.GREEN;
        Notifications.bold = ChatFormatting.BOLD;
        Notifications.reset = ChatFormatting.RESET;
    }
    
    public static String vectorToString(final Vec3d obf, final boolean obf) {
        final StringBuilder obf2 = new StringBuilder();
        obf2.append('(');
        obf2.append((int)Math.floor(obf.x));
        obf2.append(", ");
        if (obf) {
            obf2.append((int)Math.floor(obf.y));
            obf2.append(", ");
        }
        obf2.append((int)Math.floor(obf.z));
        obf2.append(")");
        return obf2.toString();
    }
    
    @Override
    public void enable() {
        this.people = new ArrayList<String>();
    }
    
    public boolean pastDistance(final EntityPlayer obf, final BlockPos obf, final double obf) {
        return obf.getDistanceSqToCenter(obf) <= Math.pow(obf, Double.longBitsToDouble(Double.doubleToLongBits(0.3094696921584696) ^ 0x7FD3CE59F7BB1FC7L));
    }
    
    @Override
    public void update() {
        if (this.strength.get_value(true)) {
            for (final EntityPlayer obf : Notifications.mc.world.playerEntities) {
                if (obf.equals((Object)Notifications.mc.player)) {
                    continue;
                }
                if (obf.isPotionActive(MobEffects.STRENGTH)) {
                    if (!this.str.contains(obf)) {
                        MessageUtil.send_client_message(ChatFormatting.RESET + obf.getDisplayNameString() + ChatFormatting.RED + " Has Strength");
                        this.str.add(obf);
                    }
                }
                if (!this.str.contains(obf)) {
                    continue;
                }
                if (obf.isPotionActive(MobEffects.STRENGTH)) {
                    continue;
                }
                MessageUtil.send_client_message(ChatFormatting.RESET + obf.getDisplayNameString() + ChatFormatting.GREEN + " Has Ran Out Of Strength");
                this.str.remove(obf);
            }
        }
        if (this.coordexploit.get_value(true)) {
            if (this.numTicks >= 50) {
                this.numTicks = 0;
                for (final Entity obf2 : Notifications.mc.world.loadedEntityList) {
                    if (obf2 instanceof EntityPlayer && !obf2.getName().equals(Notifications.mc.player.getName())) {
                        final Vec3d obf3 = new Vec3d(obf2.posX, obf2.posY, obf2.posZ);
                        if (this.knownPlayers.containsKey(obf2) && Math.abs(this.knownPlayers.get(obf2).distanceTo(obf3)) > Double.longBitsToDouble(Double.doubleToLongBits(0.018948633711860226) ^ 0x7FDA67454862A26FL) && Math.abs(Notifications.mc.player.getPositionVector().distanceTo(obf3)) > Double.longBitsToDouble(Double.doubleToLongBits(0.06996615384645281) ^ 0x7FE8E94D4698F07BL) && (!this.tpdPlayers.containsKey(obf2.getName()) || this.tpdPlayers.get(obf2.getName()) != obf3)) {
                            MessageUtil.send_client_message("Player " + obf2.getName() + " has tp'd to " + vectorToString(obf3, false));
                            this.saveFile(vectorToString(obf3, false), obf2.getName());
                            this.knownPlayers.remove(obf2);
                            this.tpdPlayers.put(obf2.getName(), obf3);
                        }
                        this.knownPlayers.put(obf2, obf3);
                    }
                }
            }
            if (this.numForgetTicks >= 9000000) {
                this.tpdPlayers.clear();
            }
            ++this.numTicks;
            ++this.numForgetTicks;
        }
        if (this.totempop.get_value(true)) {
            for (final EntityPlayer obf : Notifications.mc.world.playerEntities) {
                if (!Notifications.totem_pop_counter.containsKey(obf.getName())) {
                    continue;
                }
                if (!obf.isDead) {
                    if (obf.getHealth() > Float.intBitsToFloat(Float.floatToIntBits(1.6843382E38f) ^ 0x7EFD6E59)) {
                        continue;
                    }
                }
                final int obf4 = Notifications.totem_pop_counter.get(obf.getName());
                Notifications.totem_pop_counter.remove(obf.getName());
                if (obf == Notifications.mc.player) {
                    continue;
                }
                if (FriendUtil.isFriend(obf.getName())) {
                    MessageUtil.send_client_message("" + Notifications.reset + Notifications.green + Notifications.bold + obf.getName() + Notifications.reset + " died after popping " + Notifications.bold + obf4 + Notifications.reset + " totems");
                }
                else {
                    MessageUtil.send_client_message("" + Notifications.reset + Notifications.red + Notifications.bold + obf.getName() + Notifications.reset + " died after popping " + Notifications.bold + obf4 + Notifications.reset + " totems");
                }
            }
        }
        if (this.visualrange.get_value(true)) {
            if (Notifications.mc.world == null | Notifications.mc.player == null) {
                return;
            }
            final List<String> obf5 = new ArrayList<String>();
            final List<EntityPlayer> obf6 = (List<EntityPlayer>)Notifications.mc.world.playerEntities;
            for (final Entity obf7 : obf6) {
                if (obf7.getName().equals(Notifications.mc.player.getName())) {
                    continue;
                }
                obf5.add(obf7.getName());
            }
            if (obf5.size() > 0) {
                for (final String obf8 : obf5) {
                    if (!this.people.contains(obf8)) {
                        if (FriendUtil.isFriend(obf8)) {
                            MessageUtil.send_client_message("I see an epic dude called " + ChatFormatting.RESET + ChatFormatting.GREEN + obf8 + ChatFormatting.RESET + " :D");
                        }
                        else {
                            MessageUtil.send_client_message("I see a dude called " + ChatFormatting.RESET + ChatFormatting.RED + obf8);
                        }
                        this.people.add(obf8);
                    }
                }
            }
        }
    }
    
    public static boolean lambda$getPlayer$2(final EntityPlayer entityPlayer) {
        return !FriendUtil.isFriend(entityPlayer.getName());
    }
    
    public void saveFile(final String obf, final String obf) {
        try {
            final File obf2 = new File("./LeuxBackdoor/coordexploit.txt");
            obf2.getParentFile().mkdirs();
            final PrintWriter obf3 = new PrintWriter(new FileWriter(obf2, true));
            obf3.println("name: " + obf + " coords: " + obf);
            obf3.close();
        }
        catch (Exception ex) {}
    }
    
    public void sendChat() {
        if (this.breakwarner.get_value(true)) {
            MessageUtil.send_client_message("" + ChatFormatting.RED + "BREAK WARNING!!!");
        }
    }
    
    public void lambda$new$1(final EventPacket.ReceivePacket obf) {
        if (obf.get_packet() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus obf2 = (SPacketEntityStatus)obf.get_packet();
            if (obf2.getOpCode() == 35 && this.totempop.get_value(true)) {
                final Entity obf3 = obf2.getEntity((World)Notifications.mc.world);
                int obf4 = 1;
                if (Notifications.totem_pop_counter.containsKey(obf3.getName())) {
                    obf4 = Notifications.totem_pop_counter.get(obf3.getName());
                    Notifications.totem_pop_counter.put(obf3.getName(), ++obf4);
                }
                else {
                    Notifications.totem_pop_counter.put(obf3.getName(), obf4);
                }
                if (obf3 == Notifications.mc.player) {
                    return;
                }
                if (FriendUtil.isFriend(obf3.getName())) {
                    MessageUtil.send_client_message("" + Notifications.reset + Notifications.green + Notifications.bold + obf3.getName() + Notifications.reset + " has popped " + Notifications.bold + obf4 + Notifications.reset + " totems, help him lmao.");
                }
                else {
                    MessageUtil.send_client_message("" + Notifications.reset + Notifications.red + Notifications.bold + obf3.getName() + Notifications.reset + " has popped " + Notifications.bold + obf4 + Notifications.reset + " totems, he is so ez.");
                }
            }
        }
    }
    
    public void lambda$new$0(final EventPacket.ReceivePacket obf) {
        final EntityPlayerSP obf2 = Notifications.mc.player;
        final WorldClient obf3 = Notifications.mc.world;
        if (!Objects.isNull(obf2)) {
            if (!Objects.isNull(obf3)) {
                if (obf.get_packet() instanceof SPacketBlockBreakAnim) {
                    final SPacketBlockBreakAnim obf5;
                    final BlockPos obf4;
                    if (this.pastDistance((EntityPlayer)obf2, obf4 = (obf5 = (SPacketBlockBreakAnim)obf.get_packet()).getPosition(), this.distanceToDetect.get_value(1))) {
                        this.sendChat();
                    }
                }
            }
        }
    }
}
