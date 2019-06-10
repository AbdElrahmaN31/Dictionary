package com.abdelrahman.dictionary.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdelrahman.dictionary.R;
import com.abdelrahman.dictionary.activities.details.DetailsActivity;
import com.abdelrahman.dictionary.model.Category;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.MyViewHolder> {
    private Context mContext;
    private List<Category> mCategoryList;

    public CategoriesRecyclerAdapter(Context context, List<Category> categoryList) {
        this.mContext = context;
        this.mCategoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Category mCategory = mCategoryList.get(position);
        String categoryName = mCategory.getCategoryName();
        holder.categoryItemText.setText(categoryName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(mContext, DetailsActivity.class);
                intent.putExtra(DetailsActivity.EXTRA_DATA, mCategory.getCategoryId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryItemText;

        MyViewHolder(View view) {
            super(view);
            categoryItemText = view.findViewById(R.id.category_item_text);
        }
    }
}