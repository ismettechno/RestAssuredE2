import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _01_ApiTest {

    @Test
    public void Test1()
    {
        // POSTMAN
        // 1- Endpoint i çağırmadan önce hazırların yapıldığı bölüm: url, gidecek body,token
        // 2- Endpoint in çağrıldığı bölüm : Endpoint in çağrılması(METOD : GET, POST ..)
        // 3- Endpoint çağrıldıktan sonraki bölüm: Response, gelen body, gelen status code, TEST(Assertion)

        given()
                //1.bölümlerle ilgili işler : giden body,token
                .when()
                //2.bölümlerle ilgili işler : gidiş metodu , endpoint, apinin çağrılma kısmı
                .then()
                //3.bölümlerle ilgili işler: gelen data, assert,test
        ;
    }

    @Test
    public void statusCodeTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()  // dönüş datasını gösterir  all: bütün bilgiler
                .statusCode(200) // dönen status kod 200 e eşitmi ?, assert
                ;
    }

    @Test
    public void contentTypeTest()
    {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // status code 200 mü
                .contentType(ContentType.JSON)  // dönen datanın formatı JSON mı
        ;
    }

    @Test
    public void checkCountryInResponseBody()
    {
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // status code 200 mü
                .contentType(ContentType.JSON)  // dönen datanın formatı JSON mı
                .body("country", equalTo("United States")); // country == "United States"

        // country yi dışarı almadan
        // bulundu yeri (path i) vererek içerde assertion yapıyorum.
        // Bunu hamcrest kütüphanesi yapıyor
        ;
    }

    @Test
    public void checkHasItem() {
        // Soru : "http://api.zippopotam.us/tr/01000"  endpoint in dönen
        // places dizisinin herhangi bir elemanında  "Dörtağaç Köyü" değerinin
        // olduğunu doğrulayınız

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .statusCode(200)  // status code 200 mü
                .contentType(ContentType.JSON)
                .body("places[2].'place name'", equalTo("Dörtağaç Köyü")) // 2 indexdeki eleman buna eşit mi
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))  // tum placelerde bu eleman var mı
                ;
    }

    @Test
    public void bodyArrayHasSizeTest()
    {
        // Soru : "http://api.zippopotam.us/us/90210"  endpoint in dönen Places dizisinin
        // boyutunun 1 olduğunu doğrulayınız.

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("places", hasSize(1))
        ;
    }



}
