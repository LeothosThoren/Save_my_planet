package com.leothos.savemyplanet.controlers.fragments;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.ViewModel.ProductViewModel;
import com.leothos.savemyplanet.adapters.MyProductAdapter;
import com.leothos.savemyplanet.entities.MyProduct;
import com.leothos.savemyplanet.injections.Injection;
import com.leothos.savemyplanet.injections.ViewModelFactory;
import com.leothos.savemyplanet.utils.ItemClickSupport;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListFragment extends Fragment {

    // Widget
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.message_empty)
    TextView mEmptyMessage;
    @BindView(R.id.list_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    CardView mCardView;
    // Var
    private ProductViewModel mProductViewModel;
    private MyProductAdapter mAdapter;

    public ProductListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_list, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        this.init();
        return view;

    }

    private void init() {
        this.configureToolBar();
        this.configureRecyclerView();
        this.configureViewModel();
        this.getProductList();

    }

    // -------------
    // Configuration
    // -------------

    private void configureViewModel() {
        ViewModelFactory modelFactory = Injection.provideViewModelFactory(getContext());
        this.mProductViewModel = ViewModelProviders.of(this, modelFactory).get(ProductViewModel.class);
    }

    private void configureRecyclerView() {
        this.mAdapter = new MyProductAdapter(Glide.with(this));
        this.mRecyclerView.setAdapter(mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.configureItemClick();

    }

    private void configureToolBar() {
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(mToolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setTitle(getString(R.string.title_history));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_search:
                Toast.makeText(getContext(), "search", Toast.LENGTH_SHORT).show();
                this.prepareEntryAnimation();
                break;
            case R.id.menu_exit:
                Toast.makeText(getContext(), "quit", Toast.LENGTH_SHORT).show();
                this.prepareExitAnimation();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem exit = menu.findItem(R.id.menu_exit);
        MenuItem search = menu.findItem(R.id.menu_search);

        search.setOnMenuItemClickListener(item -> {
            exit.setVisible(true);
            return false;
        });

        exit.setOnMenuItemClickListener(item -> {
            exit.setVisible(false);
            return false;
        });

    }

    // -------------
    // Action
    // -------------

    private void configureItemClick() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_products_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Toast.makeText(getContext(), "Click on position: " + position, Toast.LENGTH_SHORT).show();
                    //TODO
                });
    }


    // -------------
    // UI
    // -------------

    private void getProductList() {
        this.mProductViewModel.getAllProducts().observe(this, this::updateProductList);
    }

    private void updateProductList(List<MyProduct> myProducts) {
        this.mEmptyMessage.setVisibility(myProducts.size() == 0 ? View.VISIBLE : View.GONE);
        this.mAdapter.updateData(myProducts);
    }

    private void prepareEntryAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_X, mCardView.getWidth(), 0);
        animator.setDuration(600);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
    }

    private void prepareExitAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mCardView, View.TRANSLATION_X, 0, mCardView.getWidth());
        animator.setDuration(600);
        animator.setInterpolator(new AnticipateOvershootInterpolator());
        animator.start();
    }
}
