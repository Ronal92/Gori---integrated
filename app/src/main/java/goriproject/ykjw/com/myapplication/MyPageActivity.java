package goriproject.ykjw.com.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import goriproject.ykjw.com.myapplication.Test.CustomPager;

public class MyPageActivity extends AppCompatActivity {

    TabLayout tabOuter_activity_my_page;        // 탭

    TuteeFragment_MyPage tutee;                        // 프래그먼트
    TutorFragment_MyPage tutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // 프래그먼트 초기화
        tutee = new TuteeFragment_MyPage();
        tutor = new TutorFragment_MyPage();

        // 탭 레이아웃(바깥) & 뷰페이저(바깥) 초기화
        tabOuter_activity_my_page = (TabLayout)findViewById(R.id.tabOuter_activity_my_page);

        tabOuter_activity_my_page.addTab(tabOuter_activity_my_page.newTab().setText("수강생"));
        tabOuter_activity_my_page.addTab(tabOuter_activity_my_page.newTab().setText("튜터"));

        ViewPager viewPagerOuter_activity_my_page = (ViewPager)findViewById(R.id.viewPagerOuter_activity_my_page);
        PagerAdapterForOuter adapterMyPage = new PagerAdapterForOuter(getSupportFragmentManager());
        viewPagerOuter_activity_my_page.setAdapter(adapterMyPage);
        tabOuter_activity_my_page.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerOuter_activity_my_page));



    }

    class PagerAdapterForOuter extends FragmentStatePagerAdapter{
        private final int TABCOUNT_MYPAGE_OUTER=2;

        public PagerAdapterForOuter(FragmentManager fm) {
            super(fm);

        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0 : fragment = tutee;
                    break;
                case 1 : fragment = tutor;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TABCOUNT_MYPAGE_OUTER;
        }
    }



}
