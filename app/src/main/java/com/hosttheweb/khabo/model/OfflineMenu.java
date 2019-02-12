package com.hosttheweb.khabo.model;

public class OfflineMenu {
    int id;
    String menuName;
    int menuImage;

    public int getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public OfflineMenu(int id, String menuName, int menuImage) {
        this.id = id;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }
}
