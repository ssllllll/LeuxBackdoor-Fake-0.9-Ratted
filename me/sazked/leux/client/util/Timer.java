// 
// Decompiled by Procyon v0.5.36
// 

package me.sazked.leux.client.util;

public class Timer
{
    public long time;
    
    public Timer() {
        this.time = ((long)1777658327 ^ 0xFFFFFFFF960B1628L);
    }
    
    public void resetTimeSkipTo(final long obf) {
        this.time = System.currentTimeMillis() + obf;
    }
    
    public boolean passed(final double obf) {
        return System.currentTimeMillis() - this.time >= obf;
    }
    
    public boolean passed(final long obf) {
        return this.getTime(System.nanoTime() - this.time) >= obf;
    }
    
    public long getTime(final long obf) {
        return obf / ((long)396743955 ^ 0x17AA9753L);
    }
    
    public void reset() {
        this.time = System.nanoTime();
    }
}
