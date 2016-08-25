package com.pordiva.esraeken.efsoretrofit;

import com.pordiva.esraeken.efsoretrofit.model.user;

        import java.util.List;

        import retrofit.Callback;
        import retrofit.http.GET;
        import retrofit.http.Path;

/**
 * Created by esraeken on 11/08/16.
 */
public interface UserInterface
{

    @GET("/users/{username}")
    void getUser(@Path("username") String username, Callback<user> response);

    @GET("/users")
    void getUserList(Callback<List<user>> response);

}

