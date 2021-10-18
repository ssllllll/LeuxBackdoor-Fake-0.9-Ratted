// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.mc12;

import com.lukflug.panelstudio.ClickGUI;
import com.lukflug.panelstudio.hud.HUDClickGUI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;

public abstract class MinecraftHUDGUI extends MinecraftGUI
{
    protected boolean hudEditor;
    
    public MinecraftHUDGUI() {
        this.hudEditor = false;
    }
    
    @Override
    public void enterGUI() {
        this.hudEditor = false;
        super.enterGUI();
    }
    
    @Override
    public void exitGUI() {
        this.hudEditor = false;
        super.exitGUI();
    }
    
    public void enterHUDEditor() {
        this.hudEditor = true;
        if (this.getHUDGUI().isOn()) {
            this.getHUDGUI().toggle();
        }
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)this);
    }
    
    public void render() {
        if (!this.getHUDGUI().isOn() && !this.hudEditor) {
            this.renderGUI();
        }
    }
    
    public void handleKeyEvent(final int scancode) {
        if (scancode != 1 && !this.getHUDGUI().isOn() && !this.hudEditor) {
            this.getHUDGUI().handleKey(scancode);
        }
    }
    
    protected abstract HUDClickGUI getHUDGUI();
    
    @Override
    protected ClickGUI getGUI() {
        return this.getHUDGUI();
    }
}
