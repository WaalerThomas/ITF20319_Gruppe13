package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Tour;

import java.util.List;
import java.util.Set;

public interface DataHandler {
    User getUserByUsername(String username);
    List<Tour> getTours();
    Tour getTourById(int id);
    List<Tour> getToursByCity(String city);
}

