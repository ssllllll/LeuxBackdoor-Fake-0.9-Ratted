// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import me.sazked.leux.Leux;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.client.network.NetworkPlayerInfo;

public class TabUtil
{
    public static String get_player_name(final NetworkPlayerInfo obf) {
        final String obf2 = (obf.getDisplayName() != null) ? obf.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)obf.getPlayerTeam(), obf.getGameProfile().getName());
        if (Leux.get_module_manager().get_module_with_tag("Settings").is_active() && Leux.get_setting_manager().get_setting_with_tag("Settings", "TabColor").get_value(true)) {
            if (FriendUtil.isFriend(obf2)) {
                return "§9" + obf2;
            }
            if (EnemyUtil.isEnemy(obf2)) {
                return "§c" + obf2;
            }
        }
        return obf2;
    }
}
