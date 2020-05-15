package com.example.team;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagePharmacist extends AppCompatActivity {

    private RecyclerView recyclerView;
    //private RecyclerViewAdapter adapter;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> usersList;
    private int usersListSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pharmacist);

        createUsersList();


//        addUser();
//        deleteUser();

    }


    private void createUsersList()
    {
        usersList= new ArrayList<>();
        ServerApi serverApi=RetrofitInstance.getRetrofitInstance(true,Login_activity.key).create(ServerApi.class);
        Call<ArrayList<User>> call = serverApi.getUsers();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if(!response.isSuccessful())
                {
                    return;
                }

                ArrayList<User> users = response.body();
                for(User user : users){
                    usersList.add(new User(user.getId(),user.getName(),user.getRole(),user.getPassword(),user.getQualification(),user.getLocationId(),user.getAccess(),user.getEmail()));
                }
//                usersListSize = usersList.size();
                buildRecyclerView();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("ON FAILURE: ",t.getMessage());
            }
        });
    }

    private void buildRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter(usersList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onClick(position);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteUser(position);
            }
        });
    }

    public void onClick(int position){
        showToast("Clicked: "+usersList.get(position).getId());
        Gson gson = new Gson();
        String myUser = gson.toJson(usersList.get(position));
        Intent intent  = new Intent(this,UpdateUser.class);
        intent.putExtra("myUser", myUser);
        startActivity(intent);
    }

    private void addUser(){
        Intent intent = new Intent(this,RegisterPage.class);
        intent.putExtra("Role","Pharmacist");
        startActivity(intent);
    }


    private void deleteUser(int position){
        usersList.remove(position);
        adapter.notifyItemRemoved(position);
    }

        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            createUsersList();
        }
    }

    public void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

            @Override
    public boolean onCreateOptionsMenu(Menu menu) {


                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.users_menu,menu);

                MenuItem searchItem = menu.findItem(R.id.action_search);
                SearchView searchView = (SearchView) searchItem.getActionView();

                searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return false;
                    }
                });
                return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this,RegisterPage.class);
//                intent.putExtra("size",usersListSize);
                startActivityForResult(intent,0);
              //  startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
