package com.example.tablayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ItemModel> itemList = new ArrayList<>();
    ActionBar actionBar;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Boolean isLoggedIn;
    private static final String FILE_NAME="items.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shooping App");
        actionBar.setSubtitle("Dashboard");
        preferences=getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
        editor=preferences.edit();
        recyclerView=findViewById(R.id.recycler_view);
        prepareItemList();
        //ItemAdapter itemAdapter=new ItemAdapter(this,itemList<>)
        ItemAdapter itemAdapter=new ItemAdapter(itemList,this);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
    public void prepareItemList(){
        try {
            String data;
            FileInputStream fis=openFileInput(FILE_NAME);
            InputStreamReader isr=new InputStreamReader(fis);//converts to character sequence
            BufferedReader br=new BufferedReader(isr);//change to readable character
            StringBuilder sb=new StringBuilder();//converts to string
            while ((data = br.readLine()) != null) {
                String[] parts=data.split("->");
                //String imageName=parts[2];
                //imageName="@drawable/"+itemList.get(i).getItemImageName();
                //int imageResource= getApplicationContext().getResources().getIdentifier(imageName,null, getApplicationContext().getPackageName());
                itemList.add(new ItemModel(parts[0],parts[1],parts[2],parts[3]));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.dashboard_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_additems:
                Intent intent = new Intent(RecyclerViewActivity.this, ItemActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_settings:
                Toast.makeText(RecyclerViewActivity.this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_us:
                Toast.makeText(RecyclerViewActivity.this, "About Us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                isLoggedIn = false;
                editor.putBoolean("isLoggedIn", isLoggedIn).commit();
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Online Clothing Shopping App")
                        .setMessage("Are you sure want to logout?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RecyclerViewActivity.this, MainActivity.class);
                                startActivity(intent);
                                //do not start activity if you want to exit the app.
                                RecyclerViewActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
