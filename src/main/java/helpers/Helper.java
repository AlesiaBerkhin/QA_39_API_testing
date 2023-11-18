package helpers;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoia2F0eUBtYWlsLnJ1IiwiaXNzIjoiUmVndWxhaXQiLCJleHAiOjE2OTkzNDA1NjMsImlhdCI6MTY5ODc0MDU2M30.iJQ2tvy7TLPxJnrwQinsbiADMMeB_0XDJBU5cFStbq0";

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    String baseURL = "https://contactapp-telran-backend.herokuapp.com";

    String authHeader = "Authorization";

    //   int i = (int)(System.currentTimeMillis()/1000)%3600;

    int i = new Random().nextInt(1000) +1000; // mozet byt mnogo variantov, randomaizer odin iz nix, sverxy toze pravilno
}
