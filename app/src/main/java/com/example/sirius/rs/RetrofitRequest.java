package com.example.sirius.rs;

import android.app.Application;

import Api.AddAllergenApi;
import Api.AddToFavApi;
import Api.AddToMenuApi;
import Api.AuthApi;
import Api.CategoryApi;
import Api.DelFromAllergenApi;
import Api.DeleteFromFavApi;
import Api.FavouriteRecipes;
import Api.GetFoodApi;
import Api.GetMenuApi;
import Api.RecipeApi;
import Api.RecipeByParamApi;
import Api.RegisterApi;
import Api.RmFromMenuApi;
import Api.UserAllergensApi;
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
    private static AddAllergenApi addAllergenApi;
    private static DelFromAllergenApi delFromAllergenApi;
    private static UserAllergensApi userAllergensApi;
    private static RmFromMenuApi rmFromMenuApi;
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
        addAllergenApi = retrofit.create(AddAllergenApi.class);
        delFromAllergenApi = retrofit.create(DelFromAllergenApi.class);
        userAllergensApi = retrofit.create(UserAllergensApi.class);
        rmFromMenuApi = retrofit.create(RmFromMenuApi.class);
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

    public static DeleteFromFavApi getDeleteFromFavApi() {
        return deleteFromFavApi;
    }

    public static GetFoodApi getFoodApi() {
        return getFoodApi;
    }

    public static AddAllergenApi addAllergenApi() {
        return addAllergenApi;
    }

    public static DelFromAllergenApi dellAllergenApi() {
        return delFromAllergenApi;
    }

    public static UserAllergensApi getUserAllergensApi() {
        return userAllergensApi;
    }

    public static RmFromMenuApi rmFromMenuApi() {
        return rmFromMenuApi;
    }
}