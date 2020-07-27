package com.example.coingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    //All Variable Declarations
    private Button btnSearch;
    private Button btnBorrow;
    private Button btnShop;
    private Button Facts;
    private Button Go;
    private EditText Text;
    private TextView textview;
    private TextView DisplayBal;


    int Flag = 0;
    int Balance = 0;
    int position = 0;
    int[] storeValue = new int[] {2600,10000,1000};
    String[] store = {"apple","cheese","cookie"};
    String[] strs = {" couch", " discord", " tree", " dog", " car", " pocket", " coat", " attic", " kitchen", " hospital"};
    String str = "";
    String Fact = "";
    boolean isSearched = false;
    boolean isBorrow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.button1);
        btnBorrow = findViewById(R.id.button2);
        btnShop = findViewById(R.id.button4);
        Go = findViewById(R.id.button5);
        textview = findViewById(R.id.textView);
        Text = findViewById(R.id.editText);
        Facts = findViewById(R.id.buttonfact);
        DisplayBal = findViewById(R.id.textdisplaybal);



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                                                        //SEARCH Button
                Go.setVisibility(View.VISIBLE);
                if(!isSearched) {
                    str = "";
                    isSearched = true;
                    delaySearch();
                    for (int j = 0; j < 3; j++) {
                        Random textrandom = new Random();
                        int positiontext = textrandom.nextInt(strs.length);
                        str = str + "," + strs[positiontext];
                    }
                    textview.setText("Where do you want to search " + str + " ?");
                    String c = Text.getText().toString().trim().toLowerCase();
                    if(isSearchItem(c)) {
                        Check(c,0);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Please Enter a Value",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Please wait for sometime",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {                                   //BORROW Button
            @Override
            public void onClick(View view) {
                if(!isBorrow) {
                    isBorrow = true;
                    delayBorrow();
                    String c = "Game";
                    Check(c,1);
                }else{
                    Toast.makeText(MainActivity.this,"Try after some time.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShop.setOnClickListener(new View.OnClickListener() {                                     //SHOP Button
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Pop.class));

            }
        });

        Go.setOnClickListener(new View.OnClickListener() {                                          //GO Button
            @Override
            public void onClick(View view) {

                String userInput = Text.getText().toString().trim().toLowerCase();
                boolean isPresent = isSearchItem(userInput);

                if (userInput.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter an item or place", Toast.LENGTH_SHORT).show();
                } else {
                        if (isPresent) {
                            int Cost = storeValue[position];

                            if (Balance < Cost) {
                                Toast.makeText(getApplicationContext(), "You do not have Sufficient Balance to make the purchase", Toast.LENGTH_LONG).show();
                            } else {
                                Balance = Balance - Cost;
                                Toast.makeText(getApplicationContext(), "Purchase successful", Toast.LENGTH_LONG).show();
                                Text.setText(" ");
                                DisplayBal.setText(String.valueOf(Balance));
                                Intent intent = new Intent(MainActivity.this,secondpopup.class);
                                intent.putExtra("name",userInput);
                                intent.putExtra("cost",String.valueOf(Cost));
                                startActivity(intent);
                            }
                        } else {
                            if (str.contains(userInput)) {
                                if (!isSearched) {

                                    Check(userInput,0);
                                } else {
                                    Toast.makeText(MainActivity.this, "Please wait for sometime", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Enter a valid item", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        });

        Facts.setOnClickListener(new View.OnClickListener() {                                       //FACT Button
            @Override
            public void onClick(View view) {
                if (Flag == 0) {
                    Fact = getString(R.string.Fact1);
                    textview.setText(Fact);
                    Flag = 1;
                }else if(Flag == 1){
                    Fact = getString(R.string.Fact2);
                    textview.setText(Fact);
                    Flag = 2;
                }else{
                    Fact = getString(R.string.Fact3);
                    textview.setText(Fact);
                    Flag = 0;
                }
            }
        });
    }

    public void Click(View view){
        startActivity(new Intent(MainActivity.this, Instructions.class));
    }


    public boolean isSearchItem(String userInput){                                                  //Searches if item entered is in the shop

        for(int i = 0;i < store.length; i++){
            if(store[i].toLowerCase().equals(userInput)){
                position = i;
                return true;
            }
        }
        return false;

    }

    public void Check(String item, int Flag){
        String Checkeditem = item;

        if(Flag == 0) {
            addBalance(Checkeditem);
        }else if(Flag == 1){
            addBalance(Checkeditem);
        }

    }

    public void addBalance( String item){                                                           //Function which adds the balance from searching and borrowing

        ArrayList<Integer> num = new ArrayList<>();

        for(int n = 0; n <= 200; n++){
            num.add(n);
        }

        Random random = new Random();
        int position = random.nextInt(num.size());
        int c = num.get(position);

        Balance = Balance + c;

        Toast.makeText(getApplicationContext(), "You got " + c + " Coins in " + item , Toast.LENGTH_LONG).show();
        Text.setText(" ");
        textview.setText(" ");
        DisplayBal.setText(String.valueOf(Balance));
    }

    public void delaySearch(){                                                                      //Delays the user from Searching too frequently
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isSearched = false;
            }
        },5000);
    }

    public void delayBorrow(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isBorrow = false;
            }
        },7000);
    }

}