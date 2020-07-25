package com.example.coingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnSearch;
    private Button btnBorrow;
    private Button btnBalance;
    private Button btnShop;
    private Button btnBuy;
    private EditText Text;
    private TextView textview;

    int Balance = 0;
    int[] Integerarray = new int[] {100,250,300};
    String[] strs = {"Couch", "Discord", "Tree", "Dog", "Car", "Pocket", "Coat", "Attic", "Kitchen", "Hospital"};
    List<String> place = Arrays.asList(strs);

    public void searchRandom(View view){



        ArrayList<Integer> num = new ArrayList<>();

        for(int n = 0; n <= 200; n++){
            num.add(n);
        }

        Random random = new Random();
        int position = random.nextInt(num.size());
        int c = num.get(position);

        Balance = Balance + c;

        Toast.makeText(getApplicationContext(), "Rupees " + c, Toast.LENGTH_LONG).show();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.button1);
        btnBorrow = findViewById(R.id.button2);
        btnBalance = findViewById(R.id.button3);
        btnShop = findViewById(R.id.button4);
        btnBuy = findViewById(R.id.button5);
        Text = findViewById(R.id.editText);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                for(int j=0; j<4;j++) {
                    Random textrandom = new Random();
                    int[] positiontext = new int[];
                    positiontext.add(textrandom.nextInt(strs.length - 1));
                }
                textview.setText("Where do you want to search? " + strs[positiontext]);
                */

                ArrayList<Integer> num = new ArrayList<>();

                for(int n = 0; n <= 200; n++){
                    num.add(n);
                }

                Random random = new Random();
                int position = random.nextInt(num.size());
                int c = num.get(position);

                Balance = Balance + c;

                Toast.makeText(getApplicationContext(), "Rupees " + c, Toast.LENGTH_LONG).show();

            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> num = new ArrayList<>();

                for(int n = 0; n <= 100; n++){
                    num.add(n);
                }

                Random random = new Random();
                int position = random.nextInt(num.size());
                int c = num.get(position);

                Balance = Balance + c;

                Toast.makeText(getApplicationContext(), "You Got Rupees " + c, Toast.LENGTH_LONG).show();


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

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Balance == 0){
                    Toast.makeText(getApplicationContext(),"Your balance is Zero", Toast.LENGTH_LONG).show();
                }
                else {
                    int Cost = 0;

                    try {
                        Cost = Integer.parseInt(Text.getText().toString());
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getApplicationContext(), "Please Enter a valid number", Toast.LENGTH_LONG).show();
                    }
                    // if(){
                    if (Balance < Cost) {
                        Toast.makeText(getApplicationContext(), "You do not have Sufficient Balance to make the purchase", Toast.LENGTH_LONG).show();
                    } else {
                        Balance = Balance - Cost;
                        Toast.makeText(getApplicationContext(), "Purchase successful", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }



}