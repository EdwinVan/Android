package com.huatec.edu.mobileshop.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.huatec.edu.mobileshop.R;
import com.huatec.edu.mobileshop.fragment.NavigationFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //一旦调用 setContentView，activity就会找到 XML 布局文件，并且读取它的每行代码，
        //我们没有创建新的 TextView对象，我们只是在视图树中找到了现有的 TextView，
        // 为了与这个视图树中的 View 进行交互，我们应该创建变量，用来引用这些具体的 View，

        initView();
    }

    //    初始化控件
    protected void initView(){
        //开始fragment事务
        //FragmentTransaction 的创建
        FragmentManager fragmentManager = getFragmentManager();
        //getFragmentManager();来获得一个对象赋给fragmentManager；找实例的
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //                                               //返回（开始一个事务）事务对象

        // FragmentTransaction transaction = getFragmentManager().beginTransaction();

        //将fragment添加到activity_main.xml的fl_main控件上
        fragmentTransaction.replace(R.id.fl_main, new NavigationFragment());
        //先将之前存在于容器的 Fragment 全部移除（销毁），
        // 然后添加要显示的 Fragment（会重新执行一遍它的生命流程）
        //显示NavigationFragment,还没有，先new一个 后replace（显示）它
        //xx方法里的 ， 功能 ， （在哪实现，实现的是什么）
        fragmentTransaction.commit();
        //提交事务
        //FragmentTransaction现在提供了四种不同的方法来commit一个
        // transaction:commit()、
        // commitAllowingStateLoss()、
        // commitNow()、立即执行并且只会执行你当前要提交的transaction。
        // commitNowAllowingStateLoss()。
        // 1.如果你操作很多transactions, 并且不需要同步, 或者你需要把transactions加在back stack里, 那就使用commit().
        // 2.如果你需要同步的操作, 并且你不需要加到back stack里, 使用commitNow().
        // 3.当报错“IllegalStateException：Can not perform this action after onSaveInstanceState”时，使用commitAllowingStateLoss()。
        // 4.如果你希望在某一个指定的点, 确保所有的transactions都被执行, 那么使用executePendingTransactions().
    }
}

