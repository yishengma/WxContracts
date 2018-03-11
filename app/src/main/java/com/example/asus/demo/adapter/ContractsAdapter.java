package com.example.asus.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.demo.R;
import com.example.asus.demo.configs.Type;
import com.example.asus.demo.entity.ContractorEntity;
import com.example.asus.demo.utils.SortListUtil;
import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 18-3-8.
 */

public class ContractsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContractorEntity> mContractorEntities;
    private List<ContractorEntity> mEntityList;
    private static final String TAG = "ContractsAdapter";

    public ContractsAdapter(List<ContractorEntity> contractorEntities) {
        mContractorEntities = contractorEntities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType== Type.CONTRACT){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contract,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_divider,parent,false);
            DividerViewHolder viewHolder = new DividerViewHolder(view);
            return viewHolder;

        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (mContractorEntities.get(position).getType()==Type.CONTRACT){
            ((ViewHolder)holder).profilePicture.setBackgroundResource(mContractorEntities.get(position).getProfilePicture());
            ((ViewHolder)holder).name.setText(mContractorEntities.get(position).getName());
            ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "onClick: "+ Pinyin.toPinyin(mContractorEntities.get(position).getName().charAt(0)));
                    Log.e(TAG, "onClick: "+Pinyin.toPinyin(mContractorEntities.get(position).getName(),"") );
                }
            });
        }else {

            ((DividerViewHolder)holder).letter.setText(mContractorEntities.get(position).getName());

        }
    }

    @Override
    public int getItemCount() {
        return mContractorEntities.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mContractorEntities.get(position).getType();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
         ImageView profilePicture;
         TextView name;
        private ViewHolder(View itemView) {
            super(itemView);
            profilePicture = itemView.findViewById(R.id.iv_profile_picture);
            name = itemView.findViewById(R.id.tv_name);

        }
    }

    class DividerViewHolder extends RecyclerView.ViewHolder{
        TextView letter;
        public DividerViewHolder(View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.tv_letter);
        }
    }
}
