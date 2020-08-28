import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;
import java.net.URLEncoder;
import java.util.Base64;

public class segMx {

    @Test
    public void get_token_status_fail_test(){
        given().queryParam("lang", "es").when()
                .log().all()
                .post("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts")
                .then().statusCode(400);

    }

    @Test
    public void Coranovirus(){
        RestAssured.baseURI = String.format("https://api.quarantine.country/api/v1/summary/latest");

        Response response = given().log().all().header("Accept","application/json").get();

        String body = response.getBody().asString();

        //Validaciones
        System.out.println("Body Response: "+ body);
        System.out.println("Response status: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders());
        System.out.println("Response Content Type: " + response.contentType());
        assertEquals(200,response.getStatusCode());
        assertTrue(body.contains("summary"));
    }

    @Test
    public void Get_Token(){

        String email = "marpj_0215@hotmail.com";
        String pass = "021591";
        String ToEncode = email + ":" + pass;
        String Basic_encoded = Base64.getEncoder().encodeToString(ToEncode.getBytes());


        RestAssured.baseURI = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts");
        Response response = given().queryParam("lang","es").log().all()
                .header("Authorization", "Basic "+ Basic_encoded)
                .post();

        String body = response.getBody().asString();
        System.out.println("Response body: "+ response.getBody().asString());

        assertEquals(200,response.getStatusCode());
        assertNotNull(body);
        assertTrue(body.contains("access_token"));

    }


}
