package no.booking.fakes;

import no.booking.GUI.UIPage;

import javax.swing.*;

public class TestPage extends UIPage {
    private JPanel mainPanel;

    public boolean hasRunSetup;
    public boolean hasRunTeardown;

    public TestPage() {
        mainPanel = new JPanel();
        hasRunSetup = false;
        hasRunTeardown = false;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void setup() {
        hasRunSetup = true;
    }

    @Override
    public void teardown() {
        hasRunTeardown = true;
    }
}
