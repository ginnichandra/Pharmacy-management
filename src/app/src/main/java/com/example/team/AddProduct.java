package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {
    EditText productName,available,price,manufacturer,required,description,storeId,chemicalFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        setEditTexts();

    }

    private void setEditTexts()
    {
        productName = findViewById(R.id.name);
        available = findViewById(R.id.available);
        price = findViewById(R.id.price);
        manufacturer = findViewById(R.id.manufacturer);
        required = findViewById(R.id.required);
        description = findViewById(R.id.description);
        storeId = findViewById(R.id.storeId);
        chemicalFormula = findViewById(R.id.chemicalFormula);
    }


}
