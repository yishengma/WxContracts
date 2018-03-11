package com.example.asus.demo;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.asus.demo.adapter.ContractsAdapter;
import com.example.asus.demo.configs.Letter;
import com.example.asus.demo.configs.Type;
import com.example.asus.demo.configs.name;
import com.example.asus.demo.entity.ContractorEntity;
import com.example.asus.demo.utils.SortListUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.demo.configs.name.NAME;


public class MainActivity extends AppCompatActivity {
    private List<ContractorEntity> mContractorEntities;
    private List<ContractorEntity> mEntityList;
    private RecyclerView mRvContracts;
    private ContractsAdapter mAdapter;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    public void initView(){
        mRvContracts = (RecyclerView)findViewById(R.id.rv_contracts);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvContracts.setLayoutManager(manager);

        mAdapter = new ContractsAdapter(mContractorEntities);
        mRvContracts.setAdapter(mAdapter);

    }

    public void initData(){

        mEntityList = new ArrayList<>();
        mContractorEntities = new ArrayList<>();
        for (int i=0;i<20;i++){
            ContractorEntity entity = new ContractorEntity();
            entity.setContractorId(i);
            entity.setType(Type.CONTRACT);
            entity.setName(NAME[i]);
            entity.setProfilePicture(R.drawable.category);
            mEntityList.add(entity);
        }

        SortListUtil.addDividerLetter(mContractorEntities,mEntityList);
        mContractorEntities.addAll(mEntityList);
        SortListUtil.sortList(mContractorEntities);




    }



}
