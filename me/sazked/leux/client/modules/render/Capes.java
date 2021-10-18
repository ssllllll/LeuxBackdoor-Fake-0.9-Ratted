// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.leux.client.modules.Category;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class Capes extends Module
{
    public Setting cape;
    
    public Capes() {
        super(Category.render);
        this.cape = this.create("Cape", "CapeCape", "LeuxNew", this.combobox("LeuxNew", "LeuxOld", "Obsidian"));
        this.name = "Capes";
        this.tag = "Capes";
        this.description = "see epic capes behind epic dudes";
    }
    
    @Override
    public String array_detail() {
        return this.cape.get_current_value();
    }
}
