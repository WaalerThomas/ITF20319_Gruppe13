package no.booking;

import no.booking.Users.User;
import no.booking.logic.Booking;
import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TourTests {
    private FakeDatabase database;

    @BeforeEach
    public void set_up() {
        database = new FakeDatabase();
    }

    @Test
    /* Guide skal kunne opprette en omvisning */
    public void guide_can_create_a_tour() {
        database.createTour("TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, 2500, 0, "TestMeetingPoint", 5);
        int toursCount = database.getTours().toArray().length;
        assertEquals(1, toursCount);
    }

    @Test
    /* Turist bruker kan ikke booke billetter når det ikke er nok billetter tilgjengelig */
    public void tourist_can_not_book_a_full_tour() {
        Tour tourTest = new Tour("TestTour", "TestLand", "TestBy", "TestBeskrivelse",
                "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);
        tourTest.decreaseTicketCount(5);
        database.createTour(tourTest);
        database.createUser("TuridTurist");

        boolean result = tourTest.book(database, "TuridTurist", 1, 0, 0, "2023.11.11");
        assertFalse(result);
    }

    @Test
    /* Turist-bruker skal kunne betale for en tjeneste */
    public void tourist_can_book_a_tour() {
        Tour tourTest = new Tour("TestTour", "TestLand", "TestBy", "TestBeskrivelse",
                "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);
        database.createTour(tourTest);
        database.createUser("TuridTurist");

        boolean result = tourTest.book(database, "TuridTurist", 1, 0, 0, "2023.11.11");
        assertTrue(result);
    }

    @Test
    /* Turist bruker skal ikke kunne booke en omvisning flere ganger */
    public void tourist_can_not_book_the_same_tour_twice() {
        fail();
    }

    @Test
    /* Turist skal kunne se en liste over tilgjengelige omvisninger */
    public void tourist_can_list_all_available_tours() {
        database.createTour("TestTitle", "TestCountry", "TestCity", "TestDescption", "2023-10-10 17:00:00", 500, 250, 0, "TestMeetingpoint", 10);
        database.createTour("TestTitle2", "TestCountry2", "TestCity2", "TestDescption2", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint2", 10);
        database.createTour("TestTitle3", "TestCountry3", "TestCity3", "TestDescption3", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint3", 10);

        List<Tour> tours = database.getTours();
        assertFalse(tours.isEmpty());
        assertEquals(3, tours.size());
    }

    @Test
    /* Turist skal kunne sjekke hvilke omvisninger de har booket */
    public void tourist_can_list_their_booked_tours() {
        database.createUser("TuridTurist");
        database.createUser("FredrikkFeilmann");
        Tour tour1 = database.createTour("TestTitle", "TestCountry", "TestCity", "TestDescption", "2023-10-10 17:00:00", 500, 250, 0, "TestMeetingpoint", 10);
        Tour tour2 = database.createTour("TestTitle2", "TestCountry2", "TestCity2", "TestDescption2", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint2", 10);
        tour1.book(database, "TuridTurist", 1, 0, 0, "2023-10-28 17:00:00");
        tour1.book(database, "FredrikkFeilmann", 1, 0, 0, "2023-10-28 18:00:00");
        tour2.book(database, "TuridTurist", 1, 0, 0, "2023-10-29 17:00:00");

        List<Booking> bookings = database.getBookingsByUsername("TuridTurist");
        assertFalse(bookings.isEmpty());
        assertEquals(2, bookings.size());
    }
}
