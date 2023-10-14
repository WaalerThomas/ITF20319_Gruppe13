package no.booking.GUI;

import no.booking.logic.Tour;
import no.booking.persistence.DataHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private final JPanel mainPanel;

    private Map<String, UIPage> pages;
    private String currentPage;

    private DataHandler dataHandler;

    public MainWindow(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        pages = new HashMap<>();
        currentPage = "";

        setTitle("Booking Prototype");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        addPages();
        setPage(LoginPage.NAME);

        add(mainPanel);
        setVisible(true);
    }

    private void addPages() {
        // Initialize the pages and add to HashMap
        pages.put(LoginPage.NAME, new LoginPage(this));
        pages.put(TouristMainPage.NAME, new TouristMainPage(this, dataHandler));
        pages.put(CreateUserPage.NAME, new CreateUserPage(this));
        pages.put(LoginGuide.NAME, new LoginGuide(this));
        pages.put(LeggTilOmvisning.NAME, new LeggTilOmvisning(this));
        pages.put(TourDetailPage.NAME, new TourDetailPage(this, dataHandler));

        // Add pages to the mainPanel, with a unique name
        // Need to add the mainPanel from the pages
        mainPanel.add(pages.get(LoginPage.NAME).getMainPanel(), LoginPage.NAME);
        mainPanel.add(pages.get(TouristMainPage.NAME).getMainPanel(), TouristMainPage.NAME);
        mainPanel.add(pages.get(CreateUserPage.NAME).getMainPanel(), CreateUserPage.NAME);
        mainPanel.add(pages.get(LoginGuide.NAME).getMainPanel(), LoginGuide.NAME);
        mainPanel.add(pages.get(LeggTilOmvisning.NAME).getMainPanel(), LeggTilOmvisning.NAME);
        mainPanel.add(pages.get(TourDetailPage.NAME).getMainPanel(), TourDetailPage.NAME);
    }

    private void createPages() {
        // Initialize the pages
        LoginPage loginPage = new LoginPage(this);
        CreateUserPage createUserPage = new CreateUserPage(this);
        TouristMainPage touristMainPage = new TouristMainPage(this, dataHandler);
        LoginGuide loginGuide = new LoginGuide(this);
        LeggTilOmvisning leggTilOmvisning = new LeggTilOmvisning(this);

        // Add pages to the mainPanel, with a unique name
        // Need to add the mainPanel from the pages
        mainPanel.add(loginPage.getMainPanel(), LoginPage.NAME);
        mainPanel.add(createUserPage.getMainPanel(), CreateUserPage.NAME);
        mainPanel.add(touristMainPage.getMainPanel(), TouristMainPage.NAME);
        mainPanel.add(loginGuide.getMainPanel(), LoginGuide.NAME);
        mainPanel.add(leggTilOmvisning.getMainPanel(), LeggTilOmvisning.NAME);
    }

    public void setPage(String name) {
        if (currentPage.equals(name)) return;

        CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());

        // Run setup on the page we are changing to
        // Then show it
        pages.get(name).setup();
        cardLayout.show(mainPanel, name);

        // Run teardown on the page we came from
        if (!currentPage.isBlank())
            pages.get(currentPage).teardown();

        currentPage = name;
    }
}
