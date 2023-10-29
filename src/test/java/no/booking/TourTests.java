package no.booking;

import no.booking.logic.Booking;
import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void can_calculate_the_correct_total_cost_for_booking() {
        FakeDatabase database = new FakeDatabase();
        Tour tourTest = database.createTour("No-on", "Title", "Country", "City",
                "Description", "2023-10-10 17:00:00", 71, 43, 10, "MeetingPoint", 20);

        tourTest.book(database, "TuridTurist", 2, 1, 3, "2023-10-23 16:00:00");
        List<Booking> bookings = database.getBookingsByUsername("TuridTurist");
        assertEquals(215, bookings.get(0).getTotalCost());
    }
}
