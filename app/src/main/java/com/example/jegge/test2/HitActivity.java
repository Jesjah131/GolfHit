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

    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hit);
    }

    @SuppressWarnings("null")
    public String CreateXMLString() throws IllegalArgumentException, IllegalStateException, IOException
    {
        mEdit = (EditText)findViewById(R.id.clubText);

        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();

        xmlSerializer.setOutput(writer);

        //Start Document
        xmlSerializer.startDocument("UTF-8", true);
        xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
        //Open Tag <file>
        xmlSerializer.startTag("", "file");

        xmlSerializer.startTag("", "something");
        xmlSerializer.attribute("", "ID", "000001");

        xmlSerializer.startTag("", "club");
        xmlSerializer.text(mEdit.getText().toString());
        xmlSerializer.endTag("", "club");

        xmlSerializer.endTag("", "something");



        //end tag <file>
        xmlSerializer.endTag("", "file");
        xmlSerializer.endDocument();

        return writer.toString();
    }

    public void onClickBtn(View v)
    {
        Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }

}
