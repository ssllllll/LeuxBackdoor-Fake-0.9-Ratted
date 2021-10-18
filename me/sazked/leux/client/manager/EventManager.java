// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.manager;

import java.util.Iterator;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.command.Command;
import me.sazked.leux.client.command.Commands;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import net.minecraft.entity.passive.AbstractHorse;
import me.sazked.leux.client.event.events.EventGameOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import me.sazked.leux.Leux;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import me.sazked.leux.client.event.EventClientBus;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraft.client.Minecraft;

public class EventManager
{
    public Minecraft mc;
    
    @SubscribeEvent
    public void onNoRender(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        EventClientBus.EVENT_BUS.post(renderBlockOverlayEvent);
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent obf) {
        if (Keyboard.getEventKeyState()) {
            Leux.get_hack_manager().bind(Keyboard.getEventKey());
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent obf) {
        if (this.mc.player == null) {
            return;
        }
        Leux.get_hack_manager().update();
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post obf) {
        if (obf.isCanceled()) {
            return;
        }
        EventClientBus.EVENT_BUS.post(new EventGameOverlay(obf.getPartialTicks(), new ScaledResolution(this.mc)));
        RenderGameOverlayEvent.ElementType obf2 = RenderGameOverlayEvent.ElementType.EXPERIENCE;
        if (!this.mc.player.isCreative() && this.mc.player.getRidingEntity() instanceof AbstractHorse) {
            obf2 = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
        }
        if (obf.getType() == obf2) {
            Leux.get_hack_manager().render();
            if (!Leux.get_hack_manager().get_module_with_tag("GUI").is_active()) {
                Leux.get_hud_manager().render();
            }
            GL11.glPushMatrix();
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GlStateManager.enableBlend();
            GL11.glPopMatrix();
            RenderHelp.release_gl();
        }
    }
    
    public EventManager() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onPlayerPush(final PlayerSPPushOutOfBlocksEvent obf) {
        EventClientBus.EVENT_BUS.post(obf);
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent obf) {
        if (obf.isCanceled()) {
            return;
        }
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent obf) {
        if (obf.isCanceled()) {
            return;
        }
        Leux.get_hack_manager().render(obf);
    }
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent inputUpdateEvent) {
        EventClientBus.EVENT_BUS.post(inputUpdateEvent);
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onChat(final ClientChatEvent obf) {
        final String obf2 = obf.getMessage();
        final String[] obf3 = CommandManager.command_list.get_message(obf.getMessage());
        boolean obf4 = false;
        if (obf3.length > 0) {
            obf.setCanceled(true);
            this.mc.ingameGUI.getChatGUI().addToSentMessages(obf.getMessage());
            for (final Command obf5 : Commands.get_pure_command_list()) {
                try {
                    if (!CommandManager.command_list.get_message(obf.getMessage())[0].equalsIgnoreCase(obf5.get_name())) {
                        continue;
                    }
                    obf4 = obf5.get_message(CommandManager.command_list.get_message(obf.getMessage()));
                }
                catch (Exception ex) {}
            }
            if (!obf4 && CommandManager.command_list.has_prefix(obf.getMessage())) {
                MessageUtil.send_client_message("Try using " + CommandManager.get_prefix() + "help list to see all commands");
                obf4 = false;
            }
        }
    }
}
