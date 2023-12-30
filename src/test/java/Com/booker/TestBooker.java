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
public class TestBooker {
    //public String ID;
    public static ValidatableResponse response;
    public static String Booking_ID;
    Logger logger = Logger.getLogger(TestBooker.class.getName());

    @DisplayName(value ="Test case for Create New Booking")
    @Test
   // @BeforeAll
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
    //@Test
    @Order(value=1)
    public void Test001 () {
        int statusCode = response.extract().statusCode();
        LinkedHashMap<String, ?> bookingDates = response.extract().path("booking.bookingdates");
        System.out.println(bookingDates);
        System.out.println(bookingDates.values().toArray()[0]);
        System.out.println(bookingDates.values().toArray()[1]);
        if (statusCode == 200) {
            Assert.assertEquals(Checking, bookingDates.values().toArray()[0]);
            System.out.println("Checking  date matched");
            Assert.assertEquals(Checkout, bookingDates.values().toArray()[1]);
            System.out.println("Checkout date matched");
        } else {
            System.out.println("Validation Failed for Createbooking");
        }
    }
    @DisplayName(value ="Test case for Upadate Booking date of previously created one")
   //// @Test
    @Order(value=2)
    public void updateBooking() {
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
    //@Test
    @Order(value =3)
    public void Test003 () {
        int statusCode = response.extract().statusCode();
        LinkedHashMap<String, ?> updatedBookingDates = response.extract().path("bookingdates");
        System.out.println(updatedBookingDates);
        System.out.println(updatedBookingDates.values().toArray()[0]);
        System.out.println(updatedBookingDates.values().toArray()[1]);
        if (statusCode == 200) {
            Assert.assertEquals(Checking, updatedBookingDates.values().toArray()[0]);
            System.out.println("Updated Checking  date matched:-Updated CI date="+ updatedBookingDates.values().toArray()[0]);
            Assert.assertEquals(Checkout, updatedBookingDates.values().toArray()[1]);
            System.out.println("Updated Checkout date matched:-Updated CI date="+updatedBookingDates.values().toArray()[1]);
        } else {
            System.out.println("Failed Post");
        }
    }
    @DisplayName(value ="Test case for getting booking details of previously created one")
   // @Test
    @Order(value =4)
    public void getBooking() {
        //testBase.URL();
        ValidatableResponse response;
        response = given()
                .when()
                .contentType(ContentType.JSON)
                .log().uri()
                .get("https://restful-booker.herokuapp.com/booking/"+Booking_ID).then().statusCode(200);
        int statusCode = response.extract().statusCode();
        if (statusCode == 200) {
            System.out.println("Test case passed Get ");
            System.out.println(response.extract().body().asPrettyString());
        } else {
            System.out.println("Failed Get");
        }
    }
}
