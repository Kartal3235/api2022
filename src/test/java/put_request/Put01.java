package put_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Put01 extends JsonplaceholderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void get01() {

        //1)Set the Url
        spec.pathParams("pp1","todos","pp2",198);
        //2)Set the expected data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String,Object>expecteddataMap=expectedData.expectedDataWithAllKeys(21,"Wash the dishes",false);

        //3)Send the put request and get the response

      Response response=given().spec(spec).when().contentType(ContentType.JSON).body(expecteddataMap).put("{pp1}/{pp2}");
      response.prettyPrint();

       //4)Do Assertion

       Map<String, Object>actualDataMap= response.as(HashMap.class);

       assertEquals(expecteddataMap.get("userId"),actualDataMap.get("userId"));
       assertEquals(expecteddataMap.get("title"),actualDataMap.get("title"));
       assertEquals(expecteddataMap.get("completed"),actualDataMap.get("completed"));

       assertEquals(200,response.statusCode());

    }
}
