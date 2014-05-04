package swing.components.decoration;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import swing.components.AColor;
import swing.components.AComponent;
import swing.components.AFrame;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class AHeader extends AComponent {

    private GridBagConstraints gc;

    private HeaderButton close;
    private HeaderButton minimise;
    private HeaderButton maximise;

    private Point ptOnFrame;

    public AHeader(final AFrame frame) {
        super("AHeader");
        
        init(frame);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(0, 40));

        this.add(close, gc);
        gc.insets = new Insets(8, 8, 8, 8);
        gc.gridx++;
        this.add(minimise, gc);
        gc.gridx++;
        gc.weightx = 1;
        this.add(maximise, gc);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ptOnFrame = e.getPoint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - ptOnFrame.x, e.getYOnScreen() - ptOnFrame.y);
            }
        });

    }

    private void init(final AFrame frame) {

        setBackground(AColor.DarkGrey);
        gc = UIToolkit.getDefaultGridBagConstraints();
        gc.insets = new Insets(8, 14, 8, 8);
        gc.anchor = GridBagConstraints.WEST;
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 0;

        close = new HeaderButton(AColor.Red);
        minimise = new HeaderButton(AColor.DarkBlue);
        maximise = new HeaderButton(AColor.DarkGreen);

        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.close();
            }
        });

        minimise.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.minimise();
            }
        });

        maximise.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                frame.maximise();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.setPaint(AColor.DarkGrey);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private class HeaderButton extends AComponent {

        public HeaderButton(AColor color) {
            super("MenuButton");
            this.init(color);
        }

        private void init(AColor color) {
            this.setBackground(color);
            this.setPreferredSize(new Dimension(15, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
            if (!this.isHover()) {
                g2d.setPaint(getBackground().darker());
            } else {
                g2d.setPaint(getBackground());
            }
            g2d.fillOval(0, 0, getWidth(), getHeight());
        }

    }

}
