package com.tlc.dinamorukural;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.wait(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);

        setContentView(R.layout.activity_splash_screen);
    }
}
