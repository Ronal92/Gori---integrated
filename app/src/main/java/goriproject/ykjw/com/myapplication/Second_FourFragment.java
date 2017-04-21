package goriproject.ykjw.com.myapplication;


import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Qna;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain_review_retrieve.ReviewsSecThreeFrag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.is_signin;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_FourFragment extends Fragment {

    private static final String KEY_FOR_TALENTDETAIL_FOUR = "threeFragmentTL";

    Context context = null;
    View view;
    TalentDetail td = null;

    RecyclerView recyclerReview = null;
    PagerAdapter adapter = null;

    public Second_FourFragment() {
        // Required empty public constructor
    }

    public static Second_FourFragment newInstance(TalentDetail td ){
        Second_FourFragment secondFourFragment = new Second_FourFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL_FOUR, td);
        secondFourFragment.setArguments(args);
        return secondFourFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.td = (TalentDetail)getArguments().getSerializable(KEY_FOR_TALENTDETAIL_FOUR);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null) {
            return view;
        }

        view = inflater.inflate(R.layout.fragment_second_four, container, false);
        context = view.getContext();

        // 리사이클러뷰 설정
        recyclerReview = (RecyclerView) view.findViewById(R.id.rv_secondfour);
        adapter = new PagerAdapter(context);
        List<Qna> qna = new ArrayList<>();
        Log.i("REALMADRID", "=====================td : " + td);
        for(int i = 0; i<td.getQna().size(); i++){
            qna.add(td.getQna().get(i));
        }
        adapter.setData(qna);
        recyclerReview.setAdapter(adapter);
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    // Adapter : 리뷰를 리스트로 보여줄 어뎁터
    public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

        private Context context = null;
        private List<Qna> qnaList= null;

        public PagerAdapter(Context context){
            this.context = context;

        }

        public void setData(List<Qna> qna){
            qnaList = qna;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_four_item, parent, false);
            ViewHolder svh = new ViewHolder(view);
            return svh;
        }

        @Override
        public int getItemCount() {
            return qnaList.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txtComment_tutee_fragment_qna.setText(qnaList.get(position).getContent());
            holder.txName_tutee_fragment_qna.setText(qnaList.get(position).getUser());
            String date = qnaList.get(position).getCreated_date();
            holder.txtDate_tutee_fragment_qna.setText(date.substring(0,date.indexOf("T")));
            Glide.with(context).load(qnaList.get(position).getUser_image()).placeholder(R.mipmap.ic_launcher).into(holder.img_tutee_fragment_qna); // 이미지 표시


        }


        public class ViewHolder extends RecyclerView.ViewHolder{
            CircleImageView img_tutee_fragment_qna;
            TextView txtDate_tutee_fragment_qna;
            TextView txName_tutee_fragment_qna;
            TextView txtComment_tutee_fragment_qna;
            Button btnResponse_tutee_fragment_qna;


            public ViewHolder(View itemView) {
                super(itemView);

                img_tutee_fragment_qna = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_qna);
                txtDate_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txtDate_tutee_fragment_qna);
                txName_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_qna);
                txtComment_tutee_fragment_qna = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_qna);
                btnResponse_tutee_fragment_qna = (Button) itemView.findViewById(R.id.btnResponse_tutee_fragment_qna);


            }

        }
    }

}
