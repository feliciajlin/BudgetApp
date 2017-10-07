package com.example.felicialin.budgethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    Button categoryButton;
    Button historyButton;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NessieClient client = NessieClient.getInstance("d9d2932feb9206207df39b565750ceb4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        categoryButton = (Button) findViewById(R.id.category_button);
        categoryButton.setOnClickListener(this);

        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.history_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        getAccounts(LoginActivity.currentCustomer);

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
