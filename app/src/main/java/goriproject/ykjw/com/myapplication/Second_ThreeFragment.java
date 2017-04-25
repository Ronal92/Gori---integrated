package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;


import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.User_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Reviews;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain.User;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.ReviewResponse;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.ReviewDetail;

import goriproject.ykjw.com.myapplication.domain_review_retrieve.Results;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;
import static goriproject.ykjw.com.myapplication.Statics.user_name;


/**
 *  Talent detail all(API) : reviews 구조 <-> Review(API) : results 구조
 */
public class Second_ThreeFragment extends Fragment {
    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL = "threeFragmentTL";

    Context context = null;
    View view = null;
    PagerAdapter adapter = null;

    Dialog dialog = null; // 다이얼로그

    TalentDetail td = null;
    List<Reviews>  reviews = null;

    UserDetail userDetail = null;

    RecyclerView recyclerReview = null;

    ProgressDialog asyncDialog = null;

    TextView txtReview_count, txtAverageRate;
    RatingBar ratingBar_average, ratingBar_AverageCurriculum, ratingBar_AverageReadiness, ratingBar_AverageTimeliness, ratingBar_AverageDelivery, ratingBar_AverageFriendliness;

    public Second_ThreeFragment() {
        // Required empty public constructor
    }

    public static Second_ThreeFragment newInstance(TalentDetail td){
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
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(view != null){
            return view;
        }

        view = inflater.inflate(R.layout.fragment_second_three, container, false);
        context = view.getContext();

        // 위젯 초기화
        txtReview_count = (TextView)view.findViewById(R.id.txtReview_count);
        txtAverageRate = (TextView)view.findViewById(R.id.txtAverageRate);
        txtReview_count.setText(td.getReview_count());
        txtAverageRate.setText(td.getAverage_rates().getTotal());
        ratingBar_average = (RatingBar)view.findViewById(R.id.rb_Avrage);
        ratingBar_AverageCurriculum = (RatingBar)view.findViewById(R.id.rb_average_curriculum);
        ratingBar_AverageReadiness = (RatingBar)view.findViewById(R.id.rb_average_readiness);
        ratingBar_AverageTimeliness = (RatingBar)view.findViewById(R.id.rb_average_timeliness);
        ratingBar_AverageDelivery = (RatingBar)view.findViewById(R.id.rb_average_delivery);
        ratingBar_AverageFriendliness = (RatingBar)view.findViewById(R.id.rb_average_friendliness);

        // 위젯 세팅
        long ratinglong_average = Math.round(Double.parseDouble(td.getAverage_rates().getTotal()));
        long ratinglong_curriculum = Math.round(Double.parseDouble(td.getAverage_rates().getCurriculum()));
        long ratinglong_readiness = Math.round(Double.parseDouble(td.getAverage_rates().getReadiness()));
        long ratinglong_timeliness = Math.round(Double.parseDouble(td.getAverage_rates().getTimeliness()));
        long ratinglong_delivery = Math.round(Double.parseDouble(td.getAverage_rates().getDelivery()));
        long ratinglong_friendliness = Math.round(Double.parseDouble(td.getAverage_rates().getFriendliness()));
        ratingBar_average.setRating((int)ratinglong_average);
        ratingBar_AverageCurriculum.setRating((int)ratinglong_curriculum);
        ratingBar_AverageReadiness.setRating((int)ratinglong_readiness);
        ratingBar_AverageTimeliness.setRating((int)ratinglong_timeliness);
        ratingBar_AverageDelivery.setRating((int)ratinglong_delivery);
        ratingBar_AverageFriendliness.setRating((int)ratinglong_friendliness);



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
        adapter = new PagerAdapter(getContext());
        //results_list = new ArrayList<>();
        reviews = new ArrayList<>();
        for (int i = 0; i < td.getReviews().size(); i++) {
            reviews.add(td.getReviews().get(i));      // Results() --> List<Results>로 : 동적할당
        }
        adapter.setData(reviews);
        recyclerReview.setAdapter(adapter);
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    public void createRetrofitUserGet(final Reviews passedReviews){
        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User_Detail_Interface tdService = retrofit.create(User_Detail_Interface.class);

        Call<UserDetail> tds = tdService.getUserRetrieve("Token " + key);

        tds.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                userDetail = response.body();   // 현재 사용하는 유저 정보를 먼저 불러온다.


                // 3. POST 이후 데이터를 따로 데이터 저장소에 보낸다.
                User user  = new User();
                user.setName(userDetail.getName());
                user.setProfile_image(userDetail.getProfile_image());
                passedReviews.setUser(user);

                reviews.add(passedReviews);
                adapter.setData(reviews);                     // 2.1 데이터 추가하고
                adapter.notifyDataSetChanged();                // 2.2 어뎁터 갱신

                createRetrofitGET_TalentDetailAgain();

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

                // 다이얼로그 처리
                if(dialog != null && dialog.isShowing()){
                    Log.i(TAG, "============================================dialog 1");
                    dialog.dismiss();
                }
            }
        });
    }


    /*
         여기서 사용 안함
     */
    public void createRetrofitGET_TalentDetailAgain() {


        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Talent_Detail_Interface tdService = retrofit.create(Talent_Detail_Interface.class);

        final Call<TalentDetail> tds = tdService.getTalentDetail(td.getPk());

        tds.enqueue(new Callback<TalentDetail>() {
            @Override
            public void onResponse(Call<TalentDetail> call, Response<TalentDetail> response) {
                TalentDetail td_again = response.body();
                // 뷰 다시그려주기
                txtReview_count.setText(td_again.getReview_count());
                txtAverageRate.setText(td_again.getAverage_rates().getTotal());
                long ratinglong_average = Math.round(Double.parseDouble(td_again.getAverage_rates().getTotal()));
                long ratinglong_curriculum = Math.round(Double.parseDouble(td_again.getAverage_rates().getCurriculum()));
                long ratinglong_readiness = Math.round(Double.parseDouble(td_again.getAverage_rates().getReadiness()));
                long ratinglong_timeliness = Math.round(Double.parseDouble(td_again.getAverage_rates().getTimeliness()));
                long ratinglong_delivery = Math.round(Double.parseDouble(td_again.getAverage_rates().getDelivery()));
                long ratinglong_friendliness = Math.round(Double.parseDouble(td_again.getAverage_rates().getFriendliness()));
                ratingBar_average.setRating((int)ratinglong_average);
                ratingBar_AverageCurriculum.setRating((int)ratinglong_curriculum);
                ratingBar_AverageReadiness.setRating((int)ratinglong_readiness);
                ratingBar_AverageTimeliness.setRating((int)ratinglong_timeliness);
                ratingBar_AverageDelivery.setRating((int)ratinglong_delivery);
                ratingBar_AverageFriendliness.setRating((int)ratinglong_friendliness);
                view.postInvalidate();

                // 다이얼로그 닫기
                if(asyncDialog != null || asyncDialog.isShowing()) {
                    asyncDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<TalentDetail> call, Throwable t) {

            }
        });


    }

    public void createRetrofitPOST(final String editReview,final String rtCurriculum,final String rtReadiness,final String rtTimeliness,final String rtDelivery,final String rtFriendness ){
        // 2. POST 통신
        // 2.1 통신 로그 확인
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // 프로그레스 다이얼로그
        asyncDialog = new ProgressDialog(getContext());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        asyncDialog.show();

        // 2.2 레트로핏을 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Review_Detail_Interface service = retrofit.create(Review_Detail_Interface.class);

        // 2.3 데이터 받아오기
        Call<ReviewResponse> reviewData = service.setReviewRetrieve("Token " + key ,
                Integer.valueOf(td.getPk()),
                Integer.valueOf(rtCurriculum) ,
                Integer.valueOf(rtReadiness) ,
                Integer.valueOf(rtTimeliness) ,
                Integer.valueOf(rtDelivery) ,
                Integer.valueOf(rtFriendness) ,
                editReview
        );


        reviewData.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                //  GET : User 정보 받아오기
                createRetrofitUserGet( createLocalDateStore(editReview, rtCurriculum, rtReadiness, rtTimeliness, rtDelivery, rtFriendness));
            }
            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
            }

        });



    }

    public Reviews createLocalDateStore(String editReview, String rtCurriculum, String rtReadiness, String rtTimeliness, String rtDelivery, String rtFriendness ){



        Reviews reviewsData_after_post = new Reviews();
        reviewsData_after_post.setCurriculum(rtCurriculum);
        reviewsData_after_post.setReadiness(rtReadiness);
        reviewsData_after_post.setTimeliness(rtTimeliness);
        reviewsData_after_post.setDelivery(rtDelivery);
        reviewsData_after_post.setFriendliness(rtFriendness);
        reviewsData_after_post.setComment(editReview);
        reviewsData_after_post.setCreated_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) + "T");

        return reviewsData_after_post;
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
//

        // 2.4 데이터 받아오기        SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
//        String token = pref.getString("token", null);
        Call<Void> reviewDelete = service.deleteReview("Token " + key, review_pk);
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
        //List<goriproject.ykjw.com.myapplication.domain_review_retrieve.Results> results = new ArrayList<>(); // 데이터 저장소
        List<Reviews> reviewsList = null;

        public PagerAdapter(Context context){
            this.context = context;

        }

        public void setData(List<Reviews> reviews){
            this.reviewsList = reviews;
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
            return reviews.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Glide.with(context).load(reviews.get(position).getUser().getProfile_image()).placeholder(R.mipmap.ic_launcher).into(holder.img); // 이미지 표시
            holder.txtName.setText(reviews.get(position).getUser().getName());             // 이름 표시
            holder.txtComment.setText(reviews.get(position).getComment());           // Comment  표시
            String date = reviews.get(position).getCreated_date();         // 날짜 표시 (시간을 제외한 일자만 표시한다.)
            holder.txtDate.setText(date.substring(0,date.indexOf("T")));

            // 별점 표시 (평균값 구해서 출력)
            Double ave_total = (double)(Double.valueOf(reviews.get(position).getCurriculum())
                    + Double.valueOf(reviews.get(position).getReadiness())
                    + Double.valueOf(reviews.get(position).getTimeliness())
                    + Double.valueOf(reviews.get(position).getDelivery())
                    + Double.valueOf(reviews.get(position).getFriendliness()))/5;
            holder.ratingBar.setRating(Math.round(Double.parseDouble(ave_total + "")));


            // 버튼 : 리뷰삭제
            if(is_signin){
                if ( user_name.equals(holder.txtName.getText())) {

                    final int review_position = position;
                    final String review_pk = reviews.get(position).getPk();   // 현재 아이템에 해당하는 PK
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

                            Call<ReviewDetail> tds = tdService.getReviewRetrieve(td.getPk());
                            tds.enqueue(new Callback<ReviewDetail>() {
                                @Override
                                public void onResponse(Call<ReviewDetail> call, Response<ReviewDetail> response) {
                                    ReviewDetail reviewsSecThreeFrag = response.body();

                                    if(TextUtils.isEmpty(reviews.get(review_position).getPk())){
                                        // 지울 정보의 pk를 찾는다.
                                        createRetrofitDelete(findPK(review_position, reviews, reviewsSecThreeFrag));

                                    } else {
                                        Log.i(TAG, "======== value of pk : " + reviews.get(review_position).getPk());
                                        createRetrofitDelete(reviews.get(review_position).getPk());
                                    }
                                    asyncDialog.dismiss();
                                    // 리뷰 삭제하고 데이터 업데이트
                                    reviews.remove(review_position);
                                    adapter.setData(reviews);
                                    adapter.notifyDataSetChanged();

                                }
                                @Override
                                public void onFailure(Call<ReviewDetail> call, Throwable t) {

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

        public String findPK(int review_position, List<Reviews> reviewsList, ReviewDetail reviewsSecThreeFrag){
            String foundPK = "";
            for(int indexOfPk = 0; indexOfPk< reviewsSecThreeFrag.getResults().length; indexOfPk++){
                if(userDetail.getName().equals(reviewsSecThreeFrag.getResults()[indexOfPk].getUser().getName() )
                        && reviewsList.get(review_position).getCreated_date().substring(0,reviewsList.get(review_position).getCreated_date().indexOf("T")).equals(reviewsSecThreeFrag.getResults()[indexOfPk].getCreated_date().substring(0,reviewsSecThreeFrag.getResults()[indexOfPk].getCreated_date().indexOf("T")))
                        && reviewsSecThreeFrag.getResults()[indexOfPk].getComment().contains(reviewsList.get(review_position).getComment()))
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

            public ViewHolder(View itemView) {
                super(itemView);

                csLayout = (ConstraintLayout)itemView.findViewById(R.id.csLayout_review);
                txtName = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_review);
                txtDate = (TextView) itemView.findViewById(R.id.txtDate_tutee_fragment_review);
                txtComment = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_review);
                img = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_review);
                ratingBar = (RatingBar)itemView.findViewById(R.id.rb_tutee_fragment_review);
                btnDelete_fragment_review = (Button)itemView.findViewById(R.id.btnDelete_fragment_review);

                /*  코멘트 화면의 사이즈를 조절(불필요)
                csLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( !clicked ) {
                            ViewGroup.LayoutParams params = txtComment.getLayoutParams();
                            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                            txtComment.setLayoutParams(params);
                            clicked = true;
                        } else if (clicked && txtComment.getText().length()>10) {
                            ViewGroup.LayoutParams params = txtComment.getLayoutParams();
                            params.height = 75;
                            txtComment.setLayoutParams(params);
                            clicked = false;
                        }
                    }
                });*/
            }


        }
    }




}