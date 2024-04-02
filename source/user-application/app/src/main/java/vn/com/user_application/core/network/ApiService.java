package vn.com.user_application.core.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import vn.com.user_application.Application;
import vn.com.user_application.core.models.Dentist;
import vn.com.user_application.core.models.User;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(String.format("http://%s:8080/api/v1/",Application.ipHost))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("dentists")
    Call<List<Dentist>> fetchAllDentists();

    @POST("user/login")
    Call<User> login(@Query("userName") String userName, @Query("password") String password);

    @POST("user/register")
    Call<User> signUp(@Body User user);
}
