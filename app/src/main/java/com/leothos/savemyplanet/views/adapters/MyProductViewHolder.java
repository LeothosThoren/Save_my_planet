package com.leothos.savemyplanet.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.leothos.savemyplanet.R;
import com.leothos.savemyplanet.views.adapters.MyProductAdapter;
import com.leothos.savemyplanet.data.database.entities.MyProduct;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.item_product_name)
    TextView productName;
    @BindView(R.id.item_product_brand)
    TextView productBrand;
    @BindView(R.id.item_product_quantity)
    TextView productQuantity;
    @BindView(R.id.item_product_quality)
    TextView productQuality;
    @BindView(R.id.item_product_picture)
    ImageView productPicture;
    private WeakReference<MyProductAdapter.Listener> mListenerWeakReference;

    public MyProductViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void updateProductItem(MyProduct myProduct, RequestManager glide, MyProductAdapter.Listener listener) {
        this.productName.setText(myProduct.getProductName());
        this.productBrand.setText(myProduct.getProductBrand());
        this.productQuantity.setText(myProduct.getQuantity());
        if (myProduct.getUrlPicture() != null) {
            glide.load(myProduct.getUrlPicture()).apply(RequestOptions.centerCropTransform()).into(this.productPicture);
        }

        switch (myProduct.getPalmOilIndicator()) {
            case 0: //No palm Oil
                this.productQuality.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_indicator_green, 0, 0, 0);
                this.productQuality.setText(R.string.no_palm_oil);
                break;
            case 1: //Contain palm oil
                this.productQuality.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_indicator_red, 0, 0, 0);
                this.productQuality.setText(R.string.contain_palm_oil);
                break;
            case 2: //May contain palm oil
                this.productQuality.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_indicator_orange, 0, 0, 0);
                this.productQuality.setText(R.string.may_contain_palm_oil);
                break;
            default:
                this.productQuality.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_indicator_orange, 0, 0, 0);
                this.productQuality.setText(R.string.not_specified);
                break;
        }

        // Handle click on picture
        this.productPicture.setOnClickListener(this);
        this.mListenerWeakReference = new WeakReference<>(listener);

    }

    @Override
    public void onClick(View v) {
        MyProductAdapter.Listener callback = mListenerWeakReference.get();
        if (callback != null) callback.onPictureClicked(getAdapterPosition());
    }
}
