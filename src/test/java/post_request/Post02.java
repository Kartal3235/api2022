package post_request;

import base_urls.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Post02 extends HerokuAppBaseUrl {

    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */

    @Test
    public void get01() {

        // 1)Set the Url
        spec.pathParam("pp1","booking");

        // 2)Set the expected data
        HerOkuAppTestData herokuApp=new HerOkuAppTestData();

       Map<String,String>bookingdatesMap= herokuApp.bookingdatesSetUp("2021-09-09","2021-09-21");
       Map<String,Object>expectedDataMap= herokuApp.expectedDataSetUp("Bahadır","Kork",11111,true,bookingdatesMap);

       // 3)Send the post request and Get the response

       Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("{pp1}");
       response.prettyPrint();

       //4) Do Assertion
        Map<String,Object>acturlDataMap=response.as(HashMap.class);

        assertEquals(200,response.statusCode());

        assertEquals(expectedDataMap.get("firstname"),((Map)acturlDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)acturlDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)acturlDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)acturlDataMap.get("booking")).get("depositpaid"));


        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)acturlDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)acturlDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}