// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class CapeUtil
{
    public static ArrayList<String> final_uuid_list;
    
    public static ArrayList<String> get_uuids() {
        try {
            final URL obf = new URL("https://pastebin.com/raw/tVAs44DN");
            final BufferedReader obf2 = new BufferedReader(new InputStreamReader(obf.openStream()));
            final ArrayList<String> obf3 = new ArrayList<String>();
            String obf4;
            while ((obf4 = obf2.readLine()) != null) {
                obf3.add(obf4);
            }
            return obf3;
        }
        catch (Exception obf5) {
            return null;
        }
    }
    
    public static boolean is_uuid_valid(final UUID obf) {
        for (final String obf2 : Objects.requireNonNull(CapeUtil.final_uuid_list)) {
            if (obf2.equals(obf.toString())) {
                return true;
            }
        }
        return false;
    }
    
    static {
        CapeUtil.final_uuid_list = get_uuids();
    }
}
