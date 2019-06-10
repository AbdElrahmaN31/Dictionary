package com.abdelrahman.dictionary.activities.main.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.database.MyDatabase;

import com.abdelrahman.dictionary.model.Word;

import java.util.List;
import java.util.Random;

public class HekamFragment extends Fragment {
    List<Word> mHekmaList;
    View view;

    public HekamFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_hekma, container, false);

        TextView arabicText = view.findViewById(R.id.hekma_arabic_text);
        TextView chineseText = view.findViewById(R.id.hekma_chinese_text);
        ImageView shareImage = view.findViewById(R.id.hekma_share_image);

        getData();
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(mHekmaList.size());
        Word hekma = mHekmaList.get(index);
        final String arabicWord = hekma.getArabicWord();
        final String chineseWord = hekma.getChineseWord();

        arabicText.setText(arabicWord);
        chineseText.setText(chineseWord);
        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = "Arabic word : " + arabicWord + "\n" +
                        "Chinese word : " + chineseWord;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getContext().getResources().getString(R.string.Dictionary));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                getContext().startActivity(Intent.createChooser(sharingIntent, getContext().getResources().getString(R.string.share_using)));

            }
        });
        return view;
    }

    private void getData() {
        MyDatabase mDatabase = new MyDatabase(getContext());
        mHekmaList = mDatabase.getHekma();
        mDatabase.close();
    }

}