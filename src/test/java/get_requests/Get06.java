package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get06 extends HerokuAppBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "GGS",
            "lastname": "FINCH",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            }

        }
     */

    @Test
    public void get06() {
        // 1. Step: Set the Url

        spec.pathParams("first","booking","second",101);

        // 2.Step: Set the expected


        // 3.Step: Send the request and get the response

       Response response=given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

       // 4.Step : Do assertion

        //1.yol assertion
       response.then().
               assertThat().
               statusCode(200).
               contentType(ContentType.JSON).
               body("firstname",equalTo("GGS"),
               "lastname",equalTo("FINCH"),"totalprice",equalTo(111),"depositpaid",equalTo(true));


        String bdatesIn=response.getBody().path("bookingdates.checkin");
        System.out.println(bdatesIn);
        String bdatesOut=response.getBody().path("bookingdates.checkout");
        System.out.println(bdatesOut);

        assertEquals(response.getBody().path("bookingdates.checkin"),"2018-01-01");
        assertEquals(response.getBody().path("bookingdates.checkout"),"2019-01-01");

        // 2.yol assertion==>JsonPath Class kullanılır.

        JsonPath json = response.jsonPath();
        assertEquals("GGS",json.getString("firstname"));
        assertEquals("FINCH",json.getString("lastname"));
        assertEquals(111,json.getInt("totalprice"));
        assertEquals(true,json.getBoolean("depositpaid"));
        assertEquals("2018-01-01",json.getString("bookingdates.checkin"));
        assertEquals("2019-01-01",json.getString("bookingdates.checkout"));

        // 3.yol: Soft Assertion

        // Soft Assertion için 3 adım izlenir;
        // 1)SoftAssert Objesi oluşturulur.

        SoftAssert softAssert=new SoftAssert();

        // 2)Obje aracılığı ile assert yapılır.

        softAssert.assertEquals(json.getString("firstname"),"GGS","firstname uyuşmadı");
        softAssert.assertEquals(json.getString("lastname"),"FINCH");
        softAssert.assertEquals(json.getString("totalprice"),111);
        softAssert.assertEquals(json.getString("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2018-01-01");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2019-01-01");

        // 3)AssertAll() methodu kullanılır.Aksi takdirde kod herzaman pass olur.

        softAssert.assertAll();







    }
}
