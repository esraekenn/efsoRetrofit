package com.pordiva.esraeken.efsoretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by esraeken on 15/08/16.
 */
public class userviewholder extends RecyclerView.ViewHolder

{

    public ImageView image2;
    public TextView kullanici_ismi, blog, lokasyon;
    private Context context;

    public userviewholder(View itemView, Context c)
    {
        super(itemView);
        context=c;
        image2= (ImageView) itemView.findViewById(R.id.image2);
        kullanici_ismi= (TextView) itemView.findViewById(R.id.kullanici_ismi2);
        blog=(TextView)itemView.findViewById(R.id.blog);
        lokasyon=(TextView)itemView.findViewById(R.id.lokasyon);
            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=  new Intent(context,ProfileActivity2.class);
                i.putExtra("username", kullanici_ismi.getText().toString());
                context.startActivity(i);
            }
        });


    }
}
