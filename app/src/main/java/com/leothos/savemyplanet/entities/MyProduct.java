package com.leothos.savemyplanet.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity
public class MyProduct {

    @PrimaryKey
    private @NonNull
    String codeId;
    private String productName;
    private String productBrand;
    private String category;
    private String quantity;
    private Integer palmOilIndicator;
    private String scoreGrade;
    private String urlPicture;
    private Date timestamp;

    public MyProduct() {
    }

    public MyProduct(String productName, String productBrand, String category, String urlPicture,
                     String quantity, Integer palmOilIndicator, String scoreGrade, Date timestamp, String codeId) {
        this.codeId = codeId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.category = category;
        this.quantity = quantity;
        this.palmOilIndicator = palmOilIndicator;
        this.scoreGrade = scoreGrade;
        this.timestamp = timestamp;
        this.urlPicture = urlPicture;
    }

    @NonNull
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(@NonNull String codeId) {
        this.codeId = codeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Integer getPalmOilIndicator() {
        return palmOilIndicator;
    }

    public void setPalmOilIndicator(Integer palmOilIndicator) {
        this.palmOilIndicator = palmOilIndicator;
    }

    public String getScoreGrade() {
        return scoreGrade;
    }

    public void setScoreGrade(String scoreGrade) {
        this.scoreGrade = scoreGrade;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
