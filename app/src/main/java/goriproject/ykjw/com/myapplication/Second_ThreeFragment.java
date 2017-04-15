package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.SignUpInterface;
import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Result2;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain.review.ReviewRetrieve;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;


/**
 * A simple {@link Fragment} subclass.
 */

public class Second_ThreeFragment extends Fragment {
    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL = "threeFragmentTL";
    private static final String KEY_FOR_TALENTDETAIL_INT = "threeFragmentTL_INT";

    Context context = null;
    private TalentDetail talentDetail = null; // SecondActivity에서 넘어온 TalentDetail
    private int id = -1;

    Dialog dialog = null; // 다이얼로그
    ReviewRetrieve reviewRetrieve = null;

    List<String> rates = new ArrayList<>();


    public Second_ThreeFragment() {
        // Required empty public constructor
    }

    public static Second_ThreeFragment newInstance(ReviewRetrieve reviewRetrieve, int id){
        Second_ThreeFragment secondThreeFragment = new Second_ThreeFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL, reviewRetrieve);
        args.putInt(KEY_FOR_TALENTDETAIL_INT, id);
        secondThreeFragment.setArguments(args);
        return secondThreeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            this.reviewRetrieve = (ReviewRetrieve)getArguments().getSerializable(KEY_FOR_TALENTDETAIL);
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
        txtReview_count.setText(reviewRetrieve.getReview_count());
        txtAverageRate.setText(reviewRetrieve.getAverage_rates().getTotal());

        // RatingBar (Total)
        RatingBar ratingBar_average = (RatingBar)view.findViewById(R.id.rb_Avrage);
        long ratinglong = Math.round(Double.parseDouble(reviewRetrieve.getAverage_rates().getTotal()));
        ratingBar_average.setRating((int)ratinglong);



        // 버튼 리뷰
        Button btnReview_second_activity = (Button)view.findViewById(R.id.btnReview_second_activity);
        btnReview_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        // 리사이클러뷰
        RecyclerView recyclerReview= (RecyclerView) view.findViewById(R.id.recycView_fragmentsecond_three);
        recyclerReview.setAdapter(new PageAdapter(getContext()));
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
                // Rating 결과 값 저장하기
                rates.add(raiting_curriculum_review.getNumStars() + "");
                rates.add(raiting_readiness_review.getNumStars() + "");
                rates.add(raiting_timeliness_review.getNumStars() + "");
                rates.add(raiting_delivery_review.getNumStars() + "");
                rates.add(raiting_friendliness_review.getNumStars() + "");


                // 레트로핏을 생성
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://mozzi.co.kr/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Review_Detail_Interface service = retrofit.create(Review_Detail_Interface.class);

                // 토큰 받아오기
                SharedPreferences pref = getContext().getSharedPreferences("pref", MODE_PRIVATE);
                String token = pref.getString("token", null);
                Log.e("HERE", token);

                Call<String> reviewData = service.setReviewRetrieve("Token " + token, reviewRetrieve.getPk(), rates.get(0), rates.get(1), rates.get(2), rates.get(3), rates.get(4), null );

                reviewData.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 201) {
                                Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
                        } else  {
                            Toast.makeText(getContext(), response.code() + "", Toast.LENGTH_SHORT).show();
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
    public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

        private static final String TAG = "RAPSTAR";
        private Context context = null;
        private Double curriculum = null;
        private Double readiness = null;
        private Double timeliness = null;
        private Double delivery = null;
        private Double friendliness = null;

        public PageAdapter(Context context){
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_three_item, parent, false);
            ViewHolder svh = new ViewHolder(view);
            return svh;
        }

        @Override
        public int getItemCount() {
            return reviewRetrieve.getReviews().length;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(context).load(reviewRetrieve.getReviews()[position].getUser().getProfile_image()).placeholder(R.mipmap.ic_launcher).into(holder.img);
            holder.txtName.setText(reviewRetrieve.getReviews()[position].getUser().getName());

            // Comment 텍스트뷰 사이즈 조절하기기
           holder.txtComment.setText(reviewRetrieve.getReviews()[position].getComment());
            Log.i("RAPSTAR","=============================== Comment" + reviewRetrieve.getReviews()[position].getComment());

            // Date에서 시간을 제외한 일자만 표시한다.
            String date = reviewRetrieve.getReviews()[position].getCreated_date();
            date = date.substring(0,date.indexOf("T"));
            holder.txtDate.setText(date);

            // Rating 평균값 구해서 출력
            curriculum = Double.valueOf(Integer.parseInt(reviewRetrieve.getReviews()[position].getCurriculum()));
            readiness = Double.valueOf(Integer.parseInt(reviewRetrieve.getReviews()[position].getReadiness()));
            timeliness = Double.valueOf(Integer.parseInt(reviewRetrieve.getReviews()[position].getTimeliness()));
            delivery = Double.valueOf(Integer.parseInt(reviewRetrieve.getReviews()[position].getDelivery()));
            friendliness = Double.valueOf(Integer.parseInt(reviewRetrieve.getReviews()[position].getFriendliness()));
            Double ave_total = (double)(curriculum + readiness + timeliness + delivery + friendliness)/5;
            long ratinglong = Math.round(Double.parseDouble(ave_total + ""));
            holder.ratingBar.setRating(ratinglong);
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ConstraintLayout csLayout;
            TextView txtName;
            TextView txtDate;
            TextView txtComment;
            ImageView img;
            RatingBar ratingBar;
            boolean clicked = false;

            public ViewHolder(View itemView) {
                super(itemView);

                csLayout = (ConstraintLayout)itemView.findViewById(R.id.csLayout_review);
                txtName = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_review);
                txtDate = (TextView) itemView.findViewById(R.id.txtDate_tutee_fragment_review);
                txtComment = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_review);
                img = (ImageView)itemView.findViewById(R.id.img_tutee_fragment_review);
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
