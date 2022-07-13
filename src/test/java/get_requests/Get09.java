package get_requests;

import base_urls.HerokuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get09 extends HerokuAppBaseUrl {
    
    /*
    Given
            https://restful-booker.herokuapp.com/booking/91
        When
            I send GET Request to the url
        Then
            Response body should be like that;
        {
        "firstname": "James",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Breakfast"
         }
     */

    @Test
    public void get01() {
        //1) Set the URl
        spec.pathParams("pp1","booking","pp2",91);

        //2) Set the expected data

        Map<String,String>bookingdatesMap=new HashMap<>();
        bookingdatesMap.put("checkin","2018-01-01");
        bookingdatesMap.put("checkout","2019-01-01");

        Map<String,Object>expectedDataMap=new HashMap<>();
        expectedDataMap.put("firstname","James");
        expectedDataMap.put("lastname","Brown");
        expectedDataMap.put("totalprice",111);
        expectedDataMap.put("depositpaid",true);
        expectedDataMap.put("bookingdates",bookingdatesMap);
        expectedDataMap.put("additionalneeds","Breakfast");
        System.out.println(expectedDataMap);

        // 3)Send the request and get the response

        // Her Class bir data formatındadır(Response)
        Response response=given().spec(spec).when().get("{pp1}/{pp2}");
        Map<String,Object>actualDataMap=response.as(HashMap.class);//response as() methodu ile HashMap'e çeviriyoruz.
        System.out.println(actualDataMap);
        // 4) Do Assertion

        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));


    }
}
