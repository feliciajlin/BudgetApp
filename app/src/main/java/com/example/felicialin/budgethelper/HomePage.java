package com.example.felicialin.budgethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.models.Purchase;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class HomePage extends AppCompatActivity implements View.OnClickListener{
    Button categoryButton;
    Button historyButton;
    List<Account> customerAccounts;
    HashMap<String, List> accountPurchases;
    NessieClient client;

    List<Purchase> purchases; //helper class variable for getPurchasesFromAccounts to get rid of scope issues

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = NessieClient.getInstance("d9d2932feb9206207df39b565750ceb4");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        categoryButton = (Button) findViewById(R.id.category_button);
        categoryButton.setOnClickListener(this);

        historyButton = (Button) findViewById(R.id.history_button);
        historyButton.setOnClickListener(this);

        getAccounts(LoginActivity.currentCustomer);
        //getPurchasesFromAccounts(customerAccounts);
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

    /*
        Returns all accounts associated with specific customer.
     */
    public void getAccounts(Customer customer){
        client.ACCOUNT.getCustomerAccounts(customer.getId(), new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                customerAccounts = (List<Account>) result;
            }

            @Override
            public void onFailure(NessieError error) {
                customerAccounts = new ArrayList<Account>();
            }
        });
    }

    public void getPurchasesFromAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            client.PURCHASE.getPurchasesByAccount(account.getAccountNumber(), new NessieResultsListener() {
                @Override
                public void onSuccess(Object result) {
                    purchases = (List<Purchase>) result;
                }

                @Override
                public void onFailure(NessieError error) {
                    purchases = new ArrayList<Purchase>();
                }
            });

            accountPurchases.put(account.getAccountNumber(), purchases);
        }


    }
}
