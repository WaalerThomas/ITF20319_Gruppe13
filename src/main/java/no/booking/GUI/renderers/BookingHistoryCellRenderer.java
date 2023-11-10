package no.booking.GUI.renderers;

import no.booking.GUI.list_elements.BookingHistoryElement;
import no.booking.logic.Booking;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookingHistoryCellRenderer extends BookingHistoryElement implements ListCellRenderer<Booking> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Booking> jList, Booking booking, int i, boolean b, boolean b1) {
        JPanel mainPanel = getMainPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(5, 5, 0, 5));

        // TODO: Get the title of the tour

        setTitle(booking.getTourId().toString());
        setAdultTicketCount(booking.getAdultTicketAmount());
        setChildTicketCount(booking.getChildTicketAmount());
        setInfantTicketCount(booking.getInfantTicketAmount());
        setDateBooked(booking.getDate());
        setPrice(booking.getTotalCost());

        return mainPanel;
    }
}
