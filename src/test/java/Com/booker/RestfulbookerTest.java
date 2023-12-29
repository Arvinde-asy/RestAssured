package Com.booker;

import com.main.*;

import static com.main.Utilities.*;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.logging.Logger;


@RunWith(JUnitPlatform.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class RestfulbookerTest {
    //public String ID;
    public static ValidatableResponse response;
    public static String Booking_ID;
    Logger logger = Logger.getLogger(RestfulbookerTest.class.getName());

    @DisplayName(value ="Test case for Create New Booking")
    @Test
    @BeforeAll
    public static void createBooking() {
       // testBase.URL();
        response = given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(Payload.postLoad().toString())
                .post("https://restful-booker.herokuapp.com/booking").then().statusCode(200);
        Booking_ID= response.extract().path("bookingid").toString();
        System.out.println(response.extract().body().asPrettyString());
    }
    @DisplayName(value ="Test case for validating details of booking creation")
    @Test
    @Order(value=1)
    public void Test001 () {
        int Statuscode = response.extract().statusCode();
        LinkedHashMap<String, ?> bookingdates = response.extract().path("booking.bookingdates");
        System.out.println(bookingdates);
        System.out.println(bookingdates.values().toArray()[0]);
        System.out.println(bookingdates.values().toArray()[1]);
        if (Statuscode == 200) {
            Assert.assertEquals(Checking, bookingdates.values().toArray()[0]);
            System.out.println("Checking  date matched");
            Assert.assertEquals(Checkout, bookingdates.values().toArray()[1]);
            System.out.println("Checkout date matched");
        } else {
            System.out.println("Validation Failed for Createbooking");
        }
    }
    @DisplayName(value ="Test case for Upadate Booking date of previously created one")
    @Test
    @Order(value=2)
    public void updatetBooking() {
        //System.out.println(Booking_ID);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Content-Type", "application/json");
        params.put("Cookie", "token=" + GetToken_booker.createToken());
        response = given()
                .log().headers()
                .log().all()
                .when()
                .headers(params)
                .body(Payload.putLoad().toString())
                .put("https://restful-booker.herokuapp.com/booking/" + Booking_ID)
                .then().statusCode(200);
    }
    @DisplayName(value ="Test Case for Validation after update")
    @Test
    @Order(value =3)
    public void Test003 () {
        int Statuscode = response.extract().statusCode();
        LinkedHashMap<String, ?> Updatedbookingdates = response.extract().path("bookingdates");
        System.out.println(Updatedbookingdates);
        System.out.println(Updatedbookingdates.values().toArray()[0]);
        System.out.println(Updatedbookingdates.values().toArray()[1]);
        if (Statuscode == 200) {
            Assert.assertEquals(Checking, Updatedbookingdates.values().toArray()[0]);
            System.out.println("Updated Checking  date matched:-Updated CI date="+ Updatedbookingdates.values().toArray()[0]);
            Assert.assertEquals(Checkout, Updatedbookingdates.values().toArray()[1]);
            System.out.println("Updated Checkout date matched:-Updated CI date="+Updatedbookingdates.values().toArray()[1]);
        } else {
            System.out.println("Failed Post");
        }
    }
    @DisplayName(value ="Test case for getting booking details of previously created one")
    @Test
    @Order(value =4)
    public void getBooking() {
        //testBase.URL();
        ValidatableResponse response;
        response = given()
                .when()
                .contentType(ContentType.JSON)
                .log().uri()
                .get("https://restful-booker.herokuapp.com/booking/"+Booking_ID).then().statusCode(200);
        int Statuscode = response.extract().statusCode();
        if (Statuscode == 200) {
            System.out.println("Test case passed Get ");
            System.out.println(response.extract().body().asPrettyString());
        } else {
            System.out.println("Failed Get");
        }
    }
}
