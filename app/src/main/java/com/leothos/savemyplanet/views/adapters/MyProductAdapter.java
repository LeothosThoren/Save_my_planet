package com.leothos.savemyplanet.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.data.database.entities.MyProduct;

import java.util.ArrayList;
import java.util.List;

public class MyProductAdapter extends RecyclerView.Adapter<MyProductViewHolder> {

    private List<MyProduct> mProductList;
    private RequestManager mGlide;
   private Listener mListener;

    public MyProductAdapter(RequestManager glide, Listener listener) {
        this.mProductList = new ArrayList<>();
        this.mGlide = glide;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_products_item, parent, false);

        return new MyProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProductViewHolder myProductViewHolder, int position) {
        myProductViewHolder.updateProductItem(this.mProductList.get(position), this.mGlide, this.mListener);
    }

    @Override
    public int getItemCount() {
        return this.mProductList.size();
    }

    //To retrieve data at i position
    public MyProduct getSingleProduct(int pos) {
        return this.mProductList.get(pos);
    }

    public void updateData(List<MyProduct> productList) {
        this.mProductList = productList;
        this.notifyDataSetChanged();
    }

    // Interface
    public interface Listener {
        void onPictureClicked(int position);
    }
}
