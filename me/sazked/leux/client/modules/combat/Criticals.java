// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import me.sazked.leux.Leux;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.CPacketUseEntity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPacket;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class Criticals extends Module
{
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    public Setting mode;
    
    public void lambda$new$0(final EventPacket.SendPacket obf) {
        if (obf.get_packet() instanceof CPacketUseEntity && ((Criticals.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && Criticals.mc.player.getHeldItemOffhand().getItem() instanceof ItemAppleGold) || Leux.get_module_manager().get_module_with_tag("AutoCrystalOld").is_disabled() || Leux.get_module_manager().get_module_with_tag("AutoCrystalNew").is_disabled())) {
            final CPacketUseEntity obf2 = (CPacketUseEntity)obf.get_packet();
            if (obf2.getAction() == CPacketUseEntity.Action.ATTACK && Criticals.mc.player.onGround) {
                if (this.mode.in("Packet")) {
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(13722.45125175407) ^ 0x7F7354A0629E12FFL), Criticals.mc.player.posZ, false));
                    Criticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Criticals.mc.player.posX, Criticals.mc.player.posY, Criticals.mc.player.posZ, false));
                }
                else if (this.mode.in("Jump")) {
                    Criticals.mc.player.jump();
                }
            }
        }
    }
    
    @Override
    public String array_detail() {
        return this.mode.get_current_value();
    }
    
    public Criticals() {
        super(Category.combat);
        this.mode = this.create("Mode", "Mode", "Packet", this.combobox("Packet", "Jump"));
        this.listener = new Listener<EventPacket.SendPacket>(this::lambda$new$0, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Criticals";
        this.tag = "Criticals";
        this.description = "You can hit with criticals when attack.";
    }
}
