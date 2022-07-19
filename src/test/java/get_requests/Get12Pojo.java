package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get12Pojo extends HerokuAppBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/18
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                     "firstname": "omto",
    "lastname": "nena",
    "totalprice": 112,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
                                 }
     */

    @Test
    public void getPojo01() {
        //1.Step : Set the Url
        spec.pathParams("pp1","booking","pp2",18);

        //2.Step :Set the expected data
        BookingdatesPojo bookingdatesPojo=new BookingdatesPojo("2018-01-01","2019-01-01");
        BookingPojo bookingPojo=new BookingPojo("omto","nena",112,true,bookingdatesPojo,"Breakfast");

        //3.Step :Send Get request and get the response
       Response response=given().spec(spec).when().get("{pp1}/{pp2}");

       //4.Step :Do Assertion
      BookingPojo actualPojo=response.as(BookingPojo.class);

      assertEquals(bookingPojo.getFirstname(),actualPojo.getFirstname());
      assertEquals(bookingPojo.getLastname(),actualPojo.getLastname());
      assertEquals(bookingPojo.getTotalprice(),actualPojo.getTotalprice());
      assertEquals(bookingPojo.getDepositpaid(),actualPojo.getDepositpaid());
      assertEquals(bookingPojo.getBookingdates().getCheckin(),actualPojo.getBookingdates().getCheckin());
      assertEquals(bookingPojo.getBookingdates().getCheckout(),actualPojo.getBookingdates().getCheckout());
      assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getAdditionalneeds());


    }
}
