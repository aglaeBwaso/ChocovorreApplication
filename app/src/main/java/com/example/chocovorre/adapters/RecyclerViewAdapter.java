package com.example.chocovorre.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.chocovorre.activities.DescriptionActivity;
import com.example.chocovorre.model.Choco;
import com.example.chocovorre.R ;

import java.util.List;
import java.util.StringTokenizer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{


    private Context mContext ;
    private List<Choco> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Choco> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, final int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.choco_row_item,parent,false) ;

        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = foundIndex(viewHolder.percent.getText().toString());


                Intent i = new Intent(mContext, DescriptionActivity.class);
                i.putExtra("composition",mData.get(a).getComposition());
                i.putExtra("title",mData.get(a).getTitle());
                i.putExtra("image_url",mData.get(a).getImage_url());

                mContext.startActivity(i);
            }
        });
        return new MyViewHolder(view);


    }

    private int foundIndex(String query) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mData.get(i).getPercentage() == Integer.parseInt(query) ) {
                return i;
            }
        }
        return -1;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(mData.get(position).getTitle());
        //holder.compo.setText(mData.get(position).getComposition());
        holder.origin.setText(mData.get(position).getOrigin());
        holder.percent.setText(Integer.toString(mData.get(position).getPercentage()));
        holder.price.setText(Integer.toString(mData.get(position).getPrice()));

        // Load Image from the internet and set it into Imageview using Glide

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(holder.img_thumbnail);



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title ;
        TextView origin ;
        TextView percent ;
        TextView price;

        LinearLayout view_container;
        ImageView img_thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);
            view_container = itemView.findViewById(R.id.container);

            title = itemView.findViewById(R.id.aa_choco_title);
            //compo = itemView.findViewById(R.id.aa_compo);
            origin = itemView.findViewById(R.id.aa_origin);
            price = itemView.findViewById(R.id.aa_price);
            percent = itemView.findViewById(R.id.aa_percentage);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);

        }

    }




}
