
package com.leothos.savemyplanet.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("image_front_url")
    @Expose
    private String imageFrontUrl;
    @SerializedName("stores")
    @Expose
    private String stores;
    @SerializedName("origins")
    @Expose
    private String origins;
    @SerializedName("generic_name_fr")
    @Expose
    private String genericNameFr;
    @SerializedName("countries")
    @Expose
    private String countries;
    @SerializedName("image_front_small_url")
    @Expose
    private String imageFrontSmallUrl;
    @SerializedName("nutriments")
    @Expose
    private Nutriments nutriments;
    @SerializedName("expiration_date")
    @Expose
    private String expirationDate;
    @SerializedName("generic_name")
    @Expose
    private String genericName;
    @SerializedName("nutrient_levels")
    @Expose
    private NutrientLevels nutrientLevels;
    @SerializedName("no_nutrition_data")
    @Expose
    private String noNutritionData;
    @SerializedName("ingredients_from_palm_oil_n")
    @Expose
    private Integer ingredientsFromPalmOilN;
    @SerializedName("created_t")
    @Expose
    private Integer createdT;
    @SerializedName("sortkey")
    @Expose
    private Integer sortkey;
    @SerializedName("image_nutrition_small_url")
    @Expose
    private String imageNutritionSmallUrl;
    @SerializedName("image_small_url")
    @Expose
    private String imageSmallUrl;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("image_ingredients_thumb_url")
    @Expose
    private String imageIngredientsThumbUrl;
    @SerializedName("image_thumb_url")
    @Expose
    private String imageThumbUrl;
    @SerializedName("fruits-vegetables-nuts_100g_estimate")
    @Expose
    private Integer fruitsVegetablesNuts100gEstimate;
    @SerializedName("interface_version_modified")
    @Expose
    private String interfaceVersionModified;
    @SerializedName("nutrition_grades")
    @Expose
    private String nutritionGrades;
    @SerializedName("nutrition_grade_fr")
    @Expose
    private String nutritionGradeFr;
    @SerializedName("brands")
    @Expose
    private String brands;
    @SerializedName("image_front_thumb_url")
    @Expose
    private String imageFrontThumbUrl;
    @SerializedName("ingredients_text_fr")
    @Expose
    private String ingredientsTextFr;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("ingredients_text_with_allergens_fr")
    @Expose
    private String ingredientsTextWithAllergensFr;
    @SerializedName("ingredients_tags")
    @Expose
    private List<String> ingredientsTags = null;
    @SerializedName("image_ingredients_url")
    @Expose
    private String imageIngredientsUrl;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("product_name_fr")
    @Expose
    private String productNameFr;
    @SerializedName("categories")
    @Expose
    private String categories;
    @SerializedName("max_imgid")
    @Expose
    private String maxImgid;
    @SerializedName("traces")
    @Expose
    private String traces;
    @SerializedName("image_nutrition_thumb_url")
    @Expose
    private String imageNutritionThumbUrl;
    @SerializedName("ingredients_from_or_that_may_be_from_palm_oil_n")
    @Expose
    private Integer ingredientsFromOrThatMayBeFromPalmOilN;
    @SerializedName("ingredients_that_may_be_from_palm_oil_n")
    @Expose
    private Integer ingredientsThatMayBeFromPalmOilN;

    public String getImageFrontUrl() {
        return imageFrontUrl;
    }

    public void setImageFrontUrl(String imageFrontUrl) {
        this.imageFrontUrl = imageFrontUrl;
    }

    public String getStores() {
        return stores;
    }

    public void setStores(String stores) {
        this.stores = stores;
    }

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getGenericNameFr() {
        return genericNameFr;
    }

    public void setGenericNameFr(String genericNameFr) {
        this.genericNameFr = genericNameFr;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getImageFrontSmallUrl() {
        return imageFrontSmallUrl;
    }

    public void setImageFrontSmallUrl(String imageFrontSmallUrl) {
        this.imageFrontSmallUrl = imageFrontSmallUrl;
    }

    public Nutriments getNutriments() {
        return nutriments;
    }

    public void setNutriments(Nutriments nutriments) {
        this.nutriments = nutriments;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public NutrientLevels getNutrientLevels() {
        return nutrientLevels;
    }

    public void setNutrientLevels(NutrientLevels nutrientLevels) {
        this.nutrientLevels = nutrientLevels;
    }

    public String getNoNutritionData() {
        return noNutritionData;
    }

    public void setNoNutritionData(String noNutritionData) {
        this.noNutritionData = noNutritionData;
    }

    public Integer getIngredientsFromPalmOilN() {
        return ingredientsFromPalmOilN;
    }

    public void setIngredientsFromPalmOilN(Integer ingredientsFromPalmOilN) {
        this.ingredientsFromPalmOilN = ingredientsFromPalmOilN;
    }

    public Integer getCreatedT() {
        return createdT;
    }

    public void setCreatedT(Integer createdT) {
        this.createdT = createdT;
    }

    public Integer getSortkey() {
        return sortkey;
    }

    public void setSortkey(Integer sortkey) {
        this.sortkey = sortkey;
    }

    public String getImageNutritionSmallUrl() {
        return imageNutritionSmallUrl;
    }

    public void setImageNutritionSmallUrl(String imageNutritionSmallUrl) {
        this.imageNutritionSmallUrl = imageNutritionSmallUrl;
    }

    public String getImageSmallUrl() {
        return imageSmallUrl;
    }

    public void setImageSmallUrl(String imageSmallUrl) {
        this.imageSmallUrl = imageSmallUrl;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageIngredientsThumbUrl() {
        return imageIngredientsThumbUrl;
    }

    public void setImageIngredientsThumbUrl(String imageIngredientsThumbUrl) {
        this.imageIngredientsThumbUrl = imageIngredientsThumbUrl;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public Integer getFruitsVegetablesNuts100gEstimate() {
        return fruitsVegetablesNuts100gEstimate;
    }

    public void setFruitsVegetablesNuts100gEstimate(Integer fruitsVegetablesNuts100gEstimate) {
        this.fruitsVegetablesNuts100gEstimate = fruitsVegetablesNuts100gEstimate;
    }

    public String getInterfaceVersionModified() {
        return interfaceVersionModified;
    }

    public void setInterfaceVersionModified(String interfaceVersionModified) {
        this.interfaceVersionModified = interfaceVersionModified;
    }

    public String getNutritionGrades() {
        return nutritionGrades;
    }

    public void setNutritionGrades(String nutritionGrades) {
        this.nutritionGrades = nutritionGrades;
    }

    public String getNutritionGradeFr() {
        return nutritionGradeFr;
    }

    public void setNutritionGradeFr(String nutritionGradeFr) {
        this.nutritionGradeFr = nutritionGradeFr;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getImageFrontThumbUrl() {
        return imageFrontThumbUrl;
    }

    public void setImageFrontThumbUrl(String imageFrontThumbUrl) {
        this.imageFrontThumbUrl = imageFrontThumbUrl;
    }

    public String getIngredientsTextFr() {
        return ingredientsTextFr;
    }

    public void setIngredientsTextFr(String ingredientsTextFr) {
        this.ingredientsTextFr = ingredientsTextFr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIngredientsTextWithAllergensFr() {
        return ingredientsTextWithAllergensFr;
    }

    public void setIngredientsTextWithAllergensFr(String ingredientsTextWithAllergensFr) {
        this.ingredientsTextWithAllergensFr = ingredientsTextWithAllergensFr;
    }

    public List<String> getIngredientsTags() {
        return ingredientsTags;
    }

    public void setIngredientsTags(List<String> ingredientsTags) {
        this.ingredientsTags = ingredientsTags;
    }

    public String getImageIngredientsUrl() {
        return imageIngredientsUrl;
    }

    public void setImageIngredientsUrl(String imageIngredientsUrl) {
        this.imageIngredientsUrl = imageIngredientsUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNameFr() {
        return productNameFr;
    }

    public void setProductNameFr(String productNameFr) {
        this.productNameFr = productNameFr;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMaxImgid() {
        return maxImgid;
    }

    public void setMaxImgid(String maxImgid) {
        this.maxImgid = maxImgid;
    }

    public String getTraces() {
        return traces;
    }

    public void setTraces(String traces) {
        this.traces = traces;
    }

    public String getImageNutritionThumbUrl() {
        return imageNutritionThumbUrl;
    }

    public void setImageNutritionThumbUrl(String imageNutritionThumbUrl) {
        this.imageNutritionThumbUrl = imageNutritionThumbUrl;
    }

    public Integer getIngredientsFromOrThatMayBeFromPalmOilN() {
        return ingredientsFromOrThatMayBeFromPalmOilN;
    }

    public void setIngredientsFromOrThatMayBeFromPalmOilN(Integer ingredientsFromOrThatMayBeFromPalmOilN) {
        this.ingredientsFromOrThatMayBeFromPalmOilN = ingredientsFromOrThatMayBeFromPalmOilN;
    }

    public Integer getIngredientsThatMayBeFromPalmOilN() {
        return ingredientsThatMayBeFromPalmOilN;
    }

    public void setIngredientsThatMayBeFromPalmOilN(Integer ingredientsThatMayBeFromPalmOilN) {
        this.ingredientsThatMayBeFromPalmOilN = ingredientsThatMayBeFromPalmOilN;
    }

}
