package com.huatec.edu.mobileshop.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huatec.edu.mobileshop.R;


/**
 * 个人中心fragment
 */

public class PersonFragment extends Fragment {
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_person,container,false);
        return view;
    }
}

