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
import java.util.Random;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Category> categoryList;
    private int[] colors = {R.color.cat1,R.color.cat2,R.color.cat3,R.color.cat4,R.color.cat5};
    static int a=5;
    private static final float SHADE_FACTOR = 0.9f;
    private Random mRandom = new Random();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categories;
        public CardView card;
        public View background1,background2;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.home_card);
            categories = (TextView) view.findViewById(R.id.tv_cat);
            thumbnail = (ImageView) view.findViewById(R.id.iv_cat);
            background1 = view.findViewById(R.id.tv_bg);
            background2 = view.findViewById(R.id.iv_bg);
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

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Category category = categoryList.get(position);
        holder.categories.setText(category.getCategory());
        int color = getRandomHSVColor();
        holder.background1.setBackgroundColor(getDarkerShade(mContext.getResources().getColor(color)));
        holder.background2.setBackgroundColor(mContext.getResources().getColor(color));
        holder.thumbnail.getLayoutParams().height = getRandomIntInRange(400,200);
        Picasso.with(mContext).load(category.getImage()).into(holder.thumbnail);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, OfferCategoryActivity.class);
                i.putExtra("category",category.getCategory());
                mContext.startActivity(i);
            }
        });
    }

    private int getDarkerShade(int color) {
        return Color.rgb((int)(
                SHADE_FACTOR * Color.red(color)),
                (int)(SHADE_FACTOR * Color.green(color)),
                (int)(SHADE_FACTOR * Color.blue(color)));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt((max-min))+min;
    }

    protected int getRandomHSVColor(){
        // Generate a random hue value between 0 to 360
        int r = mRandom.nextInt(5);
        while(r==a)
            r = mRandom.nextInt(5);
        a = r;
        // Finally, generate the color
        int color = colors[r];
        // Return the color
        return color;
    }
}
