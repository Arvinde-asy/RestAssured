package Com.booker;
import io.restassured.response.Response;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;

public class GetToken_booker {
    public static String createToken() {
        //testBase.URL();
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");
        Response response = given()
                .when()
                .contentType("application/json")
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/auth");
        String res = response.getBody().asString();
        System.out.println(res);
        String token = response.path("token");
        int status = response.getStatusCode();
        if (status == 200) {
            System.out.println("Token Generated Successfully");
        } else {
            System.out.println("Error in Token Generation");
        }
        return token;
    }
}
