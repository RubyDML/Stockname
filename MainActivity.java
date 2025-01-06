package com.example.cashregisterfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView selectedProductTextView, quantityTextView, totalTextView;
    private ListView productListView;
    private ArrayList<Product> products;
    private int selectedQuantity = 0;
    private Product selectedProduct = null;

    private final ArrayList<Product> productList = new ArrayList<>();

    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedProductTextView = findViewById(R.id.selectedProductTextView);
        quantityTextView = findViewById(R.id.quantityTextView);
        totalTextView = findViewById(R.id.totalTextView);
        productListView = findViewById(R.id.productListView);

        products = new ArrayList<>();
        populateProductList();

        ProductAdapter adapter = new ProductAdapter(this, products);
        productListView.setAdapter(adapter);

        productListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedProduct = products.get(position);
            selectedProductTextView.setText(selectedProduct.getName());
            calculateTotal();
        });
    }

    public void onNumberClick(View view) {
        Button button = (Button) view;
        String number = button.getText().toString();
        selectedQuantity = Integer.parseInt(quantityTextView.getText() + number);
        quantityTextView.setText(String.valueOf(selectedQuantity));
        calculateTotal();
    }

    public void onClearClick(View view) {
        selectedQuantity = 0;
        quantityTextView.setText("");
        totalTextView.setText("");
        selectedProductTextView.setText("");
    }


    public void onBuyClick(View view) {
        if (selectedProduct == null || selectedQuantity == 0) {
            Toast.makeText(this, "Please select a product and quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedQuantity > selectedProduct.getQuantity()) {
            Toast.makeText(this, "Insufficient stock!", Toast.LENGTH_SHORT).show();
            return;
        }
        productList.add(new Product(selectedProduct.getName(), selectedProduct.getQuantity(), selectedProduct.getPrice()));
        selectedProduct.setQuantity(selectedProduct.getQuantity() - selectedQuantity);
        selectedQuantity = 0;
        quantityTextView.setText("");
        totalTextView.setText("");
        selectedProductTextView.setText("");
        ((ProductAdapter) productListView.getAdapter()).notifyDataSetChanged();
        Toast.makeText(this, "Purchase successful!", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("SetTextI18n")
    private void calculateTotal() {
        if (selectedProduct != null) {
            total = selectedQuantity * selectedProduct.getPrice();
            totalTextView.setText("Total: $" + total);
        }
    }

    private void populateProductList() {
        products.add(new Product("Prd1:Shirts ", 10, 100));
        products.add(new Product("Prd2:Pants ", 5, 200));
        products.add(new Product("Prd3:Caps  ", 20, 50));
    }

   /* public void onManager(View view) {
        Intent intent = new Intent(this,Sec_Activity.class);
        intent.putParcelableArrayListExtra("productlist",productList);
        startActivity(intent);
    }*/

}