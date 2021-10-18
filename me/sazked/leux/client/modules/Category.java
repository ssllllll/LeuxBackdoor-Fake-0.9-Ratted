// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules;

public enum Category
{
    public String name;
    public boolean hidden;
    
    exploit("Exploit", "Exploit", false);
    
    public String tag;
    
    misc("Misc", "Misc", false), 
    chat("Player", "Chat", false), 
    secret("Secret", "Secret", true), 
    combat("Combat", "Combat", false), 
    render("Render", "Render", false);
    
    public static Category[] $VALUES;
    
    movement("Movement", "Movement", false), 
    ui("GUI", "GUI", false);
    
    public String get_name() {
        return this.name;
    }
    
    public Category(final String obf, final String obf, final boolean obf) {
        this.name = obf;
        this.tag = obf;
        this.hidden = obf;
    }
    
    static {
        Category.$VALUES = new Category[] { Category.chat, Category.combat, Category.movement, Category.render, Category.exploit, Category.misc, Category.ui, Category.secret };
    }
    
    public boolean is_hidden() {
        return this.hidden;
    }
    
    public String get_tag() {
        return this.tag;
    }
}
