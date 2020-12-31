package com.tlc.dinamorukural.ui.dashboard;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tlc.dinamorukural.R;
import com.tlc.dinamorukural.utils.FirebaseDBUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    final String TAG = "com.tlc.dinamorukural";
    private DashboardViewModel dashboardViewModel;
    RecyclerView rvKuralList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        rvKuralList = (RecyclerView) root.findViewById(R.id.rvContacts);
        // Set layout manager to position the items
        rvKuralList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        readKuralDB();
        return root;
    }


    public void readKuralDB() {
        final List<Kural> mKurals=new ArrayList<Kural>();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("kural");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren()){
                    Kural kuralItem=value.getValue(Kural.class);
                    kuralItem.setNo(value.getKey());
                    Log.d(TAG,value.toString());
                    Log.d(TAG,kuralItem.getFile());
                    Log.d(TAG,kuralItem.getKural());
                    Log.d(TAG,kuralItem.getMeaning());

                    mKurals.add(kuralItem);
                }

                Log.d(TAG,"cpt1");
                final MyKuralAdapter adapter = new MyKuralAdapter(mKurals);
                Log.d(TAG,"cpt2");
                // Attach the adapter to the recyclerview to populate items
                rvKuralList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}