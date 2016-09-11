package com.example.jegge.test2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToHit = (Button) findViewById(R.id.newHitButton);
        goToHit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, HitActivity.class);
                startActivity(intent);
            }

        });

        Button goToDashboard = (Button) findViewById(R.id.dashboardButton);
        goToDashboard.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }

        });
    }
}
