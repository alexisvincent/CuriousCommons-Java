package swing.componentsOld;

import swing.components.AScrollPane;
import swing.components.AComponent;
import swing.componentsOld.AListModel.ModelUpdatedListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

/**
 *
 * @author alexisvincent
 */
public class AList extends AComponent {

    private AListModel model;
    private AScrollPane scrollPane;
    private GridBagConstraints gc;

    public AList() {
        this(new AListModel());
    }

    public AList(AListModel model) {
        setModel(model);

        getItemsPane().setLayout(new GridBagLayout());
        
        scrollPane = new AScrollPane(getItemsPane());

        this.setDefaultGridbagConstraints();
        this.setLayout(new GridBagLayout());
        this.add(scrollPane, gc);

        this.buildItemsPane();

        getModel().addModelUpdatedListener(new ModelUpdatedListener() {
            @Override
            public void modelUpdated() {
                buildItemsPane();
            }
        });

    }

    private void setDefaultGridbagConstraints() {
        //Setup Layor Constraints
        gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.NORTH;
        gc.weightx = 1;
        gc.weighty = 1;
    }

    private void buildItemsPane() {
        getItemsPane().removeAll();

        this.setDefaultGridbagConstraints();
        gc.insets = getInsets();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.NORTH;
        gc.weighty = 0;

        for (AListItem aListItem : getItems()) {
            getItemsPane().add(aListItem, gc);
            gc.gridy++;
        }
        
        repaint();
    }

    public AComponent getItemsPane() {
        return getModel().getItemsPane();
    }

    public void setItemsPane(AComponent itemsPane) {
        getModel().setItemsPane(itemsPane);
    }

    public ArrayList<AListItem> getItems() {
        return getModel().getItems();
    }

    public void setItems(ArrayList<AListItem> items) {
        getModel().setItems(items);
    }

    public AListModel getModel() {
        return model;
    }

    public void setModel(AListModel model) {
        this.model = model;
    }

    public void setInsets(Insets insets) {
        getModel().setInsets(insets);
    }

    public void setSelectedItem(AListItem item) {
        getModel().setSelectedItem(item);
    }

    public AListItem getSelectedItem() {
        return getModel().getSelectedItem();
    }

    @Override
    public Insets getInsets() {
        return getModel().getInsets();
    }
}
