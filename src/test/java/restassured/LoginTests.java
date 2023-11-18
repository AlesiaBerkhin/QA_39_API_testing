package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponsDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements Helper {

    String endpoint = "/v1/user/login/usernamepassword";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
    }

    @Test
    public void loginPositive() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("katy@mail.ru")
                .password("Kk12345!")
                .build();

        AuthResponsDTO responseDTO = given()
                .body(requestDTO) //zapolnaim body requestDTO
                .contentType(ContentType.JSON)
                .when() // kogda otpravlaim zapros
                .post(endpoint) // na endpoint
                .then() // togda
                .assertThat().statusCode(200)//utverzdau chto status kod 200
                .extract()//esli eto tak to vitaskivau poluchenyi otvet iz json v vide classa
                .as(AuthResponsDTO.class); //v vide classa authresponseDTO

        System.out.println(responseDTO.getToken());


    }

    @Test
    public void loginNegative() {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("katymail.ru")
                .password("Kk12345!")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());

    }
}
