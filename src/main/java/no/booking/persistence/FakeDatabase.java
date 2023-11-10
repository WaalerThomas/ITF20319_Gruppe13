package no.booking.persistence;

import no.booking.logic.Booking;
import no.booking.users.Admin;
import no.booking.users.Guide;
import no.booking.users.Tourist;
import no.booking.users.User;
import no.booking.logic.Tour;

import java.util.*;
import java.util.stream.Collectors;

public class FakeDatabase implements DataHandler {
    private final Set<User> users;
    private final List<Tour> tours;
    private final List<Booking> bookings;

    public FakeDatabase() {
        this.users = new HashSet<>();
        this.tours = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void createDefaultApplicationData() {
        addDefaultData();
        addDefaultDataTours();
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
    }

    @Override
    public List<Tour> getTours() {
        return tours;
    }

    @Override
    public Tour getTourById(UUID id) {
        return tours.stream().filter(tour -> tour.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Tour> getToursByCity(String city) {
        // Return every tour if the filter is blank
        if (city.isBlank()) return getTours();

        return tours.stream().filter(tour -> tour.getCity().contains(city)).collect(Collectors.toList());
    }

    @Override
    public List<Tour> getToursByOwner(String ownerUsername) {
        return tours.stream().filter(tour -> tour.getOwnerUsername().equals(ownerUsername)).collect(Collectors.toList());
    }

    @Override
    public Tour createTour(String guideUsername, String title, String country, String city, String description, String date, int adultTicketPrice, int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount) {
        Tour newTour = new Tour(guideUsername, title, country, city, description, date, adultTicketPrice, childTicketPrice, infantTicketPrice, meetingPoint, maxTicketAmount);
        tours.add(newTour);
        return newTour;
    }

    @Override
    public void createTour(Tour tour) {
        tours.add(tour);
    }

    @Override
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> getBookingsByTourId(UUID tourId) {
        return bookings.stream().filter(booking -> booking.getTourId() == tourId).collect(Collectors.toList());
    }

    @Override
    public List<Booking> getBookingsByUsername(String username) {
        return bookings.stream().filter(booking -> booking.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public void createUser(String username) {
        users.add(new User(username, "123456", "user@mail.no"));
    }

    @Override
    public void updateTour(Tour tour) {
        // NOTE: This function is not necessary to implement.
    }

    private void addDefaultData() {
        users.add(new Tourist("TuridTurist", "123456", "turid@turist.no"));
        users.add(new Guide("GeorgGuide", "123456", "georg@guide.no"));
        users.add(new Admin("ArnoldAdmin", "123456", "arnold@admin.no"));
    }

    private void addDefaultDataTours() {
        tours.add(new Tour("GeorgGuide", "Tur til København", "Danmark", "København", "Fantastisk tur til københavn",
                "04.04.24", 600, 300, 0, "København Sentrum", 10));
        tours.add(new Tour("GeorgGuide", "Cruising rundt Faro", "Portugal","Faro" ,"Ferjetur rundt øyene", "20.06.24",
                1400, 700, 0, "FaroVeien 12", 10));
        tours.add(new Tour("GeorgGuide", "Opplev magien i Roma", "Italia", "Roma",
                "Nyt romantisk aften i Roma", "25.06.24", 2300, 1150, 0, "Romaveien 20", 10));
    }
}



