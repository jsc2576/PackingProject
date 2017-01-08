package com.example.jsc55.packingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jung suchan on 2016-10-29.
 */

public class PackListView extends ListView {

    /*
        variables
     */
    private ArrayAdapter<String> adapter;

    /*
        constructor
     */
    public PackListView(Context context) {
        super(context);
        Init(context);
    }

    public PackListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public PackListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PackListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context);
    }

    /*
            custom functions
     */

    /**
     * using in constructors
     * @param context
     */
    private void Init(Context context){

        if(adapter == null){
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        }

        this.setAdapter(adapter);
    }

    /**
     * add item in regular sequence
     * @param item
     */
    public void addItem(String item){
        adapter.add(item);
    }

    /**
     * remove item using position
     * @param position
     * @return
     */
    public boolean removeItem(int position){
        try {
            adapter.remove(adapter.getItem(position));
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * remove item using string
     * @param str
     * @return
     */
    public boolean removeItem(String str){
        try {
            adapter.remove(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * reset height
     * @return
     */
    public boolean fixHeight(){
        int listview_height = 0;

        try {
            for (int i = 0; i < adapter.getCount(); i++) {
                View listitem = adapter.getView(i, null, this);
                listitem.measure(0, 0);
                listview_height += listitem.getMeasuredHeight();
            }

            final ViewGroup.LayoutParams params = this.getLayoutParams();
            params.height = listview_height + this.getDividerHeight() * (adapter.getCount() - 1);

            this.setLayoutParams(params);
            this.deferNotifyDataSetChanged();

            return true;

        }catch(Exception e){
            return false;
        }

    }
}
