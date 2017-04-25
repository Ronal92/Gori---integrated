package goriproject.ykjw.com.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_mypage_retrieve.MyPage;

import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;
import static goriproject.ykjw.com.myapplication.Statics.user_name;

/**
 * MyPage에서 수강생 탭과 튜터 탭을 보여준다.
 * inner Fragment로 MypageTuteeFragment, MypageTutorFragment가 있다.
 */
public class MyPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private  MypageTuteeFragment TuteeFragment = null;
    private MypageTutorFragment TutorFragment = null;

    DrawerLayout drawer;
    NavigationView navigationView;

    MyPage myPage = null;
    UserDetail userDetail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Intent intent = getIntent();
        myPage = (MyPage)intent.getSerializableExtra("mypage");
        userDetail = (UserDetail)intent.getSerializableExtra("userInformation");


        // mypage 상단 부분분
        TextView txtName_profile_mypage = (TextView)findViewById(R.id.txtName_profile_mypage);
        txtName_profile_mypage.setText(userDetail.getName());
        CircleImageView img_profile_mypage = (CircleImageView)findViewById(R.id.img_profile_mypage);
        Glide.with(this).load(userDetail.getProfile_image()).placeholder(R.mipmap.ic_launcher).into(img_profile_mypage); // 이미지 표시



        // 드로어레이아웃
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mypage);
        navigationView  = (NavigationView) findViewById(R.id.nav_view_mypage);
        navigationView.setNavigationItemSelectedListener(this);

        // 프래그먼트
        TuteeFragment = MypageTuteeFragment.newInstance(myPage);
        TutorFragment = new MypageTutorFragment();

        TabLayout outerTab = (TabLayout)findViewById(R.id.outerTab);
        outerTab.addTab(outerTab.newTab().setText("수강생"));
        outerTab.addTab(outerTab.newTab().setText("튜터"));

        outerTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if(tab.getPosition() == 0){
                    transaction.replace(R.id.mypageFrame, TuteeFragment);
                } else if(tab.getPosition() == 1) {
                    transaction.replace(R.id.mypageFrame, TutorFragment);
                    transaction.addToBackStack(null); // 스택을 사용하겠다.

                }

                transaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        init();
    }

    private void init(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mypageFrame, TuteeFragment);
        transaction.addToBackStack(null); // 스택을 사용하겠다.
        transaction.commit();

    }

    // 유저가 백버튼을 누르면 프래그먼트와 액티비티가 같이 사라지게 한다.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     네비게이션 드로어 설정
     */

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.menu_introduce_gori) {
            //TODO 고리소개 페이지로드
        } else if (id == R.id.menu_signinout) {
            if(is_signin) {
                key = null;
                is_signin = false;
                item.setTitle("로그인");
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("autologin", null);
                editor.commit();
                Toast.makeText(MyPageActivity.this, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(MyPageActivity.this, SignInActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.menu_mypage) {
            // 동작할 필요 없음.
        } else if (id == R.id.menu_tutor_go) {
            // 아직 구현할 생각 없음
            Toast.makeText(MyPageActivity.this, "튜터등록은 웹사이트에서 해주세요!", Toast.LENGTH_LONG).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_mypage);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    /**
     * Mypage 수강생 탭에서 수업신청서, 수강목록, 위시리스트 페이저를 생성해준다.
     */
    public static class MypageTuteeFragment extends Fragment {

        private static final String TAG = "RAPSTAR";
        View view = null;

        MyPage mypage = null;

        public MypageTuteeFragment() {
            // Required empty public constructor
        }

        public static MypageTuteeFragment newInstance(MyPage mypage){
            Bundle args = new Bundle();
            MypageTuteeFragment mypageTuteeFragment = new MypageTuteeFragment();
            args.putSerializable("mypage", mypage);
            mypageTuteeFragment.setArguments(args);
            return mypageTuteeFragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null) {
                mypage = (MyPage)getArguments().getSerializable("mypage");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i(TAG, "==================================TuteeFragment OnCreateView()");
            if( view!=null ){
                return view;
            }
            view = inflater.inflate(R.layout.fragment_mypage_tutee, container, false);

            // 탭
            TabLayout tabTutee = (TabLayout)view.findViewById(R.id.innerTab_fragment_mypage_tutee);
            tabTutee.addTab(tabTutee.newTab().setText("수업신청서"));
            tabTutee.addTab(tabTutee.newTab().setText("수강목록"));
            tabTutee.addTab(tabTutee.newTab().setText("위시리스트"));

            // 뷰페이저
            ViewPager viewPagerTutee = (ViewPager)view.findViewById(R.id.innerViewPager_fragment_mypage_tutee);
            PagerAdapter pagerTuteeAdapter = new PagerAdapter(getChildFragmentManager());

            // 어뎁터에 프래그먼트 추가
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_APPLICATION, mypage ));
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_CLASSLIST, mypage));
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_WISHLIST, mypage));

            // 리스너
            viewPagerTutee.setAdapter(pagerTuteeAdapter);
            tabTutee.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerTutee));
            viewPagerTutee.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabTutee));

            return view;
        }


        @Override
        public void onDestroy() {
            Log.i(TAG, "==================================TuteeFragment OnDestroy()");
            super.onDestroy();
        }

        @Override
        public void onDestroyView() {
            Log.i(TAG, "==================================TuteeFragment OnDestroyView()");
            super.onDestroyView();
        }



        public class PagerAdapter extends FragmentStatePagerAdapter {

            List<Fragment> fragments;

            public PagerAdapter(FragmentManager fm) {
                super(fm);
                fragments = new ArrayList<>();
            }


            public void add(Fragment fragment) {
                fragments.add(fragment);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        }

    }

    /**
     * Mypage 튜터 탭에서 수업 신청서와 내수업을 위한 뷰페이저를 생성한다.
     */

    public static class MypageTutorFragment extends Fragment {
        private static final String TAG = "RAPSTAR";
        View view = null;
        public MypageTutorFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i(TAG, "==================================TutorFragment OnCreateView()");

            if( view!=null ){
                return view;
            }
            view = inflater.inflate(R.layout.fragment_mypage_tutor, container, false);

            // 탭
            TabLayout tabTutor = (TabLayout)view.findViewById(R.id.innerTab_fragment_mypage_tutor);
            tabTutor.addTab(tabTutor.newTab().setText("수업신청서"));
            tabTutor.addTab(tabTutor.newTab().setText("내수업"));

            // 뷰페이저
            ViewPager viewPagerTutor = (ViewPager)view.findViewById(R.id.innerViewPager_fragment_mypage_tutor);
            TutorPagerAdapter pagerTutorAdapter = new TutorPagerAdapter(getChildFragmentManager());

            // 어뎁터에 프래그먼트 추가
            pagerTutorAdapter.add(MyPageTutorOfListFragment.newInstance(MyPageTutorOfListFragment.TYPE_RESUME));
            pagerTutorAdapter.add(MyPageTutorOfListFragment.newInstance(MyPageTutorOfListFragment.TYPE_MYCLASS));

            // 리스너
            viewPagerTutor.setAdapter(pagerTutorAdapter);
            tabTutor.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerTutor));
            viewPagerTutor.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabTutor));

            return view;
        }

        @Override
        public void onDestroy() {
            Log.i(TAG, "==================================TutorFragment OnDestroy()");
            super.onDestroy();
        }

        @Override
        public void onDestroyView() {
            Log.i(TAG, "==================================TutorFragment OnDestroyView()");
            super.onDestroyView();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            Log.i(TAG, "==================================TutorFragment OnCreate()");
            super.onCreate(savedInstanceState);
        }


        public class TutorPagerAdapter extends FragmentStatePagerAdapter {

            List<Fragment> fragments;

            public TutorPagerAdapter(FragmentManager fm) {
                super(fm);
                fragments = new ArrayList<>();
            }

            public void add(Fragment fragment) {
                fragments.add(fragment);
            }

            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        }

    }

}