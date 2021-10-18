// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.movement;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.client.gui.GuiChat;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventGUIScreen;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.settings.KeyBinding;
import me.sazked.leux.client.modules.Module;

public class InventoryMove extends Module
{
    public static KeyBinding[] KEYS;
    @EventHandler
    public Listener<EventGUIScreen> state_gui;
    
    public void walk() {
        for (final KeyBinding keyBinding : InventoryMove.KEYS) {
            if (Keyboard.isKeyDown(keyBinding.getKeyCode())) {
                if (keyBinding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
                    keyBinding.setKeyConflictContext((IKeyConflictContext)KeyConflictContext.UNIVERSAL);
                }
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), true);
            }
            else {
                KeyBinding.setKeyBindState(keyBinding.getKeyCode(), false);
            }
        }
    }
    
    @Override
    public void update() {
        if (InventoryMove.mc.player == null && InventoryMove.mc.world == null) {
            return;
        }
        if (!(InventoryMove.mc.currentScreen instanceof GuiChat) && InventoryMove.mc.currentScreen != null) {
            this.walk();
        }
    }
    
    public void lambda$new$0(final EventGUIScreen eventGUIScreen) {
        if (InventoryMove.mc.player == null && InventoryMove.mc.world == null) {
            return;
        }
        if (eventGUIScreen.get_guiscreen() instanceof GuiChat || eventGUIScreen.get_guiscreen() == null) {
            return;
        }
        this.walk();
    }
    
    static {
        InventoryMove.KEYS = new KeyBinding[] { InventoryMove.mc.gameSettings.keyBindForward, InventoryMove.mc.gameSettings.keyBindRight, InventoryMove.mc.gameSettings.keyBindBack, InventoryMove.mc.gameSettings.keyBindLeft, InventoryMove.mc.gameSettings.keyBindJump, InventoryMove.mc.gameSettings.keyBindSprint };
    }
    
    public InventoryMove() {
        super(Category.movement);
        this.state_gui = new Listener<EventGUIScreen>(this::lambda$new$0, (Predicate<EventGUIScreen>[])new Predicate[0]);
        this.name = "Inventory Move";
        this.tag = "InventoryMove";
        this.description = "move in guis";
    }
}
