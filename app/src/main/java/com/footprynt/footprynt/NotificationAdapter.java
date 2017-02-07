package com.footprynt.footprynt;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private Context mContext;
    private List<Notification> notificationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView notification,date;
        public CardView card;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.cv_notification);
            notification = (TextView) view.findViewById(R.id.tv_noti);
            date = (TextView) view.findViewById(R.id.tv_date);
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


    public NotificationAdapter(Context mContext, List<Notification> notificationList) {
        this.mContext = mContext;
        this.notificationList = notificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Notification noti = notificationList.get(position);
        holder.notification.setText(noti.getNotification());
        holder.date.setText(noti.getDate());
    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}
