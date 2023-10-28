package no.booking.persistence;

import no.booking.logic.Booking;
import no.booking.Users.Admin;
import no.booking.Users.Guide;
import no.booking.Users.Tourist;
import no.booking.Users.User;
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
        throw new RuntimeException("Not implemented");
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
    public Tour createTour(String title, String country, String city, String description, String date, int adultTicketPrice, int childTicketPrice, int infantTicketPrice, String meetingPoint, int maxTicketAmount) {
        Tour newTour = new Tour(title, country, city, description, date, adultTicketPrice, childTicketPrice, infantTicketPrice, meetingPoint, maxTicketAmount);
        tours.add(newTour);
        return newTour;
    }

    @Override
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public List<Booking> getBookingsTourId(UUID tourId) {
        return bookings.stream().filter(booking -> booking.getTourId() == tourId).collect(Collectors.toList());
    }

    private void addDefaultData() {
        users.add(new Tourist("TuridTurist", "123456", "turid@turist.no"));
        users.add(new Guide("GeorgGuide", "123456", "georg@guide.no"));
        users.add(new Admin("ArnoldAdmin", "123456", "arnold@admin.no"));
    }

    private void addDefaultDataTours() {
        tours.add(new Tour("Tur til København", "Denmark", "København", "Fantastisk tur til københavn",
                "04.04.24", 600, 300, 0, "København Sentrum", 10));
        tours.add(new Tour("Cruising rundt Faro", "Portugal","Faro" ,"Ferjetur rundt øyene", "20.06.24",
                1400, 700, 0, "FaroVeien 12", 10));
        tours.add(new Tour("Opplev magien i Roma", "Italia", "Roma",
                "Nyt romantisk aften i Roma", "25.06.24", 2300, 1150, 0, "Romaveien 20", 10));
    }
}



