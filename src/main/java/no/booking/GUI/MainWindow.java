package no.booking.GUI;

import no.booking.persistence.DataHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainWindow extends JFrame {
    private final JPanel mainPanel;

    private final Map<String, UIPage> pages;
    private String currentPage;

    private final DataHandler dataHandler;

    public MainWindow(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        pages = new HashMap<>();
        currentPage = "";

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
    }

    public void display() {
        setTitle("Booking Prototype");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addPages();
        setPage(LoginPage.NAME);

        add(mainPanel);
        setVisible(true);
    }

    public boolean setPage(String name) {
        if (currentPage.equals(name))
            return false;

        CardLayout cardLayout = (CardLayout) (mainPanel.getLayout());

        // Check that there is a page with that name
        if (pages.get(name) == null)
            return false;

        // Run setup on the page we are changing to
        // Then show it
        pages.get(name).setup();
        cardLayout.show(mainPanel, name);

        // Run teardown on the page we came from
        if (!currentPage.isBlank())
            pages.get(currentPage).teardown();

        currentPage = name;
        return true;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void addPage(String pageName, UIPage page) {
        pages.put(pageName, page);
        mainPanel.add(pages.get(pageName).getMainPanel(), pageName);
    }

    private void addPages() {
        // Initialize the pages and add to HashMap
        pages.put(LoginPage.NAME, new LoginPage(this));
        pages.put(TouristMainPage.NAME, new TouristMainPage(this, dataHandler));
        pages.put(CreateUserPage.NAME, new CreateUserPage(this));
        pages.put(LoginGuide.NAME, new LoginGuide(this));
        pages.put(CreateTourPage.NAME, new CreateTourPage(this, dataHandler));
        pages.put(TourDetailPage.NAME, new TourDetailPage(this, dataHandler));
        pages.put(PayForTourPage.NAME, new PayForTourPage(this, dataHandler));
        pages.put(TouristBookingsPage.NAME, new TouristBookingsPage(this, dataHandler));
        pages.put(AdminMainPage.NAME, new AdminMainPage(this));
        pages.put(ViewCreatedToursPage.NAME, new ViewCreatedToursPage(this, dataHandler));
        pages.put(TourConfirmedPage.Name, new TourConfirmedPage(this));

        // Add pages to the mainPanel, with a unique name
        // Need to add the mainPanel from the pages
        mainPanel.add(pages.get(LoginPage.NAME).getMainPanel(), LoginPage.NAME);
        mainPanel.add(pages.get(TouristMainPage.NAME).getMainPanel(), TouristMainPage.NAME);
        mainPanel.add(pages.get(CreateUserPage.NAME).getMainPanel(), CreateUserPage.NAME);
        mainPanel.add(pages.get(LoginGuide.NAME).getMainPanel(), LoginGuide.NAME);
        mainPanel.add(pages.get(CreateTourPage.NAME).getMainPanel(), CreateTourPage.NAME);
        mainPanel.add(pages.get(TourDetailPage.NAME).getMainPanel(), TourDetailPage.NAME);
        mainPanel.add(pages.get(PayForTourPage.NAME).getMainPanel(), PayForTourPage.NAME);
        mainPanel.add(pages.get(TouristBookingsPage.NAME).getMainPanel(), TouristBookingsPage.NAME);
        mainPanel.add(pages.get(AdminMainPage.NAME).getMainPanel(), AdminMainPage.NAME);
        mainPanel.add(pages.get(ViewCreatedToursPage.NAME).getMainPanel(), ViewCreatedToursPage.NAME);
        mainPanel.add(pages.get(TourConfirmedPage.Name).getMainPanel(), TourConfirmedPage.Name);
    }
}
