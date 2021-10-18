// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.misc;

import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import me.sazked.leux.client.util.MessageUtil;
import java.util.Iterator;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import me.sazked.leux.client.modules.Category;
import net.minecraft.block.Block;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3i;
import net.minecraft.network.Packet;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.util.EnumFacing;
import me.sazked.leux.client.util.BlockInteractHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import me.sazked.leux.client.guiscreen.settings.Setting;
import me.sazked.leux.client.modules.Module;

public class AutoNomadHut extends Module
{
    public Setting rotate;
    public int new_slot;
    public boolean sneak;
    public Vec3d[] targets;
    public int offset_step;
    public int tick_runs;
    public int old_slot;
    public int blocks_placed;
    public int y_level;
    public Setting tick_for_place;
    public Setting triggerable;
    
    public boolean place_blocks(final BlockPos obf) {
        if (!AutoNomadHut.mc.world.getBlockState(obf).getMaterial().isReplaceable()) {
            return false;
        }
        if (!BlockInteractHelper.checkForNeighbours(obf)) {
            return false;
        }
        for (final EnumFacing obf2 : EnumFacing.values()) {
            final BlockPos obf3 = obf.offset(obf2);
            final EnumFacing obf4 = obf2.getOpposite();
            if (BlockInteractHelper.canBeClicked(obf3)) {
                AutoNomadHut.mc.player.inventory.currentItem = this.new_slot;
                final Block obf5;
                if (BlockInteractHelper.blackList.contains(obf5 = AutoNomadHut.mc.world.getBlockState(obf3).getBlock())) {
                    AutoNomadHut.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoNomadHut.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    this.sneak = true;
                }
                final Vec3d obf6 = new Vec3d((Vec3i)obf3).add(Double.longBitsToDouble(Double.doubleToLongBits(26.707190977824364) ^ 0x7FDAB50A77C9C82BL), Double.longBitsToDouble(Double.doubleToLongBits(3.2944608788440015) ^ 0x7FEA5B0E4E24B0F5L), Double.longBitsToDouble(Double.doubleToLongBits(11.515631808135721) ^ 0x7FC70800E47170C7L)).add(new Vec3d(obf4.getDirectionVec()).scale(Double.longBitsToDouble(Double.doubleToLongBits(2.9187667970689626) ^ 0x7FE759A268107EB2L)));
                if (this.rotate.get_value(true)) {
                    BlockInteractHelper.faceVectorPacketInstant(obf6);
                }
                AutoNomadHut.mc.playerController.processRightClickBlock(AutoNomadHut.mc.player, AutoNomadHut.mc.world, obf3, obf4, obf6, EnumHand.MAIN_HAND);
                AutoNomadHut.mc.player.swingArm(EnumHand.MAIN_HAND);
                return true;
            }
        }
        return false;
    }
    
    public AutoNomadHut() {
        super(Category.misc);
        this.rotate = this.create("Rotate", "NomadSmoth", true);
        this.triggerable = this.create("Toggle", "NomadToggle", true);
        this.tick_for_place = this.create("Blocks per tick", "NomadTickToPlace", 2, 1, 8);
        this.targets = new Vec3d[] { new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(9.844484927647545E307) ^ 0x7FE1861574072EACL), Double.longBitsToDouble(Double.doubleToLongBits(7.604331357066157E307) ^ 0x7FDB1283731C260BL), Double.longBitsToDouble(Double.doubleToLongBits(1.639307723844894E308) ^ 0x7FED2E3ED29F6592L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(9.546312858424022) ^ 0x7FD317B651A8A183L), Double.longBitsToDouble(Double.doubleToLongBits(4.1339862853434557E307) ^ 0x7FCD6F597FEFB11BL), Double.longBitsToDouble(Double.doubleToLongBits(8.204244175037356E307) ^ 0x7FDD3544A6C6BA0DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.5423575786617212E308) ^ 0x7FEB7472C4C399B8L), Double.longBitsToDouble(Double.doubleToLongBits(2.26486389867787E307) ^ 0x7FC0205989EB6F73L), Double.longBitsToDouble(Double.doubleToLongBits(9.461364505411693) ^ 0x7FD2EC37F7EC8E8FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-182.47364801365526) ^ 0x7F96CF281FE10EDFL), Double.longBitsToDouble(Double.doubleToLongBits(1.449451916049122E308) ^ 0x7FE9CD14ECCE4F40L), Double.longBitsToDouble(Double.doubleToLongBits(3.078948152446654E307) ^ 0x7FC5EC3F259FB343L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.6860795945897505E308) ^ 0x7FEE0361EDEE14EBL), Double.longBitsToDouble(Double.doubleToLongBits(1.1322960702211264E308) ^ 0x7FE427D168DE212FL), Double.longBitsToDouble(Double.doubleToLongBits(-35.87292906932651) ^ 0x7FB1EFBC23C63E17L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.1836950472695875) ^ 0x7FE0BC1A8DF1D718L), Double.longBitsToDouble(Double.doubleToLongBits(1.7346093733289412E308) ^ 0x7FEEE087C68AA7BCL), Double.longBitsToDouble(Double.doubleToLongBits(25.70033966404848) ^ 0x7FC9B34975D12E0BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(36.061494057304905) ^ 0x7FB207DF098A82EFL), Double.longBitsToDouble(Double.doubleToLongBits(8.220316050872997E307) ^ 0x7FDD43EA79C8C011L), Double.longBitsToDouble(Double.doubleToLongBits(-4.90516933822469) ^ 0x7FE39EE4B604116AL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-13.200444969902362) ^ 0x7FDA66A0B91CC1B7L), Double.longBitsToDouble(Double.doubleToLongBits(7.836614262587328E307) ^ 0x7FDBE636BEF1EDDDL), Double.longBitsToDouble(Double.doubleToLongBits(13.203453159270792) ^ 0x7FDA682B0332ABFFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.56930310133839) ^ 0x7FE246F7646709FEL), Double.longBitsToDouble(Double.doubleToLongBits(1.2548604223958408E308) ^ 0x7FE6565679207DBAL), Double.longBitsToDouble(Double.doubleToLongBits(-24.777581806472707) ^ 0x7FC8C70F99ECC3CFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.1954721828099509) ^ 0x7FC9053B84392603L), Double.longBitsToDouble(Double.doubleToLongBits(1.7283544066632222E308) ^ 0x7FEEC406DC09B52EL), Double.longBitsToDouble(Double.doubleToLongBits(1.0606389682062964E308) ^ 0x7FE2E147B3A55FCFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.39929028478286127) ^ 0x7FD98DF8D6B02FC5L), Double.longBitsToDouble(Double.doubleToLongBits(1.0422151282198376E308) ^ 0x7FE28D52D3C86569L), Double.longBitsToDouble(Double.doubleToLongBits(31.071587698490813) ^ 0x7FCF12539247D05FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.9189240948751917) ^ 0x7FED67D380DFD9E7L), Double.longBitsToDouble(Double.doubleToLongBits(1.26460773577441E308) ^ 0x7FE682C17D45B685L), Double.longBitsToDouble(Double.doubleToLongBits(-14.771579306221833) ^ 0x7FDD8B0C715CFC7DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.863088695812677) ^ 0x7FEB9E6C2F4201F7L), Double.longBitsToDouble(Double.doubleToLongBits(1.7114967798623746E308) ^ 0x7FEE773518E7E9ADL), Double.longBitsToDouble(Double.doubleToLongBits(8.840595683895412E307) ^ 0x7FDF793B9454CE6FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.4846434367624369) ^ 0x7FDF0465E7C767CDL), Double.longBitsToDouble(Double.doubleToLongBits(8.057427840464926E307) ^ 0x7FDCAF7610C83A0FL), Double.longBitsToDouble(Double.doubleToLongBits(4.075419639700838) ^ 0x7FE04D3ACE57F6FEL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.02581492791120343) ^ 0x7F9A6F3A7C8244FFL), Double.longBitsToDouble(Double.doubleToLongBits(1.3895118186893587E308) ^ 0x7FE8BBF008EA89DFL), Double.longBitsToDouble(Double.doubleToLongBits(-5.89266247957173) ^ 0x7FE792161CF081E5L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.0094061812999311E308) ^ 0x7FE1F7D094E5558FL), Double.longBitsToDouble(Double.doubleToLongBits(7.026153778367515E307) ^ 0x7FD903916CC24DA3L), Double.longBitsToDouble(Double.doubleToLongBits(0.6802368431201626) ^ 0x7FE5C4800E57883EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.317541225186085) ^ 0x7FDAA294C3729CADL), Double.longBitsToDouble(Double.doubleToLongBits(3.878691405269857E307) ^ 0x7FCB9E00C9B8628BL), Double.longBitsToDouble(Double.doubleToLongBits(0.4675479374246148) ^ 0x7FDDEC4E2F23432DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-13.505229276398042) ^ 0x7FDB02AD69663901L), Double.longBitsToDouble(Double.doubleToLongBits(9.495470601403051E307) ^ 0x7FE0E70A28176632L), Double.longBitsToDouble(Double.doubleToLongBits(0.4810717071838377) ^ 0x7FDEC9E0FC58AB8FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.7735259774021379E308) ^ 0x7FEF91DF16C70C1FL), Double.longBitsToDouble(Double.doubleToLongBits(1.1965357527343863E308) ^ 0x7FE54C8E1B9ADBDAL), Double.longBitsToDouble(Double.doubleToLongBits(-0.13734212102175478) ^ 0x7FC1946D37136B43L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-21.56887257163015) ^ 0x7FC591A1A202BE2FL), Double.longBitsToDouble(Double.doubleToLongBits(3.0859295832953822E307) ^ 0x7FC5F8F8E798DBD7L), Double.longBitsToDouble(Double.doubleToLongBits(-0.5437001669624458) ^ 0x7FE165FDE47DDEC7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(20.112339635634992) ^ 0x7FC41CC24A5518CBL), Double.longBitsToDouble(Double.doubleToLongBits(4.1680612815195685E307) ^ 0x7FCDAD75FA13F5A3L), Double.longBitsToDouble(Double.doubleToLongBits(-0.8475062798528682) ^ 0x7FEB1EC57D63ED19L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.41414387210601206) ^ 0x7FDA81554CA22F29L), Double.longBitsToDouble(Double.doubleToLongBits(7.5047440135954755) ^ 0x7FEE04DB9D5CFA36L), Double.longBitsToDouble(Double.doubleToLongBits(-6.4406353275697965) ^ 0x7FE9C335E8457F83L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.6493256390009274) ^ 0x7FE4C7468FFED33CL), Double.longBitsToDouble(Double.doubleToLongBits(6.391530092173455) ^ 0x7FE990ED43B523B1L), Double.longBitsToDouble(Double.doubleToLongBits(4.222446575888507) ^ 0x7FE0E3C90902317BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.5913645666799795) ^ 0x7FE2EC75623CEB4DL), Double.longBitsToDouble(Double.doubleToLongBits(4.9367221598191335) ^ 0x7FE3BF3418077258L), Double.longBitsToDouble(Double.doubleToLongBits(1.4153641950114364E308) ^ 0x7FE931BEE33E5CF6L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.20004010943609382) ^ 0x7FC99AEA0FF548CBL), Double.longBitsToDouble(Double.doubleToLongBits(27.663592281601805) ^ 0x7FCBA9E12F0B5B97L), Double.longBitsToDouble(Double.doubleToLongBits(5.823311612936365) ^ 0x7FE74B12330FEA30L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.8477498516719396) ^ 0x7FEB20C44C03D5A9L), Double.longBitsToDouble(Double.doubleToLongBits(7.701376798336361) ^ 0x7FEECE35B82C1C87L), Double.longBitsToDouble(Double.doubleToLongBits(-4.728979995914835) ^ 0x7FE2EA79BB6791D9L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.218835144709262E308) ^ 0x7FE5B22C1F340A60L), Double.longBitsToDouble(Double.doubleToLongBits(36.50892612597226) ^ 0x7FB241247DC590BFL), Double.longBitsToDouble(Double.doubleToLongBits(0.7359049358645289) ^ 0x7FE78C88821019A2L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.223648489909738) ^ 0x7FECE5041C17D934L), Double.longBitsToDouble(Double.doubleToLongBits(4.2687057372642006) ^ 0x7FE1132798C73072L), Double.longBitsToDouble(Double.doubleToLongBits(0.4173862818039294) ^ 0x7FDAB674F3896715L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-7.380093205222317) ^ 0x7FED853727377259L), Double.longBitsToDouble(Double.doubleToLongBits(30.131842814376594) ^ 0x7FCE21C0735FF5C7L), Double.longBitsToDouble(Double.doubleToLongBits(0.005264314481791542) ^ 0x7F759008A88FFD7FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.4084119074591195E307) ^ 0x7FCF6391396BFC73L), Double.longBitsToDouble(Double.doubleToLongBits(4.38163744918216) ^ 0x7FE186CBF7ACAA3BL), Double.longBitsToDouble(Double.doubleToLongBits(-0.26867648488984813) ^ 0x7FD131FEDAF397BBL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(23.748383422833566) ^ 0x7FC7BF960E55F04FL), Double.longBitsToDouble(Double.doubleToLongBits(24.04428029790878) ^ 0x7FC80B55F41F6017L), Double.longBitsToDouble(Double.doubleToLongBits(-0.22258946690416523) ^ 0x7FCC7DCFC864CB9FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-10.866585598681832) ^ 0x7FD5BBB11B8B0BCDL), Double.longBitsToDouble(Double.doubleToLongBits(6.268228247262096) ^ 0x7FE912AA6CF76A9AL), Double.longBitsToDouble(Double.doubleToLongBits(-0.407408528251424) ^ 0x7FDA12FB383CE30DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.2747316167372938) ^ 0x7FD19533EB4416A9L), Double.longBitsToDouble(Double.doubleToLongBits(0.8390385654812201) ^ 0x7FEAD96767DA6315L), Double.longBitsToDouble(Double.doubleToLongBits(-6.860290052079048) ^ 0x7FEB70EFE01B03BCL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.3512044205284383) ^ 0x7FD67A221B185667L), Double.longBitsToDouble(Double.doubleToLongBits(0.6312069897546785) ^ 0x7FE432D90040182DL), Double.longBitsToDouble(Double.doubleToLongBits(5.954286942027585) ^ 0x7FE7D130989C0886L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.4558948330711791) ^ 0x7FDD2D61859D30A3L), Double.longBitsToDouble(Double.doubleToLongBits(0.14255495954256311) ^ 0x7FC23F3DAC8F17BBL), Double.longBitsToDouble(Double.doubleToLongBits(154.38910905360896) ^ 0x7F934C7394D47A7FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.8703156446916716) ^ 0x7FEBD9A031E4BB7AL), Double.longBitsToDouble(Double.doubleToLongBits(0.12886003402417953) ^ 0x7FC07E7C4FF29923L), Double.longBitsToDouble(Double.doubleToLongBits(-4.7567108616916345) ^ 0x7FE306DF364DF965L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(13.855744990083425) ^ 0x7FDBB62435143F91L), Double.longBitsToDouble(Double.doubleToLongBits(0.3465693745115989) ^ 0x7FD62E315054A3BBL), Double.longBitsToDouble(Double.doubleToLongBits(0.7035959183972424) ^ 0x7FE683DB9663B00EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.415610043686757) ^ 0x7FE1A995ADE61853L), Double.longBitsToDouble(Double.doubleToLongBits(0.4888583083898515) ^ 0x7FDF49745BBA6309L), Double.longBitsToDouble(Double.doubleToLongBits(0.13835769044625365) ^ 0x7FC1B5B46DCEF1B3L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.997885397238304) ^ 0x7FE7FDD5AB692D8DL), Double.longBitsToDouble(Double.doubleToLongBits(0.39186099009011083) ^ 0x7FD914401E40F923L), Double.longBitsToDouble(Double.doubleToLongBits(-0.9277755685059004) ^ 0x7FEDB0566398573EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-10.469206120188028) ^ 0x7FD4F03BC8DA94CDL), Double.longBitsToDouble(Double.doubleToLongBits(0.3522737978253468) ^ 0x7FD68BA7663971F5L), Double.longBitsToDouble(Double.doubleToLongBits(-0.5391811583173449) ^ 0x7FE140F8D832F4E6L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.16547406287730534) ^ 0x7FC52E410C327793L), Double.longBitsToDouble(Double.doubleToLongBits(0.5200855351796809) ^ 0x7FE8A48A6B970538L), Double.longBitsToDouble(Double.doubleToLongBits(1.7603126254307064E308) ^ 0x7FEF55A8A9C314CAL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.7988783591597441) ^ 0x7FE9906959425802L), Double.longBitsToDouble(Double.doubleToLongBits(0.6974461345260671) ^ 0x7FEE517A8E505AB9L), Double.longBitsToDouble(Double.doubleToLongBits(-113.21562830493893) ^ 0x7FAC4DCCDAA9737FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.14099847976795918) ^ 0x7FC20C3CF9B1CE1BL), Double.longBitsToDouble(Double.doubleToLongBits(0.8618582971545048) ^ 0x7FE39457DA02133CL), Double.longBitsToDouble(Double.doubleToLongBits(7.691927234628494) ^ 0x7FEEC48892AFC3B0L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.4264380241717302) ^ 0x7FDB4AC2B5E5A8FBL), Double.longBitsToDouble(Double.doubleToLongBits(0.5259435620501015) ^ 0x7FE8D48797D18079L), Double.longBitsToDouble(Double.doubleToLongBits(9.691830788199867E306) ^ 0x7FAB9A6E9D405B6FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.8105609118747353) ^ 0x7FE9F01D6FFD5FA7L), Double.longBitsToDouble(Double.doubleToLongBits(3.971825877835399E-5) ^ 0x7F0CD2E4FAE4FFFFL), Double.longBitsToDouble(Double.doubleToLongBits(5.995146367730763) ^ 0x7FE7FB07A6408C1AL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-0.5315553985147147) ^ 0x7FE1028077944119L), Double.longBitsToDouble(Double.doubleToLongBits(0.6356624173088093) ^ 0x7FEC5758B5B4678DL), Double.longBitsToDouble(Double.doubleToLongBits(-9.151538673623095) ^ 0x7FD24D967A1E94B1L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.2842963965203235E307) ^ 0x7FC043C55C418CBFL), Double.longBitsToDouble(Double.doubleToLongBits(0.06276150412632096) ^ 0x7FB811234FAB99EFL), Double.longBitsToDouble(Double.doubleToLongBits(0.8669444993355938) ^ 0x7FEBBE026402FDB3L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.038218650465854) ^ 0x7FE42722CA376474L), Double.longBitsToDouble(Double.doubleToLongBits(0.9488530773442189) ^ 0x7FE65D0120FCE049L), Double.longBitsToDouble(Double.doubleToLongBits(0.18061908515608213) ^ 0x7FC71E86B3E3B01BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-6.61800568937569) ^ 0x7FEA78D67BC2712DL), Double.longBitsToDouble(Double.doubleToLongBits(0.8176624916804652) ^ 0x7FE22A4A879DDEFDL), Double.longBitsToDouble(Double.doubleToLongBits(0.584661503822411) ^ 0x7FE2B58C0AC4B7C8L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.361892400819461E307) ^ 0x7FDA358E95D3460DL), Double.longBitsToDouble(Double.doubleToLongBits(0.253045023046467) ^ 0x7FD831E3C0999BEDL), Double.longBitsToDouble(Double.doubleToLongBits(-0.6431853435761211) ^ 0x7FE494F96DFDA17FL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.233113374485921) ^ 0x7FE0EEB545BEB57CL), Double.longBitsToDouble(Double.doubleToLongBits(0.655964357937715) ^ 0x7FECFDA8F715E458L), Double.longBitsToDouble(Double.doubleToLongBits(-0.7150960720492389) ^ 0x7FE6E211285E62A3L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-7.937330583660595) ^ 0x7FEFBFD396A9738EL), Double.longBitsToDouble(Double.doubleToLongBits(0.21873804249640957) ^ 0x7FC3FF9BB174E1AFL), Double.longBitsToDouble(Double.doubleToLongBits(-0.5506840581305525) ^ 0x7FE19F342C832D5AL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(3.699679074449621E307) ^ 0x7FCA57B3F0AE5FEBL), Double.longBitsToDouble(Double.doubleToLongBits(1.6528454578970257) ^ 0x7FEA720E1430286EL), Double.longBitsToDouble(Double.doubleToLongBits(1.1694604792689687E308) ^ 0x7FE4D12CA5F1FF43L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(11.195326004839485) ^ 0x7FD66401C525B017L), Double.longBitsToDouble(Double.doubleToLongBits(0.4474701108334109) ^ 0x7FCCA359ACFDE33FL), Double.longBitsToDouble(Double.doubleToLongBits(6.193983633709653E307) ^ 0x7FD60D22E50D71CBL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.225108846007269) ^ 0x7FE0E682EEEE90EBL), Double.longBitsToDouble(Double.doubleToLongBits(1.782699845456741) ^ 0x7FEC85F045ED22A4L), Double.longBitsToDouble(Double.doubleToLongBits(7.190919621350452E307) ^ 0x7FD999BBEAA2D023L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.15024181244895E306) ^ 0x7FA45D541C0269CFL), Double.longBitsToDouble(Double.doubleToLongBits(1.1081122765092049) ^ 0x7FE1BAD3F03E7347L), Double.longBitsToDouble(Double.doubleToLongBits(4.1837834759087045) ^ 0x7FE0BC31BC4A4AD8L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.155442485042232E307) ^ 0x7FD25A9EBA261ADDL), Double.longBitsToDouble(Double.doubleToLongBits(0.21888826047965462) ^ 0x7FDC0487D01E8565L), Double.longBitsToDouble(Double.doubleToLongBits(-55.94315102526894) ^ 0x7FBBF8B92C3C5C07L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.609975242598742) ^ 0x7FEA709D59995337L), Double.longBitsToDouble(Double.doubleToLongBits(1.4803225197957317) ^ 0x7FE7AF66AAA0E133L), Double.longBitsToDouble(Double.doubleToLongBits(31.515224383243318) ^ 0x7FCF83E5BEC421BFL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-5.129197723847377) ^ 0x7FE4844C687A9189L), Double.longBitsToDouble(Double.doubleToLongBits(0.1538620740898816) ^ 0x7FD3B1C0A027C753L), Double.longBitsToDouble(Double.doubleToLongBits(7.086876792640443) ^ 0x7FEC58F63ADCB035L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-9.393205720077807) ^ 0x7FD2C9524298AB2BL), Double.longBitsToDouble(Double.doubleToLongBits(0.25537284556590584) ^ 0x7FC0580758FF7D53L), Double.longBitsToDouble(Double.doubleToLongBits(-15.815638970742194) ^ 0x7FDFA19B6E615C95L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(7.079693315989178) ^ 0x7FEC519B1FE78883L), Double.longBitsToDouble(Double.doubleToLongBits(0.2025368368781809) ^ 0x7FD9ECBA215043F3L), Double.longBitsToDouble(Double.doubleToLongBits(-5.876170001382157) ^ 0x7FE78132B576B0C8L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.03747517504577768) ^ 0x7FA32FF236778C0FL), Double.longBitsToDouble(Double.doubleToLongBits(0.05224948771336196) ^ 0x7FBAC071E1EC5C87L), Double.longBitsToDouble(Double.doubleToLongBits(9.586373322001331E307) ^ 0x7FE11076AE8DD56EL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.881340908586425) ^ 0x7FEC33F1D9602E0CL), Double.longBitsToDouble(Double.doubleToLongBits(0.05459037808816158) ^ 0x7FBBF34521213E5FL), Double.longBitsToDouble(Double.doubleToLongBits(6.302064335954591) ^ 0x7FE935505A70DA09L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(0.8276538875899723) ^ 0x7FEA7C24017365EBL), Double.longBitsToDouble(Double.doubleToLongBits(0.1392495289463027) ^ 0x7FD1D2EDB667652FL), Double.longBitsToDouble(Double.doubleToLongBits(-10.664956564097578) ^ 0x7FD554752FD01E87L)) };
        this.new_slot = 0;
        this.old_slot = 0;
        this.y_level = 0;
        this.tick_runs = 0;
        this.blocks_placed = 0;
        this.offset_step = 0;
        this.sneak = false;
        this.name = "Auto NomadHut";
        this.tag = "AutoNomadHut";
        this.description = "i fucking hate fit";
    }
    
    @Override
    public void disable() {
        if (AutoNomadHut.mc.player != null) {
            if (this.new_slot != this.old_slot && this.old_slot != -1) {
                AutoNomadHut.mc.player.inventory.currentItem = this.old_slot;
            }
            if (this.sneak) {
                AutoNomadHut.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoNomadHut.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.sneak = false;
            }
            this.old_slot = -1;
            this.new_slot = -1;
        }
    }
    
    @Override
    public void update() {
        if (AutoNomadHut.mc.player != null) {
            this.blocks_placed = 0;
            while (this.blocks_placed < this.tick_for_place.get_value(1)) {
                if (this.offset_step >= this.targets.length) {
                    this.offset_step = 0;
                    break;
                }
                final BlockPos obf = new BlockPos(this.targets[this.offset_step]);
                final BlockPos obf2 = new BlockPos(AutoNomadHut.mc.player.getPositionVector()).add(obf.getX(), obf.getY(), obf.getZ()).down();
                boolean obf3 = true;
                if (!AutoNomadHut.mc.world.getBlockState(obf2).getMaterial().isReplaceable()) {
                    obf3 = false;
                }
                for (final Entity obf4 : AutoNomadHut.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(obf2))) {
                    if (!(obf4 instanceof EntityItem)) {
                        if (obf4 instanceof EntityXPOrb) {
                            continue;
                        }
                        obf3 = false;
                        break;
                    }
                }
                if (obf3) {
                    if (this.place_blocks(obf2)) {
                        ++this.blocks_placed;
                    }
                }
                ++this.offset_step;
            }
            if (this.blocks_placed > 0 && this.new_slot != this.old_slot) {
                AutoNomadHut.mc.player.inventory.currentItem = this.old_slot;
            }
            ++this.tick_runs;
        }
    }
    
    @Override
    public void enable() {
        if (AutoNomadHut.mc.player != null) {
            this.old_slot = AutoNomadHut.mc.player.inventory.currentItem;
            this.new_slot = this.find_in_hotbar();
            if (this.new_slot == -1) {
                MessageUtil.send_client_error_message("Cannot find Obsidian in hotbar");
                this.set_active(false);
            }
            this.y_level = (int)Math.round(AutoNomadHut.mc.player.posY);
        }
    }
    
    public int find_in_hotbar() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack stackInSlot = AutoNomadHut.mc.player.inventory.getStackInSlot(i);
            if (stackInSlot != ItemStack.EMPTY) {
                if (stackInSlot.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stackInSlot.getItem()).getBlock();
                    if (block instanceof BlockEnderChest) {
                        return i;
                    }
                    if (block instanceof BlockObsidian) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
