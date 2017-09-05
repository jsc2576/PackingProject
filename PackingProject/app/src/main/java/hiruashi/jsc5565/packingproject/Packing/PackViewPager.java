package hiruashi.jsc5565.packingproject.Packing;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 정수찬 (jung suchan) on 2016-11-15.
 */

public class PackViewPager extends ViewPager{

    /*
        variables
     */
    private Context context;
    private PackViewPagerAdater adapter;

    /**
     * contstructor
     * @param context
     */
    public PackViewPager(Context context) {
        super(context);
        Init(context);
    }

    /**
     * constructor
     * @param context
     * @param attrs
     */
    public PackViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);

    }


    /**
     * Init
     * @param context
     */
    private void Init(Context context){

        this.context = context;
        ((FragmentActivity)this.context).getSupportFragmentManager();
        adapter = new PackViewPagerAdater(((FragmentActivity)this.context).getSupportFragmentManager());
        this.setAdapter(adapter);

    }


    /**
     * add fragments
     * @param fm
     */
    public void addFragments(Fragment ... fm){
        for(Fragment f : fm)
            adapter.addFragment(f);
    }


    public void addFragment(int position, Fragment fm){
        adapter.addFragment(position, fm);
    }

    /**
     * remove fragment1 by index
     * @param index
     */
    public void removeFragment(int index){
        adapter.removeFragment(index);
    }


    /**
     * add fragment title
     * @param title
     */
    public void addFragmentTitles(String ...title){
        adapter.addFragmentTitles(title);
    }

    public void addFragmentTitle(int position, String title){
        adapter.addFragmentTitle(position, title);
    }

    public void setFragmentTitle(int position, String title){
        adapter.setFragmentTitle(position, title);
    }

    public void removeFragmentTitle(int position){
        adapter.removeFragmentTitle(position);
    }


    /***********************************************************
     * Viewpager Adapter
     ***********************************************************/
    private class PackViewPagerAdater extends FragmentPagerAdapter{

        /**
         * Fragment array
         */
        ArrayList<Fragment> Fragment_List;
        ArrayList<String> Fragment_title;

        /**
         * constructor
         * @param fm
         */
        public PackViewPagerAdater(FragmentManager fm) {
            super(fm);

            Fragment_List = new ArrayList<Fragment>();
            Fragment_title = new ArrayList<String>();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Fragment_title.get(position);
        }

        /**
         * get fragment1
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return Fragment_List.get(position);
        }

        /**
         * get fragment1's getCount
         * @return
         */
        @Override
        public int getCount() {
            return Fragment_List.size();
        }


        /**
         * add fragment1
         * @param fm
         */
        public void addFragment(Fragment fm){
            Fragment_List.add(fm);
            notifyDataSetChanged();
        }

        /**
         * add fragment1 by position
         * @param position
         * @param fm
         */
        public void addFragment(int position, Fragment fm){
            Fragment_List.add(position, fm);
            notifyDataSetChanged();
        }


        /**
         * add Fragment titles
         * @param title
         */
        public void addFragmentTitles(String ...title){
            for(String t : title){
                Fragment_title.add(t);
            }
        }

        public void addFragmentTitle(int position, String title){
            Fragment_title.add(title);
        }

        public void setFragmentTitle(int position, String title){
            Fragment_title.remove(position);
            Fragment_title.add(position, title);
        }

        public void removeFragmentTitle(int position){
            Fragment_title.remove(position);
        }

        /**
         * remove fragment1 item
         * @param index
         */
        public void removeFragment(int index){
            Fragment_List.remove(index);
            notifyDataSetChanged();
        }
    }
}
