package com.rasheek.thedock.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rasheek.thedock.R;
import com.rasheek.thedock.models.Products;
import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {

    ImageView imageView;
    TextView product_name, product_desc, product_price, product_colors;
    Button payBtn;
    LinearLayout imageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        product_desc = findViewById(R.id.productDetail_desc);
        product_name = findViewById(R.id.productDetail_name);
        imageView = findViewById(R.id.productDetail_image);
        product_price = findViewById(R.id.productDetail_price);
        product_colors = findViewById(R.id.productDetail_colors);
        imageLayout = findViewById(R.id.productDetail_imageLayout);
        payBtn = findViewById(R.id.product_pay);



        Intent intent = getIntent();
        Products product = (Products) intent.getSerializableExtra("product");

        Picasso.get().load(product.getCoverImage()).into(imageView);
        product_name.setText(product.getName());
        product_desc.setText(product.getDesc());
        product_colors.setText( "Available Colors : "+ TextUtils.join(", ", product.getColors()));
        product_price.setText("Price : Rs " + product.getPrice() + "/=");
        for(int i = 0; i < product.getImages().size(); i ++){
            ImageView imageView = new ImageView(this);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(400, 400);
//            imageView.setLayoutParams(layoutParams);
            Picasso.get().load(product.getImages().get(i)).into(imageView);
            imageLayout.addView(imageView);

            Toast.makeText(this, "Looping"+ i, Toast.LENGTH_SHORT).show();
            Log.i("Test", String.valueOf(product.getImages().get(i)));
        }

        Log.i("Test", product.getColors().toString());
        payBtn.setText("Enquire");


        Toast.makeText(this, product.getName(), Toast.LENGTH_LONG).show();
        setTitle(product.getName());
    }
}