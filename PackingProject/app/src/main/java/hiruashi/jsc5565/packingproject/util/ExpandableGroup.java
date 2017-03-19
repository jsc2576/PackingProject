package hiruashi.jsc5565.packingproject.util;

import java.util.ArrayList;
import java.util.List;

/************************************************************
 * Expandable parent class
 * create by 정수찬 (jung suchan)
 * Expandable child class use the PackListItem class.
 *************************************************************/
public class ExpandableGroup<T> extends PackListItem{


    /*
        listitem is groupData
     */
    private ArrayList<PackListItem> ChildData;

    /**
     * constructor
     * @param data
     */
    public ExpandableGroup(T... data){
        ChildData = new ArrayList<PackListItem>();

        for(T d : data){
            listitem.add(d);
        }
    }


    /**
     * get data
     * @return
     */
    public List<T> getGroupItem(){
        return listitem;
    }


    /**
     * set child data
     * @param child
     */
    public void addChildItem(int index, PackListItem child){
        ChildData.add(index, child);
    }


    /**
     * get child data array
     * @return
     */
    public ArrayList<PackListItem> getChildList(){
        return ChildData;
    }


    /**
     * get a child data
     * @param position
     * @return
     */
    public PackListItem getChildItem(int position){
        return ChildData.get(position);
    }
}
