package no.booking.GUI;

import com.intellij.uiDesigner.core.GridLayoutManager;
import no.booking.logic.Tour;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TourCellRenderer extends TourElement implements ListCellRenderer<Tour> {
    public TourCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tour> list, Tour tour, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel mainPanel = getMainPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 0, 5));

        setCity(tour.getCity());
        setTitle(tour.getTitle());
        setDescription(tour.getDescription());

        return mainPanel;
    }
}
