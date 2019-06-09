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
import com.abdelrahman.dictionary.adapter.WordRecyclerAdapter;
import com.abdelrahman.dictionary.database.MyDatabase;
import com.abdelrahman.dictionary.model.Word;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private RecyclerView mRecyclerView;
    List<Word> mWordList;
    View view;

    public FavouriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_item, container, false);
        getData();
        mRecyclerView = view.findViewById(R.id.fragments_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new WordRecyclerAdapter(mRecyclerView.getContext(), mWordList));

        return view;
    }

    private void getData() {
        MyDatabase mDatabase = new MyDatabase(getContext());
        mWordList = mDatabase.getFavouriteWords();
        mDatabase.close();
    }

}
