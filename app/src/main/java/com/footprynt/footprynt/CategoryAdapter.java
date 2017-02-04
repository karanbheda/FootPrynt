package com.footprynt.footprynt;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Category> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categories;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.home_card);
            categories = (TextView) view.findViewById(R.id.tv_category);
        }
    }


    public CategoryAdapter(Context mContext, List<Category> categoryList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.categories.setText(category.getCategory());
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */

    /**
     * Click listener for popup menu items
     */

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
