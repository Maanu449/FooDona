package com.mdg.appdroid.foodona;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by ktubuntu on 27/1/18.
 */

public class Hotel_SignUP extends Fragment {

    private ImageButton addHotelLocn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab1_sign_up,null);



        return view;
    }
}
