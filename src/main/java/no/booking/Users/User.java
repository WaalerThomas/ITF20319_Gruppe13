package no.booking.Users;

public class User {

    private String userName;
    private String password;

    private String email;

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }



    public boolean validateUserName(String userName) {
        if (userName.length() < 6) {
            throw new RuntimeException("Username is short");
        }
        if (userName.length() > 12) {
            throw new RuntimeException("Username is too long");
        }
        if (!userName.matches(".*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?].*]")) {
        }
        return true;
    }

    //brukte ChatGPT til å hjelpe meg med å finne ut hvordan jeg kan validere at passordet
    // starter på stor bokstav og at passordet skal inneholde spesieltegn
    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Password is too short");
        }
        if (password.length() > 20) {
            throw new RuntimeException("Password is too long");
        }
        String førstetegn = password.substring(0,1);
        if (!førstetegn.matches("[A-Z]")) {
            throw new RuntimeException("Password must start with big letter");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?].*]")) {
            throw new RuntimeException("Password must contain special sign");
        }
        return true;
    }

    //brukte chatGPT til å hjelpe meg med å lage en if statement som sjekker at mail er valid
    public boolean validateEmail(String email) {
        if (email.contains("@") && email.indexOf("@") < email.lastIndexOf(".") &&
                email.lastIndexOf(".") < email.length() - 1) {
            return true;
        } else {
            throw new RuntimeException("Email is invalid");
        }
    }
}
