package swing.components;

import java.awt.Color;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

/**
 *
 * @author alexisvincent
 */
public class AColor extends Color{

    public static AColor LightBlue;
    public static AColor DarkBlue;
    public static AColor DarkGreen;
    public static AColor LightGreen;
    public static AColor DarkGrey;
    public static AColor Red;
    
    private AColor transformableColor;

    static {
        LightBlue = new AColor(0, 172, 255, 30);
        DarkBlue = new AColor(0, 172, 255, 230);
        LightGreen = new AColor(20, 200, 112, 60);
        DarkGreen = new AColor(20, 200, 112, 255);
        DarkGrey = new AColor(34, 34, 34, 255);
        Red = new AColor(200, 34, 34, 255);
    }

    public AColor(int r, int g, int b) {
        super(r, g, b);
    }

    public AColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public AColor(int rgb) {
        super(rgb);
    }

    public AColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public AColor(float r, float g, float b) {
        super(r, g, b);
    }

    public AColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public AColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }
    
    public void setColor(AColor color) {
        this.transformableColor = color;
    }
    
    public AColor(AColor color) {
        super(color.getRGB());
        this.transformableColor = color;
    }

    @Override
    public int getTransparency() {
        if (transformableColor != null) {
            return transformableColor.getTransparency();
        } else {
            return super.getTransparency();
        }
        
    }

    @Override
    public synchronized PaintContext createContext(ColorModel cm, Rectangle r, Rectangle2D r2d, AffineTransform xform, RenderingHints hints) {
        if (transformableColor != null) {
            return transformableColor.createContext(cm, r, r2d, xform, hints);
        } else {
            return super.createContext(cm, r, r2d, xform, hints);
        }
        
    }

    @Override
    public ColorSpace getColorSpace() {
        if (transformableColor != null) {
            return transformableColor.getColorSpace();
        } else {
            return super.getColorSpace();
        }
        
    }

    @Override
    public float[] getColorComponents(ColorSpace cspace, float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getColorComponents(cspace, compArray);
        } else {
            return super.getColorComponents(cspace, compArray);
        }
        
    }

    @Override
    public float[] getComponents(ColorSpace cspace, float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getComponents(cspace, compArray);
        } else {
            return super.getComponents(cspace, compArray);
        }
        
    }

    @Override
    public float[] getColorComponents(float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getColorComponents(compArray);
        } else {
            return super.getColorComponents(compArray);
        }
        
    }

    @Override
    public float[] getComponents(float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getComponents(compArray);
        } else {
            return super.getComponents(compArray);
        }
        
    }

    @Override
    public float[] getRGBColorComponents(float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getRGBColorComponents(compArray);
        } else {
            return super.getRGBColorComponents(compArray);
        }
        
    }

    @Override
    public float[] getRGBComponents(float[] compArray) {
        if (transformableColor != null) {
            return transformableColor.getRGBComponents(compArray);
        } else {
            return super.getRGBComponents(compArray);
        }
        
    }

    @Override
    public String toString() {
        if (transformableColor != null) {
            return transformableColor.toString();
        } else {
            return super.toString();
        }
        
    }

    @Override
    public boolean equals(Object obj) {
        if (transformableColor != null) {
            return transformableColor.equals(obj);
        } else {
            return super.equals(obj);
        }
        
    }

    @Override
    public int hashCode() {
        if (transformableColor != null) {
            return transformableColor.hashCode();
        } else {
            return super.hashCode();
        }
        
    }

    @Override
    public Color darker() {
        if (transformableColor != null) {
            return transformableColor.darker();
        } else {
            return super.darker();
        }
        
    }

    @Override
    public Color brighter() {
        if (transformableColor != null) {
            return transformableColor.brighter();
        } else {
            return super.brighter();
        }
        
    }

    @Override
    public int getRGB() {
        if (transformableColor != null) {
            return transformableColor.getRGB();
        } else {
            return super.getRGB();
        }
        
    }

    @Override
    public int getAlpha() {
        if (transformableColor != null) {
            return transformableColor.getAlpha();
        } else {
            return super.getAlpha();
        }
        
    }

    @Override
    public int getBlue() {
        if (transformableColor != null) {
            return transformableColor.getBlue();
        } else {
            return super.getBlue();
        }
        
    }

    @Override
    public int getGreen() {
        if (transformableColor != null) {
            return transformableColor.getGreen();
        } else {
            return super.getGreen();
        }
    }

    @Override
    public int getRed() {
        if (transformableColor != null) {
            return transformableColor.getRed();
        } else {
            return super.getRed();
        }
        
    }

    
}
