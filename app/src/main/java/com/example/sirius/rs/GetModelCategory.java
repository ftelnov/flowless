package com.example.sirius.rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetModelCategory {

    @SerializedName("Recipe_id")
    @Expose
    private Integer recipeId;
    @SerializedName("Recipe_title")
    @Expose
    private String recipeTitle;
    @SerializedName("Time_of_cooking")
    @Expose
    private Integer timeOfCooking;
    @SerializedName("Recipe_image")
    @Expose
    private String recipeImage;

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public GetModelCategory withRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public GetModelCategory withRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
        return this;
    }

    public Integer getTimeOfCooking() {
        return timeOfCooking;
    }

    public void setTimeOfCooking(Integer timeOfCooking) {
        this.timeOfCooking = timeOfCooking;
    }

    public GetModelCategory withTimeOfCooking(Integer timeOfCooking) {
        this.timeOfCooking = timeOfCooking;
        return this;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public GetModelCategory withRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
        return this;
    }


}
