package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.*;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2965==>V1 (dahil) kısmına kadar baseurl oluyor
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
        "meta": null,==>(olduğu için data türünü Object yapıyoruz)
        "data": {
        "id": 2965,
        "name": "Mrs. Agastya Nambeesan",
        "email": "nambeesan_agastya_mrs@littel-schultz.com",
        "gender": "female",
        "status": "inactive"
                }
        }

     */

    @Test
    public void get01() {

        //1) Set the URL
        spec.pathParams("pp1","users","pp2",2965);

        //2) Set the expected data

       //Map<String,String>dataKeyMap=new HashMap<>();
       //dataKeyMap.put("name","Mr. Gita Menon");
       //dataKeyMap.put("email","gita_menon_mr@bayer.com");
       //dataKeyMap.put("gender","female");
       //dataKeyMap.put("status","inactive");

        GoRestTestData dataKey=new GoRestTestData();// Gerekli methodun çağrılması için obje oluşturuyorum
        Map<String,Object>dataKeyMap=dataKey.
                dataKeyMap("Mr. Gita Menon","gita_menon_mr@bayer.com","female","inactive");//iç map oluşturduk

        //Map<String,Object>expectedData=new HashMap<>();
        //expectedData.put("meta",null);
        //expectedData.put("data",dataKeyMap);

        Map<String,Object>expectedData=dataKey.
                expectedDataMap(null,dataKeyMap);//Üst Mapi oluşturan method

        //3)Send the request and Get the response

       Response response=given().spec(spec).when().get("{pp1}/{pp2}");
       Map<String,Object>actualData= response.as(HashMap.class);// De-serialization

       //4) Do Assertion

       assertEquals(expectedData.get("meta"),actualData.get("meta"));
       assertEquals(dataKeyMap.get("name"),((Map)actualData.get("data")).get("name"));//Önce "data" elementine ulaşıp buradan aldığım objeyi Map formatına cast ediyorum
       assertEquals(dataKeyMap.get("email"),((Map)actualData.get("data")).get("email"));
       assertEquals(dataKeyMap.get("gender"),((Map)actualData.get("data")).get("gender"));



    }
}
