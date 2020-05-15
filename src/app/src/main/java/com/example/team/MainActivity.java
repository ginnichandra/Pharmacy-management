package com.example.team;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl=(DrawerLayout) findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

//        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch(item.getItemId())
//                {
//                    case R.id.login:
//                        showToast("redirecting to Login Page");
//                        Intent intent = new Intent(MainActivity.this, Login_activity.class);
//                        startActivity(intent);
//                        return true;
//
//                    case R.id.server:
//                        showToast("redirecting to server Page");
//                        Intent server_intent = new Intent(MainActivity.this, Server.class);
//                        startActivity(server_intent);
//                        return true;
//
//                    default:
//                        return false;
//                }
//            }
//        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.login:
                showToast("redirecting to Login Page");
                Intent intent = new Intent(MainActivity.this, Login_activity.class);
                startActivity(intent);
                return true;

            case R.id.server:
                showToast("redirecting to server Page");
//                Intent server_intent = new Intent(MainActivity.this, Server.class);
//                startActivity(server_intent);
//                return true;

            default:
                return false;
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
