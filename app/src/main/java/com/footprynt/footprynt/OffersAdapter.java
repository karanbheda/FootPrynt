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
import android.widget.TextView;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Offers> offersList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView offer,date,type;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.card_offer);
            offer = (TextView) view.findViewById(R.id.tv_offers);
            date = (TextView) view.findViewById(R.id.tv_date);
            type = (TextView) view.findViewById(R.id.tv_type);
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


    public OffersAdapter(Context mContext, List<Offers> offersList) {
        this.mContext = mContext;
        this.offersList = offersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offers_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Offers offer = offersList.get(position);
        holder.offer.setText(offer.getOffer());
        holder.date.setText(offer.getDate());
        holder.type.setText(offer.getType());
        if(offer.getType().equals("Exclusive"))
            holder.type.setTextColor(Color.parseColor("#00A79D"));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, OfferActivity.class);
                //i.putExtra("category",category.getCategory());
                mContext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return offersList.size();
    }
}
