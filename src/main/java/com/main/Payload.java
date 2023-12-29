package com.main;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import static com.main.Utilities.*;

public class Payload {
     public static JSONObject postLoad ()
    {
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",checkingDate());
        bookingdates.put("checkout",checkoutDate());

        Faker fake = new Faker();
        JSONObject payload = new JSONObject();
        payload.put ("firstname",fake.name().firstName());
        payload.put ("lastname",fake.name().lastName());
        payload.put ("totalprice",fake.random().nextInt(3));
        payload.put ("depositpaid","true");
        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds","Lunch");
        return payload;
        }
    public static JSONObject putLoad ()
    {
        Faker fake = new Faker();
        String Check = fake.date().toString();
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin",checkingDate());
        bookingdates.put("checkout",checkoutDate());

        JSONObject payload = new JSONObject();
        payload.put ("firstname",fake.name().firstName());
        payload.put ("lastname",fake.name().lastName());
        payload.put ("totalprice",fake.random().nextInt(3));
        payload.put ("depositpaid","true");
        payload.put("bookingdates",bookingdates);
        payload.put("additionalneeds","Lunch");
        return payload;
    }
}

