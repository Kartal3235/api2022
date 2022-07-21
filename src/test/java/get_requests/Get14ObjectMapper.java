package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get14ObjectMapper extends JsonplaceholderBaseUrl {

     /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get01ObjectMapper() {

        //1.Step: Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //2.Step: Set the expected data

        //JsonUtil classımızda, methodumuz(convert()) static olduğu için obje oluşturmaya gerek kalmadan çağırabiliyoruz
        JsonPlaceHolderTestData json = new JsonPlaceHolderTestData();
        String expectedData=json.expectedDataInString(10,"quis eius est sint explicabo",true);

        JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);// Bu kısım bize bir MAP veriyor
                                                      //Hashmap kısmı  istediğimiz data formatına çeviriyor

        HashMap<String,Object>expectedDataMap=JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //3.Step: Send the Get Request and Get the Response
       Response response=given().spec(spec).when().get("{pp1}/{pp2}");

       //4.Step: Do Assertion
       HashMap<String,Object>actualDataMap=JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);

       assertEquals(200,response.getStatusCode());
       assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
       assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
       assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
       assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }

    @Test
    public void get02ObjectMapper() {

        //1.Step: Set the Url
        spec.pathParams("pp1","todos","pp2",198);

        //2.Step: Set the expected data

        //JsonUtil classımızda, methodumuz(convert()) static olduğu için obje oluşturmaya gerek kalmadan çağırabiliyoruz

        String expectedData="{\n" +
                "\t\t\t\t\t\t\t\t\t    \"userId\": 10,\n" +
                "\t\t\t\t\t\t\t\t\t    \"id\": 198,\n" +
                "\t\t\t\t\t\t\t\t\t    \"title\": \"quis eius est sint explicabo\",\n" +
                "\t\t\t\t\t\t\t\t\t    \"completed\": true\n" +
                "\t\t\t\t\t\t\t\t\t  }";

        JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);// Bu kısım bize bir MAP veriyor
        //Hashmap kısmı  istediğimiz data formatına çeviriyor

        JsonPlaceHolderPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);//expectedData'yı pojo classına çevirecez

        //3.Step: Send the Get Request and Get the Response

        Response response=given().spec(spec).when().get("{pp1}/{pp2}");

        //4.Step: Do Assertion

       JsonPlaceHolderPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
       assertEquals(200,response.statusCode());
       assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
       assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());
       assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());



    }
}
