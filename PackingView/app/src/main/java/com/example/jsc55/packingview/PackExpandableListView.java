package com.example.jsc55.packingview;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsc55 on 2016-11-14.
 */

public class PackExpandableListView<T> extends ExpandableListView {

    final static int TEXT = 0;
    final static int IMAGE = 1;
    final static int IMAGEURI = 2;

    private Context context;
    private PackExpandableAdpater adapter;
    private int Group_Layout, Child_Layout;

    /*
        constructors
     */
    public PackExpandableListView(Context context) {
        super(context);
        Init(context, 0, 0);
    }

    public PackExpandableListView(Context context, int Group_Layout, int Child_Layout) {
        super(context);
        Init(context, Group_Layout, Child_Layout);
    }

    public PackExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context, 0, 0);
    }

    public PackExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init(context, 0, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PackExpandableListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init(context, 0, 0);
    }


    /**
     * initialize
     * @param context
     * @param Group_Layout
     * @param Child_Layout
     */
    private void Init(Context context, int Group_Layout, int Child_Layout){
        this.context = context;
        this.Group_Layout = Group_Layout;
        this.Child_Layout = Child_Layout;

        adapter = new PackExpandableAdpater(context, this.Group_Layout, this.Child_Layout);
        this.setAdapter(adapter);

    }


    /**
     * add group
     * @param data
     */
    public void addGroupItem(int index, T...data){
        ExpandableGroup group = new ExpandableGroup(data);
        adapter.addGroupItem(index, group);
    }


    /**
     * add child in group
     * @param GroupPosition
     * @param data
     */
    public void addChildItem(int GroupPosition, int index, T...data){
        ExpandableChild child = new ExpandableChild(data);
        adapter.addChildItem(GroupPosition, index, child);
    }


    /**
     * remove group item
     * @param GroupPosition
     */
    public void removeGroupItem(int GroupPosition){
        adapter.removeGroupItem(GroupPosition);
    }


    /**
     * remove child item in group
     * @param GroupPosition
     * @param ChildPosition
     */
    public void removeChildItem(int GroupPosition, int ChildPosition){
        adapter.removeChildItem(GroupPosition, ChildPosition);
    }

    /**
     * set group layout
     * @param Group_Layout
     */
    public void setGroupLayout(int Group_Layout){
        adapter.setGroupLayout(Group_Layout);
    }


    /**
     * set child layout
     * @param Child_Layout
     */
    public void setChildLayout(int Child_Layout){
        adapter.setChildayout(Child_Layout);
    }


    /**
     * group size
     * @return
     */
    public int GroupSize(){
        return adapter.GroupData.size();
    }


    /**
     * set group id order
     * @param id
     */
    public void setGroupIdOrder(int ... id){
        adapter.setGroupIdOrder(id);
    }


    /**
     * set child id order
     * @param id
     */
    public void setChildIdOrder(int ... id){
        adapter.setChildIdOrder(id);
    }


    /**
     * set group view order
     * @param view
     */
    public void setGroupViewOrder(int ... view){
        adapter.setGroupViewOrder(view);
    }


    /**
     * set child view order
     * @param view
     */
    public void setChildViewOrder(int ... view){
        adapter.setChildViewOrder(view);
    }

    /**
     * child size
     * @param position
     * @return
     */
    public int ChildSize(int position){
        return adapter.GroupData.get(position).getChildList().size();
    }



    /******************************************************************
     * Pack Expandable adapter
     ******************************************************************/
    class PackExpandableAdpater extends BaseExpandableListAdapter{

        Context context;
        LayoutInflater inflater;
        List<ExpandableGroup> GroupData;
        List<Integer> Group_Layout_Id, Group_View_Order;
        List<Integer> Child_Layout_Id, Child_View_Order;
        int Group_Layout, Child_Layout;

        /**
         * contstructor
         * @param context
         */
        PackExpandableAdpater(Context context, int Group_Layout, int Child_Layout){
            this.context = context;
            this.GroupData = new ArrayList<>();

            this.Group_Layout = Group_Layout;
            this.Child_Layout = Child_Layout;

            Group_Layout_Id = new ArrayList<Integer>();
            Group_View_Order = new ArrayList<Integer>();

            Child_Layout_Id = new ArrayList<Integer>();
            Child_View_Order = new ArrayList<Integer>();
        }


        /**
         * get parent size
         * @return
         */
        @Override
        public int getGroupCount() {
            return GroupData.size();
        }

        /**
         * get child size
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            return GroupData.get(groupPosition).getChildList().size();
        }

        /**
         * get a group object
         * @param groupPosition
         * @return
         */
        @Override
        public Object getGroup(int groupPosition) {
            return GroupData.get(groupPosition);
        }

        /**
         * get a child object
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return GroupData.get(groupPosition).getChildList().get(childPosition);
        }

        /**
         * get a group position
         * @param groupPosition
         * @return
         */
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        /**
         * get a child postion in group
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        /**
         *
         * @return
         */
        @Override
        public boolean hasStableIds() {
            return true;
        }


        /**
         * group view
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if(convertView == null){
                inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(Group_Layout == 0){
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                    return convertView;
                }
                convertView = inflater.inflate(Group_Layout, parent, false);
            }

            ExpandableGroup Group_Item = GroupData.get(groupPosition);

            for(int i = 0; i<Group_Item.getItem().size(); i++){
                if(Group_View_Order.get(i) == TEXT){
                    TextView textview = (TextView)convertView.findViewById(Group_Layout_Id.get(i));
                    textview.setText((String)Group_Item.getItem().get(i));
                }

                else if(Group_View_Order.get(i) == IMAGE){
                    ImageView imageview = (ImageView)convertView.findViewById(Group_Layout_Id.get(i));
                    imageview.setImageResource((int)Group_Item.getItem().get(i));
                }

                else if(Group_View_Order.get(i) == IMAGEURI){
                    ImageView imageview = (ImageView)convertView.findViewById(Group_Layout_Id.get(i));
                    imageview.setImageURI((Uri)Group_Item.getItem().get(i));
                }
            }

            return convertView;
        }


        /**
         * child view
         * @param groupPosition
         * @param childPosition
         * @param isLastChild
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if(convertView == null){
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(Child_Layout == 0){
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                    return convertView;
                }
                convertView = inflater.inflate(Child_Layout, parent, false);

            }

            ExpandableChild Child_Item = GroupData.get(groupPosition).getChildItem(childPosition);

            for(int i = 0; i<Child_Item.getItemList().size(); i++){

                if(Child_View_Order.get(i) == TEXT){
                    TextView textView = (TextView)convertView.findViewById(Child_Layout_Id.get(i));
                    textView.setText((String)Child_Item.getItemList().get(i));
                }
                else if(Child_View_Order.get(i) == IMAGE){
                    ImageView imageView = (ImageView)convertView.findViewById(Child_Layout_Id.get(i));
                    imageView.setImageResource((int)Child_Item.getItemList().get(i));
                }
                else if(Child_View_Order.get(i) == IMAGEURI){
                    ImageView imageView = (ImageView)convertView.findViewById(Child_Layout_Id.get(i));
                    imageView.setImageURI((Uri)Child_Item.getItemList().get(i));
                }
            }
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }


        /**
         * set group id order
         * @param id
         */
        public void setGroupIdOrder(int ... id){
            if(Group_Layout_Id == null){
                Group_Layout_Id = new ArrayList<Integer>();
            }

            for(int i : id){
                Group_Layout_Id.add(i);
            }
        }


        /**
         * set group view order
         * @param order
         */
        public void setGroupViewOrder(int ... order){
            if(Group_View_Order == null){
                Group_View_Order = new ArrayList<Integer>();
            }

            for(int view : order){
                Group_View_Order.add(view);
            }
        }


        /**
         * set child id order
         * @param id
         */
        public void setChildIdOrder(int ... id){
            if(Child_Layout_Id == null){
                Child_Layout_Id = new ArrayList<Integer>();
            }

            for(int i : id){
                Child_Layout_Id.add(i);
            }
        }


        /**
         * set child view order
         * @param order
         */
        public void setChildViewOrder(int ... order){
            if(Child_View_Order == null){
                Child_View_Order = new ArrayList<Integer>();
            }

            for(int view : order){
                Child_View_Order.add(view);
            }
        }


        /**
         * add group item
         * @param group
         */
        public void addGroupItem(int index, ExpandableGroup group){
            GroupData.add(index, group);
        }


        /**
         * add child item
         * @param groupPosition
         * @param child
         */
        public void addChildItem(int groupPosition, int index, ExpandableChild child){
            GroupData.get(groupPosition).addChildItem(index, child);
        }


        /**
         * remove group item
         * @param GroupPosition
         */
        public void removeGroupItem(int GroupPosition){
            GroupData.remove(GroupPosition);
        }


        /**
         * remove child item in group
         * @param GroupPosition
         * @param ChildPosition
         */
        public void removeChildItem(int GroupPosition, int ChildPosition){
            GroupData.get(GroupPosition).getChildList().remove(ChildPosition);
        }


        /**
         * set group layout
         * @param Group_Layout
         */
        public void setGroupLayout(int Group_Layout){
            this.Group_Layout = Group_Layout;
        }


        /**
         * set child layout
         * @param Child_Layout
         */
        public void setChildayout(int Child_Layout){
            this.Child_Layout = Child_Layout;
        }
    }


    /**************************************************
     * expandable child class
     * @param <T>
     ***************************************************/
    class ExpandableChild<T>{

        private List<T> ChildData;

        /**
         * constructor
         * @param data
         */
        ExpandableChild(T...data){
            ChildData = new ArrayList<T>();

            for(T d : data){
                ChildData.add(d);
            }
        }


        /**
         * get data
         * @return
         */
        public List<T> getItemList(){
            return ChildData;
        }
    }


    /************************************************************
     * Expandable parent class
     *************************************************************/
    class ExpandableGroup<T>{

        private List<T> GroupData;
        private List<ExpandableChild> ChildData;

        /**
         * constructor
         * @param data
         */
        ExpandableGroup(T ... data){
            GroupData = new ArrayList<T>();
            ChildData = new ArrayList<ExpandableChild>();

            for(T d : data){
                GroupData.add(d);
            }
        }


        /**
         * get data
         * @return
         */
        public List<T> getItem(){
            return GroupData;
        }


        /**
         * set child data
         * @param child
         */
        public void addChildItem(int index, ExpandableChild child){
            ChildData.add(index, child);
        }


        /**
         * get child data array
         * @return
         */
        public List<ExpandableChild> getChildList(){
            return ChildData;
        }


        /**
         * get a child data
         * @param position
         * @return
         */
        public ExpandableChild getChildItem(int position){
            return ChildData.get(position);
        }
    }
}
