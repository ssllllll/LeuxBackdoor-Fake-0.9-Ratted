// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.modules.Module;

public class ClearChat extends Module
{
    public ClearChat() {
        super(Category.misc);
        this.name = "Clear Chatbox";
        this.tag = "ClearChatbox";
        this.description = "Removes the default minecraft chat outline.";
    }
}
