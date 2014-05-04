package swing.toolkit;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.RenderingHints;

/**
 *
 * @author alexisvincent
 */
public class UIToolkit {
    
    private static boolean ANTIALIAS = true;

    public static boolean isANTIALIAS() {
        return ANTIALIAS;
    }

    public static void setANTIALIAS(boolean ANTIALIAS) {
        UIToolkit.ANTIALIAS = ANTIALIAS;
    }
    
    public static Graphics2D getPrettyGraphics(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        if (ANTIALIAS) g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        return g2d;
    }

    public static GridBagConstraints getDefaultGridBagConstraints() {
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        
        //gridbag constraints
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        
        return gridBagConstraints;
    }

    public static AlphaComposite makeComposite(float alpha) {
        return (AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha / 255));
    }

}
