package hiruashi.jsc5565.packingproject.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import hiruashi.jsc5565.packingproject.Packing.PackRecyclerView;

/**
 * Created by 정수찬 (jung suchan) on 2017. 1. 24..
 */

public class ViewUtil<T extends PackListItem> {

    /*
    data type
     */

    public static final int TEXT = 0;
    public static final int IMAGE_RESOURCE = 1;
    public static final int IMAGE_URI = 2;
    public static final int IMAGE_ASSETS = 3;
    public static final int BITMAP = 4;
    public static final int BUTTON = 5;
    public static final int IMAGEBUTTON = 6;
    public static final int SWITCH = 7;
    public static final int RADIOBUTTON = 8;

    private HolderActionListner holderActionListner;
    private ViewActionListener viewActionListener;

    /**
     * conduct to bind view and data.
     * @param context
     * @param position
     * @param convertView
     * @param parent
     * @param layout
     * @param inflater
     * @param listitem
     * @param Layout_Id
     * @param View_Order
     * @return
     */
    public View getView(Context context,
                        int position, View convertView, ViewGroup parent,
                        int layout, LayoutInflater inflater,
                        ArrayList<T> listitem, ArrayList<Integer> Layout_Id, ArrayList<Integer> View_Order){


        View view = ViewInflate(convertView, inflater, layout, parent);

        return ViewBind(context, view, position, listitem, Layout_Id, View_Order);
    }


    /**
     * view inflator setting
     *
     * @param view
     * @param inflater
     * @param layout
     * @param parent
     * @return
     */
    private View ViewInflate(View view, LayoutInflater inflater, int layout, ViewGroup parent){

        if(view == null){
            if(layout == 0){
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                return view;
            }
            view = inflater.inflate(layout, parent, false);
        }

        return view;
    }


    /**
     * Binding view in common list views
     * @param context
     * @param view
     * @param position
     * @param listitem
     * @param Layout_Id
     * @param View_Order
     * @return
     */
    private View ViewBind(Context context, View view, int position, ArrayList<T> listitem, ArrayList<Integer> Layout_Id, ArrayList<Integer> View_Order){
        return Bind(context, view, position, listitem, Layout_Id, View_Order, null);
    }


    /**
     * Binding view in the recyclerView
     * @param context
     * @param holder
     * @param position
     * @param listitem
     * @param Layout_Id
     * @param View_Order
     */
    public View RecyclerViewBind(Context context, PackRecyclerView.PackRecyclerAdapter.PackViewHolder holder, int position, ArrayList<T> listitem, ArrayList<Integer> Layout_Id, ArrayList<Integer> View_Order){

        return Bind(context, null, position, listitem, Layout_Id, View_Order, holder);

    }


    /**
     * Bind view method
     * @param context
     * @param view
     * @param position
     * @param listitem
     * @param Layout_Id
     * @param View_Order
     * @param holder
     * @return
     */
    private View Bind(Context context, View view, int position, ArrayList<T> listitem, ArrayList<Integer> Layout_Id, ArrayList<Integer> View_Order, PackRecyclerView.PackRecyclerAdapter.PackViewHolder holder){

        T item = listitem.get(position);

            /*
                connect
             */
        for(int i=0; i<item.getItem().size(); i++){

            //if textview
            if(View_Order.get(i) == TEXT){
                if(holder == null) {
                    setText((TextView) view.findViewById(Layout_Id.get(i)), (String) item.getItem().get(i));
                }
                else{
                    setText((TextView)holder.getViewData().get(i), (String)item.getItem().get(i));
                }
            }
            // if imageview
            else if(View_Order.get(i) == IMAGE_RESOURCE){
                if(holder == null) {
                    setImageResource((ImageView) view.findViewById(Layout_Id.get(i)), (int) item.getItem().get(i));
                }
                else{
                    setImageResource((ImageView)holder.getViewData().get(i), (int) item.getItem().get(i));
                }
            }

            //if imageview by url
            else if(View_Order.get(i) == IMAGE_URI){
                if(holder == null) {
                    setImageUri((ImageView) view.findViewById(Layout_Id.get(i)), (Uri) item.getItem().get(i));
                }
                else {
                    setImageUri((ImageView) holder.getViewData().get(i), (Uri) item.getItem().get(i));
                }
            }

            //if imageview by assets' images

            else if(View_Order.get(i) == IMAGE_ASSETS){

                if(holder == null) {
                    setImageAssets(context, (ImageView) view.findViewById(Layout_Id.get(i)), (String)item.getItem().get(i));
                }
                else{
                    setImageAssets(context, (ImageView) holder.getViewData().get(i), (String)item.getItem().get(i));
                }
            }

            else if(View_Order.get(i) == BITMAP){

                if(holder == null) {
                    setImageBitmap((ImageView) view.findViewById(Layout_Id.get(i)), (Bitmap)item.getItem().get(i));
                }
                else{
                    setImageBitmap((ImageView) holder.getViewData().get(i), (Bitmap)item.getItem().get(i));
                }
            }

            else if(View_Order.get(i) == BUTTON){

                if(holder == null) {
                    setButton((Button) view.findViewById(Layout_Id.get(i)), (String)item.getItem().get(i));
                }
                else{
                    setButton((Button) holder.getViewData().get(i), (String)item.getItem().get(i));
                }
            }


            else if(View_Order.get(i) == IMAGEBUTTON){
                if(holder == null) {
                    setImageButton((ImageButton) view.findViewById(Layout_Id.get(i)), (int)item.getItem().get(i));
                }
                else{
                    setImageButton((ImageButton) holder.getViewData().get(i), (int)item.getItem().get(i));
                }
            }
            else if(View_Order.get(i) == SWITCH){
                if (holder == null) {
                    setSwitch((Switch)view.findViewById(Layout_Id.get(i)), (boolean)item.getItem().get(i));
                }
                else{
                    setSwitch((Switch) holder.getViewData().get(i), (boolean)item.getItem().get(i));
                }
            }
            else if(View_Order.get(i) == RADIOBUTTON){
                if(holder == null){
                    setRadiobutton((RadioButton)view.findViewById(Layout_Id.get(i)), (boolean)item.getItem().get(i));
                }
                else{
                    setRadiobutton((RadioButton)holder.getViewData().get(i), (boolean)item.getItem().get(i));
                }
            }
        }

        if(this.holderActionListner != null){
            try{
                holderActionListner.getChildHolder(holder, position);
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        if(this.viewActionListener != null){
            try{
                viewActionListener.getChildView(view, position);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return view;
    }


    /**
     * set action of holder in recyclerview
     */
    public interface HolderActionListner{
        public void getChildHolder(PackRecyclerView.PackRecyclerAdapter.PackViewHolder holder, int position);
    }


    /**
     * set holder action in recyclerview
     * @param holderActionListner
     */
    public void setHolderActionListner(HolderActionListner holderActionListner){
        this.holderActionListner = holderActionListner;
    }


    /**
     * set view action except recyclerview
     */
    public interface ViewActionListener{
        public void getChildView(View view, int position);
    }

    public void setViewActionListener(ViewActionListener viewActionListener){
        this.viewActionListener = viewActionListener;
    }


    /**
     * get instance
     * @return
     */
    static public ViewUtil getInstance(){
        return new ViewUtil();
    }


    /*******************************************
     *              set resource
     *******************************************/


    private void setText(TextView textView, String text){
        textView.setText(text);
    }


    private void setImageResource(ImageView imageDrawable, int drawable){
        imageDrawable.setImageResource(drawable);
    }


    private void setImageUri(ImageView imageUri, Uri uri){
        imageUri.setImageURI(uri);
    }


    private void setImageAssets(Context context, ImageView imageAssets, String imagePath){
        AssetManager assetManager = context.getAssets();

        //inputstream in assets
        try{
            InputStream inputStream = assetManager.open(imagePath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            inputStream.close();

            imageAssets.setImageBitmap(bitmap);

        }catch (IOException e1){
            Log.e("ImageAssets", "ERROR - " + e1);
            e1.printStackTrace();
        }
    }

    private void setImageBitmap(ImageView imgBitmap, Bitmap bitmap){
        imgBitmap.setImageBitmap(bitmap);
    }


    private void setButton(Button button, String text){
        button.setText(text);
    }


    private void setImageButton(ImageButton imageButton, int drawable){
        imageButton.setImageResource(drawable);
    }

    private void setSwitch(Switch m_switch, boolean value){
        m_switch.setChecked(value);
    }

    private void setRadiobutton(RadioButton radiobutton, boolean value){
        radiobutton.setChecked(value);
    }
}

