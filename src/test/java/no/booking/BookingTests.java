package no.booking;

import no.booking.logic.Booking;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTests {
    @Test
    public void can_not_set_a_negative_amount_of_tickets() {
        Booking booking = new Booking("NoOwner", UUID.randomUUID(), 1, 1, 1, 750, "2023-10-10 17:00:00");

        booking.setAdultTicketAmount(-5);
        assertEquals(0, booking.getAdultTicketAmount());

        booking.setChildTicketAmount(-5);
        assertEquals(0, booking.getChildTicketAmount());

        booking.setInfantTicketAmount(-5);
        assertEquals(0, booking.getInfantTicketAmount());
    }

    @Test
    public void can_not_set_a_negative_total_cost() {
        Booking booking = new Booking("NoOwner", UUID.randomUUID(), 1, 1, 1, 750, "2023-10-10 17:00:00");

        booking.setTotalCost(-500);
        assertEquals(0, booking.getTotalCost());
    }

    @Test
    public void can_return_correct_total_amount_of_tickets() {
        Booking booking = new Booking("NoOwner", UUID.randomUUID(), 5, 10, 15, 750, "2023-10-10 17:00:00");

        int result = booking.getTotalTicketAmount();
        assertEquals(30, result);
    }
}
