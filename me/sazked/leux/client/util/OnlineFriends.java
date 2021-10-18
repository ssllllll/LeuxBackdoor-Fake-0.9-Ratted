// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import net.minecraft.entity.player.EntityPlayer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import java.util.List;

public class OnlineFriends
{
    public static List<Entity> entities;
    
    static {
        OnlineFriends.entities = new ArrayList<Entity>();
    }
    
    public static List<Entity> getFriends() {
        OnlineFriends.entities.clear();
        OnlineFriends.entities.addAll((Collection<? extends Entity>)Minecraft.getMinecraft().world.playerEntities.stream().filter(OnlineFriends::lambda$getFriends$0).collect(Collectors.toList()));
        return OnlineFriends.entities;
    }
    
    public static boolean lambda$getFriends$0(final EntityPlayer entityPlayer) {
        return FriendUtil.isFriend(entityPlayer.getName());
    }
}
