package com.example.slidemenudemo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.slidemenudemo.view.MyLinearLayout;
import com.example.slidemenudemo.view.ParallaxListView;
import com.example.slidemenudemo.view.SlideMenu;

/**
 * Created by malusong on 2017/7/12.
 */

public class RedFrament extends Fragment{
    private ParallaxListView listview;
    private MyLinearLayout my_layout;
    private SlideMenu slideMenu;
    private String[] indexArr = { "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z" };
    private View rootView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_red, container, false);
            initView();
        }
        return rootView;
    }

    private void initView() {
        my_layout=(MyLinearLayout)rootView.findViewById(R.id.my_layout);
        listview = (ParallaxListView) rootView.findViewById(R.id.listview);
        slideMenu=(SlideMenu) getActivity().findViewById(R.id.slideMenu);
        my_layout.setSlideMenu(slideMenu);

        listview.setOverScrollMode(ListView.OVER_SCROLL_NEVER);//永远不显示蓝色阴影

        //添加header
        View headerView = View.inflate(getContext(),R.layout.layout_header, null);
        ImageView imageView = (ImageView) headerView.findViewById(R.id.imageView);
        listview.setParallaxImageView(imageView);

        listview.addHeaderView(headerView);

        listview.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, indexArr));
    }
}
