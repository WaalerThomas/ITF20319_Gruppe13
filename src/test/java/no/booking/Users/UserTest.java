package no.booking.Users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void checksIfUserNameAlreadyExist(){
        assertEquals(false, true);
    }

    @DisplayName("Testing if password is valid")
    @Test
    void checksIfPasswordIsValid() {
        User testbruker = new User("Testbruker", "Testbruker123%", "Testbruker@fake.no");
        assertTrue(testbruker.validatePassword(testbruker.getPassword()));
    }

    @DisplayName("Testing if email is valid")
    @Test
    void checksIfEmailIsValid() {
        User testbruker = new User("Testbruker", "Testbruker123%", "Testbruker@fake.no");
        assertTrue(testbruker.validateEmail(testbruker.getEmail()));

    }

    @DisplayName("Testing if username is valid")
    @Test
    void checksIfUserNameIsValid() {
        User testbruker = new User("Testbruker", "Testbruker123%", "Testbruker@fake.no");
        assertTrue(testbruker.validateUserName(testbruker.getUserName()));
    }

    @Test
    void checksIfNewUserCanMakeUserAccount() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserCanReceiveMessagesFromGuides() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserCanPostPosts() {
        assertEquals(false, true);
    }

    @Test
    void checksIfUserGetsBankTerminalWindow() {
        assertEquals(false, true);
    }





}