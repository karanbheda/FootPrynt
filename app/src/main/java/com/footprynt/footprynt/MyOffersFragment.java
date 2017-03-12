package com.footprynt.footprynt;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyOffersFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private List<Category> categoryList;
    private ViewGroup rootView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_my_offers, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        categoryList = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity(), categoryList);
        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        preparecategories();
        return rootView;
    }
    /*@Override
    public void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        categoryList = new ArrayList<>();
        adapter = new categorysAdapter(this, categoryList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        preparecategorys();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    /*private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }*/

    /**
     * Adding few categorys for testing
     */
    private void preparecategories() {

        Category a = new Category("Journalism",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Movies",R.drawable.ic_movies);
        categoryList.add(a);

        a = new Category("Restaurants",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Breweries",R.drawable.ic_pub);
        categoryList.add(a);

        a = new Category("Electronics",R.drawable.ic_ecommerce);
        categoryList.add(a);

        a = new Category("Real Estate",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Books",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Sports",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("Humour",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Hotels",R.drawable.ic_hotel);
        categoryList.add(a);

        a = new Category("Automobiles",R.drawable.ic_automobile);
        categoryList.add(a);

        a = new Category("Health",R.drawable.ic_health);
        categoryList.add(a);

        a = new Category("Travel",R.drawable.ic_travel);
        categoryList.add(a);

        a = new Category("FMCG",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Banks",R.drawable.ic_bank);
        categoryList.add(a);

        a = new Category("Personal Care",R.drawable.ic_health);
        categoryList.add(a);

        a = new Category("Shares",R.drawable.ic_music);
        categoryList.add(a);

        a = new Category("Consumer Durables",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("E-Commerce",R.drawable.ic_ecommerce);
        categoryList.add(a);

        a = new Category("Education",R.drawable.ic_education);
        categoryList.add(a);

        a = new Category("Retail",R.drawable.ic_sports);
        categoryList.add(a);

        a = new Category("Fashion",R.drawable.ic_travel);
        categoryList.add(a);

        a = new Category("TV",R.drawable.ic_tv);
        categoryList.add(a);

        adapter.notifyDataSetChanged();
    }

}

