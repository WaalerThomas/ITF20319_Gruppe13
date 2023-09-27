package no.booking;

public class CreateUser {

    private String userName;
    private String passWord;



    static boolean createUser () {
        String name = "Pål";
        String password = "passord123";

        return true;
    }

    //lager en validator for å teste ulike krav til brukernavn
    //bare en testversjon
    public void validateLengthOfUserName(String userName){
        if (userName.length() >=12){
            throw new RuntimeException("Username is too long");
        }
    }


}
