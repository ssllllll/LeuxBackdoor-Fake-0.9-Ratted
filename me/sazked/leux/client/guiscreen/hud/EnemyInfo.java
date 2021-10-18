// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.lwjgl.opengl.GL11;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraft.item.ItemStack;
import java.util.Objects;
import net.minecraft.client.network.NetHandlerPlayClient;
import me.sazked.leux.client.modules.combat.AutoCrystalOld;
import me.sazked.leux.client.modules.combat.AutoCrystalNew;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.event.EventClientBus;
import java.util.function.Predicate;
import net.minecraft.world.World;
import net.minecraft.network.play.server.SPacketEntityStatus;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import java.util.HashMap;
import me.zero.alpine.fork.listener.Listenable;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class EnemyInfo extends Pinnable implements Listenable
{
    public static final HashMap<String, Integer> totem_pop_counter;
    @EventHandler
    private final Listener<EventPacket.ReceivePacket> packet_event;
    
    public EnemyInfo() {
        super("Enemy Info", "EnemyInfo", 1.0f, 0, 0);
        SPacketEntityStatus packet;
        Entity entity;
        int count;
        int count2;
        this.packet_event = new Listener<EventPacket.ReceivePacket>(event -> {
            if (event.get_packet() instanceof SPacketEntityStatus) {
                packet = (SPacketEntityStatus)event.get_packet();
                if (packet.getOpCode() == 35) {
                    entity = packet.getEntity((World)this.mc.world);
                    count = 1;
                    if (EnemyInfo.totem_pop_counter.containsKey(entity.getName())) {
                        count2 = EnemyInfo.totem_pop_counter.get(entity.getName());
                        EnemyInfo.totem_pop_counter.put(entity.getName(), ++count2);
                    }
                    else {
                        EnemyInfo.totem_pop_counter.put(entity.getName(), count);
                    }
                }
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.set_height(80);
        this.set_width(150);
        EventClientBus.EVENT_BUS.subscribe(this);
    }
    
    @Override
    public void render() {
        this.update_pops();
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        EntityPlayer target = (EntityPlayer)this.mc.player;
        float lowest_distance = 999.0f;
        for (final EntityPlayer e : this.mc.world.playerEntities) {
            if (e.getDistance((Entity)this.mc.player) < lowest_distance && !e.getName().equals(this.mc.player.getName()) && e.getDistance((Entity)this.mc.player) != 0.0f) {
                target = e;
                lowest_distance = e.getDistance((Entity)this.mc.player);
            }
        }
        if (AutoCrystalNew.get_target() != null && !AutoCrystalNew.get_target().isDead) {
            target = AutoCrystalNew.get_target();
        }
        else if (AutoCrystalOld.get_target() != null && !AutoCrystalOld.get_target().isDead) {
            target = AutoCrystalOld.get_target();
        }
        this.create_rect(0, 0, this.get_width(), this.get_height(), 0, 0, 0, 69);
        final float target_hp = target.getHealth() + target.getAbsorptionAmount();
        String ping_str = "Ping: ";
        try {
            final int response_time = Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(target.getUniqueID()).getResponseTime();
            ping_str = ping_str + response_time + "ms";
        }
        catch (Exception ex) {}
        final float distance_to_target = target.getDistance((Entity)this.mc.player);
        int hp_r;
        int hp_g;
        int hp_b;
        if (target_hp > 25.0f) {
            hp_r = 3;
            hp_g = 252;
            hp_b = 19;
        }
        else if (target_hp > 20.0f) {
            hp_r = 34;
            hp_g = 112;
            hp_b = 39;
        }
        else if (target_hp > 15.0f) {
            hp_r = 237;
            hp_g = 224;
            hp_b = 40;
        }
        else if (target_hp > 10.0f) {
            hp_r = 245;
            hp_g = 140;
            hp_b = 12;
        }
        else if (target_hp > 5.0f) {
            hp_r = 255;
            hp_g = 65;
            hp_b = 51;
        }
        else {
            hp_r = 255;
            hp_g = 25;
            hp_b = 25;
        }
        String pop_str = "";
        try {
            pop_str += ((EnemyInfo.totem_pop_counter.get(target.getName()) == null) ? "§70" : ("§c " + EnemyInfo.totem_pop_counter.get(target.getName())));
        }
        catch (Exception ex2) {}
        final int str_height = this.get("00hpRRRta", "height") + 3;
        try {
            this.create_line(target.getName() + " HP: " + (int)target_hp, 3, 3, hp_r, hp_g, hp_b, 255);
            this.create_line(ping_str, 3, str_height);
            this.create_line("Distance: " + (int)distance_to_target, 3, str_height * 2);
            this.create_line("Totems Popped: " + pop_str, 3, str_height * 3);
            int i1 = 3;
            for (int j = target.inventory.armorInventory.size(); j > 0; --j) {
                final ItemStack stack2 = (ItemStack)target.inventory.armorInventory.get(j - 1);
                final ItemStack armourStack = stack2.copy();
                if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
                    armourStack.stackSize = 1;
                }
                this.renderItemStack(armourStack, this.get_x() + i1, this.get_y() + str_height * 4);
                i1 += 16;
            }
            this.create_rect(0, this.get_height(), (int)(target_hp / 36.0f * this.get_width()), this.get_height() - 5, hp_r, hp_g, hp_b, 255);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GuiInventory.drawEntityOnScreen(this.get_x() + this.get_width() - 20, this.get_y() + this.get_height() - 10, 30, -target.rotationYaw, -target.rotationPitch, (EntityLivingBase)target);
        }
        catch (Exception ex3) {}
    }
    
    private void renderItemStack(final ItemStack stack, final int x, final int y) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        this.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        this.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
        this.mc.getRenderItem().renderItemOverlays(this.mc.fontRenderer, stack, x, y);
        this.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    private void update_pops() {
        for (final EntityPlayer player : this.mc.world.playerEntities) {
            if (!EnemyInfo.totem_pop_counter.containsKey(player.getName())) {
                continue;
            }
            if (!player.isDead && player.getHealth() > 0.0f) {
                continue;
            }
            EnemyInfo.totem_pop_counter.remove(player.getName());
        }
    }
    
    static {
        totem_pop_counter = new HashMap<String, Integer>();
    }
}
