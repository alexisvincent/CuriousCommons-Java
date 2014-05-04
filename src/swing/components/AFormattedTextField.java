package swing.components;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import swing.toolkit.UIToolkit;

/**
 * @about Oh how wonderful it is to be a AFormattedTextField...
 * @author alexisvincent
 */
public class AFormattedTextField extends JFormattedTextField {

    private static Color backdrop;
    private static Color outline;
    private static Font font;

    static {
        backdrop = new Color(0, 172, 255, 30);
        outline = new Color(0, 172, 255, 230);
    }

    public AFormattedTextField() {
        this(new MaskFormatter());
    }

    public AFormattedTextField(MaskFormatter maskFormatter) {
        super(maskFormatter);
        this.setOpaque(false);
        this.setForeground(Color.WHITE);
        this.setCaretColor(Color.WHITE);
        this.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.setPaint(backdrop);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
        g2d.setPaint(outline);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        super.paintComponent(g);
    }

}
