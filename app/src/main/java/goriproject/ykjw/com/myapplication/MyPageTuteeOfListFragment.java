package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import goriproject.ykjw.com.myapplication.domain_mypage_retrieve.MyPage;


public class MyPageTuteeOfListFragment extends Fragment {

    private static final String ARG_TAP_TYPE = "list_type";

    // 각 탭별로 식별하기 위한 플래그 식별자
    public static final String TYPE_APPLICATION = "APPLICATION";
    public static final String TYPE_CLASSLIST = "CLASSLIST";
    public static final String TYPE_WISHLIST = "WISHLIST";

    // 식별자를 담을 변수
    private String mListType = "";

    // 뭔진 모르지만 나중에 혹시 사용될 거 같애서.......(예상 : 이미지파일들)
    MyPage myPage_tutee = null;

    public MyPageTuteeOfListFragment() {
        // Required empty public constructor
    }

    public static MyPageTuteeOfListFragment newInstance(String typeFlag, MyPage myPage_tutee) {
        Bundle args = new Bundle();
        MyPageTuteeOfListFragment myPageTuteeOfListFragment = new MyPageTuteeOfListFragment();
        args.putString(ARG_TAP_TYPE, typeFlag);
        args.putSerializable("mypage",myPage_tutee);
        myPageTuteeOfListFragment.setArguments(args);
        return myPageTuteeOfListFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            myPage_tutee = (MyPage)getArguments().getSerializable("mypage");
            mListType = getArguments().getString(ARG_TAP_TYPE);

            /*
            if(TYPE_APPLICATION.equals(mListType)){
                //datas = DataLoader.getMusic(getContext());
                //여기서 데이터를 로드한다.
            } else if(TYPE_CLASSLIST.equals(mListType)){
                //datas = DataLoader.getMusic(getContext());
            } else if(TYPE_WISHLIST.equals(mListType)){
                //datas = DataLoader.getMusic(getContext());
            }
            */
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_mypage_tutee, container, false);

        // Set the adapter
        if(view instanceof RecyclerView){
            Context context = view.getContext();
            RecyclerView recyclerViewTutee = (RecyclerView) view;
            // 여기서 탭별로 데이터를 따로 보내야한다.
            recyclerViewTutee.setAdapter(new MyPageAdapter(getContext(), myPage_tutee, mListType));
        }
        return view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}