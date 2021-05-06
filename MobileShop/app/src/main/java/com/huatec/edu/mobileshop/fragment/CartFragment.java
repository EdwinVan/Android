package com.huatec.edu.mobileshop.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huatec.edu.mobileshop.R;

/**
 * 购物车fragment
 */

public class CartFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstenceState){
        View view = inflater.inflate(R.layout.fragment_cart,null);
        //inflate(int resource,ViewGroup root，boolean attachToRoot)

        //resource：布局资源id

        //root：resource生成view对象要填入的父布局。
        // 为null，则返回的view就是布局资源；否则，需要参照第三个参数

        //attachToRoot：是否将resource生成view对象填入root，以root作为最终返回view的根布局。
        // false，root不为null，则提供root的LayoutParams约束resource生成的view；
        // true，root不为null，以root作为最终返回view的根布局

        //inflate(int resource,ViewGroup root)

        //resource：布局资源

        //root：resource生成view对象要填入的父布局。
        // 为null，则返回的view就是布局资源的根布局；
        // 否则，返回的view以传入的root为根布局(相当于上面的那个第三个参数为true)

        return view;
    }
}

