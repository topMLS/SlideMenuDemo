package com.example.slidemenudemo;

import java.util.Arrays;
import java.util.List;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slidemenudemo.utils.Constant;
import com.example.slidemenudemo.view.SlideMenu;


public class MainActivity extends AppCompatActivity {
    private Fragment mainFragment; // 主页
    private Fragment artFragment; // 红人
    private Fragment myFragment; // 我的
    private Fragment currentFragment;
    private LinearLayout ll_main;
    private List<String> datas;
    private List<String> data;
    private ListView menu_listview;
    private SlideMenu slideMenu;
    private TextView tvMy;
    private TextView tvArt;
    private TextView tvMain;
    private ImageView main_iv, art_iv, my_iv;

    //private ImageView iv_head;
    //private MyLinearLayout my_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTab();
        initData();
    }

    private void initData() {
        menu_listview.setAdapter(new MenuAdapter());
    }

    public class MenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return getItem(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.menu_item, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            name.setText(data.get(position));
            return convertView;
        }
    }

    private void initView() {
        tvMain = (TextView) findViewById(R.id.tv_main);
        tvMy = (TextView) findViewById(R.id.tv_my);
        tvArt = (TextView) findViewById(R.id.tv_art);
        main_iv = (ImageView) findViewById(R.id.bottom_main_iv);
        art_iv = (ImageView) findViewById(R.id.bottom_art_iv);
        my_iv = (ImageView) findViewById(R.id.bottom_my_iv);
        menu_listview = (ListView) findViewById(R.id.menu_listview);
        ll_main=(LinearLayout)findViewById(R.id.ll_main);
        slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
        datas = Arrays.asList(Constant.NAMES);
        data = Arrays.asList(Constant.sCheeseStrings);

        menu_listview.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
    }

    /**
     * 初始化
     */
    private void initTab() {

        if (mainFragment == null) {
            mainFragment = new HomeFragment();
        }

        if (!mainFragment.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction().add(R.id.main_content, mainFragment).commit();
            // 记录当前Fragment
            currentFragment = mainFragment;
            // 设置图片文本的变化
            changeBottomImage(1);
        }
    }

    private void changeBottomImage(int i) {
        switch (i) {
            case 1:
                tvMain.setTextColor(getResources().getColor(R.color.main_color));
                tvArt.setTextColor(getResources().getColor(R.color.mytextColor));
                tvMy.setTextColor(getResources().getColor(R.color.mytextColor));
                main_iv.setImageResource(R.drawable.main_tab1);
                art_iv.setImageResource(R.drawable.main_art2);
                my_iv.setImageResource(R.drawable.main_my2);
                break;

            case 2:
                tvMain.setTextColor(getResources().getColor(R.color.mytextColor));
                tvArt.setTextColor(getResources().getColor(R.color.main_color));
                tvMy.setTextColor(getResources().getColor(R.color.mytextColor));
                main_iv.setImageResource(R.drawable.main_tab2);
                art_iv.setImageResource(R.drawable.main_art1);
                my_iv.setImageResource(R.drawable.main_my2);
                break;

            case 3:
                tvMain.setTextColor(getResources().getColor(R.color.mytextColor));
                tvArt.setTextColor(getResources().getColor(R.color.mytextColor));
                tvMy.setTextColor(getResources().getColor(R.color.main_color));
                main_iv.setImageResource(R.drawable.main_tab2);
                art_iv.setImageResource(R.drawable.main_art2);
                my_iv.setImageResource(R.drawable.main_my1);
                break;
        }
    }

    /**
     * @param v 主页
     */
    public void mainBtn(View v) {
        if (mainFragment == null) {
            mainFragment = new HomeFragment();
        }
        changeBottomImage(1);
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), mainFragment);
    }

    /**
     * @param v 红人界面
     */
    public void artBtn(View v) {
        if (artFragment == null) {
            artFragment = new RedFrament();
        }
        changeBottomImage(2);
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), artFragment);
    }

    /**
     * 我的界面
     *
     * @param v
     */
    public void myBtn(View v) {
        if (myFragment == null) {
            myFragment = new MyFragment();
        }
        changeBottomImage(3);
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), myFragment);
    }
    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, Fragment fragment) {
        if (currentFragment == fragment)
            return;

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(currentFragment).add(R.id.main_content, fragment).commitAllowingStateLoss();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
