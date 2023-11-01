package no.booking.GUI;

import javax.swing.*;

public class TourConfirmedPage extends UIPage {
    public static final String Name = "TourConfirmedPage";
    private JPanel panel1;
    private JButton loggUtButton;

    public TourConfirmedPage(MainWindow mainWindow) {
        loggUtButton.addActionListener(actionEvent -> mainWindow.setPage(LoginGuide.NAME));
    }

    @Override
    public JPanel getMainPanel() {
        return null;
    }

    @Override
    public void setup() {

    }

    @Override
    public void teardown() {

    }
}
