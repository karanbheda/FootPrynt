package com.footprynt.footprynt;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Category> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categories;
        public ImageView thumbnail;
        public CardView card;
        View background;

        public MyViewHolder(View view) {
            super(view);
            categories = (TextView) view.findViewById(R.id.tv_category);
            thumbnail = (ImageView) view.findViewById(R.id.iv_category);
            background = view.findViewById(R.id.textView_background);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(mContext, R.animator.lift_on_touch);
                card.setStateListAnimator(stateListAnimator);
            }
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
                }
            });
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

    /*@Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.categories.setText(category.getCategory());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, OfferCategoryActivity.class);
                i.putExtra("category",category.getCategory());
                mContext.startActivity(i);
            }
        });
    }*/
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Category categoryName = categoryList.get(position);
        holder.categories.setText(categoryName.getCategory());
        Picasso.with(mContext).load(categoryName.getImage()).resize(400, 400).centerCrop().into(holder.thumbnail);

        holder.thumbnail.setTag(categoryName);
        holder.categories.setText(categoryName.getCategory());

        int color = Color.parseColor(categoryName.getColor());
        holder.background.setBackgroundColor(color);
        if ((Color.red(color) + Color.green(color) + Color.blue(color)) < 420) {
            holder.categories.setTextColor(Color.WHITE);
        }

        /*if(position > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition= position;
        }*/
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
