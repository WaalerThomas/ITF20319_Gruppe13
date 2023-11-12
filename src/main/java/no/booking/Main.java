package no.booking;

import no.booking.GUI.MainWindow;
import no.booking.persistence.Database;
import no.booking.persistence.FakeDatabase;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();

        MainWindow mainWindow = new MainWindow(database);
        mainWindow.display();
    }
}