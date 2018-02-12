package com.example.dellpc.facts.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Balamithra on 2/11/2018.
 */

public class FactsResponse {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("title")
    @Expose
    private String title;

    public List<FactsList> getFactsList() {
        return factsLists;
    }

    public void setCategory(List<FactsList> category) {
        this.factsLists = factsLists;
    }

    @SerializedName("rows")
    private List<FactsList> factsLists = new ArrayList();
}
