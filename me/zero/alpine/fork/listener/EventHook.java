// 
// Decompiled by Procyon v0.5.36
// 

package me.zero.alpine.fork.listener;

@FunctionalInterface
public interface EventHook<T>
{
    void invoke(final T p0);
}
