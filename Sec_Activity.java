package com.example.cashregisterfinal;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/** @noinspection ALL*/
public class Sec_Activity extends AppCompatActivity {

    private ArrayList<Product> Prodt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        TextView tvReceiptDetails = findViewById(R.id.tvReceiptDetails);
        Button btnGoBack = findViewById(R.id.btnGoBack);

        double totalAmount = getIntent().getDoubleExtra("totalAmount", 0.0);
       // String name=getIntent().getIdentifier().

        Prodt1=(ArrayList<Product>)getIntent().getSerializableExtra("productlist");
        tvReceiptDetails.setText(String.format("Total Amount: $%.2f", totalAmount));

        btnGoBack.setOnClickListener(v -> finish());
    }
}