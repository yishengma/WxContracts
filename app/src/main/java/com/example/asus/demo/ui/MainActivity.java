package com.example.asus.demo.ui;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import android.util.Log;
import android.view.Gravity;

import android.view.View;

import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.asus.demo.R;
import com.example.asus.demo.adapter.ContractsAdapter;

import com.example.asus.demo.configs.Type;

import com.example.asus.demo.entity.ContractorEntity;
import com.example.asus.demo.utils.SortListUtil;
import com.example.asus.demo.widget.IndexBar;

import java.util.ArrayList;
import java.util.List;

import static com.example.asus.demo.configs.name.sNAME;


public class MainActivity extends AppCompatActivity {
    private List<ContractorEntity> mContractorEntities;
    private List<ContractorEntity> mEntityList;
    private RecyclerView mRvContracts;
    private ContractsAdapter mAdapter;
private LinearLayoutManager mManager;
    private PopupWindow mPopupWindow;
    private TextView mLetterView;
    private IndexBar mIndexBar;
    private boolean isMove;
    private int mIndex;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    public void initView(){
        mLetterView = findViewById(R.id.tv_letter);
        mIndexBar = findViewById(R.id.v_index);
        mRvContracts = findViewById(R.id.rv_contracts);

        mIndexBar.setLetterView(mLetterView);
        mIndexBar.setLetterChangeListener(new IndexBar.OnLetterChangeListener() {
            @Override
            public void OnLetterChange(String index) {
                for (int i=0;i<mContractorEntities.size();i++){
                    if (index.equals(mContractorEntities.get(i).getName())){
//                        没有滚动是因为 要滚动到的位置，已经在屏幕里面了，这时候是不滚动的，

//                        只有要滚到的位置没有在屏幕上，才会滚动。
//                      mManager.scrollToPosition(i);
//                        mRvContracts.scrollToPosition(i);
//                        mRvContracts.smoothScrollToPosition(i);
                        mIndex = i;
                      moveToPosition(i);

                        break;
                    }
                }
            }
        });

        mManager = new LinearLayoutManager(this);
        mRvContracts.setLayoutManager(mManager);
        mRvContracts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isMove) {
                    isMove = false;
                    //获取要置顶的项在当前屏幕的位置，mIndex是记录的要置顶项在RecyclerView中的位置
                    int n = mIndex - mManager.findFirstVisibleItemPosition();
                    if (0 <= n && n < mRvContracts.getChildCount()) {
                        //获取要置顶的项顶部离RecyclerView顶部的距离
                        int top = mRvContracts.getChildAt(n).getTop();
                        //最后的移动
                        mRvContracts.scrollBy(0, top);
                    }
                }
            }
        });




        //获取PopWindow宽和高
        mPopupWindow = new PopupWindow(getLayoutInflater().inflate(R.layout.menu_window,null),300,100,true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setElevation(5);
        mAdapter = new ContractsAdapter(mContractorEntities);

        mAdapter.setClickListener(new ContractsAdapter.ClickListener() {
            @Override
            public void OnClickListener(View view, float x, float y) {

            }

            @Override
            public void OnLongClickListener(final View view, float x, float y) {
                view.setBackgroundResource(R.drawable.drawableItemPressed);
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int xoff = (int)x;
                int yoff = 0 - (view.getHeight() - (int)y) - mPopupWindow.getHeight()-50;
                if (x>width/2){
                    xoff = (int) x-width/2+10;
                    mPopupWindow.setAnimationStyle(R.style.AnimationRight);
                }else {
                    mPopupWindow.setAnimationStyle(R.style.AnimationLeft);
                }
                mPopupWindow.showAsDropDown(view,xoff,yoff,Gravity.TOP);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        view.setBackgroundResource(R.drawable.item_background_selector);

                    }
                });
            }
        });








//        mAdapter.setClickListener(new ContractsAdapter.ClickListener() {
//            @Override
//            public void OnClickListener(View view, MotionEvent event) {
//                DisplayMetrics metrics = getResources().getDisplayMetrics();
//                int width = metrics.widthPixels;
//                int xoff = (int) event.getX();
//                int yoff = 0 - (view.getHeight() - (int) event.getY()) - mPopupWindow.getHeight()-50;
//                if (event.getX()>width/2){
//                     xoff = (int) event.getX()-width/2+10;
//                    mPopupWindow.setAnimationStyle(R.style.AnimationRight);
//                }else {
//                    mPopupWindow.setAnimationStyle(R.style.AnimationLeft);
//                }
//                mPopupWindow.showAsDropDown(view,xoff,yoff,Gravity.TOP);
//
//
//            }
//        });
        mRvContracts.setAdapter(mAdapter);


    }

    public void initData(){

        mEntityList = new ArrayList<>();
        mContractorEntities = new ArrayList<>();
        for (int i=0;i<sNAME.length;i++){
            ContractorEntity entity = new ContractorEntity();
            entity.setContractorId(i);
            entity.setType(Type.CONTRACT);
            entity.setName(sNAME[i]);
            entity.setProfilePicture(R.drawable.category);
            mEntityList.add(entity);
        }

        SortListUtil.addDividerLetter(mContractorEntities,mEntityList);
        mContractorEntities.addAll(mEntityList);
        SortListUtil.sortList(mContractorEntities);




    }


    private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mManager.findFirstVisibleItemPosition();
        int lastItem = mManager.findLastVisibleItemPosition();
        //然后区分情况
        if (n <= firstItem ){
            //当要置顶的项在当前显示的第一个项的前面时
            mRvContracts.scrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = mRvContracts.getChildAt(n-firstItem).getTop();
            mRvContracts.scrollBy(0, top);
        }else{
            //当要置顶的项在当前显示的最后一项的后面时
            mRvContracts.scrollToPosition(n);
            //这里这个变量是用在RecyclerView滚动监听里面的
            isMove = true;
        }

    }



}
