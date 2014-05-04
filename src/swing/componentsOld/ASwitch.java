package swing.componentsOld;

import swing.components.AColor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ASwitch extends JComponent {

    private static final Color backdropMouseOver, backdropNormal, outlineON, outlineOFF, fontON, fontOFF;
    private Color outlineAnimation, outline, font, backdrop;
    private static final String textON, textOFF;
    private String text;
    private boolean online, isAnimating;
    private int arc;
    private Thread animation;
    private ArrayList<BStateListener> listeners;

    static {
        backdropNormal = new Color(0, 172, 255, 30);
        backdropMouseOver = new Color(0, 172, 255, 60);
        outlineON = AColor.DarkGreen;
        outlineOFF = AColor.DarkBlue;
        fontON = outlineON;
        fontOFF = outlineOFF;
        textON = "ONLINE";
        textOFF = "OFFLINE";
    }

    public ASwitch() {
        online = false;
        font = fontOFF;
        outline = outlineOFF;
        text = textOFF;
        backdrop = backdropNormal;
        reset(); //initiate arc and isAnimating
        listeners = new ArrayList<>();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Thread setOnline = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        setOnline(!online);
                    }
                });
                setOnline.start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backdrop = backdropMouseOver;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backdrop = backdropNormal;
                repaint();
            }
            
            
        });

    }

    public void reset() {
        arc = 0;
        isAnimating = false;
    }

    public void setOnline(boolean online) {
        if (!isAnimating) {
            isAnimating = true;
            this.online = online;
            outlineAnimation = (online) ? outlineON : outlineOFF;
            font = (online) ? fontON : fontOFF;
            text = (online) ? textON : textOFF;
            Timer timer = new Timer(5, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (arc < 360) {
                        arc+=6;
                        repaint();
                        font = new Color(font.getRed(), font.getGreen(), font.getBlue(), arc*255/360);
                    } else {
                        outline = outlineAnimation;
                        reset();
                        repaint();
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();
            fireStateChanged(online);
        }

    }
    
    public void addStateListener(BStateListener listener) {
        this.listeners.add(listener);
    }
    
    public void fireStateChanged(boolean state) {
        for (BStateListener listener : listeners) {
            listener.stateChanged(state);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

        int cornerRadius = 18;
        
        g2d.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));

        g2d.setPaint(backdrop);
        g2d.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, cornerRadius, cornerRadius);

        g2d.setPaint(outline);
        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, cornerRadius, cornerRadius);
        
        g2d.setPaint(font);
        g2d.drawString(text, getWidth() / 2 - g2d.getFontMetrics().stringWidth(text) / 2, getHeight() / 2 + g2d.getFontMetrics().getAscent() / 2 - g2d.getFontMetrics().getDescent() / 2);
        
        g2d.clip(new Arc2D.Double(-this.getWidth() / 2, -this.getHeight() / 2, this.getWidth() * 2, this.getHeight() * 2, 140, -arc, Arc2D.PIE));

        g2d.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
        g2d.setPaint(outlineAnimation);
        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, cornerRadius, cornerRadius);

    }
}
