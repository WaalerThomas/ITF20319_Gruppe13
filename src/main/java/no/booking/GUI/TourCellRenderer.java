package no.booking.GUI;

import com.intellij.uiDesigner.core.GridLayoutManager;
import no.booking.logic.Tour;

import javax.swing.*;
import java.awt.*;

public class TourCellRenderer extends TourElement implements ListCellRenderer<Tour> {
    public TourCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tour> list, Tour tour, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel mainPanel = getMainPanel();
        //mainPanel.add(new JLabel(tour.title));
        setTitle(tour.title);

        Color background;
        Color foreground;

        if (isSelected) {
            background = Color.BLUE;
            foreground = Color.WHITE;
        } else {
            background = Color.RED;
            foreground = Color.WHITE;
        }

        mainPanel.setBackground(background);
        mainPanel.setForeground(foreground);

        return mainPanel;
    }
}
