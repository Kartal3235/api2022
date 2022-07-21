package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.*;

public class Get16 extends DummyRestApiBaseUrl {

    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    @Test
    public void get01() {
        //1.Step: Set the Url
        spec.pathParam("pp1","employees");

        //2.Step: Set the expected data

        //3.Step: Send the Get Request and Get the response
        Response response=  given().spec(spec).when().get("{pp1}");
        response.prettyPrint();

        //4.Step: Do Assertion
        //i) Status Code is 200
        response.
                then().
                assertThat().
                statusCode(200).
                body("data.id",hasSize(24),"data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        //iv) the greatest age is 66
       List<Integer>ageList=response.jsonPath().getList("data.findAll{it.id>0}.employee_age");

        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);

        //v)The name of the lowest age is "Tatyana Fitzpatrick"

        SoftAssert soft=new SoftAssert();

       List<String>nameList= response.jsonPath().getList("data.findAll{it.employee_age>0}.employee_name");
        System.out.println(nameList);
        Collections.sort(nameList);
        System.out.println(nameList);
        soft.assertEquals("Tatyana Fitzpatrick",nameList.get(0));

        //vi) Total salary of all employees is 6,644,770
       List<Integer>salaryList= response.jsonPath().getList("data.findAll{it.id>0}.employee_salary");
        System.out.println(salaryList);
        System.out.println(salaryList.stream().reduce(0, Integer::sum));
        assertEquals("6,644,770",salaryList.stream().reduce(0,Integer::sum));
    }
}
