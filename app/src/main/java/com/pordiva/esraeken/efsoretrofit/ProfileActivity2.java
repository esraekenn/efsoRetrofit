package com.pordiva.esraeken.efsoretrofit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pordiva.esraeken.efsoretrofit.constans.constans;
import com.pordiva.esraeken.efsoretrofit.model.user;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProfileActivity2 extends AppCompatActivity {
    TextView user_name, user_id, location, blog, user_gravatar, user_html, followers, following;
    ImageView image3;
    private Picasso picasso;
    private UserInterface restInterface;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        user_name = (TextView) findViewById(R.id.user_name);
        user_id = (TextView) findViewById(R.id.user_id);
        location = (TextView) findViewById(R.id.user_location);
        blog = (TextView) findViewById(R.id.user_blog);
        user_gravatar = (TextView) findViewById(R.id.user_gravatar);
        user_html = (TextView) findViewById(R.id.user_html);
        followers = (TextView) findViewById(R.id.user_followers);
        following = (TextView) findViewById(R.id.user_following);
        image3 = (ImageView) findViewById(R.id.image3);
        picasso = Picasso.with(ProfileActivity2.this);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(constans.URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        restInterface = restAdapter.create(UserInterface.class);

        final String username = getIntent().getExtras().getString("username");

        restInterface.getUser(username, new Callback<user>() {
            @Override
            public void success(user user, Response response)

            {
                user_name.setText(user.getLogin());
                user_id.setText(user.getId().toString());
                location.setText(user.getLocation());
                blog.setText(user.getBlog());
               // user_gravatar.setText(user.getGravatarId());
                user_html.setText(user.getHtmlUrl());
                followers.setText(user.getFollowers());
                following.setText(user.getFollowing());
                picasso.load(user.getAvatarUrl())
                        .centerCrop()
                        .fit()
                        .error(R.mipmap.ic_launcher)
                        .into(image3
                        );

            }

            @Override
            public void failure(RetrofitError error)

            {
                String merror = error.getMessage();
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity2.this);
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


    }
}
