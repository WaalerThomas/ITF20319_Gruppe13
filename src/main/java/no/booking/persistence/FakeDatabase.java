package no.booking.persistence;

import no.booking.Users.Admin;
import no.booking.Users.Guide;
import no.booking.Users.Tourist;
import no.booking.Users.User;
import no.booking.logic.Tour;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FakeDatabase implements DataHandler {
    private final Set<User> users;
    private final List<Tour> tours;

    public FakeDatabase() {
        this.users = new HashSet<>();
        this.tours = new ArrayList<>();
        addDefaultData();
        addDefaultDataTours();
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    private void addDefaultData() {
        users.add(new Tourist("TuridTurist", "123456", "turid@turist.no"));
        users.add(new Guide("GeorgGuide", "123456", "georg@guide.no"));
        users.add(new Admin("ArnoldAdmin", "123456", "arnold@admin.no"));
    }

    private void addDefaultDataTours() {
        tours.add(new Tour(1,"Tur til København", "Denmark", "København", "Fantastisk tur til københavn",
                "04.04.24", 600, "København Sentrum"));
        tours.add(new Tour(2,"Cruising rundt Faro", "Portugal","Faro" ,"Ferjetur rundt øyene", "20.06.24",
                1400, "FaroVeien 12"));
        tours.add(new Tour(3, "Opplev magien i Roma", "Italia", "Roma",
                "Nyt romantisk aften i Roma", "25.06.24", 2300, "Romaveien 20"));
    }

    @Override
    public List<Tour> getTours() {
        return tours;
    }

    @Override
    public Tour getTourById(int id) {
        return tours.stream().filter(tour -> tour.getTourId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Tour> getToursByCity(String city) {
        // Return every tour if the filter is blank
        if (city.isBlank()) return getTours();

        return tours.stream().filter(tour -> tour.getCity().contains(city)).collect(Collectors.toList());
    }

    @Override
    public void createTour(int tourId, String title, String country, String city, String description, String date, int price_Per_Type_Ticket, String meetingPoint) {
        Tour newTour = new Tour(tourId, title, country, city, description, date, price_Per_Type_Ticket, meetingPoint);
        tours.add(newTour);
    }

}



