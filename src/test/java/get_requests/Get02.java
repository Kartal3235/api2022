package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class Get02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"   // (Headr ın içinde server vardır)
     */

    @Test
    public void get02() {
        // 1.Step : Set url

        String url="https://restful-booker.herokuapp.com/booking/1005";

        // 2.Step : Set the expected data(Post,Put,Patch)

        // 3.Step : Type code to send request

        Response responce=given().when().get(url);
        responce.prettyPrint();

        // 4.step : Do Assertion;
        responce.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        // Response body'de bulunan spesifik bir veri nasıl assert edilir.
        Assert.assertTrue(responce.asString().contains("Not Found"));


        // Response body'de bulunan spesifik bir veri bulunmadığı nasıl assert edilir.
        // assertFalse() methodu parantezin içindeki değer true is tesi geçirir.

        assertFalse(responce.asString().contains("TechProEd"));

        // assertEquals()  methodu parantez içindeki iki değer eşit ise testi geçirir.
        assertEquals("Cowboy",responce.header("Server"));
    }
}
