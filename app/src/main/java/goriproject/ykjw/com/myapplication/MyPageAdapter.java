package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 실질적으로 MyPage 데이터들을 뿌려주는 곳.
 * 여기서 각 탭별로 구분하여 다른  CardView를 보여준다.
 */

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.SimpleViewHolder> {

    private static final String TAG = "RAPSTAR";

    private Context context = null;
    private List<?> datas;
    private String typeFlag;

    // 탭에 보여줄 xml 식별자
    private int tap_layout_id = 0;

    public MyPageAdapter(Context context, List<?> datas, String typeFlag){
        this.context = context;
        this.datas = datas;
        this.typeFlag = typeFlag;
        switch(typeFlag){
            case MyPageTuteeOfListFragment.TYPE_APPLICATION:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_one_carditem;
                break;
            case MyPageTuteeOfListFragment.TYPE_CLASSLIST:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_two_carditem;
                break;
            case MyPageTuteeOfListFragment.TYPE_WISHLIST:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_three_carditem;
                break;
            case MyPageTutorOfListFragment.TYPE_RESUME:
                tap_layout_id = R.layout.fragment_mypage_tutor_fragment_one_carditem;
                break;
            case MyPageTutorOfListFragment.TYPE_MYCLASS:
                tap_layout_id = R.layout.fragment_mypage_tutor_fragment_two_carditem;
                break;
        }

    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(tap_layout_id, parent, false);

        SimpleViewHolder svh = new SimpleViewHolder(view);
        return svh;
    }


    /*
     탭마다 다른 그림이 들어가게 설정하였다.
     */
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        switch(typeFlag){
            case MyPageTuteeOfListFragment.TYPE_APPLICATION:
                Glide.with(context).load(R.drawable.test2).into(holder.img_tutee_fragment_one);
                break;
            case MyPageTuteeOfListFragment.TYPE_CLASSLIST:
                Glide.with(context).load(R.drawable.test).into(holder.img_tutee_fragment_one);
                break;
            case MyPageTuteeOfListFragment.TYPE_WISHLIST:
                Glide.with(context).load(R.drawable.test2).into(holder.img_tutee_fragment_one);
                break;
            case MyPageTutorOfListFragment.TYPE_RESUME:
                Glide.with(context).load(R.drawable.test).into(holder.img_tutee_fragment_one);
                break;
            case MyPageTutorOfListFragment.TYPE_MYCLASS:
                Glide.with(context).load(R.drawable.test2).into(holder.img_tutee_fragment_one);
                break;

        }
    }



    @Override
    public int getItemCount() {
        return 10;
    }

    /*
    위젯 아이디는 xml 모두 똑같아야 onBindViewHolder에서 같이 지정할 수 있다.
     */

    public class SimpleViewHolder extends RecyclerView.ViewHolder{

        ImageView img_tutee_fragment_one;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            img_tutee_fragment_one = (ImageView)itemView.findViewById(R.id.img_tutee_fragment_one);
            img_tutee_fragment_one.bringToFront();
        }

    }
}
