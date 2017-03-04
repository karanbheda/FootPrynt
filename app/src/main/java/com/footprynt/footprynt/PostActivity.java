package com.footprynt.footprynt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private List<Post> postList;
    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Spread the Love");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_backbutton));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        postList = new ArrayList<>();
        adapter = new PostAdapter(this, postList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareposts();
        share = (Button) findViewById(R.id.btn_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PostActivity.this,OfferActivity.class);
                i.putExtra("COUPON","XYZABC");
                startActivity(i);
            }
        });

    }
    private void prepareposts() {

        Post a = new Post("#footprynt  You can now get #CouponDunia super saver browser extension on #Chrome & get #deals alerts, the offers are now on #footprynt");
        postList.add(a);

        a = new Post("#AntiFactory Antifactory outfits make your fitness regime less gruelling. If it is leggings or tracks it has to be Antifactory.");
        postList.add(a);

        a = new Post("#AntiFactory If fitness is your mantra & attitude is your style, break barriers with http://www.antifactory.in , Visit #footprynt app for offers on Antifactory(1/2)");
        postList.add(a);
        adapter.notifyDataSetChanged();
    }
}
