import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class Consult {
    @Test
    public void consul_info_regions_status_200(){
        given().queryParam("lang","es").queryParam("depth","1").when()
                .log().all()
                .get("https://webapi.segundamano.mx/nga/api/v1.1/public/regions")
                .then().statusCode(200);


    }
}
