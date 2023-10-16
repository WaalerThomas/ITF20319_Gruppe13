package no.booking.fakes;

import no.booking.logic.Tour;
import no.booking.persistence.DataHandler;
import no.booking.persistence.FakeDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeDatabaseTest {

    //sjekker om listen er
    @Test
    public void checkingIfListOfTourIsEmpty(){
        DataHandler database = new FakeDatabase();
        List<Tour> tours = database.getTours();
        assertTrue(!tours.isEmpty());
        assertNotNull(tours);
    }

    @Test
    public void checkingIfTourIdIsWorking() {
        DataHandler database = new FakeDatabase();
        Tour tour = database.getTourById(1);
        assertNotNull(tour);
        assertEquals(1, tour.getTourId());

        Tour faketur = database.getTourById(956);
        assertNull(faketur);
    }}