package com.example.asus.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.demo.R;
import com.example.asus.demo.entity.ContractorEntity;

import java.util.List;

/**
 * Created by asus on 18-3-8.
 */

public class ContractsAdapter extends RecyclerView.Adapter<ContractsAdapter.ViewHolder> {

    private List<ContractorEntity> mContractorEntities;
    private static final String TAG = "ContractsAdapter";

    public ContractsAdapter(List<ContractorEntity> contractorEntities) {
        mContractorEntities = contractorEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contract,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.profilePicture.setBackgroundResource(mContractorEntities.get(position).getProfilePicture());
         holder.name.setText(mContractorEntities.get(position).getName());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
    }

    @Override
    public int getItemCount() {
        return mContractorEntities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
         ImageView profilePicture;
         TextView name;
        private ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
