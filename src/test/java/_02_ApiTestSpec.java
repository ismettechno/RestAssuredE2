


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _02_ApiTestSpec {
    RequestSpecification recSpec;
    ResponseSpecification resSpec;

    @BeforeClass
    public void Setup()
    {
        baseURI ="https://gorest.co.in/public/v1";

        //request paketi
        recSpec=new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        //response paketi
        resSpec=new ResponseSpecBuilder()
                .log(LogDetail.BODY)
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void Test1()
    {
        given()
                .spec(recSpec)

                .when()
                .get("/users")

                .then()
                .spec(resSpec)
                ;
    }

    @Test
    public void Test2()
    {
        given()
                .spec(recSpec)

                .when()
                .get("/users")

                .then()
                .spec(resSpec)
        ;
    }


}
