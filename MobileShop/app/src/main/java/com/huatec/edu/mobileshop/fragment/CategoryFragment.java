package com.huatec.edu.mobileshop.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huatec.edu.mobileshop.R;

/**
 * 分类fragment
 */

public class CategoryFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        return view;
    }
}


