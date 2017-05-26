package com.example.jsc55.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import hiruashi.jsc5565.packingproject.Packing.PackListView;
import hiruashi.jsc5565.packingproject.util.PackListItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackListView listview = new PackListView(this);

        listview.addItem("test");
    }
}
