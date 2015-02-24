package swing.components;

import animationEngine.Animator;
import animationEngine.SegmentGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class AComponent extends JComponent implements Animator {

    protected boolean debugMode = false;
    protected boolean focus = false;
    protected boolean hover = false;
    
    public GridBagConstraints gc;
    
    protected SegmentGroup segmentGroup;
    
    protected Point position;
    private AColor background;
    
    public AComponent() {
        this("Default Name");
    }

    public AComponent(String name) {
        setName(name);
        setDoubleBuffered(true);
        
        this.segmentGroup = new SegmentGroup();
        
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseExited(MouseEvent e) {
                setHover(false);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setHover(true);
                repaint();
            }
        });
        
        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                setFocus(false);
                repaint();
            }

            @Override
            public void focusGained(FocusEvent e) {
                setFocus(true);
                repaint();
            }
        });
        
        gc = UIToolkit.getDefaultGridBagConstraints();
        setLayout(new GridBagLayout());
        
        init();
    }
    
    protected void init(){};

    public SegmentGroup getSegmentGroup() {
        return segmentGroup;
    }
    
    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    @Override
    public AColor getBackground() {
        return background;
    }

    public void setBackground(AColor background) {
        this.background = background;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    @Override
    protected void paintComponent(Graphics g) {
        doAnimation();
        if (isDebugMode()) {
            Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

            Random randomColor = new Random();
            g2d.setPaint(new Color(randomColor.nextFloat(), randomColor.nextFloat(), randomColor.nextFloat()));

            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public void doAnimation() {
        segmentGroup.doAnimation();
    }
}
