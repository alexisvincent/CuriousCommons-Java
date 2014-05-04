package swing.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import swing.toolkit.UIToolkit;

/**
 * @about simple label
 * @author alexisvincent
 */
public class ALabel extends JComponent {
    
    private Color labelColor;
    
    public ALabel(String name) {
        setName(name);
        labelColor = AColor.WHITE;
    }

    public void setLabelColor(Color labelColor) {
        this.labelColor = labelColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

        g2d.setPaint(labelColor);
        g2d.drawString(getName(), 0, getHeight()/2+g2d.getFontMetrics().getAscent()/2-g2d.getFontMetrics().getDescent()/2);
    }

    
}
