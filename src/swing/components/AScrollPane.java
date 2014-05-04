package swing.components;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/**
 *
 * @author alexisvincent
 */
public class AScrollPane extends JScrollPane {

    public AScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        customOptions();
    }

    public AScrollPane(Component view) {
        super(view);
        customOptions();
    }

    public AScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        customOptions();
    }

    public AScrollPane() {
        super();
        customOptions();
    }

    private void customOptions() {
        this.setOpaque(false);
        this.getViewport().setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        this.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
    }
}
