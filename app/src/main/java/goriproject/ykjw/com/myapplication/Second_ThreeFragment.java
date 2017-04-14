package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain.review.ReviewRetrieve;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        // RatingBar 설정하기
        RatingBar ratingBar_average = (RatingBar)view.findViewById(R.id.rb_Avrage);
        long ratinglong = Math.round(Double.parseDouble(reviewRetrieve.getAverage_rates().getTotal()));
        ratingBar_average.setRating((int)ratinglong);



        // 버튼 리뷰 생성
        Button btnReview_second_activity = (Button)view.findViewById(R.id.btnReview_second_activity);
        btnReview_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_second__review_);

                // Dialog 사이즈 조절 하기
                ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = 950;      //params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.height = 1500;    //params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

                dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

                ImageButton btnFinish = (ImageButton)dialog.findViewById(R.id.btnReviewFinish_second_review);
                Button btnCancel = (Button)dialog.findViewById(R.id.btnReviewCancel_second_review);
                Button btnUpload = (Button)dialog.findViewById(R.id.btnUpload_second_review);

                btnFinish.setOnClickListener(dialogListener);
                btnCancel.setOnClickListener(dialogListener);
                btnUpload.setOnClickListener(dialogListener);


                dialog.show();
            }
        });

        // 리사이클러뷰
        RecyclerView recyclerReview= (RecyclerView) view.findViewById(R.id.recycView_fragmentsecond_three);
        recyclerReview.setAdapter(new PageAdapter(getContext()));
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

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
                case R.id.btnUpload_second_review :
                    // TODO 내용 저장
                    break;
            }
        }
    };

    // 리뷰를 리스트로 보여줄 어뎁터
    public class PageAdapter extends RecyclerView.Adapter<PageAdapter.ViewHolder> {

        private static final String TAG = "RAPSTAR";
        private Context context = null;

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
            return 10;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            //ImageView img_tutee_fragment_one;
            public ViewHolder(View itemView) {
                super(itemView);
                //img_tutee_fragment_one = (ImageView)itemView.findViewById(R.id.img_tutee_fragment_one);
                //img_tutee_fragment_one.bringToFront();
                // TODO : get으로 정보 받아와서 뿌린다.
            }

        }
    }




}
