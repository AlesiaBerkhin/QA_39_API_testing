package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponsDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper {

    String endpoint = "/v1/user/registration/usernamepassword";

    @Test
    public void registrationPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("katy_" + i + "@mail.ru")
                .password("Kk12345!")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());

//        if(response.isSuccessful()) {
//            String responseJson = response.body().string();
//            AuthResponsDTO responseDTO = gson.fromJson(responseJson, AuthResponsDTO.class);
//            System.out.println( "Response code is----->" + response.code());
//            System.out.println(responseDTO.getToken());
//            Assert.assertTrue(response.isSuccessful());
//        } else {
//            ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
//            System.out.println("Response code is----->" + response.code());
//            System.out.println(errorDTO.getStatus()+ "===" + errorDTO.getMessage() + "======" + errorDTO.getError());
//            Assert.assertFalse(response.isSuccessful());
//        }
    }

    @Test
    public void registrationNegativeWrongEmail() throws IOException {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("katy_" + i + "mail.ru")
                .password("Kk12345!")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println("Response code is----->" + response.code());
        System.out.println(errorDTO.getStatus()+ "===" + errorDTO.getMessage() + "======" + errorDTO.getError());
        Assert.assertTrue(!response.isSuccessful());
    }
}

