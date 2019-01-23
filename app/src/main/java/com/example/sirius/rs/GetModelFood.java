package com.example.sirius.rs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetModelFood {

    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("Name")
    @Expose
    public String name;

}