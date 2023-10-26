package no.booking.Users;

import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TourTests {

    @Test
    /* Guide skal kunne opprette en omvisning */
    public void guide_can_create_a_tour() {
        // Arrange
        FakeDatabase database = new FakeDatabase();
        /*Tour newTour = new Tour(1,"Tur til København", "Denmark", "København", "Fantastisk tur til københavn",
                "04.04.24", 600, "København Sentrum");
        newTour.setTourId(1);
         */
        // Act
        // TODO: Please implement this test
        Tour tourTest = database.createTour(1, "TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, "TestMeetingPoint");

        // Assert
        //assertEquals(1, database.getTourById(1).getTourId());
        assertEquals(1, tourTest.getTourId());
    }
}
