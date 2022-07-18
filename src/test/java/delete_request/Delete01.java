package delete_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Delete01 extends JsonplaceholderBaseUrl {

    /*
    Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        //1)Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //2)Set the Expected data

        Map<String,Object>expectedDataMap=new HashMap<>();

        //3)Send the delete request and get the response

       Response responce=given().spec(spec).when().delete("{pp1}/{pp2}");
       responce.prettyPrint();

       //4)Do Assertion

        //1.yol
        Map<String,Object>actualMap=responce.as(HashMap.class);
        responce.then().assertThat().statusCode(200);
        assertEquals(actualMap,expectedDataMap);

        //2.yol
        assertTrue(actualMap.size()==0);
        assertTrue(actualMap.isEmpty());//tavsiye edilen

        //Delete request yapmadan önce "Post Request" yapıp o datayı silmeliyiz.





    }
}
