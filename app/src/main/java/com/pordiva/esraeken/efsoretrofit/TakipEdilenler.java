package com.pordiva.esraeken.efsoretrofit;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pordiva.esraeken.efsoretrofit.constans.constans;
import com.pordiva.esraeken.efsoretrofit.model.user;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TakipEdilenler extends AppCompatActivity
{
    RecyclerView recyclerView;
    useradapter userAdapter;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    ImageView imageView;
    private UserInterface restInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takip_edilenler);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        userAdapter=new useradapter(this);
        recyclerView.setAdapter(userAdapter);


        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(constans.URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        restInterface = restAdapter.create(UserInterface.class);






        restInterface.getUserList(new Callback<List<user>>() {
            @Override
            public void success(List<user> users, Response response)


            {

                userAdapter.updateList(users);

            }

            @Override
            public void failure(RetrofitError error)

            {

                String merror = error.getMessage();
                AlertDialog.Builder builder = new AlertDialog.Builder(TakipEdilenler.this);
                builder.setCancelable(true);
                builder.setTitle("Gelen Veri Yok");
                builder.setNegativeButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setMessage(merror);

                builder.create().show();
            }
        });
    }


}
