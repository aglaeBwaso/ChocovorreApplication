package com.example.chocovorre.adapters;


import android.content.Context;
import android.content.Intent;
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
import com.example.chocovorre.activities.SelectActivity;
import com.example.chocovorre.model.Choco;
import com.example.chocovorre.R ;
import com.example.chocovorre.model.DescriptModel;

import java.util.List;

public class RecyclerViewAdapter_selection extends RecyclerView.Adapter<RecyclerViewAdapter_selection.MyViewHolder>{


    private Context mContext ;
    private List<DescriptModel> mData ;
    RequestOptions option;


    public RecyclerViewAdapter_selection(Context mContext, List<DescriptModel> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.activity_select,parent,false) ;

        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.img_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, DescriptModel.class);
                i.putExtra("composition",mData.get(viewHolder.getAdapterPosition()).getComposition());
                /*i.putExtra("team_abbreviation",mData.get(viewHolder.getAdapterPosition()).getAbbreviation());
                i.putExtra("team_location",mData.get(viewHolder.getAdapterPosition()).getLocation());
                i.putExtra("team_arena",mData.get(viewHolder.getAdapterPosition()).getArena());
                i.putExtra("team_logo",mData.get(viewHolder.getAdapterPosition()).getLogoUrl());
                i.putExtra("team_description",mData.get(viewHolder.getAdapterPosition()).getDescription());*/

                mContext.startActivity(i);
            }
        });

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {


        holder.title.setText(mData.get(position).getTitle());
        holder.compo.setText(mData.get(position).getComposition());
        holder.origin.setText(mData.get(position).getOrigin());
        holder.percent.setText(Integer.toString(mData.get(position).getPercentage()));
        holder.price.setText(Integer.toString(mData.get(position).getPrice()));



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //public abstract void onItemClick(Choco item);

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title ;
        TextView origin ;
        TextView percent ;
        TextView compo;
        TextView price;

        //LinearLayout view_container;

        ImageView img_thumbnail;





        public MyViewHolder(View itemView) {
            super(itemView);

            compo = itemView.findViewById(R.id.aa_compo);

        }

    }




}
