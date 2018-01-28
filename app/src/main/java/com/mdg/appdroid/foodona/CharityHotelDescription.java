package com.mdg.appdroid.foodona;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class CharityHotelDescription extends AppCompatActivity {
    public  static String uid;
    public DatabaseReference databaseReference;
    public TextView hotel_desc_name, hotel_desc_addr, hotel_desc_phone;
    public ImageView hotel_desc_pic;
    public List<CharityHotelDescriptionItem> list;
    public HotelDescriptionTextAdapter textAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_hotel_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        list = new ArrayList<>();


        textAdapter = new CharityHotelDescription.HotelDescriptionTextAdapter(CharityHotelDescription.this, list);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            uid = bundle.getString("passuid");

        }

        hotel_desc_name = (TextView)findViewById(R.id.hotel_desc_name);
        hotel_desc_addr = (TextView)findViewById(R.id.hotel_desc_address);
        hotel_desc_phone = (TextView)findViewById(R.id.hotel_desc_phone);
        hotel_desc_pic = (ImageView)findViewById(R.id.hotel_desc_pic);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference description = databaseReference.child("hotel").child("user").child(uid);
        description.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("HotelName").getValue(String.class);
                String address = dataSnapshot.child("Address").getValue(String.class);
                String phone = dataSnapshot.child("Phone").getValue(String.class);
                hotel_desc_name.setText(name);
                hotel_desc_addr.setText(address);
                hotel_desc_phone.setText(phone);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference food = databaseReference.child("hotel").child("upload").child(uid);
        food.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long n = dataSnapshot.getChildrenCount();
                for (int i=1; i<=n; i++){
                    DatabaseReference food_desc = databaseReference.child("hotel").child("upload").child(uid).child("Food option " + i);
                    food_desc.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String food_des = dataSnapshot.child("description").getValue(String.class);
                            String food_qty = dataSnapshot.child("quantity").getValue(String.class);
                            String food_exp = dataSnapshot.child("expiry").getValue(String.class);
                            final CharityHotelDescriptionItem m = new CharityHotelDescriptionItem();
                            m.setFood_name(food_des);
                            m.setFood_qty(food_qty);
                            m.setFood_expry(food_exp);
                            list.add(m);
                            textAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        RecyclerView.LayoutManager recycler = new LinearLayoutManager(CharityHotelDescription.this);
        recyclerView.setLayoutManager(recycler);
        recyclerView.setAdapter(textAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public class HotelDescriptionTextAdapter extends RecyclerView.Adapter<com.mdg.appdroid.foodona.CharityHotelDescription.HotelDescriptionTextAdapter.HotelDescriptionTextHolder> {
        Context context;
        List<CharityHotelDescriptionItem> list;
        CharityHotelDescriptionItem mylist;

        public HotelDescriptionTextAdapter(Context context, List<CharityHotelDescriptionItem> list) {

            this.context = context;
            this.list = list;
        }

        @Override
        public com.mdg.appdroid.foodona.CharityHotelDescription.HotelDescriptionTextAdapter.HotelDescriptionTextHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_food_item, parent, false);
            com.mdg.appdroid.foodona.CharityHotelDescription.HotelDescriptionTextAdapter.HotelDescriptionTextHolder textHolder = new com.mdg.appdroid.foodona.CharityHotelDescription.HotelDescriptionTextAdapter.HotelDescriptionTextHolder(view);
            return textHolder;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(final com.mdg.appdroid.foodona.CharityHotelDescription.HotelDescriptionTextAdapter.HotelDescriptionTextHolder holder, int position) {

            mylist = list.get(position);
            holder.food_name.setText(mylist.getFood_name());
            holder.food_name.setTag(position);
            holder.food_qty.setText("Quantity: " + mylist.getFood_qty());
            holder.food_qty.setTag(position);
            holder.food_expry.setText("Expiry Date: " + mylist.getFood_expry());
            holder.food_expry.setTag(position);
            holder.food_pic.setImageAlpha(mylist.getFood_pic_url());
            holder.food_pic.setTag(position);
        }

        @Override
        public int getItemCount() {
            int arr = 0;
            try {
                if (list.size() == 0) {
                    arr = 0;
                } else {
                    arr = list.size();
                }
            } catch (Exception e) {

            }

            Log.d(TAG, "getItemCount: " + String.valueOf(arr));
            return arr;

        }


        public class HotelDescriptionTextHolder extends RecyclerView.ViewHolder {
            public TextView food_name, food_qty, food_expry;
            public ImageView food_pic;

            public HotelDescriptionTextHolder(View itemView) {
                super(itemView);
                food_name = itemView.findViewById(R.id.food_name);
                food_qty = itemView.findViewById(R.id.food_qty);
                food_expry = itemView.findViewById(R.id.food_expry);
                food_pic = itemView.findViewById(R.id.food_pic);
            }
        }
    }
}
