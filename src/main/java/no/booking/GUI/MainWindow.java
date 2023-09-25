package no.booking.GUI;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final JPanel mainPanel;

    public MainWindow() {
        setTitle("Booking Prototype");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        createPages();

        add(mainPanel);
        setVisible(true);
    }

    private void createPages() {
        // Initialize the pages
        LoginPage loginPage = new LoginPage(this);
        CreateUserPage createUserPage = new CreateUserPage(this);
        LoginBruker loginBruker = new LoginBruker(this);
        LoginGuide loginGuide = new LoginGuide(this);
        LeggTilOmvisning leggTilOmvisning = new LeggTilOmvisning(this);

        // Add pages to the mainPanel, with a unique name
        // Need to add the mainPanel from the pages
        mainPanel.add(loginPage.getMainPanel(), "LoginPage");
        mainPanel.add(createUserPage.getMainPanel(), "CreateUserPage");
        mainPanel.add(loginBruker.getMainPanel(), "LoginBruker");
        mainPanel.add(loginGuide.getMainPanel(), "LoginGuide");
        mainPanel.add(leggTilOmvisning.getMainPanel(), "leggTilOmvisning");
    }

    public void setPage(String name) {
        CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());
        cardLayout.show(mainPanel, name);
    }
}
