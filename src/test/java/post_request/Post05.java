package post_request;

import base_urls.DummyRestApiBaseUrl;
import org.junit.Test;
import pojos.Data;
import pojos.ResponseBody;


public class Post05 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
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
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Given
         https://dummy.restapiexample.com/api/v1/create
                     {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
    When
         User Send post request
    Then
        Status code is 200
     And
        Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    @Test
    public void post01() {

        spec.pathParam("pp1","create");
        Data dummyApiDataPojo=new Data("Tom Hanks",111111,23,"Perfect image");
        ResponseBody dummyApiResponseBodyPojo=new ResponseBody("success",dummyApiDataPojo,"Successfully! Record has been added.");
    /*
     DummyApiDataPojo dummyApiDataPojo = new DummyApiDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyApiResponseBodyPojo expectedData = new DummyApiResponseBodyPojo("success",dummyApiDataPojo,"Successfully! Record has been added.");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(dummyApiDataPojo).when().post("/{first}");
        response.prettyPrint();
        DummyApiResponseBodyPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(),DummyApiResponseBodyPojo.class);
        System.out.println(actualData);

        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getStatus(),actualData.getStatus());

        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
     */



    }
}
