package com.example.coingame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class secondpopup extends AppCompatActivity {

    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpopup);

        image = findViewById(R.id.imageView3);
        text = findViewById(R.id.Popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8),(int) (height * 0.5));

        String itemName = getIntent().getStringExtra("name");
        String cost = getIntent().getStringExtra("cost");

        if(itemName.equals("apple")){
            image.setImageResource(R.drawable.apple);
            text.setText("You successfully bought " + itemName + " for "+ cost +" Coins ");
        }else if(itemName.equals("cheese")){
            image.setImageResource(R.drawable.cheese);
            text.setText(" You successfully bought " + itemName + " for " + cost + " Coins" );
        }else if(itemName.equals("cookie")){
            image.setImageDrawable(getResources().getDrawable(R.drawable.cookie));
            text.setText(" You successfully bought " + itemName + " for "+ cost + " Coins ");
        }

    }
}