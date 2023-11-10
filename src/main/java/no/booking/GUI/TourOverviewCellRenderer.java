package no.booking.GUI;

import no.booking.logic.Tour;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TourOverviewCellRenderer extends TourOverviewElement implements ListCellRenderer<Tour> {
    public TourOverviewCellRenderer() {
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tour> jList, Tour tour, int i, boolean b, boolean b1) {
        JPanel mainPanel = getMainPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 0, 5));

        setTitle(tour.getTitle());
        setCity(tour.getCity());
        setAvailableTickets(tour.getAvailableTicketsCount());
        setMaxTickets(tour.getMaxTicketAmount());

        return mainPanel;
    }
}
