package no.booking.Users;

import no.booking.logic.Tour;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TourTests {

    @Test
    /* Guide skal kunne opprette en omvisning */
    public void guide_can_create_a_tour() {
        FakeDatabase database = new FakeDatabase();
        Tour tourTest = database.createTour(1, "TestTour", "TestLand", "TestBy", "TestBeskrivelse", "TestDate",
                5000, "TestMeetingPoint");
        assertEquals(1, tourTest.getTourId());
        
    }
}
