package com.example.tablayout;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EachItem extends AppCompatActivity {

    TextView item_name,item_price,item_description;
    ImageView item_image;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_item);

        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shopping App");
        actionBar.setSubtitle("Item Details");

        item_name=(TextView)findViewById(R.id.item_name);
        item_price=(TextView)findViewById(R.id.item_price);
        item_description=(TextView)findViewById(R.id.item_description);
        item_image=(ImageView)findViewById(R.id.item_image);

        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            //Integer image=Integer.parseInt(bundle.getString("itemImageName"));
            item_name.setText(bundle.getString("itemName"));
            item_price.setText(bundle.getString("itemPrice"));
            item_description.setText(bundle.getString("itemDescription"));
            item_image.setImageResource(bundle.getInt("itemImageName"));
        }
    }
}
