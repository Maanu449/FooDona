package com.mdg.appdroid.foodona;


/**
 * Created by suyash on 1/20/18.
 */

public class CharityHotelDescriptionItem {
    public String food_name,food_qty,food_expry;
    public int food_pic_url;

    public CharityHotelDescriptionItem(){

    }

    public CharityHotelDescriptionItem(String food_name, String food_qty, String food_expry, int food_pic_url) {
        this.food_name = food_name;
        this.food_qty = food_qty;
        this.food_expry = food_expry;
        this.food_pic_url = food_pic_url;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_qty() {
        return food_qty;
    }

    public void setFood_qty(String food_qty) {
        this.food_qty = food_qty;
    }

    public String getFood_expry() {
        return food_expry;
    }

    public void setFood_expry(String food_expry) {
        this.food_expry = food_expry;
    }

    public int getFood_pic_url() {
        return food_pic_url;
    }

    public void setFood_pic_url(int food_pic_url) {
        this.food_pic_url = food_pic_url;
    }
}


