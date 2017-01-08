package hiruashi.jsc5565.packingproject;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.graphics.BitmapCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by jsc55 on 2016-10-29.
 */


public class PackCustomListView<T> extends ListView{

    /*
        data type
     */
    static final int TEXT = 0;
    static final int IMAGE_DRAWABLE = 1;
    static final int IMAGE_URI = 2;
    static final int IMAGE_ASSETS = 3;

    /*
        variables
     */
    private Context context;
    private PackListAdapter adapter;
    private int layout;


    /*
        constructor
     */
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


    /**
     * initialize
     */

    private void Init(Context context, int layout){
        this.context = context;
        this.layout = layout;
        adapter = new PackListAdapter(this.context, this.layout);
        this.setAdapter(adapter);
    }


    /**
     * set view order using static final int
     * @param v
     */
    public void setViewOrder(int ... v){
        adapter.setViewOrder(v);
    }


    /**
     * set id order by id
     * @param id
     */
    public void setIdOrder(int ... id){
        adapter.setIdOrder(id);
    }

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

    /**
     * set layout in adapter
     * @param layout
     */
    public void setLayout(int layout){
        adapter.setLayout(layout);
    }


    /**
     * total data size
     * @return
     */
    public int size(){
        return adapter.getCount();
    }


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
     * reset height
     * @return
     */
    public boolean fixHeight() {
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

        } catch (Exception e) {
            return false;
        }
    }

    /***************************************************************
     * Pack listview adapter extends baseadapter
     ***************************************************************/

    class PackListAdapter<T extends View> extends BaseAdapter{

        /*
            animation's action
         */
        static final int ADD = 10;
        static final int REMOVE = 11;
        static final int NO_ACTION = 12;


        /*
            variables
         */
        private Context context;
        private ArrayList<PackListItem> listitem;
        private LayoutInflater inflater;
        private int layout;
        private ArrayList<Integer> Layout_Id;
        private ArrayList<Integer> View_Order;
        /* check animation position */


        /*
         * ani_position
         * get position at add and remove functions, and start animation that match item's position
         */
        private int layout_add_animation, getLayout_remove_animation;
        private int remove_duration;
        private ArrayList<Boolean> check_animation;


        /*
         * 0 = add
         * 1 = remove
         * 2 = no action
         */
        private int ani_action = ADD;

        /*
            cosntructor
         */
        PackListAdapter(Context context, @NonNull int layout){
            this.context = context;
            listitem = new ArrayList<PackListItem>();
            inflater = LayoutInflater.from(this.context);
            this.layout = layout;

            /*
                animation values
             */
            this.layout_add_animation = 0;
            this.getLayout_remove_animation = 0;
            this.remove_duration = 0;
            this.check_animation = new ArrayList<>();
        }


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

            View view = convertView;

            if(view == null){
                if(layout == 0){
                    view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                    return view;
                }
                view = inflater.inflate(layout, parent, false);
            }

            PackListItem item = listitem.get(position);

            /*
                connect
             */
            for(int i=0; i<item.getItem().size(); i++){

                // if imageview
                if(View_Order.get(i) == IMAGE_DRAWABLE){
                    ImageView imageview = (ImageView)view.findViewById(Layout_Id.get(i));
                    imageview.setImageResource((int)item.getItem().get(i));
                }

                //if textview
                else if(View_Order.get(i) == TEXT){
                    TextView textView = (TextView)view.findViewById(Layout_Id.get(i));
                    textView.setText((String)item.getItem().get(i));
                }

                //if imageview by url
                else if(View_Order.get(i) == IMAGE_URI){
                    ImageView imageView = (ImageView)view.findViewById(Layout_Id.get(i));
                    imageView.setImageURI((Uri)item.getItem().get(i));
                }

                //if imageview by assets' images

                else if(View_Order.get(i) == IMAGE_ASSETS){

                    ImageView imageView = (ImageView)view.findViewById(Layout_Id.get(i));
                    AssetManager assetManager = this.context.getAssets();

                    //inputstream in assets
                    try{
                        InputStream inputStream = assetManager.open((String)item.getItem().get(i));
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        inputStream.close();
                        imageView.setImageBitmap(bitmap);

                    }catch (IOException e1){
                        e1.printStackTrace();
                    }
                }
            }

            /*
                animation
             */
            Animation animation;

            if(layout_add_animation == 0){
                return view;
            }
            else if(ani_action == ADD) {// add action
                if(layout_add_animation == 0){ // no animation
                    return view;
                }
                animation = AnimationUtils.loadAnimation(context, layout_add_animation);
            }
            else { // remove action
                if(getLayout_remove_animation == 0){ // no animation
                    return view;
                }
                animation = AnimationUtils.loadAnimation(context, getLayout_remove_animation);
            }

            if(!check_animation.get(position)){ // position is correct with animation position item.
                if(ani_action != NO_ACTION) {//if not animation position
                    view.startAnimation(animation);
                    check_animation.set(position, true);
                }
            }

            return view;
        }


        /**
         * setting view order using static final int
         * @param v
         * @return
         */
        public void setViewOrder(int ... v){

            if (View_Order == null) {
                View_Order = new ArrayList<Integer>();
            }

            for (int i : v) {
                View_Order.add(i);
            }
        }


        /**
         * setting id order, and input arraylist(Group_Layout_Id)
         * @param id
         * @return
         */
        public void setIdOrder(int ... id){
            if(Layout_Id == null){
                Layout_Id = new ArrayList<Integer>();
            }

            for(int i : id){
                Layout_Id.add(i);
            }
        }


        /**
         * add item in adapter
         * @param item
         */
        public void addItem(int index, PackListItem item){
            ani_action = ADD; // action is add
            listitem.add(index, item);
            check_animation.add(index, false);// set animation position
            notifyDataSetChanged();
        }

        /**
         * remove item
         * @param i
         */
        public void removeItem(final int i){
            ani_action = REMOVE; // action is remove
            check_animation.set(i, false); // set position to animate

            this.notifyDataSetChanged(); //start animation of the item that will remove

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() { // remove item after 300 ms
                    try {
                        listitem.remove(i); //remove item
                        ani_action = NO_ACTION; // no action
                        notifyDataSetChanged(); // remove item with no action
                    }catch (IndexOutOfBoundsException e){
                        Log.i("PackCustomListView", "Remove Failed");
                    }

                }
            }, remove_duration);
        }


        /**
         * set animation at adding item
         * @param ani
         */
        public void setAddAnimation(int ani){
            this.layout_add_animation = ani;
        }



        /**
         * set animation at removing item
         * @param ani
         * @param duration
         */
        public void setRemoveAnimation(int ani, int duration){
            this.getLayout_remove_animation = ani;
            this.remove_duration = duration;
        }



        /**
         * set layout
         * @param layout
         */

        public void setLayout(int layout){
            this.layout = layout;
        }
    }

    /********************************************************************
     * pack listview item
     ********************************************************************/

    class PackListItem<T>{

        private ArrayList<T> listitem;

        /**
         * constructor
         * When class is maked in others, users input data
         * @param data
         */
        PackListItem(T...data){

            listitem = new ArrayList<T>();

            for(T getData : data){
                listitem.add(getData);
            }
        }

        /**
         * get list item
         * @return
         */
        public ArrayList<T> getItem(){
            return listitem;
        }
    }
}
