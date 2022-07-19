package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;
import pojos.BookingdatesPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Post04Pojo extends HerokuAppBaseUrl {

    /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)  {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void postPojo01() {

        //1.Step: Set the Url
        spec.pathParam("pp1","booking");

        //2.Step: Set the Expected Data

        BookingdatesPojo bookingdates=new BookingdatesPojo("2021-09-21","2021-12-21");
        BookingPojo bookingPojo=new BookingPojo("Ali","Can",999,true,bookingdates,"Breakfast with white tea, Dragon juice");

        // id ben atamıyorum,response body si atadığı için diğer bookingResponseBodyPojo oluşturmadık.

        //3.Step: Send Post Request and Get the Response

       Response response=given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("{pp1}");
       response.prettyPrint();

       //4.Step: Do Assertion

        BookingResponseBodyPojo actualPojo=response.as(BookingResponseBodyPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(bookingPojo.getFirstname(),actualPojo.getBooking().getFirstname());
        assertEquals(bookingPojo.getLastname(),actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(),actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(),actualPojo.getBooking().getDepositpaid());

        assertEquals(bookingdates.getCheckin(),actualPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdates.getCheckout(),actualPojo.getBooking().getBookingdates().getCheckout());
        assertEquals(bookingPojo.getAdditionalneeds(),actualPojo.getBooking().getAdditionalneeds());

    }


}
