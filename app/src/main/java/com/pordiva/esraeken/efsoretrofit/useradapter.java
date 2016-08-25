package com.pordiva.esraeken.efsoretrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pordiva.esraeken.efsoretrofit.model.user;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esraeken on 15/08/16.
 */
public class useradapter extends RecyclerView.Adapter<userviewholder>
{
    private List<user> userList;
    private Context c;
    private Picasso picasso;

    public useradapter(Context context)

    {

        userList=new ArrayList<>();
        this.c = context;
        picasso = Picasso.with(context);
    }



    @Override
    public userviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.satir, parent, false);

        userviewholder userviewholder = new userviewholder(view, c);
        return userviewholder;


    }

    @Override
    public void onBindViewHolder(userviewholder holder, int position) {
        holder.kullanici_ismi.setText(userList.get(position).getLogin());
        holder.blog.setText(userList.get(position).getBlog());
        holder.lokasyon.setText(userList.get(position).getLocation());

        Picasso.with(c).load(userList.get(position).getAvatarUrl()).into(holder.image2);
        picasso.load(userList.get(position).getAvatarUrl())
                .centerCrop()
                .fit()
                .error(R.mipmap.ic_launcher)
                .into(holder.image2);

    }
    public void updateList(List<user> list)
    {
        userList=new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return userList.size();

    }
}

