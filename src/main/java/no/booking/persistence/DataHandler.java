package no.booking.persistence;

import no.booking.Users.User;
import no.booking.logic.Booking;
import no.booking.logic.Tour;

import java.util.List;
import java.util.UUID;

public interface DataHandler {
    User getUserByUsername(String username);
    List<Tour> getTours();
    Tour getTourById(UUID id);
    List<Tour> getToursByCity(String city);
    Tour createTour(String title, String country, String city, String description, String date, int adultTicketPrice,
                    int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount);
    void createTour(Tour tour);
    void updateTour(Tour tour);
    void addBooking(Booking booking);
    List<Booking> getBookingsByTourId(UUID tourId);
    List<Booking> getBookingsByUsername(String username);
    void createUser(String username);
}