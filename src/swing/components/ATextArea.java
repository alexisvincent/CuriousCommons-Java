package swing.components;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ATextArea extends JTextArea{

    private static final Color backdrop;
    private static final Color outline;
    
    static{
        backdrop = new Color(0,172,255,30);
        outline = new Color(0,172,255,230);
    }

    public ATextArea() {
        this.setOpaque(false);
        this.setForeground(Color.WHITE);
        this.setCaretColor(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(10, 5, 3, 5));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.setPaint(backdrop);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(),8,8);
        g2d.setPaint(outline);
        g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1,8,8);
        super.paintComponent(g);
    }
    
    
}
