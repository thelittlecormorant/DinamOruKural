package com.tlc.dinamorukural.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tlc.dinamorukural.R;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;



    public DashboardViewModel() {
        mText = new MutableLiveData<>();

        // Lookup the recyclerview in activity layout
        
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}