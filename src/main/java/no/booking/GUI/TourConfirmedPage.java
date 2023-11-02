package no.booking.GUI;

import javax.swing.*;

public class TourConfirmedPage extends UIPage {
    public static final String Name = "TourConfirmedPage";
    private JPanel mainPanel;
    private JButton loggUtButton;

    public TourConfirmedPage(MainWindow mainWindow) {
        try {
            loggUtButton.addActionListener(actionEvent -> mainWindow.setPage(LoginPage.NAME));
        } catch (NullPointerException e){
        }
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setup() {

    }

    @Override
    public void teardown() {

    }
}
