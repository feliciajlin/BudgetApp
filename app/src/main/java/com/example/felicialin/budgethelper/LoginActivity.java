package com.example.felicialin.budgethelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;
import com.reimaginebanking.api.nessieandroidsdk.requestclients.NessieClient;

import java.util.List;

import static android.view.View.VISIBLE;

/**
 * A login screen
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    List<Customer> customers;
    static Customer currentCustomer;
    EditText mCustomerId;
    NessieClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        client = NessieClient.getInstance("d9d2932feb9206207df39b565750ceb4");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCustomerId = (EditText) findViewById(R.id.username);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mSignInButton.setOnClickListener(this);
    }

    @Override
    public void onClick (View view) {
        findViewById(R.id.indeterminateBar).setVisibility(View.VISIBLE);

        client.CUSTOMER.getCustomers(new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                findViewById(R.id.indeterminateBar).setVisibility(View.INVISIBLE);

                customers = (List<Customer>) result;

                Editable firstName = mCustomerId.getText();
                for (Customer c: customers) {
                    if (c.getFirstName().equals(firstName.toString())) {
                        currentCustomer = c;
                        break;
                    }
                }
                Intent i=new Intent(LoginActivity.this, HomePage.class);
                startActivity(i);
            }

            @Override
            public void onFailure(NessieError error) {
                findViewById(R.id.indeterminateBar).setVisibility(View.INVISIBLE);

                customers = null;
            }
        });


    }
}






