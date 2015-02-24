package swing.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import swing.toolkit.UIToolkit;

/**
 * O.o ... A Wild Button Appeared
 *
 * @author alexisvincent
 */
public class AButton extends AComponent {

    protected Color backdrop, font;
    protected Color backdropNormal, backdropMouseOver, fontNormal, fontMouseOver;
    private boolean selected;

    public AButton(String name) {
        backdropNormal = new Color(0, 120, 200, 40);
        backdropMouseOver = new Color(0, 120, 200, 100);

        fontNormal = backdropMouseOver.brighter().brighter();
        fontMouseOver = new Color(255, 255, 255, 200);
        
        backdrop = backdropNormal;
        font = fontNormal;

        this.setName(name);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackdrop(getBackdropMouseOver());
                setFont(getFontMouseOver());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackdrop(getBackdropNormal());
                setFont(getFontNormal());
            }
        });
    }
    

    public void setBackdrop(Color backdrop) {
        this.backdrop = backdrop;
    }

    public void setFont(Color font) {
        this.font = font;
    }
    
    public Color getBackdropNormal() {
        return backdropNormal;
    }

    public void setBackdropNormal(Color backdropNormal) {
        this.backdropNormal = backdropNormal;
    }

    public Color getBackdropMouseOver() {
        return backdropMouseOver;
    }

    public void setBackdropMouseOver(Color backdropMouseOver) {
        this.backdropMouseOver = backdropMouseOver;
    }

    public Color getFontNormal() {
        return fontNormal;
    }

    public void setFontNormal(Color fontNormal) {
        this.fontNormal = fontNormal;
    }

    public Color getFontMouseOver() {
        return fontMouseOver;
    }

    public void setFontMouseOver(Color fontMouseOver) {
        this.fontMouseOver = fontMouseOver;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);

        g2d.setPaint(backdrop);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setFont(getFont());
        g2d.setPaint(font);
        g2d.drawString(getName(), getWidth() / 2 - g2d.getFontMetrics().stringWidth(getName()) / 2, getHeight() / 2 + g2d.getFontMetrics().getAscent() / 2 - g2d.getFontMetrics().getDescent() / 2);
    }
}
