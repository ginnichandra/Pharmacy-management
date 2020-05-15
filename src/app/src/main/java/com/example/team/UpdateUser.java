package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

public class UpdateUser extends AppCompatActivity implements View.OnClickListener{
    EditText name,designation,email,password,qualification,locationId,access;
    Button update,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        setEditText();

        update=findViewById(R.id.update);
        cancel=findViewById(R.id.cancel);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    private void setEditText(){
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        designation=findViewById(R.id.designation);
        password=findViewById(R.id.password);
        qualification=findViewById(R.id.qualification);
        locationId = findViewById(R.id.locationId);
        access= findViewById(R.id.access);
        Gson gson = new Gson();
        User myUser = gson.fromJson(getIntent().getStringExtra("myUser"), User.class);

        name.setText(myUser.getName());
        email.setText(myUser.getEmail());
        designation.setText(myUser.getRole());
        password.setText(myUser.getPassword());
        qualification.setText(myUser.getQualification());
        locationId.setText(""+myUser.getLocationId());
        access.setText(""+myUser.getAccess());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register) {
            //addUSer();
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
}
