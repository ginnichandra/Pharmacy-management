package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity implements View.OnClickListener {

    private EditText quantity;
    private TextView total;
    private Button checkout;
    private  int totalPrice;
    private RecyclerView recyclerView;
    private CartRVA adapter;

    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<StoreProduct> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        buildRecyclerView();
        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(this);



    }


    private void buildRecyclerView()
    {
        quantity = findViewById(R.id.quantity);
        total = findViewById(R.id.total);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CartRVA(cartList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CartRVA.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
                showToast("Product deleted");
            }
        });

        for(StoreProduct product : cartList){
            totalPrice += product.getQuantity() * product.getPrice();
        }
        total.setText(""+totalPrice+" $");
    }

    public void removeItem(int position) {
        cartList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private void showToast (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        showToast("checkout");
        ArrayList<Sales> salesList = new ArrayList<>();
        for(StoreProduct product : cartList){
            salesList.add(new Sales(product.getProduct(),product.getQuantity(),product.getPrice()));
        }

        Invoice invoice = new Invoice(totalPrice,salesList);
        ServerApi serverApi = RetrofitInstance.getRetrofitInstance(true,Login_activity.key).create(ServerApi.class);
        Call<Void> call = serverApi.checkout(invoice);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful())
                {
                    return;
                }
                setResult(RESULT_OK);
                showToast("Order placed Successfully");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ON FAILURE: ",t.getMessage());
                showToast(t.getMessage());
            }
        });
    }

}
