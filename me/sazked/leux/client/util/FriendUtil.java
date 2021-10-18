// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.UUID;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.mojang.util.UUIDTypeAdapter;
import com.google.gson.JsonParser;
import java.util.Collection;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;

public class FriendUtil
{
    public static ArrayList<Friend> friends;
    
    public static Friend get_friend_object(final String obf) {
        final ArrayList<NetworkPlayerInfo> obf2 = new ArrayList<NetworkPlayerInfo>(Minecraft.getMinecraft().getConnection().getPlayerInfoMap());
        final NetworkPlayerInfo obf3 = obf2.stream().filter(FriendUtil::lambda$get_friend_object$1).findFirst().orElse(null);
        if (obf3 == null) {
            final String obf4 = request_ids("[\"" + obf + "\"]");
            if (obf4 != null) {
                if (!obf4.isEmpty()) {
                    final JsonElement obf5 = new JsonParser().parse(obf4);
                    if (obf5.getAsJsonArray().size() != 0) {
                        Exception ex;
                        try {
                            final String obf6 = obf5.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                            final String obf7 = obf5.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                            return new Friend(obf7, UUIDTypeAdapter.fromString(obf6));
                        }
                        catch (Exception obf8) {
                            ex = obf8;
                        }
                        ex.printStackTrace();
                    }
                }
            }
            return null;
        }
        return new Friend(obf3.getGameProfile().getName(), obf3.getGameProfile().getId());
    }
    
    public static boolean lambda$isFriend$0(final String obf, final Friend obf) {
        return obf.username.equalsIgnoreCase(obf);
    }
    
    public static String request_ids(final String obf) {
        try {
            final String obf2 = "https://api.mojang.com/profiles/minecraft";
            final URL obf3 = new URL(obf2);
            final HttpURLConnection obf4 = (HttpURLConnection)obf3.openConnection();
            obf4.setConnectTimeout(5000);
            obf4.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            obf4.setDoOutput(true);
            obf4.setDoInput(true);
            obf4.setRequestMethod("POST");
            final OutputStream obf5 = obf4.getOutputStream();
            obf5.write(obf.getBytes("UTF-8"));
            obf5.close();
            final InputStream obf6 = new BufferedInputStream(obf4.getInputStream());
            final String obf7 = convertStreamToString(obf6);
            obf6.close();
            obf4.disconnect();
            return obf7;
        }
        catch (Exception obf8) {
            return null;
        }
    }
    
    static {
        FriendUtil.friends = new ArrayList<Friend>();
    }
    
    public static boolean lambda$get_friend_object$1(final String anotherString, final NetworkPlayerInfo networkPlayerInfo) {
        return networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(anotherString);
    }
    
    public static String convertStreamToString(final InputStream obf) {
        final Scanner obf2 = new Scanner(obf).useDelimiter("\\A");
        final String obf3 = obf2.hasNext() ? obf2.next() : "/";
        return obf3;
    }
    
    public static boolean isFriend(final String obf) {
        return FriendUtil.friends.stream().anyMatch(FriendUtil::lambda$isFriend$0);
    }
    
    public static class Friend
    {
        public UUID uuid;
        public String username;
        
        public Friend(final String obf, final UUID obf) {
            this.username = obf;
            this.uuid = obf;
        }
        
        public String getUsername() {
            return this.username;
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
    }
}
