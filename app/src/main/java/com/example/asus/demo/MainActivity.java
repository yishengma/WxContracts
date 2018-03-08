package com.example.asus.demo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.asus.demo.adapter.ContractsAdapter;
import com.example.asus.demo.entity.ContractorEntity;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<ContractorEntity> mContractorEntities;
    private RecyclerView mRvContracts;
    private ContractsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void initView(){
        mRvContracts = (RecyclerView)findViewById(R.id.rv_contracts);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvContracts.setLayoutManager(manager);
        mAdapter = new ContractsAdapter(mContractorEntities);
    }



}
