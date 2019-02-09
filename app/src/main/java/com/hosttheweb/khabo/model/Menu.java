package com.hosttheweb.khabo.model;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("id")
    private int id;

    @SerializedName("menuitem")
    private String menuName;

    @SerializedName("image")
    private String menuImage;

    public Menu(int id, String menuName, String menuImage) {
        this.id = id;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public int getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuImage() {
        return menuImage;
    }
}
