package com.example.cz.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cz on 2015/6/28.
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.myholder> {

    private String[] item;
    private OnmItemClickListener mListener;

    public interface OnmItemClickListener {
        public void onClick(View view, int position);
    }

    public Myadapter(String[] item, OnmItemClickListener mListener) {
        this.item = item;
        this.mListener = mListener;
    }

    @Override
    public myholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.left_item_view, viewGroup, false);
        myholder holder = new myholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(myholder myholder, final int i) {
        myholder.textview.setText(item[i]);
        myholder.textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.length;
    }

    public static class myholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textview;

        public myholder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_item);
            textview = (TextView) itemView.findViewById(R.id.t1);
        }

    }
}
