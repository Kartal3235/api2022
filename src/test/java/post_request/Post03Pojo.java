package post_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01() {

        //1. Step: Set the Url
        spec.pathParam("pp1","todos");

        //2. Step: Set the expected data

        JsonPlaceHolderPojo requestBody=new JsonPlaceHolderPojo(55,"Tidy your room",false);
        // requestBody data tipi===> JsonPlaceHolderPojo'dur

        //3.Step: Send Post Request and Get the Response

       Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("{pp1}");
       response.prettyPrint();

        //JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);
        // response ,JsonPlaceHolderPojo data tipine cevirmemiz gerekiyor
        //Çünkü actual data oluşturacaz

        //4.Step: Do Assertion
        JsonPlaceHolderPojo actualData=response.as(JsonPlaceHolderPojo.class);

        //assertEquals(requestBody.getUserId(),actualData.getUserId());
        //assertEquals(requestBody.getCompleted(),actualData.getCompleted());
        //assertEquals(requestBody.getTitle(),actualData.getTitle());
        assertEquals(requestBody.toString(),actualData.toString());
        System.out.println(requestBody.toString());
        System.out.println(actualData.toString());

    }
}
