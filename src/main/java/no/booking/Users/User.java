package no.booking.Users;

public class User {

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void validateUserName() {
        if (this.userName.length()>12) {
            throw new RuntimeException("Username is too long");
        }
        if (this.userName.isEmpty()) {
            System.out.println("You have to type in your username");;
        }
    }
}
