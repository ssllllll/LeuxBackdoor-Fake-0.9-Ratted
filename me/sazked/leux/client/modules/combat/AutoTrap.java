// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.combat;

import java.util.List;
import me.sazked.leux.client.util.BlockUtil;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import me.sazked.leux.client.util.MessageUtil;
import me.sazked.leux.client.modules.Category;
import net.minecraft.block.Block;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import me.sazked.leux.client.util.EntityUtil;
import me.sazked.leux.client.util.FriendUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoTrap extends Module
{
    public boolean first_run;
    public String last_tick_target_name;
    public Setting blocks_per_tick;
    public Setting place_mode;
    public int offset_step;
    public Setting chad_mode;
    public Setting rotate;
    public int timeout_ticker;
    public Vec3d[] offsets_default;
    public int y_level;
    public Vec3d[] offsets_feet;
    public Setting range;
    public Vec3d[] offsets_extra;
    public Setting swing;
    public Vec3d[] offsets_face;
    
    public EntityPlayer find_closest_target() {
        if (AutoTrap.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        Object o = null;
        for (final EntityPlayer obf : AutoTrap.mc.world.playerEntities) {
            if (obf == AutoTrap.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(obf.getName())) {
                continue;
            }
            if (!EntityUtil.isLiving((Entity)obf)) {
                continue;
            }
            if (obf.getHealth() <= 0.0f) {
                continue;
            }
            if (o != null && AutoTrap.mc.player.getDistance((Entity)obf) > AutoTrap.mc.player.getDistance((Entity)o)) {
                continue;
            }
            o = obf;
        }
        return (EntityPlayer)o;
    }
    
    public int find_obi_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = AutoTrap.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY && stackInSlot.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                if (block instanceof BlockEnderChest) {
                    return i;
                }
                if (block instanceof BlockObsidian) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public void enable() {
        this.timeout_ticker = 0;
        this.y_level = (int)Math.round(AutoTrap.mc.player.posY);
        this.first_run = true;
        if (this.find_obi_in_hotbar() == -1) {
            this.set_disable();
        }
    }
    
    public AutoTrap() {
        super(Category.combat);
        this.place_mode = this.create("Place Mode", "TrapPlaceMode", "Feet", this.combobox("Extra", "Face", "Normal", "Feet"));
        this.blocks_per_tick = this.create("Speed", "TrapSpeed", 2, 0, 8);
        this.rotate = this.create("Rotation", "TrapRotation", true);
        this.chad_mode = this.create("Chad Mode", "TrapChadMode", true);
        this.range = this.create("Range", "TrapRange", 5, 1, 6);
        this.swing = this.create("Swing", "TrapSwing", "Mainhand", this.combobox("Mainhand", "Offhand", "Both", "None"));
        this.offsets_default = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.203093092641301E307) ^ 0x7FB121F303563D1FL), Double.longBitsToDouble(Double.doubleToLongBits(4.746515950881622E307) ^ 0x7FD0E5EDA9B3DD31L), Double.longBitsToDouble(Double.doubleToLongBits(-6.401334663652192) ^ 0x7FE99AF7795C8C9FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.538944093761976) ^ 0x7FEA27E0F5E451F7L), Double.longBitsToDouble(Double.doubleToLongBits(1.6791356813081162E308) ^ 0x7FEDE3BD4D6813E7L), Double.longBitsToDouble(Double.doubleToLongBits(1.2665064477514745E308) ^ 0x7FE68B687CE43ED4L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.6373396858379423E308) ^ 0x7FED2546F32CFA3DL), Double.longBitsToDouble(Double.doubleToLongBits(3.319101820453518E307) ^ 0x7FC7A1FE7CD14AA3L), Double.longBitsToDouble(Double.doubleToLongBits(5.06913607097689) ^ 0x7FE446CB9B2F4759L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-24.618904020680784) ^ 0x7FC89E707E702FD7L), Double.longBitsToDouble(Double.doubleToLongBits(3.2625296513025827E307) ^ 0x7FC73AE01D2A255FL), Double.longBitsToDouble(Double.doubleToLongBits(1.682291758521265E308) ^ 0x7FEDF21F1E0F3664L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.511285465803788E307) ^ 0x7FDABDB65D04F155L), Double.longBitsToDouble(Double.doubleToLongBits(5.513805495305501) ^ 0x7FE60E23071B5E6CL), Double.longBitsToDouble(Double.doubleToLongBits(-7.1741811170317655) ^ 0x7FECB25C88E4EDC1L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.00963146147591) ^ 0x7FDA04EE6A381A7BL), Double.longBitsToDouble(Double.doubleToLongBits(4.602218583000426) ^ 0x7FE268ABFCFC1F05L), Double.longBitsToDouble(Double.doubleToLongBits(5.044709887712195E305) ^ 0x7F66FD0E253346FFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.775080925603539E307) ^ 0x7FC3C25D087B7873L), Double.longBitsToDouble(Double.doubleToLongBits(7.246514116939542) ^ 0x7FECFC6E325907D6L), Double.longBitsToDouble(Double.doubleToLongBits(7.983524267290541) ^ 0x7FEFEF20FC4B5748L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-9.758717348022108) ^ 0x7FD3847699A9535BL), Double.longBitsToDouble(Double.doubleToLongBits(61.48223194553719) ^ 0x7FBEBDB9C6C19597L), Double.longBitsToDouble(Double.doubleToLongBits(1.445485505591964E307) ^ 0x7FB4959B088FB397L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.7903499829984539E308) ^ 0x7FEFDE89A1206253L), Double.longBitsToDouble(Double.doubleToLongBits(0.9065384346641627) ^ 0x7FED025CE42E6338L), Double.longBitsToDouble(Double.doubleToLongBits(-39.08368921623474) ^ 0x7FB38AB6540760C7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.879293864329716) ^ 0x7FDBC232CE2DBD3BL), Double.longBitsToDouble(Double.doubleToLongBits(0.9986885373551203) ^ 0x7FEFF541A9DA91F3L), Double.longBitsToDouble(Double.doubleToLongBits(4.867518487442553E307) ^ 0x7FD154357AAB928DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.7385784501447651E308) ^ 0x7FEEF29E04F056B3L), Double.longBitsToDouble(Double.doubleToLongBits(0.14498346859012043) ^ 0x7FC28ED17C071123L), Double.longBitsToDouble(Double.doubleToLongBits(9.036278409404824) ^ 0x7FD21293156BE3A5L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-6.285153338910829) ^ 0x7FE923FF3CA3DE6EL), Double.longBitsToDouble(Double.doubleToLongBits(0.686146730617326) ^ 0x7FE5F4E9FD084793L), Double.longBitsToDouble(Double.doubleToLongBits(3.4056991763439936E307) ^ 0x7FC83FD791843E23L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.545435701011217E307) ^ 0x7FD02EAA7147ABBFL), Double.longBitsToDouble(Double.doubleToLongBits(0.18908840452137562) ^ 0x7FC0340C80BC6EA7L), Double.longBitsToDouble(Double.doubleToLongBits(-6.9479159459226345) ^ 0x7FEBCAAA7A4C60E4L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.827683019996697E307) ^ 0x7FD84EAF0A51AD09L), Double.longBitsToDouble(Double.doubleToLongBits(0.7985850459940396) ^ 0x7FE18E0239F3CF4EL), Double.longBitsToDouble(Double.doubleToLongBits(7.198483018232994) ^ 0x7FECCB3F21E07D03L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.289773543386206) ^ 0x7FDA945D32A82DF9L), Double.longBitsToDouble(Double.doubleToLongBits(0.31459013837310495) ^ 0x7FDC223EACFD38F1L), Double.longBitsToDouble(Double.doubleToLongBits(1.7737748789615978E308) ^ 0x7FEF930173D2EDADL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-60.67668697122638) ^ 0x7FBE569DADBD85F7L), Double.longBitsToDouble(Double.doubleToLongBits(0.6390074181994776) ^ 0x7FEC72BFAF622E4CL), Double.longBitsToDouble(Double.doubleToLongBits(1.051106609381495E308) ^ 0x7FE2B5D772771D15L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(3.2533700647901074E307) ^ 0x7FC72A2DF5A1A9C3L), Double.longBitsToDouble(Double.doubleToLongBits(0.8340878670042465) ^ 0x7FE2B0D909D8C1A1L), Double.longBitsToDouble(Double.doubleToLongBits(1.6441907657348349E308) ^ 0x7FED447F47885647L)) };
        this.offsets_face = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.2717179592656383E308) ^ 0x7FE6A3282166CA9EL), Double.longBitsToDouble(Double.doubleToLongBits(1.2727772331460537E308) ^ 0x7FE6A7FBDB91C4BFL), Double.longBitsToDouble(Double.doubleToLongBits(-29.843410243610375) ^ 0x7FCDD7E9BBD56AFFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.984700962659553) ^ 0x7FEFF05572FBD999L), Double.longBitsToDouble(Double.doubleToLongBits(1.4146879213800024E308) ^ 0x7FE92EA9F5F06D47L), Double.longBitsToDouble(Double.doubleToLongBits(3.8893908792803916E307) ^ 0x7FCBB18180BD1797L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.3393351982959671E308) ^ 0x7FE7D74904560CEAL), Double.longBitsToDouble(Double.doubleToLongBits(1.7512961923769696E308) ^ 0x7FEF2C9246E6E72AL), Double.longBitsToDouble(Double.doubleToLongBits(6.885254638904562) ^ 0x7FEB8A80312AE8D7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-9.245154111553541) ^ 0x7FD27D84D6F7346DL), Double.longBitsToDouble(Double.doubleToLongBits(6.248535154360489E307) ^ 0x7FD63EDAA1D2FE69L), Double.longBitsToDouble(Double.doubleToLongBits(1.151300912543443E308) ^ 0x7FE47E6C11CCE925L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(8.254950160494022E307) ^ 0x7FDD637B2A11F68BL), Double.longBitsToDouble(Double.doubleToLongBits(6.593383862942134) ^ 0x7FEA5FA004F53E11L), Double.longBitsToDouble(Double.doubleToLongBits(-7.929587209780411) ^ 0x7FEFB7E5B5A325A4L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(14.950314417134868) ^ 0x7FDDE68F9C7D05EBL), Double.longBitsToDouble(Double.doubleToLongBits(6.2183787348933395) ^ 0x7FE8DF9EACD208F5L), Double.longBitsToDouble(Double.doubleToLongBits(6.206516200738126E307) ^ 0x7FD6188EF123A169L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.3687224192425478E308) ^ 0x7FE85D338C111A6FL), Double.longBitsToDouble(Double.doubleToLongBits(10.473413598051946) ^ 0x7FD4F26344623AC7L), Double.longBitsToDouble(Double.doubleToLongBits(6.9517902117059664) ^ 0x7FEBCEA217DFB85DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-7.471998918370165) ^ 0x7FEDE353AF3896C4L), Double.longBitsToDouble(Double.doubleToLongBits(9.714308139514602) ^ 0x7FD36DB9CBE4F6B1L), Double.longBitsToDouble(Double.doubleToLongBits(1.5569302773594446E308) ^ 0x7FEBB6DAFB0283BBL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.570750056532383E306) ^ 0x7F9FBB67579B5FBFL), Double.longBitsToDouble(Double.doubleToLongBits(0.5778700814179963) ^ 0x7FE27DE965A0DE6DL), Double.longBitsToDouble(Double.doubleToLongBits(-12.417619842300818) ^ 0x7FD8D5D24499AF75L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.762258968108788E306) ^ 0x7FA342720E052F3FL), Double.longBitsToDouble(Double.doubleToLongBits(0.6522485808003271) ^ 0x7FECDF386A6CCB50L), Double.longBitsToDouble(Double.doubleToLongBits(-6.663582759184109) ^ 0x7FEAA7823D238A70L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.4096329754308375E307) ^ 0x7FC1283B651BF17BL), Double.longBitsToDouble(Double.doubleToLongBits(0.5467816588931674) ^ 0x7FE97F3C3FDFF605L), Double.longBitsToDouble(Double.doubleToLongBits(146.00051341121613) ^ 0x7F92400434B38C3FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.657973941006055) ^ 0x7FEAA1C3EBB8F708L), Double.longBitsToDouble(Double.doubleToLongBits(0.56781436414513) ^ 0x7FEA2B89078679CDL), Double.longBitsToDouble(Double.doubleToLongBits(1.5625962046289734E308) ^ 0x7FEBD0ACBC38A461L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-29.864848928853416) ^ 0x7FCDDD66BD4967F3L), Double.longBitsToDouble(Double.doubleToLongBits(0.48930039334186387) ^ 0x7FD750B298D4AFFDL), Double.longBitsToDouble(Double.doubleToLongBits(8.258314791029339E307) ^ 0x7FDD668C2FA7C465L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.260815048266146E308) ^ 0x7FE67179048BEF01L), Double.longBitsToDouble(Double.doubleToLongBits(0.26572194578552794) ^ 0x7FD901969EBE9CEBL), Double.longBitsToDouble(Double.doubleToLongBits(9.392750994645907E307) ^ 0x7FE0B83B18FDA602L)) };
        this.offsets_feet = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.3176160821961523E308) ^ 0x7FE7744FF0DA98BCL), Double.longBitsToDouble(Double.doubleToLongBits(1.4335426419705167E308) ^ 0x7FE984957DDD1B93L), Double.longBitsToDouble(Double.doubleToLongBits(-7.72097712861342) ^ 0x7FEEE247D4123C08L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.282310995868167) ^ 0x7FE52116223A384AL), Double.longBitsToDouble(Double.doubleToLongBits(9.242807950190757E307) ^ 0x7FE073E70C9134BEL), Double.longBitsToDouble(Double.doubleToLongBits(9.584523890483053E307) ^ 0x7FE10F9EEE5340DCL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4287565340982643E308) ^ 0x7FE96EC61DC05214L), Double.longBitsToDouble(Double.doubleToLongBits(6.815944424316113E307) ^ 0x7FD843FC3D3EBC63L), Double.longBitsToDouble(Double.doubleToLongBits(19.633442157898838) ^ 0x7FC3A22943E8154BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-51.2213215786275) ^ 0x7FB99C5443F70D57L), Double.longBitsToDouble(Double.doubleToLongBits(1.6085072951456118E308) ^ 0x7FECA1E3AC5EBBDCL), Double.longBitsToDouble(Double.doubleToLongBits(6.41722301127497E307) ^ 0x7FD6D898312AEB67L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.385101547273184E308) ^ 0x7FE8A7D71A3DA6BAL), Double.longBitsToDouble(Double.doubleToLongBits(7.891587663015945) ^ 0x7FEF90FC5B38AEA5L), Double.longBitsToDouble(Double.doubleToLongBits(-15.379502187839437) ^ 0x7FDEC24E1C5B0FC9L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.916488786050625E307) ^ 0x7FD180D70758E289L), Double.longBitsToDouble(Double.doubleToLongBits(0.3936174603355194) ^ 0x7FD9310749D1A3F9L), Double.longBitsToDouble(Double.doubleToLongBits(-15.16662670170268) ^ 0x7FDE55501854E81FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.695215209226927) ^ 0x7FEAC7E67EED3EECL), Double.longBitsToDouble(Double.doubleToLongBits(0.7245151878363381) ^ 0x7FE72F3A79A6CBBAL), Double.longBitsToDouble(Double.doubleToLongBits(1.4507173758472634E308) ^ 0x7FE9D2D92F3C1B0AL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.355284224693178E307) ^ 0x7FC0C52A8A0083BBL), Double.longBitsToDouble(Double.doubleToLongBits(0.13973271718492683) ^ 0x7FC1E2C2FD3EC7FBL), Double.longBitsToDouble(Double.doubleToLongBits(28.644144769293785) ^ 0x7FCCA4E6ABEE019BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-7.852952310260027) ^ 0x7FEF696C54967535L), Double.longBitsToDouble(Double.doubleToLongBits(0.260050181216581) ^ 0x7FD0A4A983E938C1L), Double.longBitsToDouble(Double.doubleToLongBits(1.5825538245084245E308) ^ 0x7FEC2B9EE2CBB8D2L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.306470709552497E307) ^ 0x7FD2E4440393829BL), Double.longBitsToDouble(Double.doubleToLongBits(0.7662195261349637) ^ 0x7FE084DECFC9CD19L), Double.longBitsToDouble(Double.doubleToLongBits(-11.38796954010171) ^ 0x7FD6C6A3F18D2979L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4127430010810514E308) ^ 0x7FE925CD0E771C91L), Double.longBitsToDouble(Double.doubleToLongBits(0.2413753350562001) ^ 0x7FC6E56311104F5BL), Double.longBitsToDouble(Double.doubleToLongBits(7.6721816170105654) ^ 0x7FEEB05060B82190L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.1121296132224225) ^ 0x7FE072D21AF6D051L), Double.longBitsToDouble(Double.doubleToLongBits(0.05851914395014667) ^ 0x7FA5F638A2E95E1FL), Double.longBitsToDouble(Double.doubleToLongBits(1.2454683672549685E308) ^ 0x7FE62B89E4D98D64L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.819719577383992) ^ 0x7FE3476491A30550L), Double.longBitsToDouble(Double.doubleToLongBits(0.4757371699425326) ^ 0x7FD6727A509943F1L), Double.longBitsToDouble(Double.doubleToLongBits(1.54245321365931E308) ^ 0x7FEB74E2559DD3DDL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.786948813815952E308) ^ 0x7FEFCF09E523CAABL), Double.longBitsToDouble(Double.doubleToLongBits(0.7287386519803102) ^ 0x7FEF51D3B8B2C515L), Double.longBitsToDouble(Double.doubleToLongBits(2.4891379007037316E307) ^ 0x7FC1B926EC0D9327L)) };
        this.offsets_extra = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.5706216365014208E308) ^ 0x7FEBF53F0A2B7AEAL), Double.longBitsToDouble(Double.doubleToLongBits(1.4514104794284957E308) ^ 0x7FE9D601BEB57271L), Double.longBitsToDouble(Double.doubleToLongBits(-8.546713756454162) ^ 0x7FD117EADD907CE5L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.879361809678115) ^ 0x7FE384776C17ACEFL), Double.longBitsToDouble(Double.doubleToLongBits(1.1734093116280134E308) ^ 0x7FE4E32B46731FEDL), Double.longBitsToDouble(Double.doubleToLongBits(1.6957386923650064E308) ^ 0x7FEE2F6608FBB2A1L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4921283771394001E308) ^ 0x7FEA8F8E6920653CL), Double.longBitsToDouble(Double.doubleToLongBits(7.277481248806132E306) ^ 0x7FA4BA19B25F853FL), Double.longBitsToDouble(Double.doubleToLongBits(10.795044513590062) ^ 0x7FD5971013117793L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-15.53662437365247) ^ 0x7FDF12C06E0E25CFL), Double.longBitsToDouble(Double.doubleToLongBits(1.0352826262053988E308) ^ 0x7FE26DBB832B7F29L), Double.longBitsToDouble(Double.doubleToLongBits(1.5081560959429439E308) ^ 0x7FEAD89804EAE5BAL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.0166697358056767E307) ^ 0x7FBCB7E47EB5F4DFL), Double.longBitsToDouble(Double.doubleToLongBits(7.176791228613748) ^ 0x7FECB508C284767CL), Double.longBitsToDouble(Double.doubleToLongBits(-14.525395158000896) ^ 0x7FDD0D00981A2E63L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.51409415901373) ^ 0x7FE20E6EB3001D48L), Double.longBitsToDouble(Double.doubleToLongBits(6.99709893169006) ^ 0x7FEBFD078099F0FEL), Double.longBitsToDouble(Double.doubleToLongBits(1.5224465274498567E308) ^ 0x7FEB19B6F1A8CC19L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.958025001833712E307) ^ 0x7FD1A6B216A553B5L), Double.longBitsToDouble(Double.doubleToLongBits(13.362374060238045) ^ 0x7FDAB98917C34843L), Double.longBitsToDouble(Double.doubleToLongBits(7.0544585578731285) ^ 0x7FEC37C3FBF435ABL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-18.138214817394807) ^ 0x7FC223620BD88887L), Double.longBitsToDouble(Double.doubleToLongBits(20.182747866526338) ^ 0x7FC42EC8906E24F7L), Double.longBitsToDouble(Double.doubleToLongBits(1.185444330098763E308) ^ 0x7FE51A0314D03F9DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.0308616475167355E308) ^ 0x7FE2599616D3FB21L), Double.longBitsToDouble(Double.doubleToLongBits(0.3903442685073993) ^ 0x7FD8FB6686DAE691L), Double.longBitsToDouble(Double.doubleToLongBits(-4.771366256482698) ^ 0x7FE315E109335315L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(11.010188489442188) ^ 0x7FD605376CF9E651L), Double.longBitsToDouble(Double.doubleToLongBits(0.007747103618337413) ^ 0x7F7FBB6D4ADF28FFL), Double.longBitsToDouble(Double.doubleToLongBits(8.335727291757925E306) ^ 0x7FA7BDAE33F9EE8FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.279333559057983E308) ^ 0x7FE6C5DC554FCB68L), Double.longBitsToDouble(Double.doubleToLongBits(0.8694916058096133) ^ 0x7FEBD2E00F632979L), Double.longBitsToDouble(Double.doubleToLongBits(6.58791756094845) ^ 0x7FEA5A070FA4121BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.143920110318881) ^ 0x7FE0935FCB1C39A8L), Double.longBitsToDouble(Double.doubleToLongBits(0.5836215778989534) ^ 0x7FE2AD0728CA1C07L), Double.longBitsToDouble(Double.doubleToLongBits(3.879619858771839E306) ^ 0x7F96195B3A24E99FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4391761546915866E308) ^ 0x7FE99E416EA73342L), Double.longBitsToDouble(Double.doubleToLongBits(0.9985536928252066) ^ 0x7FE7F426DFBF7F52L), Double.longBitsToDouble(Double.doubleToLongBits(-5.954093261752149) ^ 0x7FE7D0FDD2F23CE0L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.634123179960116E307) ^ 0x7FD79E46737D5931L), Double.longBitsToDouble(Double.doubleToLongBits(0.5428974589427238) ^ 0x7FE95F6A7DE7B230L), Double.longBitsToDouble(Double.doubleToLongBits(1.3794158325204463E308) ^ 0x7FE88DEE439BF2AFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(8.442402869279295E307) ^ 0x7FDE0E52DCD76071L), Double.longBitsToDouble(Double.doubleToLongBits(0.13054684013028092) ^ 0x7FD0B5C2447A5447L), Double.longBitsToDouble(Double.doubleToLongBits(1.0919283409969979E307) ^ 0x7FAF1961FD44BD9FL)) };
        this.last_tick_target_name = "";
        this.offset_step = 0;
        this.timeout_ticker = 0;
        this.first_run = true;
        this.name = "AutoTrap";
        this.tag = "AutoTrap";
        this.description = "cover people in obsidian :o";
    }
    
    @Override
    public void update() {
        final int obf = AutoTrap.mc.player.inventory.currentItem;
        final int obf2 = 20;
        if (this.timeout_ticker > obf2 && this.chad_mode.get_value(true)) {
            this.timeout_ticker = 0;
            this.set_disable();
            return;
        }
        final EntityPlayer obf3 = this.find_closest_target();
        if (obf3 == null) {
            this.set_disable();
            MessageUtil.toggle_message(this);
            return;
        }
        if (this.chad_mode.get_value(true) && (int)Math.round(AutoTrap.mc.player.posY) != this.y_level) {
            this.set_disable();
            MessageUtil.toggle_message(this);
            return;
        }
        if (this.first_run) {
            this.first_run = false;
            this.last_tick_target_name = obf3.getName();
        }
        else if (!this.last_tick_target_name.equals(obf3.getName())) {
            this.last_tick_target_name = obf3.getName();
            this.offset_step = 0;
        }
        final List<Vec3d> obf4 = new ArrayList<Vec3d>();
        if (this.place_mode.in("Normal")) {
            Collections.addAll(obf4, this.offsets_default);
        }
        else if (this.place_mode.in("Extra")) {
            Collections.addAll(obf4, this.offsets_extra);
        }
        else if (this.place_mode.in("Feet")) {
            Collections.addAll(obf4, this.offsets_feet);
        }
        else {
            Collections.addAll(obf4, this.offsets_face);
        }
        int obf5 = 0;
    Label_0542:
        while (obf5 < this.blocks_per_tick.get_value(1)) {
            if (this.offset_step >= obf4.size()) {
                this.offset_step = 0;
                break;
            }
            final BlockPos obf6 = new BlockPos((Vec3d)obf4.get(this.offset_step));
            final BlockPos obf7 = new BlockPos(obf3.getPositionVector()).down().add(obf6.getX(), obf6.getY(), obf6.getZ());
            boolean obf8 = true;
            if (!AutoTrap.mc.world.getBlockState(obf7).getMaterial().isReplaceable()) {
                obf8 = false;
            }
            while (true) {
                for (final Entity obf9 : AutoTrap.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(obf7))) {
                    if (!(obf9 instanceof EntityItem) && !(obf9 instanceof EntityXPOrb)) {
                        obf8 = false;
                        if (obf8) {
                            if (BlockUtil.placeBlock(obf7, this.find_obi_in_hotbar(), this.rotate.get_value(true), this.rotate.get_value(true), this.swing)) {
                                ++obf5;
                            }
                        }
                        ++this.offset_step;
                        continue Label_0542;
                    }
                }
                continue;
            }
        }
        ++this.timeout_ticker;
        AutoTrap.mc.player.inventory.currentItem = obf;
    }
}
