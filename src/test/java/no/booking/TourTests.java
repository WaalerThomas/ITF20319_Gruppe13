package no.booking;

import no.booking.Users.User;
import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
