// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.Minecraft;

public class PlayerUtil
{
    public static Minecraft mc;
    
    static {
        PlayerUtil.mc = Minecraft.getMinecraft();
    }
    
    public static BlockPos GetLocalPlayerPosFloored() {
        return new BlockPos(Math.floor(PlayerUtil.mc.player.posX), Math.floor(PlayerUtil.mc.player.posY), Math.floor(PlayerUtil.mc.player.posZ));
    }
    
    public static FacingDirection GetFacing() {
        switch (MathHelper.floor(PlayerUtil.mc.player.rotationYaw * Float.intBitsToFloat(Float.floatToIntBits(1.6396283f) ^ 0x7ED1DF57) / Float.intBitsToFloat(Float.floatToIntBits(0.018021476f) ^ 0x7F27A1C6) + Double.longBitsToDouble(Double.doubleToLongBits(3.3378968965263707) ^ 0x7FEAB40349C00159L)) & 0x7) {
            case 0:
            case 1: {
                return FacingDirection.South;
            }
            case 2:
            case 3: {
                return FacingDirection.West;
            }
            case 4:
            case 5: {
                return FacingDirection.North;
            }
            case 6:
            case 7: {
                return FacingDirection.East;
            }
            default: {
                return FacingDirection.North;
            }
        }
    }
    
    public enum FacingDirection
    {
        South, 
        North;
        
        public static FacingDirection[] $VALUES;
        
        East, 
        West;
        
        static {
            FacingDirection.$VALUES = new FacingDirection[] { FacingDirection.North, FacingDirection.South, FacingDirection.East, FacingDirection.West };
        }
    }
}
