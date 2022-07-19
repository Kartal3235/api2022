package get_requests;

import base_urls.GoRestBaseUrl;
import org.junit.Test;
import pojos.GorestDataPojo;


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
               "id": 2508,
               "name": "Akshita Nehru",
              "email": "nehru_akshita@jast.info",
              "gender": "female",
              "status": "active"
                      }
            }
     */

    @Test
    public void get01Pojo() {

        //1.Step : Set the Url
        spec.pathParams("pp1","users","pp2",2508);

        //2.Step :Set the expected data
        GorestDataPojo gorestDataPojo=new GorestDataPojo();
    }
}
