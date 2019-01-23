package com.example.sirius.rs;

import android.app.Application;

import Api.AddToFavApi;
import Api.AddToMenuApi;
import Api.AuthApi;
import Api.CategoryApi;
import Api.DeleteFromFavApi;
import Api.FavouriteRecipes;
import Api.GetFoodApi;
import Api.GetMenuApi;
import Api.RecipeApi;
import Api.RecipeByParamApi;
import Api.RegisterApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRequest extends Application {

    private static CategoryApi categoryApi;
    private static RecipeApi recipeApi;
    private static RecipeByParamApi recipeByParamApi;
    private static RegisterApi registerApi;
    private static AuthApi authApi;
    private static FavouriteRecipes frApi;
    private static AddToFavApi addFrApi;
    private static AddToMenuApi addToMenu;
    private static GetMenuApi getMenuApi;
    private static DeleteFromFavApi deleteFromFavApi;
    private static GetFoodApi getFoodApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://95.163.180.77")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoryApi = retrofit.create(CategoryApi.class);
        recipeApi = retrofit.create(RecipeApi.class);
        recipeByParamApi = retrofit.create(RecipeByParamApi.class);
        registerApi = retrofit.create(RegisterApi.class);
        authApi = retrofit.create(AuthApi.class);
        frApi = retrofit.create(FavouriteRecipes.class);
        addFrApi = retrofit.create(AddToFavApi.class);
        addToMenu = retrofit.create(AddToMenuApi.class);
        getMenuApi = retrofit.create(GetMenuApi.class);
        deleteFromFavApi = retrofit.create(DeleteFromFavApi.class);
        getFoodApi = retrofit.create(GetFoodApi.class);
    }

    public static CategoryApi getCategoryApi() {
        return categoryApi;
    }

    public static RecipeApi getRecipeApi() {
        return recipeApi;
    }

    public static RecipeByParamApi getRecipeByParamApi() {
        return recipeByParamApi;
    }

    public static RegisterApi getRegisterApi() {
        return registerApi;
    }

    public static AuthApi getAuthApi() {
        return authApi;
    }

    public static FavouriteRecipes getFavouriteRecipesApi() {
        return frApi;
    }

    public static AddToFavApi getAddFavouriteRecipeApi() {
        return addFrApi;
    }

    public static AddToMenuApi getAddToMenuApi() {
        return addToMenu;
    }

    public static GetMenuApi getMenuApi() {
        return getMenuApi;
    }

    public static DeleteFromFavApi getDeleteFromFavApi(){
        return deleteFromFavApi;
    }

    public static GetFoodApi getFoodApi(){
        return getFoodApi;
    }
}