package com.abdelrahman.dictionary.activities.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.activities.main.fragments.FourFragment;
import com.abdelrahman.dictionary.activities.main.fragments.OneFragment;
import com.abdelrahman.dictionary.activities.main.fragments.ThreeFragment;
import com.abdelrahman.dictionary.activities.main.fragments.TwoFragment;
import com.abdelrahman.dictionary.adapter.MainViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

   Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.actionBar_id);
        setSupportActionBar(toolbar);

        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new OneFragment(),"ITEM ONE");
        mainViewPagerAdapter.addFragment(new TwoFragment(),"ITEM TWO");
        mainViewPagerAdapter.addFragment(new ThreeFragment(),"ITEM THREE");
        mainViewPagerAdapter.addFragment(new FourFragment(),"ITEM FOUR");

        viewPager = findViewById(R.id.viewPager_id);
        viewPager.setAdapter(mainViewPagerAdapter);

        tabLayout = findViewById(R.id.tabLayout_id);
        tabLayout.setupWithViewPager(viewPager);

    }
}
