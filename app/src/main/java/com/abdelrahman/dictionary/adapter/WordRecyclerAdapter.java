package com.abdelrahman.dictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.database.MyDatabase;
import com.abdelrahman.dictionary.model.Word;

import java.util.List;

public class WordRecyclerAdapter extends RecyclerView.Adapter<WordRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Word> mWordList;

    public WordRecyclerAdapter(Context context, List<Word> wordList) {
        this.mContext = context;
        this.mWordList = wordList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.word_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Word mWord = mWordList.get(position);

        final String arabicWord = mWord.getArabicWord();
        final String pronunciation = mWord.getPronunciation();
        final String chineseWord = mWord.getChineseWord();
        final boolean isFavored = mWord.isFavour();
        final ImageView favorImage = holder.favorImage;
        ImageView shareImage = holder.shareImage;
        // set values
        holder.arabicText.setText(arabicWord);
        holder.pronunciationText.setText(pronunciation);
        holder.chineseText.setText(chineseWord);
        shareImage.setImageResource(R.drawable.ic_share_black_24dp);
        setVaforImage(holder, position);
        //set OnClickListeners
        favorImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase mDatabase = new MyDatabase(mContext);
                int wordId = mWord.getWordId();
                if (isFavored)
                    mDatabase.unFavorWord(wordId);
                else
                    mDatabase.favorWord(wordId);
                setVaforImage(holder, position);
                notifyDataSetChanged();
                mDatabase.close();
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shareBody = "Arabic word : " + arabicWord + "\n" +
                        "Pronunciation : " + pronunciation + "\n" +
                        "Chinese word : " + chineseWord ;
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mContext.getResources().getString(R.string.Dictionary));
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mContext.startActivity(Intent.createChooser(sharingIntent, mContext.getResources().getString(R.string.share_using)));
            }
        });
    }

    private void setVaforImage(MyViewHolder holder, int position) {
        final Word mWord = mWordList.get(position);
        final boolean isFavored = mWord.isFavour();
        final ImageView favorImage = holder.favorImage;
        if (isFavored)
            favorImage.setImageResource(R.drawable.ic_heart_black);
        else
            favorImage.setImageResource(R.drawable.ic_heart_outline);
    }
    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView arabicText, pronunciationText, chineseText;
        ImageView shareImage, favorImage;

        MyViewHolder(View view) {
            super(view);
           arabicText = view.findViewById(R.id.arabic_text);
           pronunciationText = view.findViewById(R.id.pronunciation_text);
           chineseText = view.findViewById(R.id.chinese_text);
           shareImage = view.findViewById(R.id.share_image);
           favorImage = view.findViewById(R.id.favor_image);
        }
    }
}