// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

import java.util.ArrayList;
import java.util.List;

public class DrawnUtil
{
    public static List<String> hidden_tags;
    
    static {
        DrawnUtil.hidden_tags = new ArrayList<String>();
    }
    
    public static void add_remove_item(String obf) {
        obf = obf.toLowerCase();
        if (DrawnUtil.hidden_tags.contains(obf)) {
            MessageUtil.send_client_message("Added " + obf);
            DrawnUtil.hidden_tags.remove(obf);
        }
        else {
            MessageUtil.send_client_message("Removed " + obf);
            DrawnUtil.hidden_tags.add(obf);
        }
    }
}
