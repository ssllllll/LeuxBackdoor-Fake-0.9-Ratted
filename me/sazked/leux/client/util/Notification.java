// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

public class Notification
{
    public String message;
    public int g;
    public int b;
    public long timeCreated;
    public int r;
    
    public String getMessage() {
        return this.message;
    }
    
    public Notification(final String obf, final int obf, final int obf, final int obf) {
        this.timeCreated = System.currentTimeMillis();
        this.message = obf;
        this.r = obf;
        this.g = obf;
        this.b = obf;
    }
    
    public int getR() {
        return this.r;
    }
    
    public Notification(final String obf) {
        this.timeCreated = System.currentTimeMillis();
        this.message = obf;
        this.r = 50;
        this.g = 168;
        this.b = 82;
    }
    
    public int getG() {
        return this.g;
    }
    
    public int getB() {
        return this.b;
    }
    
    public long getTimeCreated() {
        return this.timeCreated;
    }
}
