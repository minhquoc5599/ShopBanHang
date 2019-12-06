package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

public class PersonalActivity extends AppCompatActivity {

    Toolbar toolbarPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        toolbarPersonal = findViewById(R.id.toolbarPersonal);

        actionBar();

    }

    private void actionBar()
    {

        setSupportActionBar(toolbarPersonal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPersonal.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
