package swing.componentsOld;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import swing.toolkit.UIToolkit;

/**
 * @about rolling label... should really look at making a more elegant solution
 * @author alexisvincent
 */
public class ARollingLabel extends JComponent {

    private String name1, name2;
    private Point center, pt1, pt2;

    public ARollingLabel(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public void roll() {
        Thread rollThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (pt2.y > center.y) {
                    try {
                        pt1.y--;
                        pt2.y--;
                        Thread.sleep(20);
                        repaint();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ARollingLabel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        rollThread.start();
    }

    public void reset() {
        if (center != null) {
            pt1 = new Point(center.x, center.y);
            pt2 = new Point(pt1.x, getHeight() + this.getGraphics().getFontMetrics(this.getFont()).getMaxAscent());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.setPaint(Color.WHITE);

        if (center == null) {
            this.center = new Point(0, getHeight() / 2 + g2d.getFontMetrics().getAscent() / 2 - g2d.getFontMetrics().getDescent() / 2);
            this.pt1 = new Point(center.x, center.y);
            this.pt2 = new Point(pt1.x, getHeight() + g2d.getFontMetrics().getMaxAscent());
        }

        g2d.drawString(name1, pt1.x, pt1.y);
        g2d.drawString(name2, pt2.x, pt2.y);
    }
}
