package no.booking.persistence;

import no.booking.Users.Admin;
import no.booking.Users.Guide;
import no.booking.Users.Tourist;
import no.booking.Users.User;

import java.util.HashSet;
import java.util.Set;

public class FakeDatabase implements DataHandler {
    private Set<User> users;

    public FakeDatabase() {
        this.users = new HashSet<>();
        addDefaultData();
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
}
