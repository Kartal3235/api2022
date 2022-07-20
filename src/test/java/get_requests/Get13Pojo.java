package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import pojos.GoRestResponseBodyPojo;
import pojos.GorestDataPojo;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;


public class Get13Pojo extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
               "meta": null,
               "data": {
               "id": 1881,
               "name": "Buddhana Nair",
               "email": "buddhana_nair@wiza-sporer.co",
               "gender": "female",
               "status": "active"
    }
            }
     */

    @Test
    public void get01Pojo() {

        //1.Step : Set the Url
        spec.pathParams("pp1","users","pp2",1881);

        //2.Step :Set the expected data
        GorestDataPojo gorestDataPojo=new GorestDataPojo(1881,"Buddhana Nair","buddhana_nair@wiza-sporer.co","female","active");
        GoRestResponseBodyPojo goRestPojo=new GoRestResponseBodyPojo(null,gorestDataPojo);

        //3.Step :Send the request and Get the Response
        Response response=given().spec(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        //4.Step :Do Assertion

         /*
         Assertion yapabilmemiz için; response'mızı assert yapacağımız karşılaştıracağımız datayı
         aynı dataformatında yapmamız lazım
         Yani;
         response.as(GoRestResponseBodyPojo.class)==>De serialization
          */

        GoRestResponseBodyPojo actualDataPojo=response.as(GoRestResponseBodyPojo.class);
        System.out.println(actualDataPojo);

        assertEquals(goRestPojo.getMeta(),actualDataPojo.getMeta());
        assertEquals(goRestPojo.getData().getId(),actualDataPojo.getData().getId());
        assertEquals(goRestPojo.getData().getEmail(),actualDataPojo.getData().getEmail());
        assertEquals(goRestPojo.getData().getGender(),actualDataPojo.getData().getGender());
        assertEquals(goRestPojo.getData().getStatus(),actualDataPojo.getData().getStatus());
        assertEquals(200,response.statusCode());

    }
}
