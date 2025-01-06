package com.example.cashregisterfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Product> products;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        Product currentProduct = (Product) getItem(position);
        TextView productName = convertView.findViewById(R.id.productName);
        TextView productQuantity = convertView.findViewById(R.id.productQuantity);
        TextView productPrice = convertView.findViewById(R.id.productPrice);

        productName.setText(currentProduct.getName());
        productQuantity.setText("Quantity: " + currentProduct.getQuantity());
        productPrice.setText("Price: $" + currentProduct.getPrice());

        return convertView;
    }
}

