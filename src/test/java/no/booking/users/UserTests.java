package no.booking.users;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {
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

    @ParameterizedTest
    @ValueSource(strings = {"", "bob", "hello", "12345"})
    void too_short_username_is_not_valid(String username) {
        User testUser = new User(username, "testUser123%", "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validateUserName(testUser.getUserName());},
                "Username is short");
    }

    @ParameterizedTest
    @ValueSource(strings = {"thirteen_char", "helloworldfromme", "thisisaverylongusername"})
    void too_long_username_is_not_valid(String username) {
        User testUser = new User(username, "testUser123%", "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validateUserName(testUser.getUserName());},
                "Username is too long");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "pass", "secrets", "1234567"})
    void too_short_password_is_not_valid(String password) {
        User testUser = new User("TestUser", password, "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validatePassword(testUser.getPassword());},
                "Password is too short");
    }

    @ParameterizedTest
    @ValueSource(strings = {"twenty-one_characters", "thisisaverylongpasswordwithnospaces"})
    void too_long_password_is_not_valid(String password) {
        User testUser = new User("TestUser", password, "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validatePassword(testUser.getPassword());},
                "Password is too long");
    }

    @Test
    void password_needs_to_begin_with_a_big_letter() {
        User testUser = new User("TestUser", "testUser123%", "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validatePassword(testUser.getPassword());},
                "Password must start with big letter");
    }

    @Test
    void password_needs_to_contain_special_character() {
        User testUser = new User("TestUser", "TestUser123", "testuser@fake.no");
        assertThrows(RuntimeException.class, () -> {testUser.validatePassword(testUser.getPassword());},
                "Password must contain special sign");
    }

    @ParameterizedTest
    @ValueSource(strings = {"testuser#fake.no", "testuser.no@fake"})
    void invalid_email_if_not_formatted_correctly(String email) {
        User testUser = new User("TestUser", "TestUser123", email);
        assertThrows(RuntimeException.class, () -> {testUser.validateEmail(testUser.getEmail());},
                "Email is invalid");
    }
}