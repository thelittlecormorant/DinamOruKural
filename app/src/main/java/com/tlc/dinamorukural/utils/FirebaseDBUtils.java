package com.tlc.dinamorukural.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tlc.dinamorukural.ui.dashboard.Kural;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDBUtils {
    final static String TAG = "com.tlc.dinamorukural";

    public static List<Kural> readKuralDB() {
        final List<Kural> myKuralList = new ArrayList<Kural>();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("kural");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                myKuralList.clear();
                for(DataSnapshot value:dataSnapshot.getChildren()){
                    Kural kuralItem=value.getValue(Kural.class);
//                    kuralItem.setNo(Integer.getInteger(value.getKey()));
                    Log.d(TAG,value.toString());
                    Log.d(TAG,kuralItem.getFile());
                    Log.d(TAG,kuralItem.getKural());
                    Log.d(TAG,kuralItem.getMeaning());

                    myKuralList.add(kuralItem);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return myKuralList;
    }

}
