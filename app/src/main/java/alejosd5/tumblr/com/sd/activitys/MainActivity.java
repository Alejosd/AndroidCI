package alejosd5.tumblr.com.sd.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import alejosd5.tumblr.com.sd.API.IUser;
import alejosd5.tumblr.com.sd.R;
import alejosd5.tumblr.com.sd.models.HttpBinResponse;
import alejosd5.tumblr.com.sd.models.User;
import alejosd5.tumblr.com.sd.rest.UserRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    String API = "http://10.0.2.2:8081";

    public void sendMessage(View view) {


        saveUsers();
        listUsers();
        //searchUsers();
    }

    public void updateListUser(List<User> users){

        ArrayList<String> list = new ArrayList<>();
        for (User user:users) {
            list.add(user.getUsername());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void listUsers(){

        UserRest userRest = new UserRest(API);
        Call<List<User>> call = userRest.listUsername();
        call.enqueue(new Callback<List<User>>() {
                         @Override
                         public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                             List<User> user = response.body();
                             updateListUser(user);


                         }

                         @Override
                         public void onFailure(Call<List<User>> call, Throwable t) {
                             Log.e("HelloWorld", t.getMessage());
                         }

                     }
        );
    }

    public void saveUsers(){

        UserRest userRest = new UserRest(API);
        EditText editText = (EditText) findViewById(R.id.editText);
        String username= editText.getText().toString();
        Call<HttpBinResponse> call = userRest.saveUsername(new User(username));
        // Asynchronously execute HTTP request
        call.enqueue(new Callback<HttpBinResponse>() {
            @Override
            public void onResponse(Call<HttpBinResponse> call, Response<HttpBinResponse> response) {
                // http response status code + headers
                System.out.println("Response status code: " + response.code());

            }

            @Override
            public void onFailure(Call<HttpBinResponse> call, Throwable t) {
                System.out.println("onFailure");
                System.out.println(t.getMessage());
            }


        });
    }

    public void searchUsers(){

        UserRest userRest = new UserRest(API);
        EditText editText = (EditText) findViewById(R.id.editText);
        String username= editText.getText().toString();
        Call<User> call = userRest.findUsername(username);
        call.enqueue(new Callback<User>() {
                         @Override
                         public void onResponse(Call<User> call, Response<User> response) {
                             User user = response.body();
                            // TextView userText = (TextView) findViewById(R.id.alejotext);
                             //userText.setText(user.getUsername());
                         }

                         @Override
                         public void onFailure(Call<User> call, Throwable t) {
                             Log.e("HelloWorld", t.getMessage());
                         }


                     }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
            }

            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }
        }
