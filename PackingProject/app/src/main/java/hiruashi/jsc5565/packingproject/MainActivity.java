package hiruashi.jsc5565.packingproject;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import hiruashi.jsc5565.packingproject.Packing.PackCustomListView;
import hiruashi.jsc5565.packingproject.Packing.PackExpandableListView;
import hiruashi.jsc5565.packingproject.Packing.PackGridView;
import hiruashi.jsc5565.packingproject.Packing.PackHttpTask;
import hiruashi.jsc5565.packingproject.Packing.PackListView;
import hiruashi.jsc5565.packingproject.Packing.PackPermissionActivity;
import hiruashi.jsc5565.packingproject.Packing.PackRecyclerView;
import hiruashi.jsc5565.packingproject.Packing.PackViewPager;

import static hiruashi.jsc5565.packingproject.util.ViewUtil.IMAGE_RESOURCE;
import static hiruashi.jsc5565.packingproject.util.ViewUtil.TEXT;

/**
 * TODO 라이브러리로 만들때는 삭제해야한다
 */
public class MainActivity extends PackPermissionActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
        runPermission();

        /*
            packlistview instruction
         */
        PackListView packListView = new PackListView(this);
        //PackListView packListView = (PackListView)findViewById(R.id.listview);

        /*
            input data
         */
        for(int i=0; i<10; i++) {
            packListView.addItem(Integer.toString(i));
        }






        /*
            packcustomlistview instruction
         */
        final PackCustomListView customListView = new PackCustomListView(this);
        //final PackCustomListView customListView = (PackCustomListView)findViewById(R.id.listview);
        customListView.setLayout(R.layout.list_item);
        customListView.setIdMatch(R.id.list_img, IMAGE_RESOURCE);
        customListView.setIdMatch(R.id.list_text, TEXT);
        //customListView.setIdOrder(R.id.list_img, R.id.list_text);
        //customListView.setViewOrder(IMAGE_RESOURCE, TEXT);

        customListView.setAddAniamtion(R.anim.over_show);
        customListView.setRemoveAnimation(R.anim.left_from_center, 400);

        customListView.useAnimation(true);
        /*
            input data
         */
        for(int i=0; i<300; i++) {
            customListView.addItem(customListView.getCount(), R.mipmap.ic_launcher, "test "+i);

        }



        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    customListView.removeItem(0);
                }
                else{
                    customListView.addItem(customListView.getLastVisiblePosition()-4, R.mipmap.ic_launcher, "add test");
                }

            }
        });



        /*
            packexpandablelistview instruction
         */
        //PackExpandableListView listview = (PackExpandableListView)findViewById(R.id.packlistview);
        PackExpandableListView listview = new PackExpandableListView(this);
        listview.setGroupLayout(R.layout.list_item);
        listview.setChildLayout(R.layout.list_item);
        listview.setGroupIdMatch(R.id.list_img, IMAGE_RESOURCE);
        listview.setGroupIdMatch(R.id.list_text, TEXT);
        listview.setChildIdMatch(R.id.list_img, IMAGE_RESOURCE);
        listview.setChildIdMatch(R.id.list_text, TEXT);
        /*
        listview.setGroupIdOrder(R.id.list_img, R.id.list_text);
        listview.setChildIdOrder(R.id.list_img, R.id.list_text);
        listview.setGroupViewOrder(IMAGE_RESOURCE, TEXT);
        listview.setChildViewOrder(IMAGE_RESOURCE, TEXT);
        */
        /*
            input data
         */
        for(int i=0; i<100; i++) {
            listview.addGroupItem(listview.getGroupCount(), R.mipmap.ic_launcher, "test");
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i);
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i+"..");
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i+"....");
        }







       /*
            packviewpager instruction
         */
        //PackViewPager viewpager = (PackViewPager)findViewById(R.id.viewpager);
        PackViewPager viewpager = new PackViewPager(this);
        /*
            input fragment1
         */
        viewpager.addFragments(new IntroFirstFragment(), new IntroFirstFragment());
        viewpager.addFragment(1, new IntroSecondFragment());













        //final PackRecyclerView recylerView = (PackRecyclerView)findViewById(R.id.listview);
        final PackRecyclerView recylerView = new PackRecyclerView(this);

        recylerView.setLayout(R.layout.list_item);
        recylerView.setViewOrder(IMAGE_RESOURCE, TEXT);
        recylerView.setIdOrder(R.id.list_img, R.id.list_text);

        for(int i=0; i<100; i++) {
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
        }
        recylerView.setClickable(true);
        recylerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("recyclerview", "click");
                Toast.makeText(getApplicationContext(), Integer.toString(v.getId()), Toast.LENGTH_SHORT).show();
            }
        });
        /*
        recylerView.setOnItemClickListener(new PackRecyclerView.OnItemClickListener() {
            @Override
            public void ItemClick(int position, View itemView) {
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");

            }
        });
*/






        PackGridView gridView = (PackGridView)findViewById(R.id.gridview);
        //PackGridView gridView = new PackGridView(this);
        gridView.setLayout(R.layout.list_item);
        gridView.setViewOrder(IMAGE_RESOURCE, TEXT);
        gridView.setIdOrder(R.id.list_img, R.id.list_text);

        /*
        gridView.useAnimation(true);
        gridView.setAddAnimation(R.anim.over_show);
        gridView.setRemoveAnimation(R.anim.under_show);
        gridView.setOverAnimation(R.anim.center_from_right);
        gridView.setUnderAnimation(R.anim.left_from_center);
        */
        for(int i=0; i<100; i++) {
            gridView.addItem(gridView.getCount(), R.mipmap.ic_launcher, "test"+i);
        }


        PackHttpTask httpTask = new PackHttpTask("http://www.melon.com/1234123");
        try {
            String data = httpTask.execute().get();
            Log.i("HTTP", httpTask.execute().get());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}