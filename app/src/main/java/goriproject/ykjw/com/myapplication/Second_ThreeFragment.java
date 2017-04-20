package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;


import goriproject.ykjw.com.myapplication.Interfaces.User_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.ReviewsSecThreeFrag;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.User;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.Results;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static goriproject.ykjw.com.myapplication.Statics.is_signin;


/**
 * A simple {@link Fragment} subclass.
 */

public class Second_ThreeFragment extends Fragment {
    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL = "threeFragmentTL";
    private static final String KEY_FOR_TALENTDETAIL_INT = "threeFragmentTL_INT";
    private static final String KEY_FOR_TOKEN = "threeFragmentToken";

    Context context = null;
    PagerAdapter adapter = null;

    List<goriproject.ykjw.com.myapplication.domain_review_retrieve.Results> results_list = null;

    Dialog dialog = null; // 다이얼로그

    ReviewsSecThreeFrag reviewsSecThreeFrag = null; // GET으로 받을 내용들

    TalentDetail td = null;

    UserDetail userDetail = null;

    RecyclerView recyclerReview = null;


    public Second_ThreeFragment() {
        // Required empty public constructor
    }

    public static Second_ThreeFragment newInstance(TalentDetail td ){
        Second_ThreeFragment secondThreeFragment = new Second_ThreeFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL, td);
        secondThreeFragment.setArguments(args);
        return secondThreeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.td = (TalentDetail)getArguments().getSerializable(KEY_FOR_TALENTDETAIL);
        }
        createRetrofitGET();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        if(view != null){
            return view;
        }

        view = inflater.inflate(R.layout.fragment_second_three, container, false);
        context = view.getContext();


        TextView txtReview_count = (TextView)view.findViewById(R.id.txtReview_count);
        TextView txtAverageRate = (TextView)view.findViewById(R.id.txtAverageRate);
        txtReview_count.setText(td.getReview_count());
        txtAverageRate.setText(td.getAverage_rates().getTotal());

        // RatingBar (Total)
        RatingBar ratingBar_average = (RatingBar)view.findViewById(R.id.rb_Avrage);
        long ratinglong = Math.round(Double.parseDouble(td.getAverage_rates().getTotal()));
        ratingBar_average.setRating((int)ratinglong);


        // 버튼 : 리뷰 작성
        Button btnReview_second_activity = (Button)view.findViewById(R.id.btnReview_second_activity);
        btnReview_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(로그인)
                if(is_signin) {
                    // User_detail 통신
                    showDialog();
                } else {
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            }
        });


        // 리사이클러뷰 설정
        recyclerReview = (RecyclerView) view.findViewById(R.id.recycView_fragmentsecond_three);

        return view;
    }

    public void createRetrofitUserGet(final Boolean showDialogCheck, final goriproject.ykjw.com.myapplication.domain_review_retrieve.Results passedResults){
        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User_Detail_Interface tdService = retrofit.create(User_Detail_Interface.class);

        // 프로그레스 다이얼로그
        final ProgressDialog asyncDialog = new ProgressDialog(context);
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        asyncDialog.show();

        // 토큰 받아오기
        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        String token = pref.getString("token", null);

        Call<UserDetail> tds = tdService.getUserRetrieve("Token " + token);

        tds.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                userDetail = response.body();   // 현재 사용하는 유저 정보를 먼저 불러온다.
                if(showDialogCheck){
                    // 3. POST 이후 데이터를 따로 데이터 저장소에 보낸다.
                    results_list.add(passedResults);
                    adapter.setData(results_list);                     // 2.1 데이터 추가하고
                    adapter.notifyDataSetChanged();                // 2.2 어뎁터 갱신
                } else {
                    // 리사이클러뷰에서 데이터 세팅
                    adapter = new Second_ThreeFragment.PagerAdapter(getContext());
                    results_list = new ArrayList<>();
                    for (int i = 0; i < reviewsSecThreeFrag.getResults().length; i++) {
                        results_list.add(reviewsSecThreeFrag.getResults()[i]);      // Results() --> List<Results>로 : 동적할당
                    }
                    adapter.setData(results_list);
                    recyclerReview.setAdapter(adapter);
                    recyclerReview.setLayoutManager(new LinearLayoutManager(context));
                }
                asyncDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {

            }
        });

    }

    public void showDialog(){

        // Dialog
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_second__review_);

        // Dialog 사이즈 조절 하기
        ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 950;      //params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = 1500;    //params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);


        // Dialog 위젯
        ImageButton btnFinish = (ImageButton)dialog.findViewById(R.id.btnReviewFinish_second_review);
        Button btnCancel = (Button)dialog.findViewById(R.id.btnReviewCancel_second_review);
        Button btnUpload = (Button)dialog.findViewById(R.id.btnUpload_second_review);
        final EditText edit_commen_review = (EditText)dialog.findViewById(R.id.edit_commen_review);
        final RatingBar raiting_curriculum_review = (RatingBar)dialog.findViewById(R.id.raiting_curriculum_review);
        final RatingBar raiting_readiness_review = (RatingBar)dialog.findViewById(R.id.raiting_readiness_review);
        final RatingBar raiting_timeliness_review = (RatingBar)dialog.findViewById(R.id.raiting_timeliness_review);
        final RatingBar raiting_delivery_review = (RatingBar)dialog.findViewById(R.id.raiting_delivery_review);
        final RatingBar raiting_friendliness_review = (RatingBar)dialog.findViewById(R.id.raiting_friendliness_review);

        dialog.show();


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // 사용자가 리뷰 업로드 눌렀을 때,
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위젯에서 필요한 내용 받아오기
                String editReview = edit_commen_review.getText().toString();
                String rtCurriculum = (int)raiting_curriculum_review.getRating() + "";
                String rtReadiness = (int)raiting_readiness_review.getRating() + "";
                String rtTimeliness = (int)raiting_timeliness_review.getRating() + "";
                String rtDelivery = (int)raiting_delivery_review.getRating() + "";
                String rtFriendness = (int)raiting_friendliness_review.getRating() + "";

                // 1. POST 처리
                createRetrofitPOST(editReview, rtCurriculum, rtReadiness, rtTimeliness, rtDelivery, rtFriendness );


                // 2. GET : User 정보 받아오기
                createRetrofitUserGet(true, createLocalDateStore(editReview, rtCurriculum, rtReadiness, rtTimeliness, rtDelivery, rtFriendness));



                // 다이얼로그 처리
                if(dialog != null && dialog.isShowing()){
                    Log.i(TAG, "============================================dialog 1");
                    dialog.dismiss();
                }
            }
        });
    }

    public Results createLocalDateStore(String editReview, String rtCurriculum, String rtReadiness, String rtTimeliness, String rtDelivery, String rtFriendness ){
        User user  = new User();
        user.setName(userDetail.getName());
        user.setProfile_image(userDetail.getProfile_image());

        Log.i(TAG, "=================user id " + userDetail.getUser_id());
        Log.i(TAG, "=================user image " + userDetail.getProfile_image());

        Results results = new Results();
        results.setCurriculum(rtCurriculum);
        results.setReadiness(rtReadiness);
        results.setTimeliness(rtTimeliness);
        results.setDelivery(rtDelivery);
        results.setFriendliness(rtFriendness);
        results.setComment(editReview);
        results.setUser(user);
        results.setCreated_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) + "T");

        return results;
    }

    public void createRetrofitGET() {
        Log.i("RAPSTAR","======================== This is createRetrofitGET_Three()");

        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Review_Detail_Interface tdService = retrofit.create(Review_Detail_Interface.class);

        Call<ReviewsSecThreeFrag> tds = tdService.getReviewRetrieve(td.getPk());
        tds.enqueue(new Callback<ReviewsSecThreeFrag>() {
            @Override
            public void onResponse(Call<ReviewsSecThreeFrag> call, Response<ReviewsSecThreeFrag> response) {
                // 데이터 받아오고
                reviewsSecThreeFrag = response.body();
                // 현재 사용하고 있는 유저정보 받고 시작하라
                createRetrofitUserGet(false, null);

            }
            @Override
            public void onFailure(Call<ReviewsSecThreeFrag> call, Throwable t) {

            }
        });


    }

    public void createRetrofitPOST(String editReview, String rtCurriculum, String rtReadiness, String rtTimeliness, String rtDelivery, String rtFriendness ){
        // 2. POST 통신
        // 2.1 통신 로그 확인
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // 2.2 레트로핏을 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Review_Detail_Interface service = retrofit.create(Review_Detail_Interface.class);

        // 토큰 받아오기
        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        String token = pref.getString("token", null);

        // 2.3 데이터 받아오기
        Call<String> reviewData = service.setReviewRetrieve("Token " + token ,
                Integer.valueOf(td.getPk()),
                Integer.valueOf(rtCurriculum) ,
                Integer.valueOf(rtReadiness) ,
                Integer.valueOf(rtTimeliness) ,
                Integer.valueOf(rtDelivery) ,
                Integer.valueOf(rtFriendness) ,
                editReview
        );


        reviewData.enqueue(new Callback<String>() {
            // POST는 Response가 안온다.
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }

        });



    }

    public void createRetrofitDelete(String review_pk){
        // 1 .delete 통신

        // 2.1 통신 로그 확인
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        // 2.2 레트로핏을 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Review_Detail_Interface service = retrofit.create(Review_Detail_Interface.class);

        // 토큰 받아오기
        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
        String token = pref.getString("token", null);

        // 2.4 데이터 받아오기
        Call<Void> reviewDelete = service.deleteReview("Token " + token, review_pk);
        // Log.i(TAG, "==========================token : " + token + ", pk : " + reviews_Pager.get(review_position).getPk());

        reviewDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();

                } else  {
                    Toast.makeText(getContext(), response.code() + "", Toast.LENGTH_SHORT).show();
                    try {
                        Log.e(TAG, "========================response.body" + response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "========================delete 통신 실패!!");
                t.printStackTrace();
            }
        });
    }


    // Adapter : 리뷰를 리스트로 보여줄 어뎁터
    public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

        private static final String TAG = "RAPSTAR";
        private Context context = null;
        List<goriproject.ykjw.com.myapplication.domain_review_retrieve.Results> results = new ArrayList<>(); // 데이터 저장소

        public PagerAdapter(Context context){
            this.context = context;

        }

        public void setData(List<goriproject.ykjw.com.myapplication.domain_review_retrieve.Results> results){
            this.results = results;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_three_item, parent, false);
            ViewHolder svh = new ViewHolder(view);
            return svh;
        }

        @Override
        public int getItemCount() {
            //return reviews_Pager.size();
            return results.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Glide.with(context).load(results.get(position).getUser().getProfile_image()).placeholder(R.mipmap.ic_launcher).into(holder.img); // 이미지 표시
            holder.txtName.setText(results.get(position).getUser().getName());             // 이름 표시
            holder.txtComment.setText(results.get(position).getComment());           // Comment  표시
            String date = results.get(position).getCreated_date();         // 날짜 표시 (시간을 제외한 일자만 표시한다.)
            holder.txtDate.setText(date.substring(0,date.indexOf("T")));

            // 별점 표시 (평균값 구해서 출력)
            Double ave_total = (double)(Double.valueOf(results.get(position).getCurriculum())
                    + Double.valueOf(results.get(position).getReadiness())
                    + Double.valueOf(results.get(position).getTimeliness())
                    + Double.valueOf(results.get(position).getDelivery())
                    + Double.valueOf(results.get(position).getFriendliness()))/5;
            holder.ratingBar.setRating(Math.round(Double.parseDouble(ave_total + "")));


            // 버튼 : 리뷰삭제
            // if(holder.txtName.getText().equals(td.getUser())){ // 현재 사용자랑 같은 이름인지 체크
            if(is_signin && userDetail != null){
                if(holder.txtName.getText().equals(userDetail.getUser_id())){
                    final int review_position = position;
                    final String review_pk = results.get(position).getPk();   // 현재 아이템에 해당하는 PK
                    holder.btnDelete_fragment_review.setVisibility(View.VISIBLE);
                    holder.btnDelete_fragment_review.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // GET으로 PK를 구해야 한다.
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://mozzi.co.kr/api/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final ProgressDialog asyncDialog = new ProgressDialog(context);
                            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            asyncDialog.setMessage("로딩중입니다..");
                            asyncDialog.show();

                            Review_Detail_Interface tdService = retrofit.create(Review_Detail_Interface.class);

                            Call<ReviewsSecThreeFrag> tds = tdService.getReviewRetrieve(td.getPk());
                            tds.enqueue(new Callback<ReviewsSecThreeFrag>() {
                                @Override
                                public void onResponse(Call<ReviewsSecThreeFrag> call, Response<ReviewsSecThreeFrag> response) {
                                    ReviewsSecThreeFrag reviewsSecThreeFrag = response.body();

                                    if(TextUtils.isEmpty(results.get(review_position).getPk())){
                                        // 지울 정보의 pk를 찾는다.
                                        createRetrofitDelete(findPK(review_position, results, reviewsSecThreeFrag));

                                    } else {
                                        Log.i(TAG, "======== value of pk : " + results.get(review_position).getPk());
                                        createRetrofitDelete(results.get(review_position).getPk());
                                    }
                                    asyncDialog.dismiss();
                                    // 리뷰 삭제하고 데이터 업데이트
                                    results.remove(review_position);
                                    adapter.setData(results);
                                    adapter.notifyDataSetChanged();

                                }
                                @Override
                                public void onFailure(Call<ReviewsSecThreeFrag> call, Throwable t) {

                                }
                            });

                        }
                    });
                }
            }
            else {
                holder.btnDelete_fragment_review.setVisibility(View.GONE);
            }


        }

        public String findPK(int review_position, List<Results> results, ReviewsSecThreeFrag reviewsSecThreeFrag){
            String foundPK = "";
            for(int indexOfPk = 0; indexOfPk< reviewsSecThreeFrag.getResults().length; indexOfPk++){
                if(userDetail.getName().equals(reviewsSecThreeFrag.getResults()[indexOfPk].getUser().getName() )
                        && results.get(review_position).getCreated_date().substring(0,results.get(review_position).getCreated_date().indexOf("T")).equals(reviewsSecThreeFrag.getResults()[indexOfPk].getCreated_date().substring(0,reviewsSecThreeFrag.getResults()[indexOfPk].getCreated_date().indexOf("T")))
                        && reviewsSecThreeFrag.getResults()[indexOfPk].getComment().contains(results.get(review_position).getComment()))
                {
                    Log.i(TAG, "========  FOUND! : " + reviewsSecThreeFrag.getResults()[indexOfPk].getPk());
                    foundPK = reviewsSecThreeFrag.getResults()[indexOfPk].getPk();
                }
            }
            return foundPK;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout csLayout;
            TextView txtName;
            TextView txtDate;
            TextView txtComment;
            CircleImageView img;
            RatingBar ratingBar;
            Button btnDelete_fragment_review;
            boolean clicked = false;

            public ViewHolder(View itemView) {
                super(itemView);

                csLayout = (ConstraintLayout)itemView.findViewById(R.id.csLayout_review);
                txtName = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_review);
                txtDate = (TextView) itemView.findViewById(R.id.txtDate_tutee_fragment_review);
                txtComment = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_review);
                img = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_review);
                ratingBar = (RatingBar)itemView.findViewById(R.id.rb_tutee_fragment_review);
                btnDelete_fragment_review = (Button)itemView.findViewById(R.id.btnDelete_fragment_review);

                csLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( !clicked) {
                            ViewGroup.LayoutParams params = txtComment.getLayoutParams();
                            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            txtComment.setLayoutParams(params);
                            clicked = true;
                        } else if (clicked) {
                            ViewGroup.LayoutParams params = txtComment.getLayoutParams();
                            params.height = 75;
                            txtComment.setLayoutParams(params);
                            clicked = false;
                        }
                    }
                });
            }

        }
    }




}