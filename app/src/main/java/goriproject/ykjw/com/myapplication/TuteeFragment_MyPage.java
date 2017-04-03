package goriproject.ykjw.com.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import goriproject.ykjw.com.myapplication.MypageFragment.MyPageTuteeOneFragment;
import goriproject.ykjw.com.myapplication.MypageFragment.MyPageTuteeThreeFragment;
import goriproject.ykjw.com.myapplication.MypageFragment.MyPageTuteeTwoFragment;

/**
 * Created by JINWOO on 2017-04-03.
 */




public class TuteeFragment_MyPage extends Fragment {

    // 탭
    TabLayout tabInner_activity_my_page = null;

    // 프래그먼트
    MyPageTuteeOneFragment myPageTutee_One_Fragment;
    MyPageTuteeThreeFragment myPageTutee_Two_Fragment;
    MyPageTuteeTwoFragment myPageTutee_Three_Fragment;

    public TuteeFragment_MyPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage_tutee, container, false);
        if(view !=null){
            return view;
        }

        myPageTutee_One_Fragment = new MyPageTuteeOneFragment();
        myPageTutee_Two_Fragment = new MyPageTuteeThreeFragment();
        myPageTutee_Three_Fragment = new MyPageTuteeTwoFragment();

        // 탭 레이아웃(안) & 뷰페이저(안) 초기화
        tabInner_activity_my_page = (TabLayout)view.findViewById(R.id.tabInner_activity_my_page);
        tabInner_activity_my_page.addTab(tabInner_activity_my_page.newTab().setText("수업신청"));
        tabInner_activity_my_page.addTab(tabInner_activity_my_page.newTab().setText("수강목록"));
        tabInner_activity_my_page.addTab(tabInner_activity_my_page.newTab().setText("위시리스트"));


        ViewPager viewPagerInner_activity_may_page = (ViewPager)view.findViewById(R.id.viewPagerInner_activity_my_page);
        PagerAdapterForTutee adapterForTutee = new PagerAdapterForTutee(getActivity().getSupportFragmentManager());
        viewPagerInner_activity_may_page.setAdapter(adapterForTutee);
        tabInner_activity_my_page.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPagerInner_activity_may_page));
        viewPagerInner_activity_may_page.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabInner_activity_my_page));

        return view;
    }


    class PagerAdapterForTutee extends FragmentStatePagerAdapter {
        private final int TABCOUNT_MYPAGE_INNER=3;

        public PagerAdapterForTutee(FragmentManager fm) {
            super(fm);

        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0 : fragment = myPageTutee_One_Fragment;
                    break;
                case 1 : fragment = myPageTutee_Two_Fragment;
                    break;
                case 2 : fragment = myPageTutee_Three_Fragment;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return TABCOUNT_MYPAGE_INNER;
        }
    }

}
