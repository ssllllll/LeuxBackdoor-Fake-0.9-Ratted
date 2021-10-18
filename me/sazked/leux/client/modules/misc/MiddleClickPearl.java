// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import net.minecraft.entity.player.InventoryPlayer;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventPlayerTravel;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.modules.Module;

public class MiddleClickPearl extends Module
{
    public boolean clicked;
    @EventHandler
    public Listener<EventPlayerTravel> listener;
    
    public int findPearlInHotbar() {
        for (int obf = 0; InventoryPlayer.isHotbar(obf); ++obf) {
            if (this.isItemStackPearl(MiddleClickPearl.mc.player.inventory.getStackInSlot(obf))) {
                return obf;
            }
        }
        return -1;
    }
    
    public MiddleClickPearl() {
        super(Category.misc);
        this.listener = new Listener<EventPlayerTravel>(this::lambda$new$0, (Predicate<EventPlayerTravel>[])new Predicate[0]);
        this.name = "MiddleClickPearl";
        this.tag = "MiddleClickPearl";
        this.description = "throws a pearl when middleclick";
    }
    
    public boolean isItemStackPearl(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemEnderPearl;
    }
    
    public void lambda$new$0(final EventPlayerTravel obf) {
        if (MiddleClickPearl.mc.currentScreen == null && Mouse.isButtonDown(2)) {
            final int obf2;
            if (!this.clicked && (obf2 = this.findPearlInHotbar()) != -1) {
                final int obf3 = MiddleClickPearl.mc.player.inventory.currentItem;
                MiddleClickPearl.mc.player.inventory.currentItem = obf2;
                MiddleClickPearl.mc.playerController.processRightClick((EntityPlayer)MiddleClickPearl.mc.player, (World)MiddleClickPearl.mc.world, EnumHand.MAIN_HAND);
                MiddleClickPearl.mc.player.inventory.currentItem = obf3;
            }
            this.clicked = true;
        }
        else {
            this.clicked = false;
        }
    }
}
