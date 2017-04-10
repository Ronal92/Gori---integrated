package goriproject.ykjw.com.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageTutorOfListFragment extends Fragment {

    private static final String ARG_TAP_TYPE = "list_type";

    // 각 탭별로 식별하기 위한 플래그 식별자
    public static final String TYPE_RESUME = "RESUME";
    public static final String TYPE_MYCLASS = "MYCLASS";

    // 식별자를 담을 변수
    private String mListType = "";

    // 뭔진 모르지만 나중에 혹시 사용될 거 같애서.......(예상 : 이미지파일들)
    private List<?> datas;

    public MyPageTutorOfListFragment() {
    }

    public static MyPageTutorOfListFragment newInstance(String typeFlag){
        Bundle args = new Bundle();
        MyPageTutorOfListFragment myPageTutorOfListFragment = new MyPageTutorOfListFragment();
        args.putString(ARG_TAP_TYPE, typeFlag);
        myPageTutorOfListFragment.setArguments(args);
        return myPageTutorOfListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
                mListType = getArguments().getString(ARG_TAP_TYPE);

                if(TYPE_RESUME.equals(mListType)){
                    //datas = DataLoader.getMusic(getContext());
                    //여기서 데이터를 로드한다.
                } else if(TYPE_MYCLASS.equals(mListType)){
                    //datas = DataLoader.getMusic(getContext());
                }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.list_fragment_mypage_tutor, container, false);

        // Set the adapter
        if(view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerViewTutor = (RecyclerView) view;
            recyclerViewTutor.setAdapter(new MyPageAdapter(getContext(), datas, mListType));
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
