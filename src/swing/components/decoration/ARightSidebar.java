package swing.components.decoration;

import java.awt.Graphics;
import java.awt.Graphics2D;
import swing.components.AColor;
import swing.components.AComponent;
import swing.components.AFrame;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ARightSidebar extends AComponent {

    public ARightSidebar(AFrame frame) {
        setBackground(AColor.DarkGrey);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

        g2d.setPaint(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
