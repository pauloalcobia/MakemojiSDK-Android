package com.makemoji.mojilib;

import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makemoji.mojilib.model.MojiModel;
import com.makemoji.mojilib.model.ReactionsData;
import com.makemoji.mojilib.wall.MojiWallActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott Baar on 1/5/2016.
 */
class ReactionsAdapter extends Adapter<ReactionsAdapter.CellHolder>{

    List<ReactionsData.Reaction> list = new ArrayList<>();
    ReactionsData data;
    public ReactionsAdapter(){}
    @Override
    public CellHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mm_reaction_cell, parent, false);
        return new CellHolder(v,parent);
    }

    @Override
    public void onBindViewHolder(final CellHolder holder, final int position) {
        final ReactionsData.Reaction r = list.get(position);
        Moji.setText(r.toSpanned(holder.left),holder.left);
        holder.right.setText(getTotalString(r.total));
        holder.right.setVisibility(r.total>0?View.VISIBLE:View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.onClick(position);
                notifyDataSetChanged();

            }
        });
        holder.itemView.setSelected(r.selected);

    }


    public void setReactions(ReactionsData data){
        list = data.getReactions();
        this.data = data;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
       return list.size();
    }

    public class CellHolder extends ViewHolder
    {
        View v;
        TextView left,right;
        public CellHolder(View v,ViewGroup parent){
            super(v);
            this.v = v;

            left = (TextView)v.findViewById(R.id._mm_reaction_left_tv);
            right =(TextView) v.findViewById(R.id._mm_reaction_right_tv);

        }
    }
    static String abbreviations[] = {"K","M","B"};
    static String getTotalString(int total) {
        if (total < 1000) return "" + total;

        for (int i = abbreviations.length; i >=0; i--) {

            double size = Math.pow(10, (i + 1) * 3);
            if (size < total) {
                total = (int) (total / size);
                return total + abbreviations[i];
            }

        }
        return "" + total;
    }
}