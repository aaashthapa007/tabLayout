package com.example.tablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Online Clothing Shopping App");


        tabLayout=findViewById(R.id.tabId);
        viewPager=findViewById(R.id.view_pager);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(),"Login");
        adapter.addFragment(new RegisterFragment(),"Register");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
