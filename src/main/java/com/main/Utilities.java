package com.main;
import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;

public class Utilities {

    public static String Checking ;
    public static String Checkout;
    public static String firstname;
    public static String lastname;
    public static String checkingDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-MM");
        Faker fake = new Faker();
        Checking = sdf.format(fake.date().birthday());
        // System.out.println(Checking);
        return Checking;
    }
    public static String checkoutDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-MM");
        Faker fake = new Faker();
        Checkout = sdf.format(fake.date().birthday());
        //System.out.println(Checkout);
        return Checkout;
    }
    public static String generateFirstName()
    {
        Faker fake = new Faker();
        firstname =fake.name().firstName().toUpperCase();
        return firstname;
    }
    public static String generateLastName()
    {
        Faker fake = new Faker();
        lastname =fake.name().lastName().toLowerCase();
        return lastname;
    }
}
