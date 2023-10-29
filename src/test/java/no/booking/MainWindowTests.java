package no.booking;

import no.booking.GUI.MainWindow;
import no.booking.stubs.TestPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTests {
    private MainWindow mainWindow;

    @BeforeEach
    public void set_up() {
        mainWindow = new MainWindow(null);
    }

    @Test
    public void can_not_set_page_to_invalid_page_name() {
        boolean result = mainWindow.setPage("NonExistingPage");
        assertFalse(result);
        assertEquals("", mainWindow.getCurrentPage());
    }

    @Test
    public void can_set_page_to_valid_page_name() {
        mainWindow.addPage("TestPage", new TestPage());

        boolean result = mainWindow.setPage("TestPage");
        assertTrue(result);
        assertEquals("TestPage", mainWindow.getCurrentPage());
    }

    @Test
    public void does_not_change_page_if_it_is_the_current_page() {
        mainWindow.addPage("TestPage", new TestPage());
        mainWindow.setPage("TestPage");

        boolean result = mainWindow.setPage("TestPage");
        assertFalse(result);
    }

    @Test
    public void runs_setup_when_displaying_the_page() {
        TestPage testPage = new TestPage();
        mainWindow.addPage("TestPage", testPage);

        mainWindow.setPage("TestPage");
        assertTrue(testPage.hasRunSetup);
    }

    @Test
    public void runs_teardown_when_changing_to_another_page() {
        TestPage testPage1 = new TestPage();
        TestPage testPage2 = new TestPage();
        mainWindow.addPage("TestPage1", testPage1);
        mainWindow.addPage("TestPage2", testPage2);
        mainWindow.setPage("TestPage1");

        mainWindow.setPage("TestPage2");
        assertTrue(testPage1.hasRunTeardown);
    }
}