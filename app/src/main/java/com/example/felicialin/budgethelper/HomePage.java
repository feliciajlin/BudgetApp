package com.example.felicialin.budgethelper;

import android.graphics.Color;
import android.util.Log;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    Button categoryButton;
    Button historyButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NessieClient client = NessieClient.getInstance("d9d2932feb9206207df39b565750ceb4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        categoryButton = (Button) findViewById(R.id.category_button);
        categoryButton.setOnClickListener(this);

        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);

        getAccounts(LoginActivity.currentCustomer);


        // Create some chart
        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(8f, 0));
        yvalues.add(new Entry(15f, 1));
        yvalues.add(new Entry(12f, 2));
        yvalues.add(new Entry(25f, 3));
        yvalues.add(new Entry(23f, 4));
        yvalues.add(new Entry(17f, 5));
        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");

        ArrayList<String> categories = new ArrayList<String>();
        addCategories(categories);

        PieData data = new PieData(categories, dataSet);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.DKGRAY);

        pieChart.animateXY(1400, 1400);
    }

    public void addCategories(ArrayList<String> cats) {
        // TODO: Change types
        cats.add("Food");
        cats.add("Clothing");
        cats.add("March");
        cats.add("April");
        cats.add("May");
        cats.add("June");
    }

    @Override
    public void onClick(View view) {
        //categoryButton.setFocusable(!categoryButton.isFocusableInTouchMode());
        if (view.equals(categoryButton)) {
            historyButton.setSelected(false);
            view.setSelected(true);
        } else {
            categoryButton.setSelected(false);
            view.setSelected(true);
        }
    }

    public void getAccounts(Customer customer){

    }
}