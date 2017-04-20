package goriproject.ykjw.com.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.io.IOException;
import java.util.Random;

import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.datas;
import static goriproject.ykjw.com.myapplication.Statics.maxsize;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_OneFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    LinearLayout ll_one_curiculum;
    View view;
    private Talent talent;
    Results t1,t2,t3,t4;
    Results item;
    TalentDetail td = new TalentDetail();
    SecondActivity activity;

    TextView txt_secondone_allprice,txt_secondone_alltime,txt_one_tutorinfo,txt_one_whotuty,txt_one_introduce;

    public void setActivity(SecondActivity activity){
        this.activity = activity;
    }

    public Second_OneFragment() {
        // Required empty public constructor
    }
    public void setTalent(Talent talenta, Results item, TalentDetail td) {
        // Required empty public constructor
        talent = talenta;
        this.item = item;
        this.td = td;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null) {
            ImageView iv_second_profile = (ImageView)view.findViewById(R.id.iv_second_profile);
            ImageView iv_second_cover = (ImageView)view.findViewById(R.id.iv_second_cover);
            Glide.with(this).load(item.getCover_image()).thumbnail(0.1f).into(iv_second_cover);
            Glide.with(this).load(item.getTutor().getProfile_image()).into(iv_second_profile);
            return view;
        }

        view = inflater.inflate(R.layout.fragment_second_one, container, false);

        // 맨위 요약
        RatingBar rating_second;
        rating_second = (RatingBar)view.findViewById(R.id.rating_second);
        TextView tv_second_new = (TextView)view.findViewById(R.id.tv_second_new);
        if(Integer.parseInt(item.getReview_count().trim()) != 0) {
            long ratinglong = Math.round(Double.parseDouble(item.getAverage_rate()));
            int rating = (int)ratinglong;
            rating_second.setRating(rating);
            //레이팅바의 색깔을 바꿔야 할 경우에 사용
            LayerDrawable stars = (LayerDrawable) rating_second.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.argb(255,238,83,78), PorterDuff.Mode.SRC_ATOP);
            tv_second_new.setVisibility(View.GONE);
        } else {
            rating_second.setVisibility(View.GONE);
        }

        ImageView iv_second_profile = (ImageView)view.findViewById(R.id.iv_second_profile);
        ImageView iv_second_cover = (ImageView)view.findViewById(R.id.iv_second_cover);
        Button btn_second_numoftuty = (Button)view.findViewById(R.id.btn_second_numoftuty);
        TextView txt_name = (TextView)view.findViewById(R.id.txt_second_name);
        TextView txt_title = (TextView)view.findViewById(R.id.txt_second_Title);
        TextView tv_location = (TextView)view.findViewById(R.id.tv_second_location);
        TextView tv_price = (TextView)view.findViewById(R.id.tv_second_timeper);
        TextView tv_maxman = (TextView)view.findViewById(R.id.tv_second_maxman);
        TextView tv_schedule = (TextView)view.findViewById(R.id.tv_second_schedule);
        txt_name.setText(td.getTutor().getName()+"/"+item.getTutor().getNickname());
        txt_title.setText(td.getTitle());
        if(item.getRegions() != null) {
            String location = "";
            for(String i : item.getRegions()) {
                location = location + ", " + i;
            }
            if(location.length() > 2) {
                location = location.substring(2,location.length());
                tv_location.setText(location);
            } else {
                tv_location.setText("없음");
            }
        } else {

        }

        Glide.with(this).load(td.getCover_image()).thumbnail(0.1f).into(iv_second_cover);
        Glide.with(this).load(td.getTutor().getProfile_image()).into(iv_second_profile);

        tv_price.setText(td.getPrice_per_hour()+"원/시간");
        tv_maxman.setText(td.getType());
        tv_schedule.setText(td.getHours_per_class()+"시간/회");
        btn_second_numoftuty.setText("누적참여자"+td.getRegistration_count()+"명");

        int time = Integer.parseInt(td.getHours_per_class().trim())*Integer.parseInt(td.getNumber_of_class().trim());
        int priceall = time * Integer.parseInt(td.getPrice_per_hour().trim());

        //수업 기본 정보 뿌리기

        txt_one_tutorinfo = (TextView)view.findViewById(R.id.txt_one_tutorinfo);
        txt_one_introduce = (TextView)view.findViewById(R.id.txt_one_introduce);
        txt_secondone_alltime = (TextView)view.findViewById(R.id.txt_secondone_alltime);
        txt_secondone_allprice = (TextView)view.findViewById(R.id.txt_secondone_allprice);

        txt_one_tutorinfo.setText(td.getTutor_info());
        txt_one_introduce.setText(td.getClass_info());
        txt_secondone_allprice.setText("총 "+priceall+"원");
        txt_secondone_alltime.setText(td.getNumber_of_class()+"회 총 "+time+"시간");

        //커리큘럼 추가 부분
        ll_one_curiculum = (LinearLayout)view.findViewById(R.id.ll_one_curiculum);

        Log.e("dfaasdfasdfasdf",String.valueOf(td.getTitle()));
        if(td.getCurriculums() != null) {
            for(int i = 0 ; i < td.getCurriculums().size(); i ++) {
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
                tv_curi.setText(td.getCurriculums().get(i).getInformation());
                li_son3.addView(tv_curi_hoicha);
                li_son3.addView(tv_curi);

                if(td.getCurriculums().get(i).getImage() != null) {
                    ImageView iv3 = new ImageView(getContext());
                    Glide.with(getContext()).load(td.getCurriculums().get(i).getImage()).into(iv3);
                    LinearLayout.LayoutParams p7 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    iv3.setLayoutParams(p7);
                    li_son3.addView(iv3);
                } else {
                    View v = new View(getContext());
                    LinearLayout.LayoutParams p20 = new LinearLayout.LayoutParams(30, 30);
                    v.setLayoutParams(p20);
                    li_son3.addView(v);
                }


                //li_son.addView(li_son2);
                li_son.addView(li_son3);

                ll_one_curiculum.addView(li_son);
            }
        }


        //유튜브
        if(td.getVideo1() != "" && td.getVideo1() != null) {
            YouTubePlayerSupportFragment mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
            mYoutubePlayerFragment.initialize("AIzaSyBQVAdj7fCNWvgha7ue8EXg2hCn-1lUBBo", this);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
            fragmentTransaction.commit();
        } else {
            TextView tv_title_youtube = (TextView)view.findViewById(R.id.tv_one_title_youtube);
            tv_title_youtube.setVisibility(View.GONE);
            FrameLayout yt = (FrameLayout)view.findViewById(R.id.fragment_youtube_player);
            yt.setVisibility(View.GONE);
        }
        //관련 이미지
        if(td.getClass_images() != null) {
            for(int i = 0 ; i < td.getClass_images().size() ; i ++) {
                LinearLayout li_one_img = (LinearLayout)view.findViewById(R.id.li_one_img);
                ImageView iv3 = new ImageView(getContext());
                Glide.with(getContext()).load(td.getClass_images().get(i).getImage()).into(iv3);
                LinearLayout.LayoutParams p6 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                p6.setMargins(0,20,0,20);
                iv3.setLayoutParams(p6);
                li_one_img.addView(iv3);
            }
        } else {
            TextView tv_title_img = (TextView)view.findViewById(R.id.tv_one_title_img);
            tv_title_img.setVisibility(View.GONE);
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

        // 랜덤 생성
        if(datas.size() == 0 || datas == null) {
            TutorLoader.loadData();
        }
        Log.e("ddddddddddddxxxxxd", String.valueOf(maxsize));
        Log.e("d1asdfs13232",td.getVideo1());
        int[] arr = new int[maxsize];  //1차원배열 방 10개를 만듭니다.
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
        t1 = datas.get(arr[0]);
        Glide.with(getContext()).load(t1.getCover_image()).into(iv_one_otherimg1);
        tv_one_otherimg1.setText(t1.getTitle());
        if(t1.getRegions() != null && t1.getRegions().size() != 0) {
            tv_one_otherlocation1.setText(t1.getRegions().get(0));
        } else {
            tv_one_otherlocation1.setText("지역없음");
        }


        t2 = datas.get(arr[1]);
        Glide.with(getContext()).load(t2.getCover_image()).into(iv_one_otherimg2);
        tv_one_otherimg2.setText(t2.getTitle());
        if(t2.getRegions() != null && t2.getRegions().size() != 0) {
            tv_one_otherlocation2.setText(t2.getRegions().get(0));
        } else {
            tv_one_otherlocation2.setText("지역없음");
        }

        t3 = datas.get(arr[2]);
        Glide.with(getContext()).load(t3.getCover_image()).into(iv_one_otherimg3);
        tv_one_otherimg3.setText(t3.getTitle());
        if(t3.getRegions() != null && t3.getRegions().size() != 0) {
            tv_one_otherlocation3.setText(t3.getRegions().get(0));
        } else {
            tv_one_otherlocation3.setText("지역없음");
        }

        t4 = datas.get(arr[3]);
        Glide.with(getContext()).load(t4.getCover_image()).into(iv_one_otherimg4);
        tv_one_otherimg4.setText(t4.getTitle());
        if(t4.getRegions() != null && t4.getRegions().size() != 0) {
            tv_one_otherlocation4.setText(t4.getRegions().get(0));
        } else {
            tv_one_otherlocation4.setText("지역없음");
        }

        iv_one_otherimg1.setOnClickListener(cl);
        iv_one_otherimg2.setOnClickListener(cl);
        iv_one_otherimg3.setOnClickListener(cl);
        iv_one_otherimg4.setOnClickListener(cl);

        return view;
    }

    View.OnClickListener cl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            CheckTypesTask task;
            switch (v.getId()) {
                case R.id.iv_one_otherimg1 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    onDetach();
                    intent.putExtra("id",Integer.parseInt(t1.getPk().trim()));
                    intent.putExtra("item",t1);
                    task = new CheckTypesTask();
                    task.setidintent(Integer.parseInt(t1.getPk().trim()),intent);
                    task.execute();
                    break;
                case R.id.iv_one_otherimg2 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    onDetach();
                    intent.putExtra("id",Integer.parseInt(t2.getPk().trim()));
                    intent.putExtra("item",t2);
                    task = new CheckTypesTask();
                    task.setidintent(Integer.parseInt(t2.getPk().trim()),intent);
                    task.execute();
                    break;
                case R.id.iv_one_otherimg3 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    onDetach();
                    intent.putExtra("id",Integer.parseInt(t3.getPk().trim()));
                    intent.putExtra("item",t3);
                    task = new CheckTypesTask();
                    task.setidintent(Integer.parseInt(t3.getPk().trim()),intent);
                    task.execute();
                    break;
                case R.id.iv_one_otherimg4 :
                    intent = new Intent(getContext(), SecondActivity.class);
                    onDetach();
                    intent.putExtra("id",Integer.parseInt(t4.getPk().trim()));
                    intent.putExtra("item",t4);
                    task = new CheckTypesTask();
                    task.setidintent(Integer.parseInt(t1.getPk().trim()),intent);
                    task.execute();
                    break;
            }
        }
    };


    //유튜브
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
           // https://youtu.be/84SnMVQVYkY
            if(!td.getVideo1().equals("")) {
                int yt = td.getVideo1().indexOf("be/");
                String ytt = td.getVideo1().substring(yt+3,td.getVideo1().length());
                youTubePlayer.cueVideo(ytt);
            }
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

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                getContext());

        Intent intent;
        int id = 0;

        public void setidintent(int id, Intent intent) {
            this.id = id;
            this.intent = intent;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("데이터 로딩중..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // 1. 레트로핏을 생성하고
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mozzi.co.kr/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Talent_Detail_Interface tdService = retrofit.create(Talent_Detail_Interface.class);

            final Call<TalentDetail> tds = tdService.getTalentDetail(String.valueOf(id));

            try {
                td = tds.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            intent.putExtra("td",td);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            activity.finish();
            getContext().startActivity(intent);
            super.onPostExecute(result);
        }
    }
}
