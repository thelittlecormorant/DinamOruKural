package com.tlc.dinamorukural.ui.dashboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyKuralAdapter extends RecyclerView.Adapter<MyKuralAdapter.ViewHolder>{
    final String TAG = "com.tlc.dinamorukural";
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView kuralNo;
        public TextView kuralName;
        public TextView kuralMeaning;
        public Button play;

        public ViewHolder(View itemView) {
            super(itemView);
            kuralNo=(TextView)itemView.findViewById(R.id.kural_no);
            kuralName=(TextView)itemView.findViewById(R.id.kural_name);
            kuralMeaning=(TextView)itemView.findViewById(R.id.kural_meaning);
            play=(Button)itemView.findViewById(R.id.play);
        }
    }

    private List<Kural> mKurals;
    public MyKuralAdapter(List<Kural> kuralList) {
        mKurals=kuralList;

    }

    @Override
    public MyKuralAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_kural, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyKuralAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Log.d(TAG,mKurals.toString());
        final Kural kuralItem = mKurals.get(position);

        // Set item views based on your views and data model
        TextView tvKuralNo = holder.kuralNo;
        tvKuralNo.setText(kuralItem.getNo());
        TextView tvKuralName = holder.kuralName;
        tvKuralName.setText(kuralItem.getKural());
        TextView tvKuralMeaning = holder.kuralMeaning;
        tvKuralMeaning.setText(kuralItem.getMeaning());

        Button btPlay=holder.play;
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkPlayers(kuralItem.getNo());
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mKurals.size();
    }

    public void linkPlayers(String itemNo){

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathRef=storageRef.child("kural audio/"+itemNo+".mpeg");

        File storagePath = new File(Environment.getExternalStorageDirectory(), "dhinamorukural");
// Create direcorty if not exists
        if(!storagePath.exists()) {
            storagePath.mkdirs();
        }

        final File myFile = new File(storagePath,itemNo+".mpeg");

        pathRef.getFile(myFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                final MediaPlayer mp=new MediaPlayer();
                try{
                    mp.setDataSource(myFile.getPath());
                    mp.prepare();
                }catch(Exception e){e.printStackTrace();}

                mp.start();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                exception.printStackTrace();
            }
        });






    }

}
