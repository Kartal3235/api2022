package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

public class Get11 extends GoRestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
        And
            The female users are more than or equls to male users
     */

    @Test
    public void get01() {

        //1)Set the URL
        spec.pathParam("pp1","users");

        //2)Set the expected data

        //3)Send the request and get the response
       Response response=given().spec(spec).when().get("{pp1}");
       response.prettyPrint();

       //4) Do Assertion


        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit",equalTo(10),
                        "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id",hasSize(10),"data.status",hasItem("active"),
                        "data.name",hasItems("Aalok Acharya DDS","Acharyasuta Chattopadhyay DC","Shresth Nehru"));

        //Bayan ve erkek
        //1.yol
        JsonPath json=response.jsonPath();
        List<String>genders =json.getList("data.gender");
        System.out.println(genders);

        int numOfFemale=0;
        for (String each:genders
             ) {
            if(each.equalsIgnoreCase("female")){
                numOfFemale++;
            }
        }
        System.out.println(numOfFemale);//6

        assertTrue(numOfFemale>genders.size()-numOfFemale);

        //2.yol:Tum bayan ve bayları ayrı ayrı Grovvy ile çekelim
        List<String>femaleList=json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String>maleList=json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()>maleList.size());
    }
}
