/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.componentsOld;

import swing.components.AColor;
import swing.components.AComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class APopup extends JFrame {
    
    private String message;
    private Font font;
    FontMetrics fm;

    public APopup() throws HeadlessException {
        this("");
    }

    public APopup(String title) throws HeadlessException {
        super(title);
        message = title;
        
        //font = ResourceManager.getFont("Sax Mono").deriveFont(22f);
        fm = getFontMetrics(font);
        
        this.setSize(fm.stringWidth(message)+50, 100);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);
        this.requestFocus();
        this.setBackground(new Color(0,0,0,0));
        
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                APopup.this.dispose();
            }
        });
        
        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                APopup.this.dispose();
            }

        });
        
        AComponent contentPane = new AComponent() {

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
                g2d.setPaint(AColor.DarkGrey);
                g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 15, 15);
                
                g2d.setPaint(AColor.DarkBlue);
                g2d.setFont(font);
                g2d.drawString(message, getWidth()/2-fm.stringWidth(message)/2, getHeight()/2+fm.getMaxAscent()/2);
            }
            
        };
        
        this.setContentPane(contentPane);
    }
    
    
}
