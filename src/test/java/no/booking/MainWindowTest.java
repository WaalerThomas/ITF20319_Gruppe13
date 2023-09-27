package no.booking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class MainWindowTest {

    @Test
    void checksIfGuideCanMakeBooking() {
        assertEquals(false, true);
    }

    @Test
    void checksIfNewUserCanMakeUserAccount() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserNameLengthIsValid() {
        List<String> randomBrukernavn = Arrays.asList("Admin1643545", "Bruker13453453", "Guide12");
        CreateUser createUser = new CreateUser();
        for(String brukerNavn:randomBrukernavn) {
            createUser.validateLengthOfUserName(brukerNavn);
        }
    }

    @Test
    void checksIfPasswordIsValid() {
        assertEquals(false, true);
    }

    @Test
    void checksIfEmailIsValid() {
        assertEquals(false, true);
    }

    @Test
    void checksIfGuideHasReceivedMoney() {
        assertEquals(false, true);
    }

    @Test
    void checksIfPaymentAppIsWorking() {
        assertEquals(false, true);
    }

    @Test
    void checksIfGuideHasValidBankAccount() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserGetsBankTerminalWindow() {
        assertEquals(false, true);
    }

    @Test
    void checksIfPaymentIsApproved() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserCanPostPosts() {
        assertEquals(false, true);
    }

    @Test
    void checksIfAdminHasAccessToUserAccounts() {
        assertEquals(false, true);
    }

    @Test
    void checksIfAdminCanMakeChangesInUserAccounts() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserCanReceiveMessagesFromGuides() {
        assertEquals(false, true);
    }




  
}