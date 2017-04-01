package com.footprynt.footprynt;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private Context mContext;
    private List<Post> postList;


    private void resetPosts(){
        for(Post p:postList)  p.setChecked(false);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView posts;
        private CardView card;
        private ImageView tick;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.post_card);
            posts = (TextView) view.findViewById(R.id.tv_post);
            tick = (ImageView) view.findViewById(R.id.iv_check);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(mContext, R.animator.lift_on_touch);
                card.setStateListAnimator(stateListAnimator);
            }
            /*
            Delete later
            defining the onClickListener in the onBindViewHolder
             card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tick.setVisibility(View.VISIBLE);
                    card.setCardBackgroundColor(Color.WHITE);
                    post.setTextColor(Color.BLACK);


                }
            });
            */
        }
    }


    public PostAdapter(Context mContext, List<Post> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.posts.setText(post.getPost());
        if(post.getChecked()){
            holder.tick.setVisibility(View.VISIBLE);
            holder.card.setCardBackgroundColor(Color.WHITE);
            holder.posts.setTextColor(Color.BLACK);
        }
        /*Remember to set this later!*/
        else{
            holder.tick.setVisibility(View.GONE);
            holder.card.setCardBackgroundColor(Color.parseColor("#f7f7f7"));
            holder.posts.setTextColor(Color.GRAY);
        }

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean x = post.getChecked();
                resetPosts();
                post.setChecked(!x);
                PostAdapter.this.notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }
}