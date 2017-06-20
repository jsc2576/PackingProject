/*

�ش� �ҽ� �� ���̺귯���� ������, ��������� ����ϴ� ���� ���մϴ�.

*/

#PackingProject

packing view container list

 - PackListView
 - PackCustomListView
 - PackExpandableListView
 - PackRecyclerView
 - PackGridView

packing ETC ���

 - PackPermissionAcitivity
 - PackHttpTask
 - PackViewPager



PackListView

PackListView�� listview ���� �������� �ؽ�Ʈ �ϳ����� �����ϴ� ������ ����Ʈ���Դϴ�.

�Լ�

 - void addItem(String) : String�� ����Ʈ �� ���� �Ʒ��ʿ� �����մϴ�.
 - void removeItem(int) : ���ϴ� ��ġ�� �������� �����մϴ�.
 - void removeItem(String) : string���� �˻��Ͽ� �������� �����մϴ�.
 - void fixHeight() : ����Ʈ���� ���̰� ����� ������ ���� �� �������ִ� �Լ��Դϴ�.


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

�����ڰ� ���ϴ� �������� ���̾ƿ��� �����ϴ� ����Ʈ���Դϴ�. �������� ���̾ƿ��� ���������� �ٲ� �� �ֽ��ϴ�.

�Լ�

 - void setIdOrder(int ...) : �������� ���̾ƿ� �� �������� id�� �޴� �Լ��Դϴ�. setViewOrder �Լ��� ���� ������ ��ġ�ؾ� ���������� �۵��մϴ�.
 - void setViewOrder(int ...) : �������� ���̾ƿ� �� �������� ������ �޴� �Լ��Դϴ�. setIdOrder �Լ��� ���� ������ ��ġ�ϵ��� ��������� ���������� ��Ī�� �˴ϴ�.
 - void setLayout(int) : �������� ���̾ƿ��� �޴� �Լ��Դϴ�. res/layout ���� ���� ���̾ƿ��� ���ڷ� �־���� �մϴ�.
 - void addItem(int, T...) : �������� �����͸� �޾Ƽ� �־��ִ� �Լ��Դϴ�. ù��° ���ڷ� ����Ʈ�� ��ġ�� �������ְ� �ι�° ���ں��� setIdOrder�� ���� ������� �����͵��� �־���� �մϴ�. �����ʹ� template�� �ޱ� ������ ���Ŀ� ���ֹ��� �ʽ��ϴ�.
 - void removeItem(int) : ���ϴ� ��ġ�� �������� �����ϴ� �Լ��Դϴ�.
 - int getCount() : �������� ���� ��ȯ�ϴ� �Լ��Դϴ�.

 - void setAddAnimation(int) : �������� �߰��� ���� �ִϸ��̼��� �������ִ� �Լ��Դϴ�. res ���� ���� �ִϸ��̼� xml ������ ���ڷ� �������־�� �մϴ�.
 - void setRemoveAnimation(int) : �������� ������ ���� �ִϸ��̼��� �������ִ� �Լ��Դϴ�. res ���� ���� �ִϸ��̼� xml ������ ���ڷ� �������־�� �մϴ�.
 - void setOverAnimation(int) : �������� ���ʿ��� ȭ������ ������ ���� �ִϸ��̼��� �������ִ� �Լ��Դϴ�. res ���� ���� �ִϸ��̼� xml ������ ���ڷ� �������־�� �մϴ�.
 - void setUnderAnimation(int) : �������� �Ʒ��ʿ��� ȭ������ ������ ���� �ִϸ��̼��� �������ִ� �Լ��Դϴ�. res ���� ���� �ִϸ��̼� xml ������ ���ڷ� �������־�� �մϴ�.
 - void fixHeight() : ����Ʈ���� ���̰� ����� ������ ���� �� �������ִ� �Լ��Դϴ�.
 - void useAnimation(boolean) : �ִϸ��̼��� ��� ���θ� �������ִ� �Լ��Դϴ�.

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

�����ڰ� ���ϴ� group�� child�� ���̾ƿ��� ���������� �־ ���� �� �ִ� expandableListView�Դϴ�.

�Լ� 

 - void setGroupIdOrder(int ...) : �������� group ���̾ƿ� �� �������� id�� �޴� �Լ��Դϴ�. setGroupViewOrder �Լ��� ���� ������ ��ġ�ؾ� ���������� �۵��մϴ�.
 - void setGroupViewOrder(int ...) : �������� group ���̾ƿ� �� �������� ������ �޴� �Լ��Դϴ�. setGroupIdOrder �Լ��� ���� ������ ��ġ�ϵ��� ��������� ���������� ��Ī�� �˴ϴ�.
 - void setChildIdOrder(int ...) : �������� child ���̾ƿ� �� �������� id�� �޴� �Լ��Դϴ�. setChildViewOrder �Լ��� ���� ������ ��ġ�ؾ� ���������� �۵��մϴ�.
 - void setChildViewOrder(int ...) : �������� child ���̾ƿ� �� �������� ������ �޴� �Լ��Դϴ�. setChildIdOrder �Լ��� ���� ������ ��ġ�ϵ��� ��������� ���������� ��Ī�� �˴ϴ�.
 - void addGroupItem(int, T...) : group �������� �����͸� �޾Ƽ� �־��ִ� �Լ��Դϴ�. ù��° ���ڷ� ����Ʈ�� ��ġ�� �������ְ� �ι�° ���ں��� setIdOrder�� ���� ������� �����͵��� �־���� �մϴ�. �����ʹ� template�� �ޱ� ������ ���Ŀ� ���ֹ��� �ʽ��ϴ�.
 - void addChildItem(int groupPosition, int, T...) : child �������� �����͸� �޾Ƽ� ���ϴ� group�� ���ϴ� ��ġ�� �־��ִ� �Լ��Դϴ�. ù��° ���ڷ� ����Ʈ�� ��ġ�� �������ְ� �ι�° ���ں��� setIdOrder�� ���� ������� �����͵��� �־���� �մϴ�. �����ʹ� template�� �ޱ� ������ ���Ŀ� ���ֹ��� �ʽ��ϴ�.
 - void removeGroupItem(int) : ���ϴ� ��ġ�� group �������� �����ϴ� �Լ��Դϴ�.
 - void removeChildItem(int groupPosition, int) : group�� ���ϴ� ��ġ�� child �������� �����ϴ� �Լ��Դϴ�.
 - void getGroupCount() : group �������� ���� ��ȯ�ϴ� �Լ��Դϴ�.
 - void getChildCount(int) : group�ϳ��� child ������ ���� ��ȯ�ϴ� �Լ��Դϴ�.

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

android 5.0 ���� �Ұ��� ����Ʈ�並 ����, �����Ͽ� ���� recyclerview�Դϴ�.

�Լ�

 - void setIdOrder(int ...) : �������� ���̾ƿ� �� �������� id�� �޴� �Լ��Դϴ�. setViewOrder �Լ��� ���� ������ ��ġ�ؾ� ���������� �۵��մϴ�.
 - void setViewOrder(int ...) : �������� ���̾ƿ� �� �������� ������ �޴� �Լ��Դϴ�. setIdOrder �Լ��� ���� ������ ��ġ�ϵ��� ��������� ���������� ��Ī�� �˴ϴ�.
 - void setLayout(int) : �������� ���̾ƿ��� �޴� �Լ��Դϴ�. res/layout ���� ���� ���̾ƿ��� ���ڷ� �־���� �մϴ�.
 - void addItem(int, T...) : �������� �����͸� �޾Ƽ� �־��ִ� �Լ��Դϴ�. ù��° ���ڷ� ����Ʈ�� ��ġ�� �������ְ� �ι�° ���ں��� setIdOrder�� ���� ������� �����͵��� �־���� �մϴ�. �����ʹ� template�� �ޱ� ������ ���Ŀ� ���ֹ��� �ʽ��ϴ�.
 - void removeItem(int) : ���ϴ� ��ġ�� �������� �����ϴ� �Լ��Դϴ�.
 - int getCount() : �������� ���� ��ȯ�ϴ� �Լ��Դϴ�.

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

2������ �׸��忡 �����۵��� �����ִ� gridview�Դϴ�.

�Լ�

 - void setIdOrder(int ...) : �������� ���̾ƿ� �� �������� id�� �޴� �Լ��Դϴ�. setViewOrder �Լ��� ���� ������ ��ġ�ؾ� ���������� �۵��մϴ�.
 - void setViewOrder(int ...) : �������� ���̾ƿ� �� �������� ������ �޴� �Լ��Դϴ�. setIdOrder �Լ��� ���� ������ ��ġ�ϵ��� ��������� ���������� ��Ī�� �˴ϴ�.
 - void setLayout(int) : �������� ���̾ƿ��� �޴� �Լ��Դϴ�. res/layout ���� ���� ���̾ƿ��� ���ڷ� �־���� �մϴ�.
 - void addItem(int, T...) : �������� �����͸� �޾Ƽ� �־��ִ� �Լ��Դϴ�. ù��° ���ڷ� ����Ʈ�� ��ġ�� �������ְ� �ι�° ���ں��� setIdOrder�� ���� ������� �����͵��� �־���� �մϴ�. �����ʹ� template�� �ޱ� ������ ���Ŀ� ���ֹ��� �ʽ��ϴ�.
 - void removeItem(int) : ���ϴ� ��ġ�� �������� �����ϴ� �Լ��Դϴ�.
 - int getCount() : �������� ���� ��ȯ�ϴ� �Լ��Դϴ�.


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

�ȵ���̵� 6.0 �̻���� ���������� ����Ǵ� permission�� �����Ų activity�Դϴ�. ��������ִ� �Լ��� permission�� ��û�ϴ� �˾�â�� �ߵ��� �մϴ�.

�Լ�

 - void setPermissions(String[]) : ��û�ϰ��� �ϴ� permission�� ������ ���ڷ� �޾Ƽ� �����ϴ� �Լ��Դϴ�. ���� runPermssion �Լ��� ����Ͽ� �˾�â���� ��û�մϴ�.
 - void setFinish(boolean) : ������ �ź��Ͽ��� �� ��Ƽ��Ƽ�� ���� ���θ� �����ϴ� �Լ��Դϴ�.
 - void runPermission() : ������ ��û�ϴ� �˾�â�� ���� �Լ��Դϴ�.

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

�ȵ���̵忡�� http ����� �����ϴ� AsyncTask�Դϴ�. task�� ������ �� ���ڷ� ���ϴ� url�� �ְ� �����ʹ� String���� ��ȯ�޽��ϴ�.

�Լ�
 - boolean ClearSession(Context) : �����Ǵ� ������ �ʱ�ȭ�ϴ� �Լ��Դϴ�. �ش� ������ ��Ű�� �����˴ϴ�.
 - void keepSession(boolean) : ���� ���� ���θ� �����ϴ� �Լ��Դϴ�.
 - void setUrl(String) : ��û�� url�� �����ϴ� �Լ��Դϴ�.
 - void setRequestMethod(String) : ��û����� �����ϴ� �Լ��Դϴ�. �⺻������ get�������� ����ϵ��� �����Ǿ� �ֽ��ϴ�.
 - void setContentType(String) : ������ �������� ������ ���ϴ� �Լ��Դϴ�. �⺻������ text/html�� �����Ǿ� �ֽ��ϴ�.
 - void setFormat(String) : ������ �������� ������ �����ϴ� �Լ��Դϴ�. �⺻������ utf-8�� �����Ǿ� �ֽ��ϴ�.
 - void setData(String) : ���� �����͸� �����ϴ� �Լ��Դϴ�.
 - void setRequestProperty(key, value) : ���ο� ����� �߰��ϴ� �Լ��Դϴ�.

 - String getUrl() : ���� �����Ǿ� �ִ� url�� ��ȯ�ϴ� �Լ��Դϴ�.
 - String getFormat() : ���� �����Ǿ� �ִ� format�� ��ȯ�ϴ� �Լ��Դϴ�.
 - String getData() : ���� �����Ǿ� �ִ� �����͸� ��ȯ�ϴ� �Լ��Դϴ�.

 - String execute().get() : asynctask�� �����ϴ� �Լ��Դϴ�. httpTask������ �����ϱ� ���� ���˴ϴ�.

 
Example

	PackHttpTask httpTask = new PackHttpTask("http://www.xxx.com/");

	try {

	    String data = httpTask.execute().get();

	}catch (Exception e){
	    e.printStackTrace();
	}



PackViewPager

�����׸�Ʈ���� ������ó�� �ѱ⵵�� ������� viewpager�Դϴ�.

�Լ�
 - addFragments(Fragment...) : �����׸�Ʈ���� �� ���� ���� �� �ִ� �Լ��Դϴ�.
 - addFragment(int position, Fragment) : ���ϴ� ��ġ�� �����׸�Ʈ�� �ִ� �Լ��Դϴ�.
 - removeFragment(int) : ���ϴ� ��ġ�� fragment�� �����ϴ� �Լ��Դϴ�.


	PackViewPager viewpager = (PackViewPager)findViewById(R.id.viewpager);

	or

	PackViewPager viewpager = new PackViewPager(this);
	/*
	    input fragment1
	 */
	viewpager.addFragments(new IntroFirstFragment(), new IntroFirstFragment());
	viewpager.addFragment(1, new IntroSecondFragment()); // 1 means position
