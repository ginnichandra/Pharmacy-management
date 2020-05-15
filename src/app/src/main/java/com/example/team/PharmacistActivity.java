package com.example.team;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class PharmacistActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    TextView textView;
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist);
        dl=(DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
        textView = findViewById(R.id.textView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.products:
                Intent intent = new Intent(PharmacistActivity.this,DisplayProducts.class);
                startActivity(intent);
                return true;

            case R.id.logout:
                Intent logoutIntent = new Intent(PharmacistActivity.this,Login_activity.class);
                startActivity(logoutIntent);

            default:
                return true;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
