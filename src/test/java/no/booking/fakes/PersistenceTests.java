package no.booking.fakes;

import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceTests {
    private FakeDatabase database;

    @BeforeEach
    public void set_up() {
        database = new FakeDatabase();
    }

    @Test
    public void check_if_list_of_tours_is_empty(){
        List<Tour> tours = database.getTours();
        assertTrue(tours.isEmpty());
        assertNotNull(tours);
    }

    @Test
    public void check_if_we_get_the_correct_tour_by_id() {
        String testID = "f6306509-b1d9-4820-a060-5c2a2e8a610d";
        Tour testTour = database.createTour("TourTitle", "TourCountry", "TourCity", "TourDescription",
                "TourDate", 500, "TourMeetingPoint", 5);
        testTour.setId(UUID.fromString(testID));

        Tour tour = database.getTourById(testTour.getId());
        assertNotNull(tour);
        assertEquals(testID, tour.getId().toString());

        Tour fakeTour = database.getTourById(UUID.randomUUID());
        assertNull(fakeTour);
    }}