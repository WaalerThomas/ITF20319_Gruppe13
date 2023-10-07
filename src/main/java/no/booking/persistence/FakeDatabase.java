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

public class FakeDatabase implements DataHandler {
    private Set<User> users;
    private List<Tour> tours;

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
        tours.add(new Tour("Tur til København", "København", "Fantastisk tur til københavn",
                "04.04.24", "600 kr", "København Sentrum"));
        tours.add(new Tour("Cruising rundt Faro", "Faro", "Ferjetur rundt øyene", "20.06.24",
                "1400 kr", "FaroVeien 12"));
    }

    @Override
    public List<Tour> getTours() {
        return tours;
    }
}


