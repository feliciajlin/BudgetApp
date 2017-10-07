package com.example.felicialin.budgethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View getStarted = findViewById(R.id.getStarted);
        getStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
