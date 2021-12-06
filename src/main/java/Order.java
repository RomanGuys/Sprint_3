import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Order {
    public final String firstName;
    public final String lastName;
    public final String address;
    public final int metroStation;
    public final String phone;
    public final int rentTime;
    public final String deliveryDate;
    public final String comment;
    public final String[] color;

    public Order(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public static Order getRandomOrder(String whatColor){
        Faker faker = new Faker();
        Random rand = new Random();
        Order order = null;
        final String firstName = faker.pokemon().name();
        final String lastName = faker.pokemon().name();
        final String address = faker.harryPotter().location();
        final int metroStation = rand.nextInt(10)+1;
        final String phone = "+7" + RandomStringUtils.randomNumeric(9);
        final int rentTime = rand.nextInt(10)+1;
        final String deliveryDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        final String comment = faker.howIMetYourMother().catchPhrase();
        if(whatColor.equals("ALL")) {
            final String[] color = {"BLACK", "GRAY"};
            order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        }
         else if(whatColor.equals("BLACK")){
            final String[] color = {"BLACK"};
            order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        }
        else if(whatColor.equals("GRAY")){
            final String[] color = {"GRAY"};
            order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        }
        else if(whatColor.equals("NONE")){
            final String[] color = null;
            order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        }
        return order;
    }
}