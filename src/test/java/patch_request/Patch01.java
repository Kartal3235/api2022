package patch_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class Patch01 extends JsonplaceholderBaseUrl {

    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void get01() {

        //1)Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //2)Set the expected Data

        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String,Object>requestBodyMap=requestBody.expectedDataWithMissingKeys(null,"Wash the dishes",null);

        //3)Send the Patch request and Get the response
       Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("{pp1}/{pp2}");
        response.prettyPrint();

        //4)Do Assertion
        response.then().assertThat().statusCode(200).body("title",equalTo(requestBodyMap.get("title")));

        Map<String, Object>mapToAssert=requestBody.expectedDataWithAllKeys(10,"Wash the dishes",true);
        response.then().assertThat().statusCode(200).body("title",equalTo(mapToAssert.get("title")),
                "userId",equalTo(mapToAssert.get("userId")),"completed",equalTo(mapToAssert.get("completed")));


    }
}
