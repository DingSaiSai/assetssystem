package com.example.assets.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assets.R;


/**
 * Created by Administrator on 2016/11/18.
 */

public class ChartFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View messageView=inflater.inflate(R.layout.frag_chart,container,false);
        return messageView;
    }
}
