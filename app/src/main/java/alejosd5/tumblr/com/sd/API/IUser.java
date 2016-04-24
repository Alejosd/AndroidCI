package alejosd5.tumblr.com.sd.API;

import java.util.List;

import alejosd5.tumblr.com.sd.models.HttpBinResponse;
import alejosd5.tumblr.com.sd.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by alejosd on 23/04/2016.
 */
public interface IUser {


    @GET("/users/{username}")
    Call<User> getRestUsername(@Path("username") String user);

    @GET("/users")
    Call<List<User>> getListUsername();


    @POST("/users/save")
    Call<HttpBinResponse> postRestUsername(@Body User user);

}
