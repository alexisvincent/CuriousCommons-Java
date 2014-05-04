package swing.components;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLayeredPane;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ALayeredPane extends JLayeredPane {

    protected boolean debugMode = false;

    public ALayeredPane() {
        super();
        setGoodDefaults();
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setGoodDefaults() {
        this.setOpaque(false);
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return true;
    }

    @Override
    public void doLayout() {
        for (Component component : getComponents()) {
            component.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isDebugMode()) {
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
            g2d.setPaint(getBackground());
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
