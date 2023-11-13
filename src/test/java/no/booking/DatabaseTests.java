package no.booking;

import no.booking.logic.Tour;
import no.booking.persistence.Database;
import no.booking.users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTests {
    Database database;

    @BeforeEach
    void set_up() {
        database = new Database("test_database.sqlite", false);
    }

    @AfterEach
    void teardown() {
        File file = new File(database.getFolderPath() + "/" + database.getFileName());
        if (!file.delete())
            throw new RuntimeException("Could not delete database file");
    }

    @Test
    void can_update_ticket_count_on_a_tour() {
        database.createUser("TestUser");
        Tour testTour = database.createTour("TestUser", "TestTitle", "TestCountry", "TestCity",
                "TestDescription", "2023-11-10 17:00:00", 500, 250,
                0, "TestMeetingpoint", 5);
        testTour.decreaseTicketCount(3);

        database.updateTour(testTour);
        Tour dataTour = database.getTourById(testTour.getId());
        assertNotNull(dataTour);
        assertEquals(2, dataTour.getAvailableTicketsCount());
        assertEquals(5, dataTour.getMaxTicketAmount());
    }

    @Test
    void can_get_user_by_username() {
        database.createUser("TestUser");

        User user = database.getUserByUsername("TestUser");
        assertNotNull(user);
        assertEquals("TestUser", user.getUserName());
    }

    @Test
    void can_get_all_tours_available() {
        database.createUser("TestUser");
        database.createTour("TestUser", "TestTitle", "TestCountry", "TestCity",
                "TestDescription", "2023-11-10 17:00:00", 500, 250,
                0, "TestMeetingpoint", 0);

        List<Tour> tours = database.getTours();
        assertNotNull(tours);
        assertEquals(1, tours.size());
    }

    @Test
    void can_get_a_tour_by_id() {
        UUID testID = UUID.fromString("8236a967-1f7a-4695-97ed-ff6207483806");
        database.createUser("TestUser");
        Tour tempTour = new Tour("TestUser", "TestTitle", "TestCountry", "TestCity",
                "TestDescription", "2023-11-10 17:00:00", 500, 250,
                0, "TestMeetingpoint", 0);
        tempTour.setId(testID);
        database.createTour(tempTour);

        Tour testTour = database.getTourById(testID);
        assertNotNull(testTour);
        assertEquals(testID.toString(), testTour.getId().toString());
    }
}
