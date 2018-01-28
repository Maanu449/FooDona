package com.mdg.appdroid.foodona;

import android.widget.RatingBar;

/**
 * Created by suyash on 1/20/18.
 */

public class CharityHotelListItem {
    public String hotel_name,hotel_address,hotel_phone;
    public int hotel_pic_url;

    public CharityHotelListItem(){

    }


    public CharityHotelListItem(String hotel_name, String hotel_address, int hotel_pic_url, String hotel_phone) {
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_pic_url = hotel_pic_url;
        this.hotel_phone = hotel_phone;

    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public int getHotel_pic_url() {
        return hotel_pic_url;
    }

    public void setHotel_pic_url(int hotel_pic_url) {
        this.hotel_pic_url = hotel_pic_url;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

}
