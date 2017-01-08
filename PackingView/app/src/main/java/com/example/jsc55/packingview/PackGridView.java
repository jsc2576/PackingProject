package com.example.jsc55.packingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jsc55 on 2016-12-22.
 */

public class PackGridView<T> extends GridView {

    /*
        data type
    */
    static final int TEXT = 0;
    static final int IMAGE = 1;
    static final int IMAGEURI = 2;


    /*
        private data
     */
    private Context context;
    private int layout=0;

    private GridAdapter adapter;


    /*
        constructor
     */
    public PackGridView(Context context) {
        super(context);
        Init(context);
    }

    public PackGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public PackGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PackGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context);
    }

    /**
     * init
     * @param context
     */
    private void Init(Context context){
        this.context = context;
        adapter = new GridAdapter(this.context, this.layout);
        this.setAdapter(adapter);
    }


    /**
     * set id order in adapter
     * @param id
     */
    public void setIdOrder(int...id){
        adapter.setIdOrder(id);
    }


    /**
     * set view order in adapter
     * @param view
     */
    public void setViewOrder(int...view){
        adapter.setViewOrder(view);
    }


    /**
     * set layout
     * @param layout_id
     */
    public void setLayout(int layout_id){
        this.layout = layout_id;
        adapter.setLayout(layout_id);
    }


    /**
     * add item
     * @param index
     * @param item
     */
    public void addItem(int index, T ...item){
        GridViewItem gridViewItem = new GridViewItem(item);
        adapter.addItem(index, gridViewItem);
    }


    /**
     * remove item
     * @param index
     */
    public void removeItem(int index){
        adapter.removeItem(index);
    }


    public int size(){
        return adapter.size();
    }


    /************************************
     * GridView Adapter
     ***********************************/
    private class GridAdapter<T extends View> extends BaseAdapter{

        /*
            variables
         */
        private int layout; // layout
        private Context context; //context
        private ArrayList<Integer> Layout_Id, ViewOrder; //id, view order
        private ArrayList<GridViewItem> gridViewItems; // item arraylist
        private LayoutInflater inflater; // inflater


        /**
         * construtor
         * @param context
         * @param layout
         */
        GridAdapter(Context context, int layout){
            this.context = context;
            this.layout = layout;
            this.inflater = LayoutInflater.from(this.context);

            Layout_Id = new ArrayList<Integer>();
            ViewOrder = new ArrayList<Integer>();
            gridViewItems = new ArrayList<GridViewItem>();
        }

        /**
         * get count
         * @return
         */
        @Override
        public int getCount() {
            return gridViewItems.size();
        }

        /**
         * get item in the item arraylist
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return gridViewItems.get(position);
        }


        /**
         * getItemId is position
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return position;
        }


        /**
         * get View
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;


            /*
                if layout don't set, layout sets simple layout. (If layout don't set, occur error in onCreate.)
             */
            if(layout == 0){
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            else{
                view = inflater.inflate(this.layout, parent, false);
            }


            /*
                get a item at position
             */
            GridViewItem item = gridViewItems.get(position);

            for(int i=0; i<item.getItem().size(); i++){

                // if imageview
                if(ViewOrder.get(i) == IMAGE){
                    ImageView imageview = (ImageView)view.findViewById(Layout_Id.get(i));
                    imageview.setImageResource((int)item.getItem().get(i));
                }

                //if textview
                else if(ViewOrder.get(i) == TEXT){
                    TextView textView = (TextView)view.findViewById(Layout_Id.get(i));
                    textView.setText((String)item.getItem().get(i));
                }

                //if imageview by url
                else if(ViewOrder.get(i) == IMAGEURI){
                    ImageView imageView = (ImageView)view.findViewById(Layout_Id.get(i));
                    imageView.setImageURI((Uri)item.getItem().get(i));
                }
            }

            return view;
        }


        /**
         * set layout
         * @param layout
         */
        public void setLayout(int layout){
            this.layout = layout;
        }


        /**
         * set id order
         * @param id
         */
        public void setIdOrder(int ...id){
            for(int i: id){
                Layout_Id.add(i);
            }
        }


        /**
         * set view order
         * @param view
         */
        public void setViewOrder(int ...view){
            for(int v: view){
                ViewOrder.add(v);
            }
        }


        /**
         * add item
         * @param index
         * @param item
         */
        public void addItem(int index, GridViewItem item){
            gridViewItems.add(index, item);
        }


        /**
         * remove item
         * @param index
         */
        public void removeItem(int index){
            gridViewItems.remove(index);
        }


        /**
         * return item's arraylist size
         * @return
         */
        public int size(){
            return gridViewItems.size();
        }
    }//end GridViewAdapter




    /**
     * GridView item
     * @param <T>
     */
    private class GridViewItem<T>{
        private ArrayList<T> item;

        /**
         * constructor, set item
         * @param data
         */
        GridViewItem(T...data){
            this.item = new ArrayList<T>();

            for(T d : data){
                item.add(d);
            }
        }

        /**
         * get item arraylist
         * @return
         */
        public ArrayList<T> getItem(){
            return item;
        }
    }//end GridViewItem
}
