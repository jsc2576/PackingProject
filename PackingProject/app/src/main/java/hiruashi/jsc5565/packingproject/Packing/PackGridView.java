package hiruashi.jsc5565.packingproject.Packing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

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
     * set matching both id and view.
     * @param id
     * @param view
     */
    public void setIdMatch(int id, int view){
        adapter.setIdMatch(id, view);
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


            View view = ViewUtil.getInstance().getView(this.context, position, convertView, parent, layout, inflater, gridViewItems, Layout_Id, View_Order);

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
         * set matching both id and view.
         *
         * @param id
         * @param view
         */
        public void setIdMatch(int id, int view) {
            if (Layout_Id == null) {
                Layout_Id = new ArrayList<Integer>();
            }
            if (View_Order == null) {
                View_Order = new ArrayList<Integer>();
            }

            Layout_Id.add(id);
            View_Order.add(view);
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

    }

}
