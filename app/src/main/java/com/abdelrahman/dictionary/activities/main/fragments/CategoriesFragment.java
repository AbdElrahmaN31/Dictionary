package com.abdelrahman.dictionary.activities.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.adapter.CategoriesRecyclerAdapter;
import com.abdelrahman.dictionary.database.MyDatabase;
import com.abdelrahman.dictionary.model.Category;

import java.util.List;

public class CategoriesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    List<Category>  mCategoryList;
    View view;

    public CategoriesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_recycler, container, false);
        getData();
        mRecyclerView = view.findViewById(R.id.fragments_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new CategoriesRecyclerAdapter(mRecyclerView.getContext(), mCategoryList));

        return view;
    }

    private void getData() {
        MyDatabase mDatabase = new MyDatabase(getContext());
        mCategoryList = mDatabase.getCategories();
        mDatabase.close();
    }

}