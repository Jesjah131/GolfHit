package com.example.jegge.test2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;


import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;

import io.realm.Realm;
import io.realm.RealmResults;

public class HitActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editClub, editDistance, editWind, editHit;
    Button buttonSaveHit;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit);

        myDb = new DatabaseHelper(this);

        editClub = (EditText)findViewById(R.id.editText);
        editDistance = (EditText)findViewById(R.id.distanceText);
        editWind = (EditText)findViewById(R.id.windText);
        editHit = (EditText)findViewById(R.id.hitText);
        buttonSaveHit = (Button)findViewById(R.id.btnSaveHit);
        AddData();

        realm = Realm.getDefaultInstance();
        Realm.getDefaultInstance();
        Realm.init(this);
    }

    public void AddData(){
        buttonSaveHit.setOnClickListener(
               new View.OnClickListener(){
                   @Override
                   public void onClick(View v){
                       final  String height = editClub.getText().toString();
                       final int a = Integer.parseInt(height);

                       save_into_database(a, Integer.parseInt(editDistance.getText().toString().trim()), editWind.getText().toString(), editHit.getText().toString());
                       refresh_views();

                       /*
                       if(isInserted == true)
                           Toast.makeText(HitActivity.this,"Lyckades!",Toast.LENGTH_LONG).show();

                       else
                           Toast.makeText(HitActivity.this,"Lyckades inte!",Toast.LENGTH_LONG).show();
                           */
                   }
               }
        );
    }

    public void refresh_views(){
        RealmResults<Hit> r = realm.where(Hit.class).findAll();
    }

    public void save_into_database(final float club,final float distance,final String wind,final String latestHit){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Hit hit = bgRealm.createObject(Hit.class);
                hit.setClub(club);
                hit.setDistance(distance);
                hit.setWind(wind);
                hit.setHit(latestHit);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                Toast.makeText(HitActivity.this,"Lyckades!",Toast.LENGTH_LONG).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Toast.makeText(HitActivity.this,"Lyckades inte!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

}
