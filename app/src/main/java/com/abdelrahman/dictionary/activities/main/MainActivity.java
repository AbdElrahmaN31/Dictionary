package com.abdelrahman.dictionary.activities.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.activities.main.fragments.CategoriesFragment;
import com.abdelrahman.dictionary.activities.main.fragments.FavouriteFragment;
import com.abdelrahman.dictionary.activities.main.fragments.HekamFragment;
import com.abdelrahman.dictionary.activities.main.fragments.SearchFragment;
import com.abdelrahman.dictionary.adapter.MainViewPagerAdapter;

import java.util.ArrayList;

import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout;

public class MainActivity extends AppCompatActivity {

    private CoordinatorTabLayout mCoordinatorTabLayout;
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;
    private int[] mColorArray;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
        addTitles();
        initViewPager();
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light};

        mCoordinatorTabLayout = findViewById(R.id.coordinatortablayout);
        mCoordinatorTabLayout.setTranslucentStatusBar(this)
                .setContentScrimColorArray(mColorArray)
                .setupWithViewPager(mViewPager);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new CategoriesFragment());
        mFragments.add(new SearchFragment());
        mFragments.add(new HekamFragment());
        mFragments.add(new FavouriteFragment());
    }

    private void addTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("قوائم");
        mTitles.add("بحث");
        mTitles.add("حكمة");
        mTitles.add("المفضلة");
    }

    private void initViewPager() {
        mViewPager = findViewById(R.id.vp);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }
    @Override
    public void onRestart() {
        super.onRestart();
        recreate();
    }
}
