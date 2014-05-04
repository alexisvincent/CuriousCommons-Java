/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swing.componentsOld;

import swing.components.AColor;
import swing.components.AComponent;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import swing.componentsOld.AListModel.FocusListener;
import swing.componentsOld.AListModel.SelectionListener;
import swing.toolkit.UIToolkit;

/**
 *
 * @author alexisvincent
 */
public class AListItem extends AComponent {

    private String displayName;
    private Paint normalBackgroundPaint;
    private Paint normalBorderPaint;
    private Paint normalDisplayNamePaint;
    private Paint focusBackgroundPaint;
    private Paint focusBorderPaint;
    private Paint focusDisplayNamePaint;
    private Paint selectedBackgroundPaint;
    private Paint selectedBorderPaint;
    private Paint selectedDisplayNamePaint;
    private boolean focus;
    private boolean selected;
    private ArrayList<SelectionListener> selectionListeners;
    private ArrayList<FocusListener> focusListeners;

    public AListItem() {
        this("DefaultName");
    }

    public AListItem(String displayName) {
        setDisplayName(displayName);
        this.setPreferredSize(new Dimension(0, 30));

        selectionListeners = new ArrayList<>();
        focusListeners = new ArrayList<>();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                setSelected(true);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setFocus(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setFocus(false);
            }
        });

        normalBackgroundPaint = AColor.LightBlue;
        normalBorderPaint = AColor.LightBlue;
        normalDisplayNamePaint = AColor.WHITE;
        focusBackgroundPaint = AColor.LightBlue;
        focusBorderPaint = AColor.DarkGreen;
        focusDisplayNamePaint = AColor.WHITE;
        selectedBackgroundPaint = AColor.LightBlue;
        selectedBorderPaint = AColor.WHITE;
        selectedDisplayNamePaint = AColor.WHITE;
    }

    public Paint getNormalBackgroundPaint() {
        return normalBackgroundPaint;
    }

    public void setNormalBackgroundPaint(Paint normalBackgroundPaint) {
        this.normalBackgroundPaint = normalBackgroundPaint;
    }

    public Paint getNormalBorderPaint() {
        return normalBorderPaint;
    }

    public void setNormalBorderPaint(Paint normalBorderPaint) {
        this.normalBorderPaint = normalBorderPaint;
    }

    public Paint getNormalDisplayNamePaint() {
        return normalDisplayNamePaint;
    }

    public void setNormalDisplayNamePaint(Paint normalDisplayNamePaint) {
        this.normalDisplayNamePaint = normalDisplayNamePaint;
    }

    public Paint getFocusBackgroundPaint() {
        return focusBackgroundPaint;
    }

    public void setFocusBackgroundPaint(Paint focusBackgroundPaint) {
        this.focusBackgroundPaint = focusBackgroundPaint;
    }

    public Paint getFocusBorderPaint() {
        return focusBorderPaint;
    }

    public void setFocusBorderPaint(Paint focusBorderPaint) {
        this.focusBorderPaint = focusBorderPaint;
    }

    public Paint getFocusDisplayNamePaint() {
        return focusDisplayNamePaint;
    }

    public void setFocusDisplayNamePaint(Paint focusDisplayNamePaint) {
        this.focusDisplayNamePaint = focusDisplayNamePaint;
    }

    public Paint getSelectedBackgroundPaint() {
        return selectedBackgroundPaint;
    }

    public void setSelectedBackgroundPaint(Paint selectedBackgroundPaint) {
        this.selectedBackgroundPaint = selectedBackgroundPaint;
    }

    public Paint getSelectedBorderPaint() {
        return selectedBorderPaint;
    }

    public void setSelectedBorderPaint(Paint selectedBorderPaint) {
        this.selectedBorderPaint = selectedBorderPaint;
    }

    public Paint getSelectedDisplayNamePaint() {
        return selectedDisplayNamePaint;
    }

    public void setSelectedDisplayNamePaint(Paint selectedDisplayNamePaint) {
        this.selectedDisplayNamePaint = selectedDisplayNamePaint;
    }

    public ArrayList<SelectionListener> getSelectionListeners() {
        return selectionListeners;
    }

    public void setSelectionListeners(ArrayList<SelectionListener> selectionListeners) {
        this.selectionListeners = selectionListeners;
    }

    public void setFocusListeners(ArrayList<FocusListener> focusListeners) {
        this.focusListeners = focusListeners;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
        if (focus) {
            fireFocusListeners();
        }
        repaint();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            fireSelectionListeners();
        }
        repaint();
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void addSelectionListener(SelectionListener listener) {
        selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener) {
        selectionListeners.remove(listener);
    }

    public void fireSelectionListeners() {
        for (SelectionListener listener : selectionListeners) {
            listener.itemSelected(this);
        }
    }

    public void addFocusListener(FocusListener listener) {
        focusListeners.add(listener);
    }

    public void removeFocusListener(FocusListener listener) {
        focusListeners.remove(listener);
    }

    public void fireFocusListeners() {
        for (FocusListener listener : focusListeners) {
            listener.itemFocused(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = UIToolkit.getPrettyGraphics(g);
        g2d.setStroke(new BasicStroke(2f));
        g2d.setPaint(normalBackgroundPaint);
        if (isSelected()) {
            g2d.setPaint(selectedBackgroundPaint);
        } else if (isFocus()) {
            g2d.setPaint(focusBackgroundPaint);
        }
        g2d.fillRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 15, 15);
        g2d.setPaint(normalBorderPaint);
        if (isFocus()) {
            g2d.setPaint(focusBorderPaint);
        } else if (isSelected()) {
            g2d.setPaint(selectedBorderPaint);
        }
        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 15, 15);
        g2d.setPaint(normalDisplayNamePaint);
        if (isSelected()) {
            g2d.setPaint(selectedDisplayNamePaint);
        } else if (isFocus()) {
            g2d.setPaint(focusDisplayNamePaint);
        }
        g2d.drawString(getDisplayName(), 15, 20);
    }
}
