package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener  {

    EditText name,designation,email,password,qualification,locationId,access;
    Button register,cancel;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        designation=findViewById(R.id.designation);
        password=findViewById(R.id.password);
        qualification=findViewById(R.id.qualification);
        locationId = findViewById(R.id.locationId);
        access= findViewById(R.id.access);
//        Intent intent = getIntent();
//        size = intent.getIntExtra("size",0);
//        size +=1;
        register=findViewById(R.id.register);
        cancel=findViewById(R.id.cancel);

        register.setOnClickListener(this);
        cancel.setOnClickListener(this);


    }

    private void addUSer()
    {

        ServerApi serverApi = RetrofitInstance.getRetrofitInstance(true,Login_activity.key).create(ServerApi.class);
        User newUser=
                new User(null,name.getText().toString(),designation.getText().toString(),password.getText().toString(),qualification.getText().toString(),
                Integer.parseInt(locationId.getText().toString()),Integer.parseInt(access.getText().toString()),email.getText().toString());

        Call<Void> call = serverApi.addUser(newUser);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    return;
                }
                setResult(RESULT_OK);
                Log.i("RESPONSE: " ,""+ response.code());
                showToast("User created successfully");
                finish();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("onFailure: ",t.getMessage());
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.register) {
            addUSer();
        }
        else if(view.getId()== R.id.cancel)
            {
            name.getText().clear();
            designation.getText().clear();
            email.getText().clear();
            password.getText().clear();
            qualification.getText().clear();
            locationId.getText().clear();
            access.getText().clear();
        }
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
