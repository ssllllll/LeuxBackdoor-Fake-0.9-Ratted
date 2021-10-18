// 
// Decompiled by Procyon v0.5.36
// 

package com.lukflug.panelstudio.settings;

import java.awt.Color;
import com.lukflug.panelstudio.Slider;
import com.lukflug.panelstudio.FocusableComponent;
import com.lukflug.panelstudio.Context;
import com.lukflug.panelstudio.Component;
import com.lukflug.panelstudio.Animation;
import com.lukflug.panelstudio.theme.Renderer;
import com.lukflug.panelstudio.theme.ColorScheme;
import com.lukflug.panelstudio.CollapsibleContainer;

public class ColorComponent extends CollapsibleContainer
{
    protected ColorSetting setting;
    protected final boolean alpha;
    protected final boolean rainbow;
    protected ColorScheme scheme;
    protected ColorScheme overrideScheme;
    protected Toggleable colorModel;
    
    public ColorComponent(final String title, final String description, final Renderer renderer, final Animation animation, final Renderer componentRenderer, final ColorSetting setting, final boolean alpha, final boolean rainbow, final Toggleable colorModel) {
        super(title, description, renderer, new SimpleToggleable(false), animation, null);
        this.setting = setting;
        this.alpha = alpha;
        this.rainbow = rainbow;
        this.scheme = new ColorSettingScheme(renderer);
        this.overrideScheme = new ColorSettingScheme(componentRenderer);
        this.colorModel = colorModel;
        if (rainbow) {
            this.addComponent(new ColorButton(componentRenderer));
        }
        this.addComponent(new ColorSlider(componentRenderer, 0));
        this.addComponent(new ColorSlider(componentRenderer, 1));
        this.addComponent(new ColorSlider(componentRenderer, 2));
        if (alpha) {
            this.addComponent(new ColorSlider(componentRenderer, 3));
        }
    }
    
    @Override
    public void render(final Context context) {
        this.renderer.overrideColorScheme(this.scheme);
        super.render(context);
        this.renderer.restoreColorScheme();
    }
    
    protected class ColorButton extends FocusableComponent
    {
        public ColorButton(final Renderer renderer) {
            super("Rainbow", null, renderer);
        }
        
        @Override
        public void render(final Context context) {
            super.render(context);
            this.renderer.overrideColorScheme(ColorComponent.this.overrideScheme);
            this.renderer.renderTitle(context, this.title, this.hasFocus(context), ColorComponent.this.setting.getRainbow());
            this.renderer.restoreColorScheme();
        }
        
        @Override
        public void handleButton(final Context context, final int button) {
            super.handleButton(context, button);
            if (button == 0 && context.isClicked()) {
                ColorComponent.this.setting.setRainbow(!ColorComponent.this.setting.getRainbow());
            }
        }
    }
    
    protected class ColorSlider extends Slider
    {
        private final int value;
        
        public ColorSlider(final Renderer renderer, final int value) {
            super("", null, renderer);
            this.value = value;
        }
        
        @Override
        public void render(final Context context) {
            this.title = this.getTitle(this.value) + (int)(this.getMax() * this.getValue());
            this.renderer.overrideColorScheme(ColorComponent.this.overrideScheme);
            super.render(context);
            this.renderer.restoreColorScheme();
        }
        
        @Override
        protected double getValue() {
            final Color c = ColorComponent.this.setting.getColor();
            if (this.value < 3) {
                if (ColorComponent.this.colorModel.isOn()) {
                    return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null)[this.value];
                }
                switch (this.value) {
                    case 0: {
                        return c.getRed() / 255.0;
                    }
                    case 1: {
                        return c.getGreen() / 255.0;
                    }
                    case 2: {
                        return c.getBlue() / 255.0;
                    }
                }
            }
            return c.getAlpha() / 255.0;
        }
        
        @Override
        protected void setValue(final double value) {
            Color c = ColorComponent.this.setting.getColor();
            final float[] color = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
            switch (this.value) {
                case 0: {
                    if (ColorComponent.this.colorModel.isOn()) {
                        c = Color.getHSBColor((float)value, color[1], color[2]);
                    }
                    else {
                        c = new Color((int)(255.0 * value), c.getGreen(), c.getBlue());
                    }
                    if (ColorComponent.this.alpha) {
                        ColorComponent.this.setting.setValue(new Color(c.getRed(), c.getGreen(), c.getBlue(), ColorComponent.this.setting.getColor().getAlpha()));
                        break;
                    }
                    ColorComponent.this.setting.setValue(c);
                    break;
                }
                case 1: {
                    if (ColorComponent.this.colorModel.isOn()) {
                        c = Color.getHSBColor(color[0], (float)value, color[2]);
                    }
                    else {
                        c = new Color(c.getRed(), (int)(255.0 * value), c.getBlue());
                    }
                    if (ColorComponent.this.alpha) {
                        ColorComponent.this.setting.setValue(new Color(c.getRed(), c.getGreen(), c.getBlue(), ColorComponent.this.setting.getColor().getAlpha()));
                        break;
                    }
                    ColorComponent.this.setting.setValue(c);
                    break;
                }
                case 2: {
                    if (ColorComponent.this.colorModel.isOn()) {
                        c = Color.getHSBColor(color[0], color[1], (float)value);
                    }
                    else {
                        c = new Color(c.getRed(), c.getGreen(), (int)(255.0 * value));
                    }
                    if (ColorComponent.this.alpha) {
                        ColorComponent.this.setting.setValue(new Color(c.getRed(), c.getGreen(), c.getBlue(), ColorComponent.this.setting.getColor().getAlpha()));
                        break;
                    }
                    ColorComponent.this.setting.setValue(c);
                    break;
                }
                case 3: {
                    ColorComponent.this.setting.setValue(new Color(c.getRed(), c.getGreen(), c.getBlue(), (int)(255.0 * value)));
                    break;
                }
            }
        }
        
        protected String getTitle(final int value) {
            switch (value) {
                case 0: {
                    return (ColorComponent.this.colorModel.isOn() ? "Hue:" : "Red:") + " §7";
                }
                case 1: {
                    return (ColorComponent.this.colorModel.isOn() ? "Saturation:" : "Green:") + " §7";
                }
                case 2: {
                    return (ColorComponent.this.colorModel.isOn() ? "Brightness:" : "Blue:") + " §7";
                }
                case 3: {
                    return "Alpha: §7";
                }
                default: {
                    return "";
                }
            }
        }
        
        protected int getMax() {
            if (!ColorComponent.this.colorModel.isOn()) {
                return 255;
            }
            if (this.value == 0) {
                return 360;
            }
            if (this.value < 3) {
                return 100;
            }
            return 255;
        }
    }
    
    protected class ColorSettingScheme implements ColorScheme
    {
        ColorScheme scheme;
        
        public ColorSettingScheme(final Renderer renderer) {
            this.scheme = renderer.getDefaultColorScheme();
        }
        
        @Override
        public Color getActiveColor() {
            return ColorComponent.this.setting.getValue();
        }
        
        @Override
        public Color getInactiveColor() {
            return this.scheme.getInactiveColor();
        }
        
        @Override
        public Color getBackgroundColor() {
            return this.scheme.getBackgroundColor();
        }
        
        @Override
        public Color getOutlineColor() {
            return this.scheme.getOutlineColor();
        }
        
        @Override
        public Color getFontColor() {
            return this.scheme.getFontColor();
        }
        
        @Override
        public int getOpacity() {
            return this.scheme.getOpacity();
        }
    }
}
