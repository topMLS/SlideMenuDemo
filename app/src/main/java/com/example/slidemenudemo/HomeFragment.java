package com.example.slidemenudemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.slidemenudemo.utils.Constant;
import com.example.slidemenudemo.view.MyLinearLayout;
import com.example.slidemenudemo.view.SlideMenu;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;



import java.util.Arrays;

import java.util.List;

/**
 * Created by malusong on 2017/7/11.
 */

public class HomeFragment extends Fragment {
    private View rootView;
    private ListView main_listview;
    private ImageView iv_head;
    private MyLinearLayout my_layout;
    private List<String> datas;
    private SlideMenu slideMenu;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        main_listview = (ListView)rootView.findViewById(R.id.main_listview);
        iv_head = (ImageView)rootView.findViewById(R.id.iv_head);
        my_layout=(MyLinearLayout)rootView.findViewById(R.id.my_layout);
        slideMenu=(SlideMenu) getActivity().findViewById(R.id.slideMenu);
        my_layout.setSlideMenu(slideMenu);
        slideMenu.setOnDragStateChangeListener(new SlideMenu.OnDragStateChangeListener() {
            @Override
            public void onOpen() {
                Log.e("tag", "onOpen2");
            }

            @Override
            public void onClose() {
                ViewPropertyAnimator.animate(iv_head).translationXBy(15)
                        .setInterpolator(new CycleInterpolator(4))
                        .setDuration(500)
                        .start();
            }

            @Override
            public void onDraging(float fraction) {
                ViewHelper.setAlpha(iv_head,1-fraction);
            }
        });
        datas = Arrays.asList(Constant.NAMES);
        main_listview.setAdapter(new MainAdapter());
    }

    public class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.main_item, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.tv_name);
            //先缩小view
            ViewHelper.setScaleX(convertView, 0.5f);
            ViewHelper.setScaleY(convertView, 0.5f);
            //以属性动画放大
            ViewPropertyAnimator.animate(convertView).scaleX(1).setDuration(350).start();
            ViewPropertyAnimator.animate(convertView).scaleY(1).setDuration(350).start();
            name.setText(datas.get(position));
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), datas.get(position), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getContext(),TwoActivity.class);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

}
