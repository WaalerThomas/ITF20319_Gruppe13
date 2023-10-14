package no.booking.GUI;

import javax.swing.*;

public abstract class UIPage {
    public abstract JPanel getMainPanel();
    public abstract void setup();
    public abstract void teardown();
}
