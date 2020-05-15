package com.example.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class Inventory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartRVA adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Products> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
    }
}
