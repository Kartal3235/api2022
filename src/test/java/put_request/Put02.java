package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Data;
import pojos.ResponseBody;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    /*
    Given
      https://dummy.restapiexample.com/api/v1/update/21
      {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }


    When
        User Sends the Put request
    Then
          Status code is 200
    And
         {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }


    */

    @Test
    public void put01() {
        spec.pathParams("pp1","update","pp2",21);
        Data data=new Data("bahadır",22222,35,"Perfect image");
        ResponseBody dummyApiResponseBody=new ResponseBody("success",data,"Successfully! Record has been updated.");
       Response response=given().spec(spec).body(dummyApiResponseBody).when().contentType(ContentType.JSON).get("{pp1}/{pp2}");
       response.prettyPrint();

       ResponseBody actualData= JsonUtil.convertJsonToJavaObject(response.asString(),ResponseBody.class);
       assertEquals(dummyApiResponseBody.getStatus(),actualData.getStatus());
       assertEquals(dummyApiResponseBody.getMessage(),actualData.getMessage());
       assertEquals(dummyApiResponseBody.getData().getEmployee_name(),actualData.getData().getEmployee_name());
       assertEquals(dummyApiResponseBody.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
       assertEquals(dummyApiResponseBody.getData().getEmployee_age(),actualData.getData().getProfile_image());



    }
}
