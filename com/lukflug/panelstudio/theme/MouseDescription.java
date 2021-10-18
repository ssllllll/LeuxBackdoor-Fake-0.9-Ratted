// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.theme;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import com.lukflug.panelstudio.Context;
import java.awt.Point;

public class MouseDescription implements DescriptionRenderer
{
    protected Point offset;
    
    public MouseDescription(final Point offset) {
        this.offset = offset;
    }
    
    @Override
    public void renderDescription(final Context context) {
        if (context.getDescription() != null) {
            final Point pos = context.getInterface().getMouse();
            pos.translate(this.offset.x, this.offset.y);
            final Rectangle r = new Rectangle(pos, new Dimension(context.getInterface().getFontWidth(context.getDescription()), context.getInterface().getFontHeight()));
            final Color bgcolor = new Color(0, 0, 0);
            context.getInterface().fillRect(r, bgcolor, bgcolor, bgcolor, bgcolor);
            final Color color = new Color(255, 255, 255);
            context.getInterface().drawRect(r, color, color, color, color);
            context.getInterface().drawString(pos, context.getDescription(), color);
        }
    }
}
