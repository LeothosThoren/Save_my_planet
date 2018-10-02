package com.leothos.savemyplanet.controlers.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.message_empty)
    TextView mEmptyMessage;
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

        this.init();
        return view;

    }

    private void init() {
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

    // -------------
    // Action
    // -------------

    private void configureItemClick() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_products_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Toast.makeText(getContext(), "Click on position: " + position, Toast.LENGTH_SHORT).show();

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


}
