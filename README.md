/*

해당 소스 및 라이브러리를 개인적, 상업적으로 사용하는 것을 금합니다.

*/

#PackingProject

packing view container list

 - PackListView
 - PackCustomListView
 - PackExpandableListView
 - PackRecyclerView
 - PackGridView

packing ETC 기능

 - PackPermissionAcitivity
 - PackHttpTask
 - PackViewPager



PackListView

PackListView는 listview 내부 아이템이 텍스트 하나만을 수용하는 간단한 리스트뷰입니다.

함수

 - void addItem(String) : String을 리스트 뷰 가장 아래쪽에 삽입합니다.
 - void removeItem(int) : 원하는 위치의 아이템을 삭제합니다.
 - void removeItem(String) : string으로 검색하여 아이템을 삭제합니다.
 - void fixHeight() : 리스트뷰의 높이가 제대로 나오지 않을 때 조정해주는 함수입니다.


Example

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

개발자가 원하는 아이템의 레이아웃을 설정하는 리스트뷰입니다. 아이템의 레이아웃을 유동적으로 바꿀 수 있습니다.

함수

 - void setIdOrder(int ...) : 아이템의 레이아웃 내 위젯들의 id를 받는 함수입니다. setViewOrder 함수의 인자 순서와 일치해야 정상적으로 작동합니다.
 - void setViewOrder(int ...) : 아이템의 레이아웃 내 위젯들의 종류를 받는 함수입니다. setIdOrder 함수의 인자 순서와 일치하도록 설정해줘야 정상적으로 매칭이 됩니다.
 - void setLayout(int) : 아이템의 레이아웃을 받는 함수입니다. res/layout 폴더 내의 레이아웃을 인자로 넣어줘야 합니다.
 - void addItem(int, T...) : 아이템의 데이터를 받아서 넣어주는 함수입니다. 첫번째 인자로 리스트의 위치를 설정해주고 두번째 인자부터 setIdOrder의 인자 순서대로 데이터들을 넣어줘야 합니다. 데이터는 template로 받기 때문에 형식에 구애받지 않습니다.
 - void removeItem(int) : 원하는 위치의 아이템을 삭제하는 함수입니다.
 - int getCount() : 아이템의 수를 반환하는 함수입니다.

 - void setAddAnimation(int) : 아이템이 추가될 때의 애니메이션을 설정해주는 함수입니다. res 폴더 내의 애니메이션 xml 파일을 인자로 설정해주어야 합니다.
 - void setRemoveAnimation(int) : 아이템이 삭제될 때의 애니메이션을 설정해주는 함수입니다. res 폴더 내의 애니메이션 xml 파일을 인자로 설정해주어야 합니다.
 - void setOverAnimation(int) : 아이템이 위쪽에서 화면으로 진입할 때의 애니메이션을 설정해주는 함수입니다. res 폴더 내의 애니메이션 xml 파일을 인자로 설정해주어야 합니다.
 - void setUnderAnimation(int) : 아이템이 아래쪽에서 화면으로 진입할 때의 애니메이션을 설정해주는 함수입니다. res 폴더 내의 애니메이션 xml 파일을 인자로 설정해주어야 합니다.
 - void fixHeight() : 리스트뷰의 높이가 제대로 나오지 않을 때 조정해주는 함수입니다.
 - void useAnimation(boolean) : 애니메이션의 사용 여부를 설정해주는 함수입니다.

Example

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

개발자가 원하는 group과 child의 레이아웃을 유동적으로 넣어서 만들 수 있는 expandableListView입니다.

함수 

 - void setGroupIdOrder(int ...) : 아이템의 group 레이아웃 내 위젯들의 id를 받는 함수입니다. setGroupViewOrder 함수의 인자 순서와 일치해야 정상적으로 작동합니다.
 - void setGroupViewOrder(int ...) : 아이템의 group 레이아웃 내 위젯들의 종류를 받는 함수입니다. setGroupIdOrder 함수의 인자 순서와 일치하도록 설정해줘야 정상적으로 매칭이 됩니다.
 - void setChildIdOrder(int ...) : 아이템의 child 레이아웃 내 위젯들의 id를 받는 함수입니다. setChildViewOrder 함수의 인자 순서와 일치해야 정상적으로 작동합니다.
 - void setChildViewOrder(int ...) : 아이템의 child 레이아웃 내 위젯들의 종류를 받는 함수입니다. setChildIdOrder 함수의 인자 순서와 일치하도록 설정해줘야 정상적으로 매칭이 됩니다.
 - void addGroupItem(int, T...) : group 아이템의 데이터를 받아서 넣어주는 함수입니다. 첫번째 인자로 리스트의 위치를 설정해주고 두번째 인자부터 setIdOrder의 인자 순서대로 데이터들을 넣어줘야 합니다. 데이터는 template로 받기 때문에 형식에 구애받지 않습니다.
 - void addChildItem(int groupPosition, int, T...) : child 아이템의 데이터를 받아서 원하는 group의 원하는 위치에 넣어주는 함수입니다. 첫번째 인자로 리스트의 위치를 설정해주고 두번째 인자부터 setIdOrder의 인자 순서대로 데이터들을 넣어줘야 합니다. 데이터는 template로 받기 때문에 형식에 구애받지 않습니다.
 - void removeGroupItem(int) : 원하는 위치의 group 아이템을 삭제하는 함수입니다.
 - void removeChildItem(int groupPosition, int) : group의 원하는 위치의 child 아이템을 삭제하는 함수입니다.
 - void getGroupCount() : group 아이템의 수를 반환하는 함수입니다.
 - void getChildCount(int) : group하나의 child 아이템 수를 반환하는 함수입니다.

Example

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

android 5.0 부터 소개된 리스트뷰를 수정, 보완하여 만든 recyclerview입니다.

함수

 - void setIdOrder(int ...) : 아이템의 레이아웃 내 위젯들의 id를 받는 함수입니다. setViewOrder 함수의 인자 순서와 일치해야 정상적으로 작동합니다.
 - void setViewOrder(int ...) : 아이템의 레이아웃 내 위젯들의 종류를 받는 함수입니다. setIdOrder 함수의 인자 순서와 일치하도록 설정해줘야 정상적으로 매칭이 됩니다.
 - void setLayout(int) : 아이템의 레이아웃을 받는 함수입니다. res/layout 폴더 내의 레이아웃을 인자로 넣어줘야 합니다.
 - void addItem(int, T...) : 아이템의 데이터를 받아서 넣어주는 함수입니다. 첫번째 인자로 리스트의 위치를 설정해주고 두번째 인자부터 setIdOrder의 인자 순서대로 데이터들을 넣어줘야 합니다. 데이터는 template로 받기 때문에 형식에 구애받지 않습니다.
 - void removeItem(int) : 원하는 위치의 아이템을 삭제하는 함수입니다.
 - int getCount() : 아이템의 수를 반환하는 함수입니다.

Example

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

2차원의 그리드에 아이템들을 보여주는 gridview입니다.

함수

 - void setIdOrder(int ...) : 아이템의 레이아웃 내 위젯들의 id를 받는 함수입니다. setViewOrder 함수의 인자 순서와 일치해야 정상적으로 작동합니다.
 - void setViewOrder(int ...) : 아이템의 레이아웃 내 위젯들의 종류를 받는 함수입니다. setIdOrder 함수의 인자 순서와 일치하도록 설정해줘야 정상적으로 매칭이 됩니다.
 - void setLayout(int) : 아이템의 레이아웃을 받는 함수입니다. res/layout 폴더 내의 레이아웃을 인자로 넣어줘야 합니다.
 - void addItem(int, T...) : 아이템의 데이터를 받아서 넣어주는 함수입니다. 첫번째 인자로 리스트의 위치를 설정해주고 두번째 인자부터 setIdOrder의 인자 순서대로 데이터들을 넣어줘야 합니다. 데이터는 template로 받기 때문에 형식에 구애받지 않습니다.
 - void removeItem(int) : 원하는 위치의 아이템을 삭제하는 함수입니다.
 - int getCount() : 아이템의 수를 반환하는 함수입니다.


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

안드로이드 6.0 이상부터 개별적으로 적용되는 permission을 내장시킨 activity입니다. 만들어져있는 함수로 permission을 요청하는 팝업창이 뜨도록 합니다.

함수

 - void setPermissions(String[]) : 요청하고자 하는 permission의 종류를 인자로 받아서 저장하는 함수입니다. 추후 runPermssion 함수를 사용하여 팝업창으로 요청합니다.
 - void setFinish(boolean) : 권한을 거부하였을 때 액티비티의 종료 여부를 설정하는 함수입니다.
 - void runPermission() : 권한을 요청하는 팝업창을 띄우는 함수입니다.

 Example

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

안드로이드에서 http 통신을 수행하는 AsyncTask입니다. task를 생성할 때 인자로 원하는 url을 넣고 데이터는 String으로 반환받습니다.

함수
 - boolean ClearSession(Context) : 유지되는 세션을 초기화하는 함수입니다. 해당 세션은 쿠키로 유지됩니다.
 - void keepSession(boolean) : 세션 유지 여부를 설정하는 함수입니다.
 - void setUrl(String) : 요청할 url을 설정하는 함수입니다.
 - void setRequestMethod(String) : 요청방식을 설정하는 함수입니다. 기본적으로 get형식으로 통신하도록 설정되어 있습니다.
 - void setContentType(String) : 보내는 데이터의 종류를 정하는 함수입니다. 기본적으로 text/html로 설정되어 있습니다.
 - void setFormat(String) : 보내는 데이터의 포맷을 설정하는 함수입니다. 기본적으로 utf-8로 설정되어 있습니다.
 - void setData(String) : 보낼 데이터를 설정하는 함수입니다.
 - void setRequestProperty(key, value) : 새로운 헤더를 추가하는 함수입니다.

 - String getUrl() : 현재 설정되어 있는 url을 반환하는 함수입니다.
 - String getFormat() : 현재 설정되어 있는 format을 반환하는 함수입니다.
 - String getData() : 현재 설정되어 있는 데이터를 반환하는 함수입니다.

 - String execute().get() : asynctask를 동작하는 함수입니다. httpTask에서도 동작하기 위해 사용됩니다.

 
Example

	PackHttpTask httpTask = new PackHttpTask("http://www.xxx.com/");

	try {

	    String data = httpTask.execute().get();

	}catch (Exception e){
	    e.printStackTrace();
	}



PackViewPager

프래그먼트들을 페이지처럼 넘기도록 만들어진 viewpager입니다.

함수
 - addFragments(Fragment...) : 프래그먼트들을 한 번에 여러 개 넣는 함수입니다.
 - addFragment(int position, Fragment) : 원하는 위치에 프래그먼트를 넣는 함수입니다.
 - removeFragment(int) : 원하는 위치의 fragment를 삭제하는 함수입니다.


	PackViewPager viewpager = (PackViewPager)findViewById(R.id.viewpager);

	or

	PackViewPager viewpager = new PackViewPager(this);
	/*
	    input fragment1
	 */
	viewpager.addFragments(new IntroFirstFragment(), new IntroFirstFragment());
	viewpager.addFragment(1, new IntroSecondFragment()); // 1 means position
