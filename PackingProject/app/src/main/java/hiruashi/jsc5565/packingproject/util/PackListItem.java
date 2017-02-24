package hiruashi.jsc5565.packingproject.util;

import java.util.ArrayList;
import java.util.List;


/********************************************************************
 * pack listview item
 * create by 정수찬 (jung suchan)
 ********************************************************************/

public class PackListItem<T>{

    protected ArrayList<T> listitem;

    /**
     * constructor
     * When class is maked in others, users input data
     * @param data
     */
    public PackListItem(T... data){

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




