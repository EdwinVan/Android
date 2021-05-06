package com.huatec.edu.mobileshop.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huatec.edu.mobileshop.R;


/**
 * Created by Administrator on 2020/5/24 0024.
 */

public class HomeFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
}

