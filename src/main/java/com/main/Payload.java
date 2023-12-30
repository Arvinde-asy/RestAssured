package com.main;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import static com.main.Utilities.*;

public class Payload {
     public static JSONObject postLoad ()
    {
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin",checkingDate());
        bookingDates.put("checkout",checkoutDate());

        Faker fake = new Faker();
        JSONObject payload = new JSONObject();
        payload.put ("firstname",generateFirstName());
        payload.put ("lastname",generateLastName());
        payload.put ("totalprice",fake.random().nextInt(3));
        payload.put ("depositpaid","true");
        payload.put("bookingdates",bookingDates);
        payload.put("additionalneeds","Lunch");
        return payload;
        }
    public static JSONObject putLoad ()
    {
        Faker fake = new Faker();
        String Check = fake.date().toString();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin",checkingDate());
        bookingDates.put("checkout",checkoutDate());

        JSONObject payload = new JSONObject();
        payload.put ("firstname",generateFirstName());
        payload.put ("lastname",generateLastName());
        payload.put ("totalprice",fake.random().nextInt(3));
        payload.put ("depositpaid","true");
        payload.put("bookingdates",bookingDates);
        payload.put("additionalneeds","Lunch");
        return payload;
    }
}

