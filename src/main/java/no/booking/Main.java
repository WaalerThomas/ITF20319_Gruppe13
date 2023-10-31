package no.booking;

import no.booking.GUI.MainWindow;
import no.booking.persistence.Database;
import no.booking.persistence.FakeDatabase;

public class Main {
    public static void main(String[] args) {
        FakeDatabase database = new FakeDatabase();
        //Database database = new Database();
        database.createDefaultApplicationData();

        MainWindow mainWindow = new MainWindow(database);
        mainWindow.display();
    }
}