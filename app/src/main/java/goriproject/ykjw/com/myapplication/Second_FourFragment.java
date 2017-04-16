package goriproject.ykjw.com.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain_test.TalentAll;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_FourFragment extends Fragment {
    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL = "fourFragmentTL";
    private static final String KEY_FOR_TALENTDETAIL_INT = "fourFragmentTL_INT";

    View view;
    TalentAll td = null;

    public Second_FourFragment() {
        // Required empty public constructor
    }

    public static Second_FourFragment newInstance(TalentAll td, int id){
        Second_FourFragment secondFourFragment = new Second_FourFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL, td);
        args.putInt(KEY_FOR_TALENTDETAIL_INT, id);
        secondFourFragment.setArguments(args);
        return secondFourFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            this.td = (TalentAll)getArguments().getSerializable(KEY_FOR_TALENTDETAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_second_four, container, false);
        Context context = view.getContext();

        // 리사이클러뷰
        RecyclerView rv_secondfour = (RecyclerView)view.findViewById(R.id.rv_secondfour);
        PagerAdapter adapter = new PagerAdapter(getContext());
        rv_secondfour.setAdapter(adapter);
        rv_secondfour.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder>{

        private Context ctx = null;

        public PagerAdapter(Context ctx){
            this.ctx = ctx;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_four_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Glide.with(ctx).load(td.getQna()[position].getUser_image()).placeholder(R.mipmap.ic_launcher).into(holder.imgView);
            holder.txtName_tutee_fragment_qna.setText(td.getQna()[position].getUser());
            holder.txtComment_tutee_fragment_qna.setText(td.getQna()[position].getContent());

            // Date에서 시간을 제외한 일자만 표시한다.
            String date = td.getQna()[position].getCreated_date();
            date = date.substring(0,date.indexOf("T"));
            holder.txtComment_tutee_fragment_qna.setText(date);

        }

        @Override
        public int getItemCount() {
            return td.getQna().length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            CircleImageView imgView;
            TextView txtName_tutee_fragment_qna;
            TextView txtDate_tutee_fragment_qna;
            TextView txtComment_tutee_fragment_qna;


            public ViewHolder(View itemView) {
                super(itemView);

                imgView = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_qna);
                txtName_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_qna);
                txtDate_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txtDate_tutee_fragment_qna);
                txtComment_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txtComment_tutee_fragment_qna);


            }
        }
    }

}
