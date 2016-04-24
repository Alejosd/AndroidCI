package alejosd5.tumblr.com.sd.rest;

import android.util.Log;
import android.widget.TextView;

import alejosd5.tumblr.com.sd.API.IUser;
import alejosd5.tumblr.com.sd.R;
import alejosd5.tumblr.com.sd.models.HttpBinResponse;
import alejosd5.tumblr.com.sd.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alejosd on 23/04/2016.
 */
public class UserRest {

    private String API;
    private Retrofit retrofit;

    public UserRest(String API){
        this.API = API;
        retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<User> findUsername(String username){

        IUser service = retrofit.create(IUser.class);
        return service.getRestUsername(username);

    }
    public Call<HttpBinResponse> saveUsername(User user){

        IUser service = retrofit.create(IUser.class);
        return service.postRestUsername(user);

    }
}
