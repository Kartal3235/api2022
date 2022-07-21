package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get15ObjectMapper extends HerokuAppBaseUrl {

    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get01() {
        //1. Step: Set the Url
        spec.pathParams("pp1","booking","pp2",22);

        //2.Step: Set the expected data

        String expectedData ="{\n" +
                "                \"firstname\": \"Oliver\",\n" +
                "                \"lastname\": \"Smith\",\n" +
                "                \"totalprice\": 100,\n" +
                "                \"depositpaid\": true,\n" +
                "                \"bookingdates\": {\n" +
                "                    \"checkin\": \"2022-07-18\",\n" +
                "                    \"checkout\": \"2022-07-19\"\n" +
                "                },\n" +
                "                \"additionalneeds\": \"Breakfast\"\n" +
                "            }";

       BookingPojo expectedDataPojo =JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3. step: Send the get request and get the response
       Response response =given().spec(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //4.Step: Do Assertion

        BookingPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

        //2.YOL
        //Soft Assertion
        //1. Adım:Softassert objesi oluştur
        SoftAssert softAssert=new SoftAssert();

        //2.Adım: Assertion yap
        softAssert.assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        softAssert.assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        softAssert.assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        softAssert.assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        softAssert.assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
        softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        softAssert.assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

        //3.Adım: assertAll() methodunu çalıştır.
        softAssert.assertAll();
    }

}
