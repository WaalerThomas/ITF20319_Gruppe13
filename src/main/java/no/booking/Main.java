package no.booking;

import no.booking.GUI.MainWindow;
import no.booking.persistence.Database;
import no.booking.persistence.FakeDatabase;

public class Main {
    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();
        fakeDatabase.createDefaultApplicationData();

        MainWindow mainWindow = new MainWindow(fakeDatabase);
    }
}