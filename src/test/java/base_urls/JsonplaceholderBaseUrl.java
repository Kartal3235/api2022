package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {

    protected RequestSpecification spec;  //protected yaptık neden?==> Çünkü biz farklı bir packege altındaki classlardan oluşturduğumuz baseUrl classına
    // extends yaparak yani inharetance ilişkisi oluşturarak child ve parent class ilişkisi oluşturarak
    // burada hazırlamış olduğumuz verilere erişeceğiz
    // fakat aynı zamanda bu ulaşacağımız verinin değiştirilebilir olmaması gerekiyor (spec'in)


    //=================================================================================
    // RequestSpecification bir interface olduğundan bunun constructor yoktur.
    // Bunun için  restassured kutuphanesinin bize sağladığı RequestSpecBuilder() constructorı kullanarak spec 'e atama
    // yapabiliriz.

    // Before annotation'ı
    @Before
    public void setUp(){

        spec=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com/").build();


   }

}
