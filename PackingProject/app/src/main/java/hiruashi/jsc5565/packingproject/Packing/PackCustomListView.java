package hiruashi.jsc5565.packingproject.Packing;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hiruashi.jsc5565.packingproject.R;
import hiruashi.jsc5565.packingproject.util.AnimationUtil;
import hiruashi.jsc5565.packingproject.util.PackListItem;
import hiruashi.jsc5565.packingproject.util.ViewUtil;

import static hiruashi.jsc5565.packingproject.util.AnimationUtil.ADD;
import static hiruashi.jsc5565.packingproject.util.AnimationUtil.REMOVE;

/**
 * Created by 정수찬 (jung suchan) on 2016-10-29.
 */


public class PackCustomListView<T> extends ListView {


    //================================================
    //                  variables
    //================================================

    private Context context;
    private PackListAdapter adapter;
    private int layout;

    //================================================
    //                 constructor
    //================================================

    public PackCustomListView(Context context) {
        super(context);
        Init(context, 0);
    }

    public PackCustomListView(Context context, int layout) {
        super(context);
        Init(context, layout);
    }

    public PackCustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, 0);
    }

    public PackCustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PackCustomListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context, 0);
    }

    /*
        custom fuction
     */



    //================================================
    //                  init
    //================================================

    /**
     * initialize
     */

    private void Init(Context context, int layout){
        this.context = context;
        this.layout = layout;
        adapter = new PackListAdapter(this.context, this.layout);
        this.setAdapter(adapter);
    }



    //================================================
    //                  base setting
    //================================================

    /**
     * set view order using static final int
     * @param v
     */
    /*
    public void setViewOrder(int ... v){
        adapter.setViewOrder(v);
    }
     */


    /**
     * set id order by id
     * @param id
     */
    /*
    public void setIdOrder(int ... id){
        adapter.setIdOrder(id);
    }
    */

    public void setIdMatch(int id, int v){
        adapter.setIdMatch(id, v);
    }

    /**
     * set layout in adapter
     * @param layout
     */
    public void setLayout(int layout){
        adapter.setLayout(layout);
    }




    //================================================
    //                control item
    //================================================

    /**
     * add item
     * @param data
     */
    public void addItem(int index, T ... data){

        final PackListItem item = new PackListItem(data);
        adapter.addItem(index, item);
    }

    public void removeItem(int i){
        adapter.removeItem(i);
    }



    //================================================
    //                  getCount
    //================================================

    /**
     * total data getCount
     * @return
     */
    public int getCount(){
        return adapter.getCount();
    }




    //================================================
    //              animation layout
    //================================================

    /**
     * set animation at adding item
     * @param ani
     */
    public void setAddAniamtion(int ani){
        adapter.setAddAnimation(ani);
    }


    /**
     * set animation at removing item
     * @param ani
     * @param duration
     */
    public void setRemoveAnimation(int ani, int duration){
        adapter.setRemoveAnimation(ani, duration);
    }


    /**
     * set animation at over item
     * @param ani
     */
    public void setOverAnimation(int ani){
        adapter.setOverAnimation(ani);
    }


    /**
     * set animation at under item
     * @param ani
     */
    public void setUnderAnimation(int ani){
        adapter.setUnderAnimation(ani);
    }


    /**
     * set using animation
     * @param use
     */
    public void useAnimation(boolean use){
        adapter.useAnimation(use);
    }




    //================================================
    //                 fix height
    //================================================

    /**
     * reset height
     * @return
     */
    public void fixHeight() throws Exception{
        int listview_height = 0;


        for (int i = 0; i < adapter.getCount(); i++) {
            View listitem = adapter.getView(i, null, this);
            listitem.measure(0, 0);
            listview_height += listitem.getMeasuredHeight();
        }

        final ViewGroup.LayoutParams params = this.getLayoutParams();
        params.height = listview_height + this.getDividerHeight() * (adapter.getCount() - 1);

        this.setLayoutParams(params);
        this.deferNotifyDataSetChanged();

    }






    /***************************************************************
     * Pack listview adapter extends baseadapter
     ***************************************************************/

    class PackListAdapter<T extends View> extends BaseAdapter{



        //================================================
        //                  variables
        //================================================

        private Context context;
        private ArrayList<PackListItem> listitem;
        private LayoutInflater inflater;
        private int layout;
        private ArrayList<Integer> Layout_Id;
        private ArrayList<Integer> View_Order;


        //================================================
        //                  animation
        //================================================

        AnimationUtil animationUtil;
        private int remove_duration;
        private boolean useAnimation;


        //================================================
        //                  contstructor
        //================================================

        PackListAdapter(Context context, @NonNull int layout){
            this.context = context;
            listitem = new ArrayList<PackListItem>();
            inflater = LayoutInflater.from(this.context);
            this.layout = layout;

            /*
                animation values
             */
            this.remove_duration = 0;
            this.animationUtil = new AnimationUtil(this.context);
        }




        //================================================
        //                  get method
        //================================================

        /**
         * override get count
         * @return
         */
        @Override
        public int getCount() {
            return listitem.size();
        }


        /**
         * override get item
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return listitem.get(position);
        }


        /**
         * override get item id
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return position;
        }




        //================================================
        //             view item of listview
        //================================================

        /**
         * get view
         * This is item layout setting
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = ViewUtil.getInstance().getView(this.context,
                    position, convertView, parent,
                    layout, inflater,
                    listitem, Layout_Id, View_Order);

            if(useAnimation) // if using animation
                animationUtil.ViewAnimation(view, position, getFirstVisiblePosition(), getLastVisiblePosition());

            return view;
        }



        //================================================
        //             base setting in listview
        //================================================


        /**
         * setting view order using static final int
         * @param v
         * @return
         */
        /*
        public void setViewOrder(int ... v){

            if (View_Order == null) {
                View_Order = new ArrayList<Integer>();
            }

            for (int i : v) {
                View_Order.add(i);
            }
        }
        */


        /**
         * setting id order, and input arraylist(Group_Layout_Id)
         * @param id
         * @return
         */
        /*
        public void setIdOrder(int ... id){
            if(Layout_Id == null){
                Layout_Id = new ArrayList<Integer>();
            }

            for(int i : id){
                Layout_Id.add(i);
            }
        }
        */


        /**
         * set matching both id and view.
         * @param v
         * @param id
         */
        public void setIdMatch(int id, int v){
            if(View_Order == null){
                View_Order = new ArrayList<Integer>();
            }
            if(Layout_Id == null){
                Layout_Id = new ArrayList<Integer>();
            }

            Layout_Id.add(id);
            View_Order.add(v);
        }



        /**
         * set layout
         * @param layout
         */

        public void setLayout(int layout){
            this.layout = layout;
        }





        //================================================
        //             control item
        //================================================


        /**
         * add item in adapter
         * @param item
         */
        public void addItem(int index, PackListItem item){

            /*
                set add animation
             */
            animationUtil.SetAnimation(index, ADD);

            listitem.add(index, item);
            notifyDataSetChanged();

        }

        /**
         * remove item
         * @param i
         */
        public void removeItem(final int i){
            animationUtil.SetAnimation(i, REMOVE);

            this.notifyDataSetChanged(); //start animation of the item that will remove

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() { // remove item after 300 ms
                    try {
                        listitem.remove(i); //remove item
                        notifyDataSetChanged(); // remove item with no action
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }

                }
            }, remove_duration);
        }




        //================================================
        //             setting animation
        //================================================



        /**
         * set animation at adding item
         * @param ani
         */
        public void setAddAnimation(int ani){
            animationUtil.setAddAnimation(ani);
        }



        /**
         * set animation at removing item
         * @param ani
         * @param duration
         */
        public void setRemoveAnimation(int ani, int duration){
            animationUtil.setRemoveAnimation(ani);
            this.remove_duration = duration;
        }


        /**
         * set animation at over item
         * @param ani
         */
        public void setOverAnimation(int ani){
            animationUtil.setOverAnimation(ani);
        }


        /**
         * set animation at under item
         * @param ani
         */
        public void setUnderAnimation(int ani){
            animationUtil.setUnderAnimation(ani);
        }



        /**
         * use or not use animation
         * @param use
         */
        public void useAnimation(boolean use){
            this.useAnimation = use;
        }
    }
}
