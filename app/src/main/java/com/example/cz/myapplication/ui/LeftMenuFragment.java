package com.example.cz.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by cz on 2015/6/26.
 */
public class LeftMenuFragment extends Fragment implements Myadapter.OnmItemClickListener {

    public static  final String MENU_BUMBER="leftmenu_number";

    private RecyclerView mItemList;
    private String item[] = null;
    private int currentItem;
    public static Fragment newInstance(int positionn){
        Fragment fragment=new LeftMenuFragment();
        Bundle args=new Bundle();
        args.putInt(LeftMenuFragment.MENU_BUMBER,positionn);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        currentItem=savedInstanceState.getInt("current",0);
        item=getResources().getStringArray(R.array.item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mItemList= (RecyclerView) inflater.inflate(R.layout.leftmenu_view,null,false);
        mItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mItemList.setAdapter(new Myadapter(item,this));

        return mItemList;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current",currentItem);
    }


    @Override
    public void onClick(View view, int position) {
        selectItem(position);
    }

    private void selectItem(int position) {
        Toast.makeText(getActivity(),position+"",Toast.LENGTH_SHORT).show();

    }
}
