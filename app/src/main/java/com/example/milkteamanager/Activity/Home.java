package com.example.milkteamanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.milkteamanager.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayout product= findViewById(R.id.product);
        LinearLayout warehouse= findViewById(R.id.warehouse);
        LinearLayout member= findViewById(R.id.member);
        LinearLayout account= findViewById(R.id.accountmanager);
        LinearLayout sta= findViewById(R.id.statistical);
        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Home.this, Procduct.class);
                startActivity(i);
                finish();
            }
        });
        warehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Home.this, Warehouse.class);
                startActivity(i);
                finish();
            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Home.this, DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Home.this, SaleManager.class);
                startActivity(i);
                finish();
            }
        });
        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Home.this, Statictical.class);
                startActivity(i);
                finish();
            }
        });

    }
}
