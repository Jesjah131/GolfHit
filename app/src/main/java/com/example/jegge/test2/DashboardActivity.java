package com.example.jegge.test2;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.realm.implementation.RealmBarDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class DashboardActivity extends AppCompatActivity {

    BarChart barChart;
    Button btnViewAll;
    DatabaseHelper myDb;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myDb = new DatabaseHelper(this);
        btnViewAll = (Button)findViewById(R.id.btnShowAllData);
        viewAll();

        realm = Realm.getDefaultInstance();

        barChart = (BarChart) findViewById(R.id.bargraph);

/*
        ArrayList<BarEntry> barEntries = new ArrayList<BarEntry>();
                barEntries.add(new BarEntry(0f, 210f));
        barEntries.add(new BarEntry(1f,190f));
        barEntries.add(new BarEntry(2f,170f));
        barEntries.add(new BarEntry(3f,30f));
        barEntries.add(new BarEntry(4f,40f));
        barEntries.add(new BarEntry(5f,50f));
        barEntries.add(new BarEntry(6f,30f));
        barEntries.add(new BarEntry(7f,40f));
        barEntries.add(new BarEntry(8f,50f));
        barEntries.add(new BarEntry(9f,30f));

        final String[] quarters = new String[] { "Driver", "Driver", "Driver", "Driver", "Driver", "Driver", "Driver", "Driver", "Driver", "Driver" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
    }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {  return 0; }
        };
        */

        Realm realm = Realm.getDefaultInstance();

// load your data from Realm.io database
        final RealmResults<Hit> results = realm.where(Hit.class).findAll();



// create a DataSet and specify fields, MPAndroidChart-Realm does the rest
        RealmBarDataSet<Hit> barDataSet = new RealmBarDataSet<Hit>(results, "club", "distance");
        barDataSet.setColors(new int[]{ColorTemplate.rgb("#FF5722"), ColorTemplate.rgb("#03A9F4"), ColorTemplate.rgb("#ffb121"), ColorTemplate.rgb("#a2ff21"), ColorTemplate.rgb("#ff2138"), ColorTemplate.rgb("#2176ff")});
        barDataSet.setLabel("Avstånd sorterat efter klubba");

        ArrayList<IBarDataSet> barDataSets = new ArrayList<IBarDataSet>();
        barDataSets.add(barDataSet);

        BarData barData = new BarData(barDataSets);

        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateY(900, Easing.EasingOption.EaseInOutQuart);

        // X-axeln
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.animateXY(3000, 3000);
        barChart.setHorizontalScrollBarEnabled(true);
        barChart.setDoubleTapToZoomEnabled(true);
        ArrayList<IBarDataSet> dataSets = null;


/*
                        BarData theData = new BarData(barDataSet);
        theData.setBarWidth(0.2f); // set custom bar width
        barChart.setData(theData);
        barChart.setFitBars(true); // make
                barChart.setData(theData);

        // Y-axeln
        YAxis yAxisl = barChart.getAxisLeft();
        yAxisl.setAxisMaxValue(300f);

        YAxis yAxisr = barChart.getAxisRight();
        yAxisr.setDrawLabels(false);
*/
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
                            buffer.append("\nKlubba: " + res.getString(1)+"\n");
                            buffer.append("Avstånd: " + res.getString(2)+"\n");
                            buffer.append("Vind: " + res.getString(3)+"\n");
                            buffer.append("Träff: " + res.getString(4)+"\n----------");
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
