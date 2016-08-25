package com.pordiva.esraeken.efsoretrofit;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pordiva.esraeken.efsoretrofit.constans.constans;
import com.pordiva.esraeken.efsoretrofit.model.user;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    EditText edt1;
    Button button;
    private UserInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(constans.URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        restInterface = restAdapter.create(UserInterface.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ProfileActivity.class);
                i.putExtra("username", edt1.getText().toString());
                startActivity(i);
            }
        });


    }
}
