package okhttp;


import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponsDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests {

    // token = eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2F0eUBtYWlsLnJ1IiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTg1OTY1NDksImlhdCI6MTY5Nzk5NjU0OX0.QJhBVJeYwkp52gYqoA1tuBKIUJfNYFzPhFDL6P5y1VQ

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    String baseURL = "https://contactapp-telran-backend.herokuapp.com";

    @Test
    public void loginPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("katy@mail.ru")
                .password("Kk12345!")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + "/v1/user/login/usernamepassword")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if(response.isSuccessful()) {
            String responseJson = response.body().string();
            AuthResponsDTO responseDTO = gson.fromJson(responseJson, AuthResponsDTO.class);
            System.out.println( "Response code is----->" + response.code());
            System.out.println(responseDTO.getToken());
            Assert.assertTrue(response.isSuccessful());
        } else {
            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
            System.out.println("Response code is----->" + response.code());
            System.out.println(errorDTO.getStatus()+ "===" + errorDTO.getMessage() + "======" + errorDTO.getError());
            Assert.assertFalse(response.isSuccessful());
        }


    }

}
