package com.example.team;

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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayProducts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PharmacistRVA adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StoreProduct> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);
        productsList = new ArrayList<StoreProduct>();
        createProductsList();
        fab();

    }

    private void fab()
    {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(DisplayProducts.this,Cart.class);
                startActivityForResult(intent,0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            createProductsList();
        }
    }

    private void createProductsList()
    {
        Log.i("XYZ",""+getIntent().getStringExtra("locationId"));

        ServerApi serverApi=RetrofitInstance.getRetrofitInstance(true,Login_activity.key).create(ServerApi.class);
        Call<ArrayList<StoreProduct>> call = serverApi.getProducts(Login_activity.locationId);

        call.enqueue(new Callback<ArrayList<StoreProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<StoreProduct>> call, Response<ArrayList<StoreProduct>> response) {

                ArrayList<StoreProduct>products = response.body();
                for(StoreProduct product : products){
//                    productsList.add(new Products(product.getName(),product.getAvailable(),product.getPrice(),product.getManufacturer(),product.getRequired(),product.getDescription(),product.getStoreid(),product.getChemicalformula(),1));
                    productsList.add(new StoreProduct(product.getId(),product.getProduct(),product.getLocation(),product.getAvailablequantity(),product.getRequiredquantity(),product.getPrice()/100,1));
                }
                buildRecyclerView();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<StoreProduct>> call, Throwable t) {
                Log.e("ON FAILURE: ",t.getMessage());
            }
        });

    }

    private void buildRecyclerView()
    {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PharmacistRVA(productsList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PharmacistRVA.OnItemClickListener() {
            @Override
            public void onAddClick(int position) {
                addToCart(position);
            }

            @Override
            public void onPlusClick(int position) {
                increment(position);
            }

            @Override
            public void onMinusClick(int position) {
                decrement(position);
            }
        });
    }

    private void increment(int position)
    {
        if(productsList.get(position).getQuantity() < productsList.get(position).getAvailablequantity()) {
            productsList.get(position).changeQuantity(productsList.get(position).getQuantity() + 1);
            adapter.notifyItemChanged(position);
        }else{
            showToast("Exceeded available limit");
        }

//        productsList.get(position).changeQuantity(productsList.get(position).getQuantity() + 1);
//        adapter.notifyItemChanged(position);

    }

    private void decrement(int position)
    {
        if(productsList.get(position).getQuantity()>1) {
            productsList.get(position).changeQuantity(productsList.get(position).getQuantity() - 1);
            adapter.notifyItemChanged(position);
        }else {
            showToast("Add to delete");
        }

    }


    private void addToCart(int position)
    {
        Cart.cartList.add(productsList.get(position));
        showToast("Added to cart");
    }


    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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
}
