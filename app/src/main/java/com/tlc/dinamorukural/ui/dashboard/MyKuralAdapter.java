package com.tlc.dinamorukural.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tlc.dinamorukural.R;

import java.util.List;

public class MyKuralAdapter extends RecyclerView.Adapter<MyKuralAdapter.ViewHolder>{

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
    public MyKuralAdapter(List<Kural> contacts) {
        mKurals= contacts;
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
        Kural kuralItem = mKurals.get(position);

        // Set item views based on your views and data model
        TextView tvKuralNo = holder.kuralNo;
        tvKuralNo.setText(kuralItem.getNo());
        TextView tvKuralName = holder.kuralName;
        tvKuralName.setText(kuralItem.getKuralName());
        TextView tvKuralMeaning = holder.kuralMeaning;
        tvKuralMeaning.setText(kuralItem.getKuralMeaning());

        Button btPlay=holder.play;
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DOK","Do nothing for now");
            }
        });

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

}