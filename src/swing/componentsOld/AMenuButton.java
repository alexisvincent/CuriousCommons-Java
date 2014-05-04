package swing.componentsOld;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JComponent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class AMenuButton extends JComponent {

    private Image imgNormal, imgMouseOver, imgDisplay;
    private Dimension imgDimensions;

    public AMenuButton(Image imgNormal, Image imgMouseOver) {

        this.imgNormal = imgNormal;
        this.imgMouseOver = imgMouseOver;
        this.imgDisplay = this.imgNormal;
        
        imgDimensions = new Dimension(imgDisplay.getWidth(this), imgDisplay.getHeight(this));

        this.setMinimumSize(new Dimension(imgDimensions));
        this.setPreferredSize(new Dimension(imgDimensions));
    }

    public void setMouseOver(boolean mouseOver) {
        if (mouseOver) {
            imgDisplay = imgMouseOver;
        } else {
            imgDisplay = imgNormal;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.drawImage(imgDisplay, 0, 0, (int)imgDimensions.getWidth(), (int)imgDimensions.getHeight(), this);
    }

}
