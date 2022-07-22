package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Data;
import pojos.ResponseBody;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employee/1
    When
         User sends Get Request
    Then
        Status code is 200
    And
       "employee_name" is "Tiger Nixon"
    And
       "employee_salary" is 320800
    And
       "employee_age" is 61
    And
       "status" is "success"
    And
       "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01() {

        //1.Step: Set the Url
        spec.pathParams("pp1","employee","pp2",1);

        //2.Step: Set the expected data
        Data dataPojo=new Data(1,"Tiger Nixon",320800,61,"");
        ResponseBody responseBodyPojo=new ResponseBody("success",dataPojo,"Successfully! Record has been fetched.");

        Response response=given().spec(spec).when().get("{pp1}/{pp2}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

       ResponseBody responseBody= JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBody.class);
       System.out.println(responseBody);
       Data dummyApiDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),Data.class);

       assertEquals(responseBodyPojo.getStatus(),responseBody.getStatus());
       assertEquals(responseBodyPojo.getMessage(),responseBody.getMessage());
       assertEquals(responseBodyPojo.getData().getId(),responseBody.getData().getId());
       assertEquals(responseBodyPojo.getData().getEmployee_name(),responseBody.getData().getEmployee_name());
       assertEquals(responseBodyPojo.getData().getEmployee_salary(),responseBody.getData().getEmployee_salary());
      assertEquals(responseBodyPojo.getData().getEmployee_age(),responseBody.getData().getEmployee_age());
      assertEquals(responseBodyPojo.getData().getProfile_image(),responseBody.getData().getProfile_image());

    }
}
