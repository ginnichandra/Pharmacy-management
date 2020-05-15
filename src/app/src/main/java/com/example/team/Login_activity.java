package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login_activity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login, cancel;
    static String key;
    static int access, locationId;
    Jwt jwt;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        cancel = findViewById(R.id.cancel);

        login.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    public void onClick(View view) {
        if (view.getId() == R.id.login) {

            // gives key jwt
            validate("", "");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    // time ran out.
                    switch (Login_activity.access) {
                        case 0:
                            Intent intent = new Intent(Login_activity.this, PharmacistActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            Intent intent1 = new Intent(Login_activity.this, AdminActivity.class);
                            startActivity(intent1);
                            break;
                        case 2:
                            Intent intent2 = new Intent(Login_activity.this, SuperAdminActivity.class);
                            startActivity(intent2);
                            break;
                        default:
                            break;
                    }
                    timer.cancel();
                }
            }, 400);

//            Intent intent1 = new Intent(this, PharmacistActivity.class);
//                    startActivity(intent1);

//                                Intent intent = new Intent(this, AdminActivity.class);
//                    startActivity(intent);

        } else {
            email.getText().clear();
            password.getText().clear();
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void validate(String email, String password) {

        // ADMIN
        email = "chintu";
        password = "emo";

        //pharma
//        email = "hi";
//        password = "h";

        ServerApi serverApi = RetrofitInstance.getRetrofitInstance(false, "").create(ServerApi.class);

        Details detail = new Details(email, password);

        Call<Jwt> call = serverApi.authenticate(detail);


        call.enqueue(new Callback<Jwt>() {
            @Override
            public void onResponse(Call<Jwt> call, Response<Jwt> response) {

                if (!response.isSuccessful()) {
                    Log.i("in Response Code: ", "" + response.code());
                    return;
                }
                jwt = response.body();
                key = "" + jwt.getJwt();
                Log.i("token", key);
                Log.i("KEY", "" + Login_activity.key);
                getAccess();
            }

            @Override
            public void onFailure(Call<Jwt> call, Throwable t) {
                Log.i("in failure: ", t.getMessage());
            }
        });

    }

    public void getAccess() {

        ServerApi serverApi = RetrofitInstance.getRetrofitInstance(true, Login_activity.key).create(ServerApi.class);
        Call<User> call = serverApi.user();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.i("in Response Code: ", "" + response.code());
                    return;
                }
                User user = response.body();
                Log.i("Access", "" + user.getAccess());
                Log.i("XLO", "" + user.getLocationId());

                access = user.getAccess();
                locationId = user.getLocationId();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("in failure: ", t.getMessage());
            }
        });
    }

    public void onBackPressed() {
        //do nothing
    }

}


