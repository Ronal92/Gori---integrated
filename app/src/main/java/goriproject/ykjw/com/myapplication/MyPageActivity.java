package goriproject.ykjw.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * MyPage에서 수강생 탭과 튜터 탭을 보여준다.
 * inner Fragment로 MypageTuteeFragment, MypageTutorFragment가 있다.
 */
public class MyPageActivity extends AppCompatActivity {

    private  MypageTuteeFragment TuteeFragment = null;
    private MypageTutorFragment TutorFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        TuteeFragment = new MypageTuteeFragment();
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
     * Mypage 수강생 탭에서 수업신청서, 수강목록, 위시리스트 페이저를 생성해준다.
     */
    public static class MypageTuteeFragment extends Fragment {

        private static final String TAG = "RAPSTAR";
        View view = null;

        public MypageTuteeFragment() {
            // Required empty public constructor
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
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_APPLICATION));
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_CLASSLIST));
            pagerTuteeAdapter.add(MyPageTuteeOfListFragment.newInstance(MyPageTuteeOfListFragment.TYPE_WISHLIST));

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

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            Log.i(TAG, "==================================TuteeFragment OnCreate()");
            super.onCreate(savedInstanceState);
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



