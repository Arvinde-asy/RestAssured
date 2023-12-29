package Com.booker;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class testBase {
    public static void URL ()
    {
      String baseURI1 ="https://restful-booker.herokuapp.com";
      String baseURI2 ="https://restful-booker.herokuapp.com/auth";
        String basePath="/booking";
    }
}
