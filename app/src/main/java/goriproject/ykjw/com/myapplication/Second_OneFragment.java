package goriproject.ykjw.com.myapplication;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_OneFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    LinearLayout ll_one_curiculum;
    View view;
    private Talent talent;
    tutor t1,t2,t3,t4;

    SecondActivity activity;

    public void setActivity(SecondActivity activity){
        this.activity = activity;
    }

    public Second_OneFragment() {
        // Required empty public constructor
    }
    public void setTalent(Talent talenta) {
        // Required empty public constructor
        talent = talenta;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_second_one, container, false);

        ll_one_curiculum = (LinearLayout)view.findViewById(R.id.ll_one_curiculum);
        for(int i = 0 ; i < 5 ; i ++) {
            LinearLayout li_son = new LinearLayout(getContext());
            li_son.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            li_son.setLayoutParams(p2);

            LinearLayout li_son2 = new LinearLayout(getContext());
            li_son2.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(40, ViewGroup.LayoutParams.MATCH_PARENT);
            li_son2.setLayoutParams(p4);

            ImageView iv = new ImageView(getContext());
            Glide.with(getContext()).load(R.drawable.dong2).into(iv);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(40, 40);
            iv.setLayoutParams(p);
            ImageView iv2 = new ImageView(getContext());
            Glide.with(getContext()).load(R.drawable.aaa).into(iv2);
            LinearLayout.LayoutParams p5 = new LinearLayout.LayoutParams(40, ViewGroup.LayoutParams.MATCH_PARENT);
            iv2.setLayoutParams(p5);

            li_son2.addView(iv);
            li_son2.addView(iv2);

            LinearLayout li_son3 = new LinearLayout(getContext());
            li_son3.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams p6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            li_son3.setLayoutParams(p6);

            TextView tv_curi_hoicha = new TextView(getContext());
            LinearLayout.LayoutParams p9 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv_curi_hoicha.setLayoutParams(p9);
            tv_curi_hoicha.setTextSize(20);
            //tv_curi_hoicha.setPadding(30,0,0,15); // 나중에는 이걸로 설정할듯
            tv_curi_hoicha.setPadding(00,0,0,15);
            tv_curi_hoicha.setTextColor(getResources().getColor(R.color.colorAccent));
            tv_curi_hoicha.setText(i+1 + "회차");

            TextView tv_curi = new TextView(getContext());
            //tv_curi.setPadding(30,0,30,0);
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv_curi.setTextSize(15);
            tv_curi.setLayoutParams(p3);
            tv_curi.setText("결과물 발표\n" +
                    "\n" +
                    "커리큘럼은 언제든지 변경될 수 있습니다.\n" +
                    "수강생의 상황에 맞게 낭비되는 시간 없이 최적화 될 수 있도록 유연성 있게 구성하려고 합니다.\n");

            ImageView iv3 = new ImageView(getContext());
            Glide.with(getContext()).load(R.drawable.list_dummy).into(iv3);
            LinearLayout.LayoutParams p7 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iv3.setLayoutParams(p7);

            li_son3.addView(tv_curi_hoicha);
            li_son3.addView(tv_curi);
            li_son3.addView(iv3);

            //li_son.addView(li_son2);
            li_son.addView(li_son3);

            ll_one_curiculum.addView(li_son);
        }

        //유튜브
        YouTubePlayerSupportFragment mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
        mYoutubePlayerFragment.initialize("AIzaSyBQVAdj7fCNWvgha7ue8EXg2hCn-1lUBBo", this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
        fragmentTransaction.commit();


        //관련 이미지
        for(int i = 0 ; i < 5 ; i ++) {
            LinearLayout li_one_img = (LinearLayout)view.findViewById(R.id.li_one_img);
            ImageView iv3 = new ImageView(getContext());
            Glide.with(getContext()).load(R.drawable.list_dummy).into(iv3);
            LinearLayout.LayoutParams p6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p6.setMargins(0,20,0,20);
            iv3.setLayoutParams(p6);
            li_one_img.addView(iv3);
        }

        //다른 클래스 추가
        ImageView iv_one_otherimg1 = (ImageView)view.findViewById(R.id.iv_one_otherimg1);
        ImageView iv_one_otherimg2 = (ImageView)view.findViewById(R.id.iv_one_otherimg2);
        ImageView iv_one_otherimg3 = (ImageView)view.findViewById(R.id.iv_one_otherimg3);
        ImageView iv_one_otherimg4 = (ImageView)view.findViewById(R.id.iv_one_otherimg4);
        TextView tv_one_otherimg1 = (TextView)view.findViewById(R.id.tv_one_otherimg1);
        TextView tv_one_otherimg2 = (TextView)view.findViewById(R.id.tv_one_otherimg2);
        TextView tv_one_otherimg3 = (TextView)view.findViewById(R.id.tv_one_otherimg3);
        TextView tv_one_otherimg4 = (TextView)view.findViewById(R.id.tv_one_otherimg4);
        TextView tv_one_otherlocation1 = (TextView)view.findViewById(R.id.tv_one_otherlocation1);
        TextView tv_one_otherlocation2 = (TextView)view.findViewById(R.id.tv_one_otherlocation2);
        TextView tv_one_otherlocation3 = (TextView)view.findViewById(R.id.tv_one_otherlocation3);
        TextView tv_one_otherlocation4 = (TextView)view.findViewById(R.id.tv_one_otherlocation4);


        /*
        // 랜덤 생성
        if(TutorLoader.datas.size() == 0) {
            TutorLoader.loadData();
        }
        int maxsize = TutorLoader.datas.size();
        int[] arr = new int[10];  //1차원배열 방 10개를 만듭니다.
        int ran=0;    //랜덤값을 받을 변수를 만듭니다.
        boolean cheak;    // 비교하기 위해 boolean형 변수를 만듭니다.
        Random r = new Random();    // Random형 객체를 만듭니다.

        for (int i=0; i<arr.length; i++) {    // 배열의 크기만큼 for문을 돌립니다.
            ran = r.nextInt(maxsize);    // nextInt(10)하면 0-9까지 나오므로  +1을 시켜 1-10까지로 만듭니다.
            cheak = true;    // i문이 돌 때마다 cheak를 true로 만듭니다.
            for (int j=0; j<i; j++) {    //if문 때문에 j를 i값만큼 돌립니다.
                if(arr[j] == ran) {
                    // arr배열의 방은 다 비어있는 상태이고 위에서 nextInt를 해야 하나씩 들어갑니다.
                    // 그러므로 i의 값만큼 배열에  들어가있는 것입니다.
                    // for문을 돌리면서  방금 받은 random값과 배열에 들어있는 값들을 비교하여 같은게 있으면
                    i--;    // i의 값을 하나 줄여 한 번 더 돌게 합니다.
                    cheak=false;    // 목적과는 다르게 같은 값이 나왔으므로 cheak를 false로 만듭니다.
                }
            }
            if(cheak)    // 위의 if문의 조건을 만족하지 않았으면 자동으로 cheak는 true므로 실행이 됩니다.
                arr[i] = ran;    // ran에 받은 값을 arr[i]방에 넣습니다.
        }

        // 데이터 넣기
        t1 = TutorLoader.datas.get(arr[0]);
        Glide.with(getContext()).load(R.drawable.list_dummy).into(iv_one_otherimg1);
        tv_one_otherimg1.setText(t1.getClass_name());
        tv_one_otherlocation1.setText(t1.getLocation());

        t2 = TutorLoader.datas.get(arr[1]);
        Glide.with(getContext()).load(R.drawable.list_dummy).into(iv_one_otherimg2);
        tv_one_otherimg2.setText(t2.getClass_name());
        tv_one_otherlocation2.setText(t2.getLocation());

        t3 = TutorLoader.datas.get(arr[2]);
        Glide.with(getContext()).load(R.drawable.list_dummy).into(iv_one_otherimg3);
        tv_one_otherimg3.setText(t3.getClass_name());
        tv_one_otherlocation3.setText(t3.getLocation());

        t4 = TutorLoader.datas.get(arr[3]);
        Glide.with(getContext()).load(R.drawable.list_dummy).into(iv_one_otherimg4);
        tv_one_otherimg4.setText(t4.getClass_name());
        tv_one_otherlocation4.setText(t4.getLocation());

        iv_one_otherimg1.setOnClickListener(cl);
        iv_one_otherimg2.setOnClickListener(cl);
        iv_one_otherimg3.setOnClickListener(cl);
        iv_one_otherimg4.setOnClickListener(cl);
        */
        return view;
    }

    View.OnClickListener cl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.iv_one_otherimg1 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("id",t1.getTutor_id());
                    activity.finish();
                    getContext().startActivity(intent);
                    break;
                case R.id.iv_one_otherimg2 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("id",t2.getTutor_id());
                    activity.finish();
                    getContext().startActivity(intent);
                    break;
                case R.id.iv_one_otherimg3 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("id",t3.getTutor_id());
                    activity.finish();
                    getContext().startActivity(intent);
                    break;
                case R.id.iv_one_otherimg4 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    intent.putExtra("id",t4.getTutor_id());
                    activity.finish();
                    getContext().startActivity(intent);
                    break;
            }
        }
    };


    //유튜브
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
            youTubePlayer.cueVideo("xpw-RsB57Js");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this.getActivity(),1).show();
        } else {
            Toast.makeText(this.getActivity(),
                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
