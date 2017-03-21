package hiruashi.jsc5565.packingproject.Packing;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import hiruashi.jsc5565.packingproject.R;
import hiruashi.jsc5565.packingproject.util.AnimationUtil;
import hiruashi.jsc5565.packingproject.util.PackListItem;
import hiruashi.jsc5565.packingproject.util.ViewUtil;

/**
 * Created by 정수찬 (jung suchan) on 2016-11-17.
 */

public class PackRecyclerView<T> extends RecyclerView {

    /*
        recycler list data
     */
    private int Layout;
    private PackRecyclerAdapter adapter;
    private Context context;

    /**
     * constructor
     * @param context
     */
    public PackRecyclerView(Context context) {
        super(context);
        Init(context);
    }

    public PackRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    public PackRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Init(context);
    }


    /**
     * Initialize recycler view
     */
    private void Init(Context context){
        Layout = 0;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.setLayoutManager(mLayoutManager);
        adapter = new PackRecyclerAdapter(context);
        this.setAdapter(adapter);
        this.setFocusable(true);
        this.setClickable(true);
    }


    /**
     * set layout
     * @param layout
     */
    public void setLayout(int layout){
        adapter.setLayout(layout);
    }


    /**
     * add item using index
     * @param data
     */
    public void addItem(int index, T...data){
        adapter.addItem(index, data);

    }


    /**
     * remove item
     * @param index
     */
    public void removeItem(int index){
        adapter.removeItem(index);
    }


    /**
     * set id order
     * @param id
     */
    public void setIdOrder(int ... id){
        adapter.setIdOrder(id);
    }


    /**
     * set view order
     * @param view
     */
    public void setViewOrder(int ... view){
        adapter.setView_Order(view);
    }


    /**
     * get count of items
     * @return
     */
    public int getCount(){
        return adapter.getItemCount();
    }



    /**************************************************
     * pack recycler adapter
     ***************************************************/
    public class PackRecyclerAdapter<T> extends RecyclerView.Adapter<PackRecyclerAdapter<T>.PackViewHolder>{
        private ArrayList<Integer> Layout_Id;
        private ArrayList<Integer> View_Order;
        private ArrayList<PackListItem> RecyclerList;
        private int Layout;
        private Context context;
        private AnimationUtil animationUtil;



        /****************************************
         * viewholder
         ***************************************/
        public class PackViewHolder extends RecyclerView.ViewHolder{
            List<View> ViewData;

            public PackViewHolder(View itemView) {
                super(itemView);
                ViewData = new ArrayList<View>();

                for(int i=0; i<Layout_Id.size(); i++){
                    ViewData.add(itemView.findViewById(Layout_Id.get(i)));
                }
            }

            /**
             * get view data
             * @return
             */
            public List<View> getViewData(){
                return ViewData;
            }

        }

        /***********************************
         * end viewholder
         ***********************************/



        /**
         * constructor
         */
        PackRecyclerAdapter(Context context){
            this.Layout = 0;
            this.context = context;
            RecyclerList = new ArrayList<PackListItem>();
            Layout_Id = new ArrayList<Integer>();
            View_Order = new ArrayList<Integer>();
            animationUtil = new AnimationUtil(context);
        }


        /**
         * set layout in adapter
         * @param Layout
         */
        public void setLayout(int Layout){
            this.Layout = Layout;
        }



        /**
         * set id order
         * @param id
         */
        public void setIdOrder(int ... id){
            for(int i : id){
                Layout_Id.add(i);
            }
        }


        /**
         * set view order
         * @param view
         */
        public void setView_Order(int ... view){
            for(int v : view){
                View_Order.add(v);
            }
        }


        /**
         * add item in recyclerlist
         * @param data
         */
        public void addItem(int index, T...data){
            RecyclerList.add(index, new PackListItem(data));
            this.notifyItemInserted(RecyclerList.size()-1);

        }


        /**
         * remove item in recyclerlist
         * @param index
         */
        public void removeItem(int index){
            RecyclerList.remove(index);
            this.notifyItemRemoved(index);
        }

        /**
         * oncreate view holder
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public PackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if(Layout == 0){
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            else{
                view = LayoutInflater.from(parent.getContext()).inflate(Layout, parent, false);
            }
            return new PackViewHolder(view);
        }




        /**
         * bind view and data
         * @param holder
         * @param position
         */

        int lastposition=0;
        @Override
        public void onBindViewHolder(PackViewHolder holder, int position) {

            ViewUtil.getInstance().RecyclerViewBind(context, holder, position, RecyclerList, Layout_Id, View_Order);


            if(position > lastposition) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.over_show);
                holder.itemView.startAnimation(animation);

                lastposition = position;
            }
        }




        /**
         * item's count
         * @return
         */
        @Override
        public int getItemCount() {
            return RecyclerList.size();
        }

    }

}
