package com.abdelrahman.dictionary.activities.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.adapter.WordRecyclerAdapter;
import com.abdelrahman.dictionary.database.MyDatabase;
import com.abdelrahman.dictionary.model.Word;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "Category ID";
    List<Word> mWordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int data = intent.getIntExtra(EXTRA_DATA,1);

        getData(data);
        RecyclerView mRecyclerView = findViewById(R.id.details_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new WordRecyclerAdapter(mRecyclerView.getContext(), mWordList));

    }

    private void getData(int categoryId) {
        MyDatabase mDatabase = new MyDatabase(this);
        mWordList = mDatabase.getCategory(categoryId);
        mDatabase.close();

    }
}
