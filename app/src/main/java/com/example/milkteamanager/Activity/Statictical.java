package com.example.milkteamanager.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.milkteamanager.R;

public class Statictical extends AppCompatActivity {
    Toolbar tbsta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statictical);
        tbsta=(Toolbar)findViewById(R.id.toolbarstatictical);
        ActionToolbar();
    }
    private void ActionToolbar() {
        setSupportActionBar(tbsta);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbsta.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Statictical.this, Home.class);
                startActivity(i);
                finish();
            }
        });
    }
}
