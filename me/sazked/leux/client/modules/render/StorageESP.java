// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityShulkerBox;
import me.sazked.leux.Leux;
import me.sazked.leux.client.event.events.EventRender;
import me.sazked.turok.draw.RenderHelp;
import net.minecraft.tileentity.TileEntity;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class StorageESP extends Module
{
    public Setting oth_;
    public Setting ot_a;
    public Setting che;
    public Setting oth;
    public Setting shu_;
    public Setting a;
    public Setting enc_;
    public Setting che_;
    public int color_alpha;
    public Setting shu;
    public Setting enc;
    
    public void draw(final TileEntity obf, final int obf, final int obf, final int obf) {
        RenderHelp.prepare("quads");
        RenderHelp.draw_cube(obf.getPos(), obf, obf, obf, this.a.get_value(1), "all");
        RenderHelp.release();
        RenderHelp.prepare("lines");
        RenderHelp.draw_cube_line(obf.getPos(), obf, obf, obf, this.ot_a.get_value(1), "all");
        RenderHelp.release();
    }
    
    @Override
    public void render(final EventRender eventRender) {
        final int client_r = Leux.client_r;
        final int client_g = Leux.client_g;
        final int client_b = Leux.client_b;
        this.color_alpha = this.a.get_value(1);
        for (final TileEntity obf : StorageESP.mc.world.loadedTileEntityList) {
            if (this.shu.get_value(true)) {
                if (obf instanceof TileEntityShulkerBox) {
                    final int n = 0xFF000000 | (((TileEntityShulkerBox)obf).getColor().getColorValue() & -1);
                    if (this.shu_.in("HUD")) {
                        this.draw(obf, client_r, client_g, client_b);
                    }
                    else if (this.shu_.in("RAINBOW")) {
                        this.draw(obf, (n & 0xFF0000) >> 16, (n & 0xFF00) >> 8, n & 0xFF);
                    }
                    else {
                        this.draw(obf, 204, 0, 0);
                    }
                }
            }
            if (this.enc.get_value(true) && obf instanceof TileEntityEnderChest) {
                if (this.enc_.in("HUD")) {
                    this.draw(obf, client_r, client_g, client_b);
                }
                else {
                    this.draw(obf, 204, 0, 255);
                }
            }
            if (this.che.get_value(true) && obf instanceof TileEntityChest) {
                if (this.che_.in("HUD")) {
                    this.draw(obf, client_r, client_g, client_b);
                }
                else {
                    this.draw(obf, 153, 102, 0);
                }
            }
            if (this.oth.get_value(true)) {
                if (!(obf instanceof TileEntityDispenser) && !(obf instanceof TileEntityDropper) && !(obf instanceof TileEntityHopper)) {
                    if (!(obf instanceof TileEntityFurnace)) {
                        if (!(obf instanceof TileEntityBrewingStand)) {
                            continue;
                        }
                    }
                }
                if (this.oth_.in("HUD")) {
                    this.draw(obf, client_r, client_g, client_b);
                }
                else {
                    this.draw(obf, 190, 190, 190);
                }
            }
        }
    }
    
    public StorageESP() {
        super(Category.render);
        this.shu = this.create("Shulkers", "StorageShulker", true);
        this.shu_ = this.create("Shulker Color", "StorageESPShulker", "Client", this.combobox("HUD", "Client", "RAINBOW"));
        this.enc = this.create("EChests", "StorageEChests", false);
        this.enc_ = this.create("EChest Color", "StorageESPEnchest", "Client", this.combobox("HUD", "Client"));
        this.che = this.create("Chests", "StorageChests", true);
        this.che_ = this.create("Chest Color", "StorageESPChest", "Client", this.combobox("HUD", "Client"));
        this.oth = this.create("Misc", "StorageESPMisc", false);
        this.oth_ = this.create("Misc Color", "StorageESPOthers", "Client", this.combobox("HUD", "Client"));
        this.ot_a = this.create("Outline A", "StorageESPOutlineA", 128, 0, 255);
        this.a = this.create("Solid A", "StorageESPSolidA", 32, 0, 255);
        this.name = "Storage ESP";
        this.tag = "StorageESP";
        this.description = "Is able to see storages in world";
    }
}
