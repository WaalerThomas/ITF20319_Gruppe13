package no.booking;

import no.booking.logic.Tour;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourTests {
    @Test
    public void can_not_set_a_negative_ticket_price() {
        Tour tourTest = new Tour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse",
                "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);

        tourTest.setAdultTicketPrice(-5);
        assertEquals(0, tourTest.getAdultTicketPrice());

        tourTest.setChildTicketPrice(-5);
        assertEquals(0, tourTest.getChildTicketPrice());

        tourTest.setInfantTicketPrice(-5);
        assertEquals(0, tourTest.getInfantTicketPrice());
    }
}
