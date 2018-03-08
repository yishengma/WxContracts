package com.example.asus.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.demo.R;
import com.example.asus.demo.entity.ListDataEntity;

import java.util.List;

/**
 * Created by asus on 18-3-8.
 */

public class ListTextAdapter extends ArrayAdapter<ListDataEntity> {
    private int mResId;

    public ListTextAdapter(@NonNull Context context, int resource, @NonNull List<ListDataEntity> objects) {
        super(context, resource, objects);
        mResId =resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListDataEntity entity = getItem(position);
        View view  = LayoutInflater.from(getContext()).inflate(mResId,parent,false);

        ImageView imageView = view.findViewById(R.id.iv_image);
        TextView textView = view.findViewById(R.id.tv_string);
        imageView.setImageResource(entity.getId());
        textView.setText(entity.getString());
        return view;
    }
}
