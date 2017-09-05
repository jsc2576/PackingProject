package hiruashi.jsc5565.packingproject.Packing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

import hiruashi.jsc5565.packingproject.R;
import hiruashi.jsc5565.packingproject.util.AnimationUtil;
import hiruashi.jsc5565.packingproject.util.PackListItem;
import hiruashi.jsc5565.packingproject.util.ViewUtil;

/**
 * Created by 정수찬 (jung suchan) on 2016-12-22.
 */

public class PackGridView<T> extends GridView {

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
     * set id order
     * @param id
     */
    public void setIdOrder(int ...id){
        adapter.setIdOrder(id);
    }


    /**
     * set view order
     * @param view
     */
    public void setViewOrder(int ...view){
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
        PackListItem gridViewItem = new PackListItem(item);
        adapter.addItem(index, gridViewItem);
    }


    /**
     * remove item
     * @param index
     */
    public void removeItem(int index){
        adapter.removeItem(index);
    }


    /**
     * get count of items
     * @return
     */
    public int getCount(){
        return adapter.getCount();
    }


    public void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

    /**********************************
     *      setting animation
     **********************************/
/*
    public void setAddAnimation(int ani){
        adapter.setAddAnimation(ani);
    }

    public void setRemoveAnimation(int ani){
        adapter.setRemoveAnimation(ani);
    }

    public void setOverAnimation(int ani){
        adapter.setOverAnimation(ani);
    }

    public void setUnderAnimation(int ani){
        adapter.setUnderAnimation(ani);
    }


    public void useAnimation(boolean use){
        adapter.useAnimation(use);
    }
*/




    /************************************
     * GridView Adapter
     ***********************************/
    private class GridAdapter<T extends View> extends BaseAdapter {

        /*
            variables
         */
        private int layout; // layout
        private Context context; //context
        private ArrayList<Integer> Layout_Id, View_Order; //id, view order
        private ArrayList<PackListItem> gridViewItems; // item arraylist
        private LayoutInflater inflater; // inflater
        private AnimationUtil animationUtil;
        private boolean useAnimation = false;
        private ViewUtil viewUtil;

        /**
         * construtor
         *
         * @param context
         * @param layout
         */
        GridAdapter(Context context, int layout) {
            this.context = context;
            this.layout = layout;
            this.inflater = LayoutInflater.from(this.context);

            Layout_Id = new ArrayList<Integer>();
            View_Order = new ArrayList<Integer>();
            gridViewItems = new ArrayList<PackListItem>();
            animationUtil = new AnimationUtil(this.context);
            viewUtil = new ViewUtil();
        }

        /**
         * get count
         *
         * @return
         */
        @Override
        public int getCount() {
            return gridViewItems.size();
        }

        /**
         * get item in the item arraylist
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return gridViewItems.get(position);
        }


        /**
         * getItemId is position
         *
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return position;
        }


        /**
         * get View
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = viewUtil.getView(this.context, position, convertView, parent, layout, inflater, gridViewItems, Layout_Id, View_Order);

            return view;
        }


        /**
         * set layout
         *
         * @param layout
         */
        public void setLayout(int layout) {
            this.layout = layout;
        }


        /**
         * set id order
         * @param id
         */
        public void setIdOrder(int ...id){
            if (Layout_Id == null) {
                Layout_Id = new ArrayList<Integer>();
            }
            for(int i: id) {
                Layout_Id.add(i);
            }
        }


        /**
         * set view order
         * @param view
         */
        public void setViewOrder(int ...view){
            if (View_Order == null) {
                View_Order = new ArrayList<Integer>();
            }
            for(int v: view) {
                View_Order.add(v);
            }
        }

        /**
         * add item
         *
         * @param index
         * @param item
         */
        public void addItem(int index, PackListItem item) {
            gridViewItems.add(index, item);
        }


        /**
         * remove item
         *
         * @param index
         */
        public void removeItem(int index) {
            gridViewItems.remove(index);
        }

        /***************************************
         *      setting animation
         ***************************************/

        public void setAddAnimation(int ani){
            animationUtil.setAddAnimation(ani);
        }

        public void setRemoveAnimation(int ani){
            animationUtil.setRemoveAnimation(ani);
        }


        public void setOverAnimation(int ani){
            animationUtil.setOverAnimation(ani);
        }

        public void setUnderAnimation(int ani){
            animationUtil.setUnderAnimation(ani);
        }

        public void useAnimation(boolean use){
            this.useAnimation = use;
        }
    }

}
