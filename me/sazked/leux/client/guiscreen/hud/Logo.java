// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.guiscreen.hud;

import me.sazked.leux.client.util.TextureHelper;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import me.sazked.leux.client.guiscreen.render.pinnables.Pinnable;

public class Logo extends Pinnable
{
    ResourceLocation r;
    
    public Logo() {
        super("Logo", "Logo", 1.0f, 0, 0);
        this.r = new ResourceLocation("custom/leux.png");
    }
    
    @Override
    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.get_x(), (float)this.get_y(), 0.0f);
        TextureHelper.drawTexture(this.r, (float)this.get_x(), (float)this.get_y(), 100.0f, 25.0f);
        GL11.glPopMatrix();
        this.set_width(100);
        this.set_height(25);
    }
}
