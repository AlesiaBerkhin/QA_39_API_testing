package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContactTests implements Helper {

    String endpoint = "/v1/contacts";


    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void addNewContactPositive(){

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Pipin")
                .lastName("Lock")
                .email("pipin1_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Sheer")
                .description("Hobbit")
                .build();

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200);

    }

}