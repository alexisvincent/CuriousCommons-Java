package swing.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class APane extends AComponent {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isDebugMode()) {
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
            g2d.setPaint(new Color(50, 50, 50));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
