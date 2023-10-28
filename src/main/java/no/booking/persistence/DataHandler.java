package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Tour;

import java.util.List;
import java.util.UUID;

public interface DataHandler {
    User getUserByUsername(String username);
    List<Tour> getTours();
    Tour getTourById(UUID id);
    List<Tour> getToursByCity(String city);

    Tour createTour(String title, String city, String country, String description, String date,
                    int price_Per_Type_Ticket, String meetingPoint, int maxTicketAmount);
}

