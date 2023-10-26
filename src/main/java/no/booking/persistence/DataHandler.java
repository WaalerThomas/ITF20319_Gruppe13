package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Tour;

import java.util.List;

public interface DataHandler {
    User getUserByUsername(String username);
    List<Tour> getTours();
    Tour getTourById(int id);
    List<Tour> getToursByCity(String city);

    void createTour(int tourId, String title, String city, String country, String description, String date,
                    int price_Per_Type_Ticket, String meetingPoint);
}

