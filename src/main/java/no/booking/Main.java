package no.booking;

import no.booking.GUI.MainWindow;
import no.booking.persistence.FakeDatabase;

public class Main {
    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        MainWindow mainWindow = new MainWindow(fakeDatabase);
    }
}