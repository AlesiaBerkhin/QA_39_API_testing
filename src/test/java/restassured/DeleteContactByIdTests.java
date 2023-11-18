package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteContactByIdTests implements Helper {

    String endpoint = "/v1/contacts";

    String id;

    ContactDTO contactDTO;

    @BeforeMethod
    public void precondition(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";


        contactDTO = ContactDTO.builder()
                .name("Frodo")
                .lastName("Beggins")
                .email("frodo_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Sheer")
                .description("Hobbit")
                .build();

        String message = given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void deleteContactByIDPositive() throws IOException {

        contactDTO.setId(id);

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .delete()
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was deleted"));

    }

    }


