import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

public class Courier {

    public String login;
    public String password;
    public String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public static Courier getRandom() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, firstName);
    }

    public static Courier getRandomWOName() {
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String password = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, password, null);
    }

    public static Courier getRandomWOPass(){
        final String login = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login, null, firstName);
    }

    public static Courier getRandomWOLogin(){
        final String password = RandomStringUtils.randomAlphabetic(10);
        final String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(null, password, firstName);
    }
}
