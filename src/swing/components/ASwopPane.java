package swing.components;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class ASwopPane extends AComponent {

    public ASwopPane() {
        this("ASwopPane");
    }

    public ASwopPane(String name) {
        super(name);
        setLayout(new GridBagLayout());
    }
    
    /**
     * Adds a listener to automatically hide all other panes in this component when one is shown.
     * @param pane 
     */
    public void addSwopable(AComponent pane) {
        pane.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                //check all components on this layer
                for (Component component : ASwopPane.this.getComponents()) {
                    //we only want to hide all OTHER components except the component we set to visible
                    if (!e.getComponent().equals(component)) {
                        component.setVisible(false);
                    }
                }
            }
        });
        pane.setVisible(false);
        this.add(pane, UIToolkit.getDefaultGridBagConstraints());
    }
}
