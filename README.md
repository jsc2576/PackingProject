#PackingProject

packing view

 - PackListView
 - PackCustomListView
 - PackExpandableListView
 - PackRecyclerView
 - PackGridView

packing ETC

 - PackPermissionAcitivity
 - PackHttpTask
 - PackViewPager



PackListView

	/*
            packlistview instruction
         */
        PackListView packListView = new PackListView(this);
        
	or
	
	PackListView packListView = (PackListView)findViewById(R.id.listview);

        /*
            input data
         */
        for(int i=0; i<10; i++) {
            packListView.addItem(Integer.toString(i));
        }




PackCustomListView

	/*
            packcustomlistview instruction
         */

	final PackCustomListView customListView = new PackCustomListView(this);
        
	or

	final PackCustomListView customListView = (PackCustomListView)findViewById(R.id.listview);

        customListView.setLayout(R.layout.list_item);
	customListView.setIdOrder(R.id.list_img, R.id.list_text);
	customListView.setViewOrder(IMAGE_RESOURCE, TEXT);

        customListView.setAddAniamtion(R.anim.over_show);
        customListView.setRemoveAnimation(R.anim.left_from_center, 400);

        customListView.useAnimation(true);
        /*
            input data
         */
        for(int i=0; i<300; i++) {
            customListView.addItem(customListView.getCount(), R.mipmap.ic_launcher, "test "+i);

        }



PackExpandableListView

	/*
            packexpandablelistview instruction
         */
        PackExpandableListView listview = (PackExpandableListView)findViewById(R.id.packlistview);

	or

        PackExpandableListView listview = new PackExpandableListView(this);

        listview.setGroupLayout(R.layout.list_item);
        listview.setChildLayout(R.layout.list_item);

	listview.setGroupIdOrder(R.id.list_img, R.id.list_text);
	listview.setGroupViewOrder(IMAGE_RESOURCE, TEXT);
	listview.setChildIdOrder(R.id.list_img, R.id.list_text);
	listview.setChildViewOrder(IMAGE_RESOURCE, TEXT);

        /*
            input data
         */
        for(int i=0; i<100; i++) {
            listview.addGroupItem(listview.getGroupCount(), R.mipmap.ic_launcher, "test");
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i);
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i+"..");
            listview.addChildItem(listview.getGroupCount() - 1, listview.getChildCount(listview.getGroupCount() - 1), R.mipmap.ic_launcher, "child"+i+"....");
        }



PackRecyclerView

	PackRecyclerView recylerView = (PackRecyclerView)findViewById(R.id.listview);
	
	or
	
        PackRecyclerView recylerView = new PackRecyclerView(this);

        recylerView.setLayout(R.layout.list_item);
        recylerView.setViewOrder(IMAGE_RESOURCE, TEXT);
        recylerView.setIdOrder(R.id.list_img, R.id.list_text);

        /*
            input data
         */

        for(int i=0; i<100; i++) {
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
            recylerView.addItem(recylerView.getCount(), R.mipmap.ic_launcher, "test");
        }



PackGridView

        PackGridView gridView = (PackGridView)findViewById(R.id.gridview);
        
	or

	PackGridView gridView = new PackGridView(this);

        gridView.setLayout(R.layout.list_item);
        gridView.setViewOrder(IMAGE_RESOURCE, TEXT);
        gridView.setIdOrder(R.id.list_img, R.id.list_text);

        for(int i=0; i<100; i++) {
            gridView.addItem(gridView.getCount(), R.mipmap.ic_launcher, "test"+i);
        }




PackPermissionActivity

public class MainActivity extends PackPermissionActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
        runPermission();
    }
}




PackHttpTask

	PackHttpTask httpTask = new PackHttpTask("http://www.melon.com/");

        try {

            String data = httpTask.execute().get();

        }catch (Exception e){
            e.printStackTrace();
        }



PackViewPager

	PackViewPager viewpager = (PackViewPager)findViewById(R.id.viewpager);

	or

        PackViewPager viewpager = new PackViewPager(this);
        /*
            input fragment1
         */
        viewpager.addFragments(new IntroFirstFragment(), new IntroFirstFragment());
        viewpager.addFragment(1, new IntroSecondFragment()); // 1 means position
