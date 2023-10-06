package no.booking.persistence;

import no.booking.Users.User;

public interface DataHandler {
    public User getUserByUsername(String username);
}
