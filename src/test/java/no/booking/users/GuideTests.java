package no.booking.users;

import no.booking.logic.Booking;
import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuideTests {
    private FakeDatabase database;

    @BeforeEach
    public void set_up() {
        database = new FakeDatabase();
    }

    @Test
    void checksIfGuideHasReceivedMoney() {
        fail();
    }

    @Test
    void checksIfGuideHasValidBankAccount() {
        fail();
    }

    @Test
    /* Guide skal kunne opprette en omvisning */
    public void can_create_a_tour() {
        database.createTour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, 2500, 0, "TestMeetingPoint", 5);
        int toursCount = database.getTours().toArray().length;
        assertEquals(1, toursCount);
    }

    @Test
    /* Guide kan ha en oversikt over alle omvisninger de har opprettet */
    public void can_see_all_their_created_tours() {
        database.createUser("GeorgGuide");
        database.createUser("FredrikkFeilmann");
        database.createTour("GeorgGuide", "TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);
        database.createTour("GeorgGuide", "TestTour2", "TestLand2", "TestBy2", "TestBeskrivelse2", "TestDate2", 5000, 2500, 0, "TestMeetingPoint2", 5);
        database.createTour("FredrikkFeilmann", "TestTour3", "TestLand3", "TestBy3", "TestBeskrivelse3", "TestDate3", 5000, 2500, 0, "TestMeetingPoint3", 5);

        List<Tour> tours = database.getToursByOwner("GeorgGuide");
        assertFalse(tours.isEmpty());
        assertEquals(2, tours.size());
    }

    @Test
    /* Guide bruker kan se hvor mange som har booket omvisningene sine */
    public void can_see_how_many_has_booked_their_tours() {
        database.createUser("GeorgGuide");
        database.createUser("TuridTurist");
        database.createUser("TrulsTurist");
        Tour tourTest = new Tour("GeorgGuide", "Title", "Country", "City",
                "Description", "2023-10-10 17:00:00", 500, 250,
                0, "MeetingPoint", 20);
        database.createTour(tourTest);
        tourTest.book(database, "TuridTurist", 2, 1, 0, "2023-10-05 10:00:00");
        tourTest.book(database, "TrulsTurist", 1, 0, 0, "2023-10-05 10:00:00");

        List<Booking> bookings = database.getBookingsByTourId(tourTest.getId());
        assertFalse(bookings.isEmpty());
        assertEquals(2, bookings.size());
        assertEquals(16, tourTest.getAvailableTicketsCount());
    }
}