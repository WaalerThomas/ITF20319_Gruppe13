package no.booking;

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
        database.createTour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, 2500, 0, "TestMeetingPoint", 5);
        int toursCount = database.getTours().toArray().length;
        assertEquals(1, toursCount);
    }

    @Test
    /* Guide kan ha en oversikt over alle omvisninger de har opprettet */
    public void guide_can_see_all_their_created_tours() {
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
    /* Turist bruker kan ikke booke billetter når det ikke er nok billetter tilgjengelig */
    public void tourist_can_not_book_a_full_tour() {
        Tour tourTest = new Tour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse",
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
        Tour tourTest = new Tour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse",
                "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);
        database.createTour(tourTest);
        database.createUser("TuridTurist");

        boolean result = tourTest.book(database, "TuridTurist", 1, 0, 0, "2023-11-11 11:00:00");
        assertTrue(result);
    }

    @Test
    /* Turist bruker skal ikke kunne booke en omvisning flere ganger */
    public void tourist_can_not_book_the_same_tour_twice() {
        Tour tourTest = new Tour("No-one", "TestTour", "TestLand", "TestBy", "TestBeskrivelse",
                "TestDate", 5000, 2500, 0, "TestMeetingPoint", 5);
        database.createTour(tourTest);
        database.createUser("TuridTurist");
        tourTest.book(database, "TuridTurist", 1, 0, 0, "2023-10-10 17:00:00");

        boolean result = tourTest.book(database, "TuridTurist", 1, 0, 0, "2023-10-10 17:00:00");
        assertFalse(result);
    }

    @Test
    /* Turist skal kunne se en liste over tilgjengelige omvisninger */
    public void tourist_can_list_all_available_tours() {
        database.createTour("No-one", "TestTitle", "TestCountry", "TestCity", "TestDescption", "2023-10-10 17:00:00", 500, 250, 0, "TestMeetingpoint", 10);
        database.createTour("No-one", "TestTitle2", "TestCountry2", "TestCity2", "TestDescption2", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint2", 10);
        database.createTour("No-one", "TestTitle3", "TestCountry3", "TestCity3", "TestDescption3", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint3", 10);

        List<Tour> tours = database.getTours();
        assertFalse(tours.isEmpty());
        assertEquals(3, tours.size());
    }

    @Test
    /* Turist skal kunne sjekke hvilke omvisninger de har booket */
    public void tourist_can_list_their_booked_tours() {
        database.createUser("TuridTurist");
        database.createUser("FredrikkFeilmann");
        Tour tour1 = database.createTour("No-one", "TestTitle", "TestCountry", "TestCity", "TestDescption", "2023-10-10 17:00:00", 500, 250, 0, "TestMeetingpoint", 10);
        Tour tour2 = database.createTour("No-one", "TestTitle2", "TestCountry2", "TestCity2", "TestDescption2", "2023-10-10 18:00:00", 500, 250, 0, "TestMeetingpoint2", 10);
        tour1.book(database, "TuridTurist", 1, 0, 0, "2023-10-28 17:00:00");
        tour1.book(database, "FredrikkFeilmann", 1, 0, 0, "2023-10-28 18:00:00");
        tour2.book(database, "TuridTurist", 1, 0, 0, "2023-10-29 17:00:00");

        List<Booking> bookings = database.getBookingsByUsername("TuridTurist");
        assertFalse(bookings.isEmpty());
        assertEquals(2, bookings.size());
    }
}
