import java.util.Random;

public class BaseTest {
    public static String BASE_URI = "https://studio-api.softr.io";
    public static String headerValidKey = "khIbAyJIU5CIuh1oDuBRx1s49";
    public static String headerValidDomain= "jere237.softr.app";

    public static String deleteEndpoint = "v1/api/users/";

    public String generateRandomEmail(){
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
       String randomEmail = "User"+ randomInt;
       return randomEmail;


    }
}
