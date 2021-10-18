// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import com.lukflug.panelstudio.Context;
import java.awt.Point;

public class FixedDescription implements DescriptionRenderer
{
    protected Point pos;
    
    public FixedDescription(final Point pos) {
        this.pos = pos;
    }
    
    @Override
    public void renderDescription(final Context context) {
        if (context.getDescription() != null) {
            final Rectangle r = new Rectangle(this.pos, new Dimension(context.getInterface().getFontWidth(context.getDescription()), context.getInterface().getFontHeight()));
            final Color bgcolor = new Color(0, 0, 0);
            context.getInterface().fillRect(r, bgcolor, bgcolor, bgcolor, bgcolor);
            final Color color = new Color(255, 255, 255);
            context.getInterface().drawRect(r, color, color, color, color);
            context.getInterface().drawString(this.pos, context.getDescription(), color);
        }
    }
}
