package com.footprynt.footprynt;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OfferCategoryActivity extends AppCompatActivity {
    private String category;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private OffersAdapter adapter;
    private List<Offers> offersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_category);
        category = getIntent().getStringExtra("category");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(category + " Offers");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_backbutton));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        offersList = new ArrayList<>();
        adapter = new OffersAdapter(this, offersList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        prepareoffers();

    }
    private void prepareoffers() {

        Offers a = new Offers("Rs 100 off on T-shirt", "16th Dec","Exclusive offer");
        offersList.add(a);

        a = new Offers("50% off on Pizza", "5th Oct","General");
        offersList.add(a);

        adapter.notifyDataSetChanged();
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_noti, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                Toast.makeText(this, "refresh", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_clear:
                Toast.makeText(this, "clear", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}
