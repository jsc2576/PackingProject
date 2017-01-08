package com.example.jsc55.packingview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * TODO 예제 프래그먼트이므로 라이브러리로 만들때는 삭제해야한다
 */
public class IntroFirstFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        return view;
    }
}