// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.render;

import java.util.function.Predicate;
import me.sazked.leux.client.modules.Category;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.common.MinecraftForge;
import me.zero.alpine.fork.listener.EventHandler;
import me.sazked.leux.client.event.events.EventFirstPerson;
import me.zero.alpine.fork.listener.Listener;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class ViewmodleChanger extends Module
{
    public Setting xRight;
    public float fov;
    @EventHandler
    public Listener<EventFirstPerson> eventListener;
    public Setting yRight;
    public Setting zRight;
    public Setting custom_fov;
    public Setting xLeft;
    public Setting scaleLeft;
    public Setting scaleRight;
    public Setting rotateRightX;
    public Setting rotateLeftZ;
    public Setting cancelEating;
    public Setting rotateRightY;
    public Setting yLeft;
    public Setting rotateRightZ;
    public Setting rotateLeftX;
    public Setting zLeft;
    public Setting rotateLeftY;
    
    @Override
    public void enable() {
        this.fov = ViewmodleChanger.mc.gameSettings.fovSetting;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @Override
    public void disable() {
        ViewmodleChanger.mc.gameSettings.fovSetting = this.fov;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void lambda$new$0(final EventFirstPerson obf) {
        if (obf.getHandSide() == EnumHandSide.RIGHT) {
            GL11.glTranslatef((float)this.xRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(15.752963344418914) ^ 0x7FDF81846956BB67L)) / Float.intBitsToFloat(Float.floatToIntBits(0.095588915f) ^ 0x7F0BC41F), (float)this.yRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(11.529585497059808) ^ 0x7FD70F25D48C9D95L)) / Float.intBitsToFloat(Float.floatToIntBits(0.07384073f) ^ 0x7F5F39CF), (float)this.zRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(4.351423692270328) ^ 0x7FE167DB9CC55CCDL)) / Float.intBitsToFloat(Float.floatToIntBits(0.10006745f) ^ 0x7F04F02A));
            GL11.glRotatef((float)this.rotateRightX.get_value(Double.longBitsToDouble(Double.doubleToLongBits(4.597796878404285) ^ 0x7FE26424DD6996D4L)), Float.intBitsToFloat(Float.floatToIntBits(14.711097f) ^ 0x7EEB60A7), Float.intBitsToFloat(Float.floatToIntBits(8.799853E37f) ^ 0x7E8467CD), Float.intBitsToFloat(Float.floatToIntBits(2.3716868E37f) ^ 0x7D8EBD9F));
            GL11.glRotatef((float)this.rotateRightY.get_value(Double.longBitsToDouble(Double.doubleToLongBits(28.04849866373699) ^ 0x7FCC0C6A688EA66BL)), Float.intBitsToFloat(Float.floatToIntBits(3.0180323E38f) ^ 0x7F630D30), Float.intBitsToFloat(Float.floatToIntBits(1125.5624f) ^ 0x7B0CB1FF), Float.intBitsToFloat(Float.floatToIntBits(1.5150618E38f) ^ 0x7EE3F60F));
            GL11.glRotatef((float)this.rotateRightZ.get_value(Double.longBitsToDouble(Double.doubleToLongBits(58.92445090820518) ^ 0x7FBD76546848BFD7L)), Float.intBitsToFloat(Float.floatToIntBits(7.253551E37f) ^ 0x7E5A4753), Float.intBitsToFloat(Float.floatToIntBits(3.335889E38f) ^ 0x7F7AF6E3), Float.intBitsToFloat(Float.floatToIntBits(4.925451f) ^ 0x7F1D9D4B));
            GL11.glScalef((float)this.scaleRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(13.180295104514665) ^ 0x7FDA5C4FA3D309EDL)) / Float.intBitsToFloat(Float.floatToIntBits(0.22812241f) ^ 0x7F4998EC), (float)this.scaleRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(21.28390602998024) ^ 0x7FC548AE10C9E6FFL)) / Float.intBitsToFloat(Float.floatToIntBits(0.011759697f) ^ 0x7D60ABBF), (float)this.scaleRight.get_value(Double.longBitsToDouble(Double.doubleToLongBits(4.255167353568224) ^ 0x7FE1054A973A545EL)) / Float.intBitsToFloat(Float.floatToIntBits(1.5744542f) ^ 0x7EE987B7));
        }
        else if (obf.getHandSide() == EnumHandSide.LEFT) {
            GL11.glTranslatef((float)this.xLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(295.4437305887731) ^ 0x7F827719853EF03FL)) / Float.intBitsToFloat(Float.floatToIntBits(0.067944095f) ^ 0x7F432646), (float)this.yLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(7.251973756563039) ^ 0x7FED0205688F8DD8L)) / Float.intBitsToFloat(Float.floatToIntBits(0.07182695f) ^ 0x7F5B1A02), (float)this.zLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(6.622693612723329) ^ 0x7FEA7DA364F84C95L)) / Float.intBitsToFloat(Float.floatToIntBits(0.011952302f) ^ 0x7E8BD397));
            GL11.glRotatef((float)this.rotateLeftX.get_value(Double.longBitsToDouble(Double.doubleToLongBits(39.64344408357817) ^ 0x7FB3D25C602FE2EFL)), Float.intBitsToFloat(Float.floatToIntBits(4.811701f) ^ 0x7F19F974), Float.intBitsToFloat(Float.floatToIntBits(3.2148041E38f) ^ 0x7F71DAE0), Float.intBitsToFloat(Float.floatToIntBits(2.1478538E38f) ^ 0x7F219629));
            GL11.glRotatef((float)this.rotateLeftY.get_value(Double.longBitsToDouble(Double.doubleToLongBits(7.167522149652038) ^ 0x7FECAB8AED2871CAL)), Float.intBitsToFloat(Float.floatToIntBits(9.006439E37f) ^ 0x7E87838B), Float.intBitsToFloat(Float.floatToIntBits(4.362088f) ^ 0x7F0B963A), Float.intBitsToFloat(Float.floatToIntBits(2.957023E38f) ^ 0x7F5E7631));
            GL11.glRotatef((float)this.rotateLeftZ.get_value(Double.longBitsToDouble(Double.doubleToLongBits(21.41345920639085) ^ 0x7FC569D87669ADC7L)), Float.intBitsToFloat(Float.floatToIntBits(1.0799478E38f) ^ 0x7EA27E13), Float.intBitsToFloat(Float.floatToIntBits(8.919919E36f) ^ 0x7CD6BD3F), Float.intBitsToFloat(Float.floatToIntBits(50.158443f) ^ 0x7DC8A23F));
            GL11.glScalef((float)this.scaleLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(52.71977368240572) ^ 0x7FBA5C218B453A1FL)) / Float.intBitsToFloat(Float.floatToIntBits(0.16624312f) ^ 0x7F0A3BA3), (float)this.scaleLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(22.059502229644348) ^ 0x7FC60F3B89C25C8FL)) / Float.intBitsToFloat(Float.floatToIntBits(0.008540257f) ^ 0x7D2BEC6F), (float)this.scaleLeft.get_value(Double.longBitsToDouble(Double.doubleToLongBits(11.69848751331609) ^ 0x7FD765A027C4B6C5L)) / Float.intBitsToFloat(Float.floatToIntBits(1.3355688f) ^ 0x7E8AF3EB));
        }
    }
    
    public ViewmodleChanger() {
        super(Category.render);
        this.custom_fov = this.create("FOV", "FOVSlider", 130, 10, 170);
        this.cancelEating = this.create("No Eat", "NoEat", false);
        this.xRight = this.create("Right X", "RightX", Double.longBitsToDouble(Double.doubleToLongBits(9.325998356233008E307) ^ 0x7FE099CFDF1B62F7L), Double.longBitsToDouble(Double.doubleToLongBits(-0.057726311380207375) ^ 0x7FE48E4D96FE4FE5L), Double.longBitsToDouble(Double.doubleToLongBits(0.04587517792413294) ^ 0x7FEE7CF389C3C10AL));
        this.yRight = this.create("Right Y", "RightY", Double.longBitsToDouble(Double.doubleToLongBits(3.163269758463134E306) ^ 0x7F9204C1D51A65BFL), Double.longBitsToDouble(Double.doubleToLongBits(-0.020986829719857058) ^ 0x7FDC7D924D2C8D13L), Double.longBitsToDouble(Double.doubleToLongBits(1.4650449168997672) ^ 0x7FBE70D2F05416AFL));
        this.zRight = this.create("Right Z", "RightZ", Double.longBitsToDouble(Double.doubleToLongBits(1.2765310715015062E308) ^ 0x7FE6B9170236FDE8L), Double.longBitsToDouble(Double.doubleToLongBits(-0.029808067589528548) ^ 0x7FD786018DD0E0D5L), Double.longBitsToDouble(Double.doubleToLongBits(0.02147522865189785) ^ 0x7FDCFD9A32EFA3D7L));
        this.scaleRight = this.create("Scale Right", "ScaleRight", Double.longBitsToDouble(Double.doubleToLongBits(0.03009477570083782) ^ 0x7FBAD12A35A986F7L), Double.longBitsToDouble(Double.doubleToLongBits(1.1062813696336705E308) ^ 0x7FE3B14531507260L), Double.longBitsToDouble(Double.doubleToLongBits(0.04912795672987129) ^ 0x7FE0274CAEF2BD46L));
        this.rotateRightX = this.create("Rotate Right X", "RotateRightX", 0, -360, 360);
        this.rotateRightY = this.create("Rotate Right Y", "RotateRightY", 0, -360, 360);
        this.rotateRightZ = this.create("Rotate Right Z", "RotateRightZ", 0, -360, 360);
        this.scaleLeft = this.create("Scale Left", "ScaleLeft", Double.longBitsToDouble(Double.doubleToLongBits(0.23047239578653608) ^ 0x7FE9801E9544582BL), Double.longBitsToDouble(Double.doubleToLongBits(2.3835557429100497E307) ^ 0x7FC0F8B2EDC39ED3L), Double.longBitsToDouble(Double.doubleToLongBits(0.023485548536675564) ^ 0x7FD10C987B9316F1L));
        this.xLeft = this.create("Left X", "LeftX", Double.longBitsToDouble(Double.doubleToLongBits(1.1742016186647568E308) ^ 0x7FE4E6C79079D933L), Double.longBitsToDouble(Double.doubleToLongBits(-0.057903335720737845) ^ 0x7FE4A5818A7FEFF6L), Double.longBitsToDouble(Double.doubleToLongBits(0.012296429503994939) ^ 0x7FC02EDED49E2997L));
        this.yLeft = this.create("Left Y", "LeftY", Double.longBitsToDouble(Double.doubleToLongBits(1.4136820321760606E308) ^ 0x7FE92A1482CDC05EL), Double.longBitsToDouble(Double.doubleToLongBits(-0.04396965781189069) ^ 0x7FEF8330E4A302E4L), Double.longBitsToDouble(Double.doubleToLongBits(0.0367768284998133) ^ 0x7FEBD4699750897AL));
        this.zLeft = this.create("Left Z", "LeftZ", Double.longBitsToDouble(Double.doubleToLongBits(1.5525335171512287E308) ^ 0x7FEBA2D1CF6168EBL), Double.longBitsToDouble(Double.doubleToLongBits(-0.02786037032544923) ^ 0x7FD5876DCD9BE84BL), Double.longBitsToDouble(Double.doubleToLongBits(1.3483618049360009) ^ 0x7FBC92E3D3F602AFL));
        this.rotateLeftX = this.create("Rotate Left X", "RotateLeftX", 0, -360, 360);
        this.rotateLeftY = this.create("Rotate Left Y", "RotateLeftY", 0, -360, 360);
        this.rotateLeftZ = this.create("Rotate Left Z", "RotateLeftZ", 0, -360, 360);
        this.eventListener = new Listener<EventFirstPerson>(this::lambda$new$0, (Predicate<EventFirstPerson>[])new Predicate[0]);
        this.name = "Custom Viewmodel";
        this.tag = "CustomViewmodel";
        this.description = "anti chad";
    }
    
    @Override
    public void update() {
        ViewmodleChanger.mc.gameSettings.fovSetting = (float)this.custom_fov.get_value(1);
    }
}
