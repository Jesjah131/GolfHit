package com.example.jegge.test2;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    BarChart barChart;
    Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnViewAll = (Button)findViewById(R.id.btnShowAllData);
        viewAll();

/*
        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < myDb.queryYData().size(); i++)
            yVals.add(new BarEntry(myDb.queryYData().get(i), i));

        ArrayList<String> xVals = new ArrayList<String>();
        for(int i = 0; i < myDb.queryXData().size(); i++)
            xVals.add(myDb.queryXData().get(i));

        BarDataSet dataSet = new BarDataSet(yVals, "expense values");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(xVals, dataSet);


        LimitLine line = new LimitLine(12f, "average daily expense");
        line.setTextSize(12f);
        line.setLineWidth(4f);
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.addLimitLine(line);

        barChart.setData(data);
        barChart.setDescription("The expenses chart.");
        barChart.animateY(2000);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(44f,0));
        barEntries.add(new BarEntry(88f,1));
        barEntries.add(new BarEntry(66f,2));
        barEntries.add(new BarEntry(12f,3));
        barEntries.add(new BarEntry(19f,4));
        barEntries.add(new BarEntry(91f,5));
        BarDataSet barDataSet = new BarDataSet(barEntries,"Dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("April");
        theDates.add("May");
        theDates.add("June");
        theDates.add("July");
        theDates.add("August");
        theDates.add("September");

        BarData theData = new BarData(barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);*/
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Error","No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id :" + res.getString(1)+"\n");
                        }

                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
