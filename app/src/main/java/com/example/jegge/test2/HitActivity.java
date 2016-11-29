package com.example.jegge.test2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;


import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;

public class HitActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editClub, editDistance, editWind, editHit;
    Button buttonSaveHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit);

        myDb = new DatabaseHelper(this);

        editClub = (EditText)findViewById(R.id.clubText);
        editDistance = (EditText)findViewById(R.id.distanceText);
        editWind = (EditText)findViewById(R.id.windText);
        editHit = (EditText)findViewById(R.id.hitText);
        buttonSaveHit = (Button)findViewById(R.id.btnSaveHit);
        AddData();
    }

    public void AddData(){
        buttonSaveHit.setOnClickListener(
               new View.OnClickListener(){
                   @Override
                   public void onClick(View v){
                       boolean isInserted = myDb.insertData(editClub.getText().toString(), editDistance.getText().toString(), editWind.getText().toString(), editHit.getText().toString());

                       if(isInserted == true)
                           Toast.makeText(HitActivity.this,"Lyckades!",Toast.LENGTH_LONG).show();

                       else
                           Toast.makeText(HitActivity.this,"Lyckades inte!",Toast.LENGTH_LONG).show();
                   }
               }
        );
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

}
