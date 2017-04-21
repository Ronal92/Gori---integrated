[ Date : 2017. 03. 31(금) ]

#Done 

 drawer layout 생성!

## drawer layout 사용법

###1. 드로어를 가장 바깥에 두어야 한다. 메인 화면은 <include>에 넣고 드로어에 넣을 내용은 <include> 아래에 포함시킨다.

<activity_main.xml>

		<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
		    android:id="@+id/drawer_layout"
		    android:layout_width="match_parent"
		    android:layout_height="match_parent">

	  	  	<include
		        layout="@layout/main_view"
		        android:layout_width="match_parent"
		        android:layout_height="match_parent" />

		
		    <RelativeLayout
		        android:id="@+id/drawer_relativelayout"
		        android:layout_width="wrap_content"
		        android:layout_height="match_parent"
		        android:layout_gravity="end"
		        android:background="@color/colorPrimary">


		</android.support.v4.widget.DrawerLayout>

###2. 메인에서 아래와 같이 선언한다.

<MainActivity.java>

	        // Drawer 만들기
	        ListView listView = (ListView)findViewById(R.id.drawer_listView);
	        ArrayAdapter listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);
	        listView.setAdapter(listAdapter);
	        drawer_relativeLayout = (RelativeLayout)findViewById(R.id.drawer_relativelayout);
	        btnDrawerMenu = (Button)findViewById(R.id.btnDrawerMenu);
	        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
	
	        btnDrawerMenu.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                drawerLayout.openDrawer(drawer_relativeLayout);
	            }
	        });
#Todo

뷰페이저의 각 페이지마다 크기가 다르기 때문에 동적할당을 해야한다.

각 페이지의 contents를 채우고 버튼의 동적 할당을 고민해야 한다.