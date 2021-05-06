package com.huatec.edu.mobileshop.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.huatec.edu.mobileshop.R;


/**
 * 导航fragment，包括内容区和下面的菜单栏
 */

public class NavigationFragment extends Fragment implements  View.OnClickListener{
    //    底部上的四个图片按钮
    private ImageButton mIbHome;
    private ImageButton mIbCategory;
    private ImageButton mIbCart;
    private ImageButton mIbPersonal;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private CartFragment cartFragment;
    private PersonFragment personFragment;

    public  NavigationFragment() {
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_navigation,container,false);
        initView(view);//天字第一号进程
        return view;
    }   //这个意思就是返回要浏览的视图（也就是我们的页面文件）他的名字是与你的控制器名字一样的view
    //这里就是初始化完以后就返回视图 前面View view中的view；这个view是已经加了f_n的界面

    //
    //初始化fragment里所有的控件
    //
    protected void initView(View view){

        //获取菜单图片按钮实例对象
        mIbHome = view.findViewById(R.id.ib_home);
        mIbCategory = view.findViewById(R.id.ib_category);
        mIbCart = view.findViewById(R.id.ib_cart);
        mIbPersonal = view.findViewById(R.id.ib_personal);

        //图片按钮设置监听器，因为本类已实现View.onClickListener,
        // 所以可以使用本类做按钮监听,就是点击按钮才会运行的
        mIbHome.setOnClickListener(this);
        mIbCategory.setOnClickListener(this);
        mIbCart.setOnClickListener(this);
        mIbPersonal.setOnClickListener(this);

        //初始化所有控件后，默认选中并显示内容
        select(mIbHome);
    }

    private void select(View v) {
        mIbHome.setImageResource(R.drawable.tab_home_normal);
        mIbCategory.setImageResource(R.drawable.tab_category_normal);
        mIbCart.setImageResource(R.drawable.tab_shopping_normal);
        mIbPersonal.setImageResource(R.drawable.tab_my_normal);

        //启动fragment事务管理fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //隐藏所有Fragment
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (cartFragment != null) {
            fragmentTransaction.hide(cartFragment);
            //不为空代表已经有实例了  不需要new
        }
        if (categoryFragment != null) {
            fragmentTransaction.hide(categoryFragment);
        }
        if (personFragment != null) {
            fragmentTransaction.hide(personFragment);
        }

//        根据选中的菜单按钮的id来执行不同的操作
        switch (v.getId()){
            case R.id.ib_home://点击了首页菜单
                mIbHome.setImageResource(R.drawable.tab_home_pressed);
                //设置菜单图片为这张
                if (homeFragment == null){
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,homeFragment);
                }
                else{
                    //如果fragment已经初始化，则直接显示，
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.ib_category:
                mIbCategory.setImageResource(R.drawable.tab_category_pressed);
                if (categoryFragment == null){
                    //为空代表没有实例  需要new一个
                    categoryFragment = new CategoryFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,categoryFragment);
                }
                else{
                    fragmentTransaction.show(categoryFragment);
                    //show,hide,remove 格式都是一样的
                }
                break;
            case R.id.ib_cart:
                mIbCart.setImageResource(R.drawable.tab_shopping_pressed);
                if (cartFragment == null){
                    cartFragment = new CartFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,cartFragment);
                }
                else{
                    fragmentTransaction.show(cartFragment);
                }
                break;
            case R.id.ib_personal:
                mIbPersonal.setImageResource(R.drawable.tab_my_pressed);
                if (personFragment == null){
                    personFragment = new PersonFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,personFragment);
                }
                else{
                    fragmentTransaction.show(personFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }


    @Override
    public void onClick(View v) {
        select(v);
    }
}

