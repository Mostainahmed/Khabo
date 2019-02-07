package com.hosttheweb.khabo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("registration.php")
    Call<User> performRegistration(@Query("name") String Name,@Query("password") String Password,@Query("location") String Location,@Query("location_details") String LocationDetails,@Query("number") String Number);

    @GET("login.php")
    Call<User> performLogin(@Query("name") String Name,@Query("password") String Password);
}
