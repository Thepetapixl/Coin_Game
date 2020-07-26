package com.example.coingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private Button btnBorrow;
    private Button btnBalance;
    private Button btnShop;
    private Button Go;
    private EditText Text;
    private TextView textview;


    int Balance = 0;
    int position = 0;
    int[] storeValue = new int[] {100,250,300};
    String[] store = {"apple","cheese","cookie"};
    String[] strs = {" couch", " discord", " tree", " dog", " car", " pocket", " coat", " attic", " kitchen", " hospital"};
    String str = "";
    boolean isSearched = false;

    //private static int SPLASH_TIME_OUT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.button1);
        btnBorrow = findViewById(R.id.button2);
        btnBalance = findViewById(R.id.button3);
        btnShop = findViewById(R.id.button4);
        Go = findViewById(R.id.button5);
        textview = findViewById(R.id.textView);
        Text = findViewById(R.id.editText);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent OpeningWin = new Intent(MainActivity.this, OpeningWinDesign.class);
                startActivity(OpeningWin);
                finish();
            }
        },SPLASH_TIME_OUT);*/

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isSearched) {
                    str = "";
                    for (int j = 0; j < 3; j++) {
                        Random textrandom = new Random();
                        int positiontext = textrandom.nextInt(strs.length);
                        str = str + "," + strs[positiontext];
                    }
                    textview.setText("Where do you want to search " + str + " ?");
                    String c = Text.getText().toString().trim().toLowerCase();
                    if(isSearchItem(c)) {
                        addBalance(1);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Please Enter a Value",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Please wait for sometime",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBorrowed()) {
                    addBalance(1);
                }
            }
        });

        btnBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Your current Balance is " + Balance , Toast.LENGTH_LONG).show();
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Pop.class));


            }
        });

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userInput = Text.getText().toString().trim().toLowerCase();
                boolean isPresent = isSearchItem(userInput);

                if (userInput.isEmpty()) {

                } else {
                    if (str.isEmpty()) {

                    } else {
                        if (isPresent) {
                            int Cost = storeValue[position];

                            if (Balance < Cost) {
                                Toast.makeText(getApplicationContext(), "You do not have Sufficient Balance to make the purchase", Toast.LENGTH_LONG).show();
                            } else {
                                Balance = Balance - Cost;
                                Toast.makeText(getApplicationContext(), "Purchase successful", Toast.LENGTH_LONG).show();
                                Text.setText(" ");
                            }
                        } else {
                            if (str.contains(userInput)) {
                                if (!isSearched) {
                                    addBalance(1);
                                } else {
                                    Toast.makeText(MainActivity.this, "Please wait for sometime", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Enter a valid item", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
            }
        });
    }

    public boolean isSearchItem(String userInput){

        for(int i = 0;i < store.length; i++){
            if(store[i].toLowerCase().equals(userInput)){
                position = i;
                return true;
            }
        }
        return false;

    }

    public void addBalance(int Check){
        isSearched = true;
        if(Check == 1){
            delaySearch();
        }
        ArrayList<Integer> num = new ArrayList<>();

        for(int n = 0; n <= 200; n++){
            num.add(n);
        }

        Random random = new Random();
        int position = random.nextInt(num.size());
        int c = num.get(position);

        Balance = Balance + c;

        Toast.makeText(getApplicationContext(), "Rupees " + c, Toast.LENGTH_LONG).show();

        Text.setText(" ");
        textview.setText(" ");
    }

    public void delaySearch(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isSearched = false;
            }
        },10000);
    }

    public boolean isBorrowed(){
            delaySearch();
            return true;
    }

}