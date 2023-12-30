package Com.booker;

import com.main.Payload;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


import static com.main.Utilities.Checking;
import static com.main.Utilities.Checkout;
import static io.restassured.RestAssured.given;



public class TestngBookerTest {
    public static ValidatableResponse response;
    public static String Booking_ID;
    @BeforeTest
    public void createBooking() {
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
    @Test(priority = 1)
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
    @Test(priority = 2)
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
    @Test(priority = 3)
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
    @Test(priority = 4)
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
