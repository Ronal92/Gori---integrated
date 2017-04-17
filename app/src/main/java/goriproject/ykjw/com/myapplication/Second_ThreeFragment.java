package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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


import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;


import goriproject.ykjw.com.myapplication.domain_talent_detail_all.Reviews;
import goriproject.ykjw.com.myapplication.domain_talent_detail_all.TalentAll;
import goriproject.ykjw.com.myapplication.domain_talent_detail_all.User;
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

    Context context = null;
    PagerAdapter adapter = null;
    private int id = -1;

    Dialog dialog = null; // 다이얼로그
    List<Reviews> reviews = null;
    TalentAll td = null;




    public Second_ThreeFragment() {
        // Required empty public constructor
    }

    public static Second_ThreeFragment newInstance(TalentAll td, int id){
        Second_ThreeFragment secondThreeFragment = new Second_ThreeFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL, td);
        args.putInt(KEY_FOR_TALENTDETAIL_INT, id);
        secondThreeFragment.setArguments(args);
        return secondThreeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            this.td = (TalentAll)getArguments().getSerializable(KEY_FOR_TALENTDETAIL);
            this.id = getArguments().getInt(KEY_FOR_TALENTDETAIL_INT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_second_three, container, false);
        Context context = view.getContext();

        TextView txtReview_count = (TextView)view.findViewById(R.id.txtReview_count);
        TextView txtAverageRate = (TextView)view.findViewById(R.id.txtAverageRate);
        txtReview_count.setText(td.getReview_count());
        txtAverageRate.setText(td.getAverage_rates().getTotal());

        // RatingBar (Total)
        RatingBar ratingBar_average = (RatingBar)view.findViewById(R.id.rb_Avrage);
        long ratinglong = Math.round(Double.parseDouble(td.getAverage_rates().getTotal()));
        ratingBar_average.setRating((int)ratinglong);

        // Reviews[] --> List<Reviews> : 데이터 동기화를 위해 사용한다.
        reviews = new ArrayList<>();
        for(int i = 0; i < Integer.valueOf(td.getReview_count()) ; i ++) {
            reviews.add(td.getReviews()[i]);
        }

//        // 버튼 리뷰
//        Button btnDelete_fragment_review = (Button)view.findViewById(R.id.btnDelete_fragment_review);
//        String check_userId = "a.gori";
//        if("a.gori".equals(check_userId)){
//            btnDelete_fragment_review.setVisibility(View.VISIBLE);
//            btnDelete_fragment_review.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.i(TAG, "======================================delete!!");
//                }
//            });
//        } else {
//            btnDelete_fragment_review.setVisibility(View.GONE);
//
//        }


        Button btnReview_second_activity = (Button)view.findViewById(R.id.btnReview_second_activity);
        btnReview_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(로그인)
                if(is_signin) {
                    showDialog();
                } else {
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            }
        });


        // 리사이클러뷰
        RecyclerView recyclerReview= (RecyclerView) view.findViewById(R.id.recycView_fragmentsecond_three);
        adapter = new PagerAdapter(getContext(), reviews);
        recyclerReview.setAdapter(adapter);
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    public void showDialog(){

        // Dialog
        dialog = new Dialog(getActivity());
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


        btnFinish.setOnClickListener(dialogListener);
        btnCancel.setOnClickListener(dialogListener);
        // 사용자가 리뷰 확인을 눌렀을 때,
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1. data를 로컬에서 새로 동기화시킨다.
                Reviews added_reviews = new Reviews();
                User temp_user = new User();
                temp_user.setName("바꿔쭤");
                temp_user.setProfile_image(null);
                added_reviews.setUser(temp_user);

                added_reviews.setCreated_date((new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()))) + "T"); // 현재 날짜 구하기
                added_reviews.setCurriculum(raiting_curriculum_review.getRating() + "");
                added_reviews.setReadiness(raiting_readiness_review.getRating() + "");
                added_reviews.setTimeliness(raiting_timeliness_review.getRating() + "");
                added_reviews.setDelivery(raiting_delivery_review.getRating() + "");
                added_reviews.setFriendliness(raiting_friendliness_review.getRating() + "");
                String textData = "";
                try {
                    //textData = URLEncoder.encode(edit_commen_review.getText().toString(),"utf-8");
                    textData = edit_commen_review.getText().toString();
                }catch (Exception e){
                    e.printStackTrace();
                }
                added_reviews.setComment(textData);

                    //1.2 added_reviews를 reviews에 넣고 어뎁터를 재세팅한다.
                reviews.add(added_reviews);
                adapter.notifyDataSetChanged();


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

                    // 2.3 토큰 받아오기
                SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
                String token = pref.getString("token", null);
                    // 2.4 데이터 받아오기

                String textData2 = "";
                try {
                    //textData2 = URLEncoder.encode(edit_commen_review.getText().toString(),"utf-8");
                    textData2 = edit_commen_review.getText().toString();
                }catch (Exception e){
                    e.printStackTrace();
                }


                Call<String> reviewData = service.setReviewRetrieve("Token " + token ,
                                                                        Integer.valueOf(td.getPk()),
                                                                        (int)raiting_curriculum_review.getRating() ,
                                                                        (int)raiting_readiness_review.getRating() ,
                                                                        (int)raiting_timeliness_review.getRating() ,
                                                                        (int)raiting_delivery_review.getRating() ,
                                                                        (int)raiting_friendliness_review.getRating() ,
                                                                        textData2
                        );

                reviewData.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 201) {
                                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                        } else  {
                            Toast.makeText(getContext(), response.code() + "", Toast.LENGTH_SHORT).show();
                            try {
                                Log.e(TAG, "========================response.body" + response.errorBody().string() );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        dialog.dismiss();
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        dialog.dismiss();
                    }

                });

            }

        });

        dialog.show();
    }

    // Dialog 화면 버튼 리스너
    View.OnClickListener dialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btnReviewFinish_second_review :
                    dialog.dismiss();
                    break;
                case R.id.btnReviewCancel_second_review :
                    dialog.dismiss();
                    break;
            }
        }
    };

    // 리뷰를 리스트로 보여줄 어뎁터
    public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

        private static final String TAG = "RAPSTAR";
        private Context context = null;
        private Double curriculum = null;
        private Double readiness = null;
        private Double timeliness = null;
        private Double delivery = null;
        private Double friendliness = null;
        List<Reviews> reviews = null;

        public PagerAdapter(Context context,  List<Reviews> reviews){
            this.context = context;
            this.reviews = reviews;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_three_item, parent, false);
            ViewHolder svh = new ViewHolder(view);
            return svh;
        }

        @Override
        public int getItemCount() {
            return reviews.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            // 이미지 표시
            Glide.with(context).load(reviews.get(position).getUser().getProfile_image()).placeholder(R.mipmap.ic_launcher).into(holder.img);
            // 이름 표시
            holder.txtName.setText(reviews.get(position).getUser().getName());
            // Comment  표시
           holder.txtComment.setText(reviews.get(position).getComment());

            // 날짜 표시 (시간을 제외한 일자만 표시한다.)
            String date = reviews.get(position).getCreated_date();
            date = date.substring(0,date.indexOf("T"));
            holder.txtDate.setText(date);

            // 별점 표시 (평균값 구해서 출력)
            curriculum = Double.valueOf(reviews.get(position).getCurriculum());
            readiness = Double.valueOf(reviews.get(position).getReadiness());
            timeliness = Double.valueOf(reviews.get(position).getTimeliness());
            delivery = Double.valueOf(reviews.get(position).getDelivery());
            friendliness = Double.valueOf(reviews.get(position).getFriendliness());

            Double ave_total = (double)(curriculum + readiness + timeliness + delivery + friendliness)/5;
            long ratinglong = Math.round(Double.parseDouble(ave_total + ""));
            holder.ratingBar.setRating(ratinglong);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout csLayout;
            TextView txtName;
            TextView txtDate;
            TextView txtComment;
            CircleImageView img;
            RatingBar ratingBar;
            boolean clicked = false;

            public ViewHolder(View itemView) {
                super(itemView);

                csLayout = (ConstraintLayout)itemView.findViewById(R.id.csLayout_review);
                txtName = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_review);
                txtDate = (TextView) itemView.findViewById(R.id.txtDate_tutee_fragment_review);
                txtComment = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_review);
                img = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_review);
                ratingBar = (RatingBar)itemView.findViewById(R.id.rb_tutee_fragment_review);

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
