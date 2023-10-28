package no.booking;

import no.booking.logic.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TourTicketCountTests {
    private Tour tourTest;

    @BeforeEach
    public void set_up() {
        tourTest = new Tour("TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, 2500, 0, "TestMeetingPoint", 5);
    }

    @Test
    public void cannot_remove_more_tickets_than_available() {
        boolean result = tourTest.decreaseTicketCount(6);
        assertFalse(result);
        assertEquals(5, tourTest.getAvailableTicketsCount());
    }

    @Test
    public void can_remove_available_tickets() {
        boolean result = tourTest.decreaseTicketCount(4);
        assertTrue(result);
        assertEquals(1, tourTest.getAvailableTicketsCount());
    }

    @Test
    public void cannot_add_more_tickets_than_the_max_amount_set() {
        boolean result = tourTest.increaseTicketCount();
        assertFalse(result);
        assertEquals(5, tourTest.getAvailableTicketsCount());
    }

    @Test
    public void can_add_available_tickets_and_not_exceeding_the_max_amount() {
        tourTest.decreaseTicketCount(3);

        boolean result = tourTest.increaseTicketCount(2);
        assertTrue(result);
        assertEquals(4, tourTest.getAvailableTicketsCount());
    }
}
