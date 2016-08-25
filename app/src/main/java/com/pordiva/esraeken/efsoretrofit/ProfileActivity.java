package com.pordiva.esraeken.efsoretrofit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pordiva.esraeken.efsoretrofit.constans.constans;
import com.pordiva.esraeken.efsoretrofit.model.user;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView takipci, takipedilen, adi, blog, lokasyon;
    private UserInterface restInterface;
    private Context context;
    ImageView image;
    Button takipedilenbutton;

    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        takipci = (TextView) findViewById(R.id.takipci);
        takipedilen = (TextView) findViewById(R.id.takipedilen);
        adi = (TextView) findViewById(R.id.kullanici_ismi);
        blog = (TextView) findViewById(R.id.blog);
        lokasyon = (TextView) findViewById(R.id.lokasyon);
        image = (ImageView) findViewById(R.id.imageView);
        picasso = Picasso.with(ProfileActivity.this);
        takipedilenbutton = (Button) findViewById(R.id.takipedilenbutton);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(constans.URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        restInterface = restAdapter.create(UserInterface.class);

        final String username = getIntent().getExtras().getString("username");

            restInterface.getUser(username, new Callback<user>() {
                @Override
                public void success(user user, Response response) {
                    adi.setText(user.getLogin());
                    takipci.setText(user.getFollowers().toString());
                    takipedilen.setText(user.getFollowing().toString());
                    blog.setText(user.getBlog());
                    lokasyon.setText(user.getLocation());
                    picasso.load(user.getAvatarUrl())
                            .centerCrop()
                            .fit()
                            .error(R.mipmap.ic_launcher)
                            .into(image);

                }

                @Override
                public void failure(RetrofitError error) {
                    String merror = error.getMessage();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                    builder.setTitle("Gelen Veri Yok");
                    builder.setCancelable(true);

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

        takipedilenbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, TakipEdilenler.class);
                startActivity(i);
            }
        });


    }
}
