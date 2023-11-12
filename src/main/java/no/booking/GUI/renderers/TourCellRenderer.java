package no.booking.GUI.renderers;

import no.booking.GUI.list_elements.TourElement;
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

        int maxTicketPrice = Math.max(tour.getAdultTicketPrice(), Math.max(tour.getChildTicketPrice(), tour.getInfantTicketPrice()));
        setPrice(String.valueOf(maxTicketPrice));

        return mainPanel;
    }
}
