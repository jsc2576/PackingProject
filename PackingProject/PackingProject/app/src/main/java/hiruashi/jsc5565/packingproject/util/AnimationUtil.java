package hiruashi.jsc5565.packingproject.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
/**
 * Created by 정수찬 (jung suchan) on 2017. 1. 27..
 */

public class AnimationUtil {

    /*
    animation's action
     */
    public static final int NO_ACTION = 10;
    public static final int ADD = 11;
    public static final int REMOVE = 12;


    private Animation animation;
    private int over_show_animation, under_show_animation, add_show_animation, remove_show_animation;
    private Context context;
    private int visible_item_count;

    private ArrayList<Integer> check_animation;
    /*****************************************************
                        contstructor
     *****************************************************/

    public AnimationUtil(Context context){
        Init(context, 0, 0, 0, 0);
    }

    public AnimationUtil(Context context, int AddAnimation, int RemoveAnimation){
        Init(context, AddAnimation, RemoveAnimation, 0, 0);
    }

    public AnimationUtil(Context context, int AddAnimation, int RemoveAnimation, int OverAnimation, int UnderAnimation){
        Init(context, AddAnimation, RemoveAnimation, OverAnimation, UnderAnimation);
    }

    private void Init(Context context, int AddAnimation, int RemoveAnimation, int OverAnimation, int UnderAnimation){
        this.context = context;
        this.add_show_animation = AddAnimation;
        this.remove_show_animation = RemoveAnimation;
        this.over_show_animation = OverAnimation;
        this.under_show_animation = UnderAnimation;
        this.visible_item_count = 0;
        this.check_animation = new ArrayList<Integer>();

    }


    /*****************************************************
                        set animation
     *****************************************************/

    public void SetAnimation(final int index, int value){

        switch (value){
            case ADD:
                check_animation.add(index, value);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        check_animation.set(index, NO_ACTION);
                    }
                }, 100);
                break;
            case REMOVE:
                check_animation.set(index, value);
                break;
            default:
                break;
        }
    }


    /*****************************************************
                        view animation
     *****************************************************/


    public View ViewAnimation(View view, int position, int first_position, int last_position){
        if(check_animation.size() < visible_item_count){
            visible_item_count = check_animation.size();
        }
        else if(visible_item_count < (last_position - first_position)){
            visible_item_count = last_position - first_position;
        }

        if(check_animation.size() <= 0){}
        else if(check_animation.get(position) == ADD){
            if(add_show_animation != 0){
                animation = AnimationUtils.loadAnimation(context, add_show_animation);
            }
            check_animation.set(position, NO_ACTION);

        }

        else if(check_animation.get(position) == REMOVE){
            if(remove_show_animation != 0){
                animation = AnimationUtils.loadAnimation(context, remove_show_animation);
            }
            check_animation.remove(position);
        }

        else if (position < first_position) {
            if (over_show_animation != 0) {
                animation = AnimationUtils.loadAnimation(context, over_show_animation);
            }
        } else if (position >= first_position + visible_item_count) {
            if (under_show_animation != 0) {
                animation = AnimationUtils.loadAnimation(context, under_show_animation);
            }
        }

        if(animation != null){
            view.startAnimation(animation);
        }

        animation = null;
        return view;

    }



    /*
        set animation layout
     */


    /**
     * set add animation
     * @param layout
     */
    public void setAddAnimation(int layout){
        this.add_show_animation = layout;
    }


    /**
     * set remove animation
     * @param layout
     */
    public void setRemoveAnimation(int layout){
        this.remove_show_animation = layout;
    }


    /**
     * set over animation
     * @param layout
     */
    public void setOverAnimation(int layout){
        this.over_show_animation = layout;
    }


    /**
     * set under animation
     * @param layout
     */
    public void setUnderAnimation(int layout){
        this.under_show_animation = layout;
    }

}
