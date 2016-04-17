package com.example.chanti.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by chanti on 09-Feb-16.
 */
public class home extends AppCompatActivity {

    int TAKE_PHOTO_CODE = 25;
    Button p,m;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        p = (Button) findViewById(R.id.takePhoto);
        m = (Button) findViewById(R.id.getLocation);
    }

    public void takePhotoButton(View v) {
        Intent c = new Intent(home.this, Image.class);
        startActivity(c);
    }

    public void getLocationButton(View v) {
        Intent m = new Intent(home.this, map.class);
        startActivity(m);
    }
}
