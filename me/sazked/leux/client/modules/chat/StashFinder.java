// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.modules.chat;

import java.util.HashMap;
import me.sazked.leux.client.modules.Category;
import java.util.Iterator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import me.sazked.leux.client.util.MessageUtil;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.tileentity.TileEntityShulkerBox;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import me.sazked.leux.client.guiscreen.settings.Setting;
import java.util.Map;
import me.sazked.leux.client.modules.Module;

public class StashFinder extends Module
{
    public Map<Long, Integer> stashMap;
    public Setting sound;
    public ArrayList<BlockPos> chestPositions;
    public Setting chests;
    public Setting file;
    public Setting shulkers;
    
    @Override
    public void update() {
        for (final TileEntity obf : StashFinder.mc.world.loadedTileEntityList) {
            final BlockPos obf2 = obf.getPos();
            if (!(obf instanceof TileEntityChest) && !(obf instanceof TileEntityShulkerBox)) {
                continue;
            }
            boolean obf3 = false;
            for (final BlockPos obf4 : this.chestPositions) {
                if (obf4.equals((Object)obf2)) {
                    obf3 = true;
                    break;
                }
            }
            if (obf3) {
                continue;
            }
            this.chestPositions.add(obf2);
            final int obf5 = obf2.getX() / 16;
            final int obf6 = obf2.getZ() / 16;
            final long obf7 = ChunkPos.asLong(obf5, obf6);
            if (!this.stashMap.containsKey(obf7)) {
                this.stashMap.put(obf7, 0);
            }
            final int obf8 = this.chests.get_value(1);
            int obf9 = this.stashMap.get(obf7) + 1;
            if (this.shulkers.get_value(true)) {
                if (obf instanceof TileEntityShulkerBox && obf9 < obf8) {
                    obf9 = obf8;
                }
            }
            this.stashMap.put(obf7, obf9);
            if (obf9 != obf8) {
                continue;
            }
            if (obf instanceof TileEntityShulkerBox) {
                MessageUtil.send_client_message("1 or more Shulker Boxs found at " + obf2.toString());
            }
            else {
                MessageUtil.send_client_message(obf9 + " or more Chests found at " + obf2.toString());
            }
            if (this.sound.get_value(true)) {
                StashFinder.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getRecord(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, Float.intBitsToFloat(Float.floatToIntBits(9.491166f) ^ 0x7E97DBD1), Float.intBitsToFloat(Float.floatToIntBits(7.6044393f) ^ 0x7F735791)));
            }
            if (!this.file.get_value(true)) {
                continue;
            }
            IOException ex;
            try {
                final BufferedWriter obf10 = new BufferedWriter(new FileWriter("LeuxBackdoor\\stashfinder.txt", true));
                String obf11 = "";
                final Calendar obf12 = Calendar.getInstance();
                final SimpleDateFormat obf13 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                obf11 = obf11 + "[" + obf13.format(obf12.getTime()) + "|";
                if (StashFinder.mc.getCurrentServerData() != null) {
                    obf11 = obf11 + StashFinder.mc.getCurrentServerData().serverIP + "|";
                }
                switch (StashFinder.mc.player.dimension) {
                    case 0: {
                        obf11 += "Overworld";
                        break;
                    }
                    case 1: {
                        obf11 += "End";
                        break;
                    }
                    case -1: {
                        obf11 += "Nether";
                        break;
                    }
                }
                obf11 += "] ";
                if (obf instanceof TileEntityShulkerBox && this.shulkers.get_value(true)) {
                    obf11 = obf11 + "Shulker Box found at " + obf2.toString();
                }
                else if (this.chests.get_value(true)) {
                    obf11 = obf11 + obf9 + " or more Chests found at " + obf2.toString();
                }
                obf10.write(obf11);
                obf10.newLine();
                obf10.close();
                continue;
            }
            catch (IOException obf14) {
                ex = obf14;
            }
            ex.printStackTrace();
        }
    }
    
    @Override
    public void enable() {
        this.chestPositions.clear();
        this.stashMap.clear();
    }
    
    public StashFinder() {
        super(Category.chat);
        this.chests = this.create("Chests", "StashFinderChests", 6, 2, 20);
        this.file = this.create("Log to File", "StashFinderFile", true);
        this.shulkers = this.create("Log Shulkers", "StashFinderShulkers", false);
        this.sound = this.create("Play Sound", "StashFinderSound", true);
        this.name = "StashFinder";
        this.tag = "StashFinder";
        this.description = "ez raid";
        this.chestPositions = new ArrayList<BlockPos>();
        this.stashMap = new HashMap<Long, Integer>();
    }
}
