package hiruashi.jsc5565.packingproject;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsc55 on 2016-11-17.
 */

public class PackRecyclerView<T> extends RecyclerView {

    /*
        view type
     */
    public final static int TEXT = 0;
    public final static int IMAGE = 1;
    public final static int IMAGEURI = 2;

    /*
        recycler list data
     */
    private int Layout;
    private PackRecyclerAdapter adapter;

    /**
     * constructor
     * @param context
     */
    public PackRecyclerView(Context context) {
        super(context);
        Init();
    }

    public PackRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public PackRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Init();
    }


    /**
     * Initialize recycler view
     */
    private void Init(){
        Layout = 0;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        this.setLayoutManager(mLayoutManager);
        adapter = new PackRecyclerAdapter();
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
        adapter.setViewOrder(view);
    }

    public int size(){
        return adapter.getItemCount();
    }


    /**
     * set onItemClickListener in adapter
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        adapter.setOnItemClickListener(onItemClickListener);
    }

    /**************************************************
     * pack recycler adapter
     ***************************************************/
    class PackRecyclerAdapter<T> extends RecyclerView.Adapter<PackRecyclerAdapter<T>.PackViewHolder>{
        private List<Integer> Layout_Id;
        private List<Integer> ViewOrder;
        private List<RecyclerItem> RecyclerList;
        private int Layout;
        private OnItemClickListener mItemClickListenter;

        /****************************************
         * viewholder
         ***************************************/
        class PackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            List<View> ViewData;

            public PackViewHolder(View itemView) {
                super(itemView);
                ViewData = new ArrayList<View>();

                for(int i=0; i<Layout_Id.size(); i++){
                    ViewData.add(itemView.findViewById(Layout_Id.get(i)));
                }
                itemView.setOnClickListener(this);
            }

            /**
             * get view data
             * @return
             */
            public List<View> getViewData(){
                return ViewData;
            }


            /**
             * use making onItemClickListener
             * @param v
             */
            @Override
            public void onClick(View v) {
                if(mItemClickListenter != null)
                    mItemClickListenter.ItemClick(getAdapterPosition(), v);
            }
        }

        /***********************************
         * viewholder
         ***********************************/


        /**
         * constructor
         */
        PackRecyclerAdapter(){
            this.Layout = 0;
            RecyclerList = new ArrayList<RecyclerItem>();
            Layout_Id = new ArrayList<Integer>();
            ViewOrder = new ArrayList<Integer>();
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
        public void setViewOrder(int ... view){
            for(int v : view){
                ViewOrder.add(v);
            }
        }


        /**
         * add item in recyclerlist
         * @param data
         */
        public void addItem(int index, T...data){
            RecyclerList.add(index, new RecyclerItem(data));
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
            RecyclerItem recyclerItem = RecyclerList.get(position);

            for(int i=0; i<Layout_Id.size(); i++){
                if(ViewOrder.get(i) == TEXT){
                    ((TextView)holder.getViewData().get(i)).setText((String)recyclerItem.getListItem().get(i));
                }
                else if(ViewOrder.get(i) == IMAGE){
                    ((ImageView)holder.getViewData().get(i)).setImageResource((int)recyclerItem.getListItem().get(i));
                }
                else if(ViewOrder.get(i)== IMAGEURI){
                    ((ImageView)holder.getViewData().get(i)).setImageURI((Uri)recyclerItem.getListItem().get(i));
                }
            }

            if(position > lastposition) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.center_from_right);
                for(int i=0; i<holder.getViewData().size(); i++) {
                    holder.getViewData().get(i).startAnimation(animation);
                }
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


        /**
         * setOnItemClickListener in RecyclerView
         * @param onItemClickListener
         */
        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.mItemClickListenter = onItemClickListener;
        }
    }

    /**************************************************
     * pack recycler adapter
     ***************************************************/

    /**L
     * recycler view item class
     * @param <T>
     */
    class RecyclerItem<T>{
        private List<T> ListItem;
        RecyclerItem(T...data){
            ListItem = new ArrayList<T>();

            for(T d : data){
                ListItem.add(d);
            }
        }

        /**
         * get listitem
         * @return
         */
        public List<T> getListItem(){
            return ListItem;
        }
    }

    /**
     * interface OnItemClickListener in RecyclerView
     */
    public interface OnItemClickListener{
        void ItemClick(int position, View itemView);
    }
}
