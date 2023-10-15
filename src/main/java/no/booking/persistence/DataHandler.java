package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Tour;

import java.util.List;
import java.util.Set;

public interface DataHandler {
    public User getUserByUsername(String username);
    public List<Tour> getTours();
    public List<Tour> getToursByCity(String city);
}

