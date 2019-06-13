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
import android.widget.EditText;
import android.widget.ImageView;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.adapter.WordRecyclerAdapter;
import com.abdelrahman.dictionary.database.MyDatabase;
import com.abdelrahman.dictionary.model.Word;

import java.util.List;

public class SearchFragment extends Fragment {

    List<Word> mWordList;
    private RecyclerView mRecyclerView;
    View view;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        final EditText searchEditText = view.findViewById(R.id.search_edit);
        ImageView searchImage = view.findViewById(R.id.search_image);
        mRecyclerView = view.findViewById(R.id.fragments_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchEditText != null) {
                    MyDatabase mDatabase = new MyDatabase(getContext());
                    mWordList = mDatabase.search(searchEditText.getText().toString());
                    mRecyclerView.setAdapter(new WordRecyclerAdapter(mRecyclerView.getContext(), mWordList));
                    mDatabase.close();
                } else {
                    searchEditText.setError("Can't be empty");
                }
            }
        });

        return view;
    }
}
