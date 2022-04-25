package com.example.krishiapp2;

public class ItemsModel {
    private String name;
    private String desc;
    private int image;
    private String itemsfilter;

    public ItemsModel(String name, String desc, int image, String itemsfilter) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.itemsfilter = itemsfilter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getItemsfilter() {
        return itemsfilter;
    }

    public void setItemsfilter(String itemsfilter) {
        this.itemsfilter = itemsfilter;
    }
}
