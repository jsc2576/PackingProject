package hiruashi.jsc5565.packingproject;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


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
        customListView.setIdOrder(R.id.list_img, R.id.list_text);
        customListView.setViewOrder(PackExpandableListView.IMAGE, PackExpandableListView.TEXT);

        customListView.setAddAniamtion(R.anim.center_from_right);
        customListView.setRemoveAnimation(R.anim.left_from_center, 400);


        /*
            input data
         */
        customListView.addItem(0, R.mipmap.ic_launcher, "test");
        customListView.addItem(1, R.mipmap.ic_launcher, "test");
        customListView.addItem(2, R.mipmap.ic_launcher, "test");
        customListView.addItem(3, R.mipmap.ic_launcher, "test");
        customListView.addItem(4, R.mipmap.ic_launcher, "test");


        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){
                    customListView.removeItem(customListView.size()-1);
                }
                else{
                    customListView.addItem(customListView.size(), R.mipmap.ic_launcher, "add test");
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
        listview.setGroupIdOrder(R.id.list_img, R.id.list_text);
        listview.setChildIdOrder(R.id.list_img, R.id.list_text);
        listview.setGroupViewOrder(PackExpandableListView.IMAGE, PackExpandableListView.TEXT);
        listview.setChildViewOrder(PackExpandableListView.IMAGE, PackExpandableListView.TEXT);

        /*
            input data
         */
        listview.addGroupItem(listview.GroupSize(), R.mipmap.ic_launcher, "test");
        listview.addChildItem(listview.GroupSize()-1, listview.ChildSize(listview.GroupSize()-1), 0, R.mipmap.ic_launcher, "child1");
        listview.addChildItem(listview.GroupSize()-1, listview.ChildSize(listview.GroupSize()-1), 0, R.mipmap.ic_launcher, "child2");
        listview.addGroupItem(listview.GroupSize(), R.mipmap.ic_launcher, "test");
        listview.addGroupItem(listview.GroupSize(), R.mipmap.ic_launcher, "test");
        listview.addGroupItem(listview.GroupSize(), R.mipmap.ic_launcher, "test");
        listview.addGroupItem(listview.GroupSize(), R.mipmap.ic_launcher, "test");
        listview.addChildItem(listview.GroupSize()-1, listview.ChildSize(listview.GroupSize()-1), 4, R.mipmap.ic_launcher, "child51");
        listview.addChildItem(listview.GroupSize()-1, listview.ChildSize(listview.GroupSize()-1), 4, R.mipmap.ic_launcher, "child52");









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
        recylerView.setViewOrder(PackRecyclerView.IMAGE, PackRecyclerView.TEXT);
        recylerView.setIdOrder(R.id.list_img, R.id.list_text);

        recylerView.addItem(recylerView.size(), R.mipmap.ic_launcher, "test");
        recylerView.addItem(recylerView.size(), R.mipmap.ic_launcher, "test");
        recylerView.addItem(recylerView.size(), R.mipmap.ic_launcher, "test");

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
                recylerView.addItem(recylerView.size(), R.mipmap.ic_launcher, "test");

            }
        });
*/






        PackGridView gridView = (PackGridView)findViewById(R.id.gridview);

        gridView.setLayout(R.layout.list_item);
        gridView.setViewOrder(PackRecyclerView.IMAGE, PackRecyclerView.TEXT);
        gridView.setIdOrder(R.id.list_img, R.id.list_text);

        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test1");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test2");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test3");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test4");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test5");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test6");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test7");
        gridView.addItem(gridView.size(), R.mipmap.ic_launcher, "test8");


    }
}