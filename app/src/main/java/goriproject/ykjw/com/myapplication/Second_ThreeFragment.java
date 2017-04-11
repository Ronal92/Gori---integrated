package goriproject.ykjw.com.myapplication;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_ThreeFragment extends Fragment {
    private Talent talent;
    Context ctx;

    public Second_ThreeFragment() {
        // Required empty public constructor
    }


    public void setTalent(Talent talenta) {
        // Required empty public constructor
        talent = talenta;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = null;
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_second_three, container, false);
        Button btnReview_second_activity = (Button)view.findViewById(R.id.btnReview_second_activity);
        btnReview_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_second__review_);

                // Dialog 사이즈 조절 하기
                ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = 950;
                params.height = 1500;
                //params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                //params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

                dialog.show();
            }
        });



        Context context = view.getContext();
        RecyclerView recyclerReview= (RecyclerView) view.findViewById(R.id.recycView_fragmentsecond_three);
        // 여기서 탭별로 데이터를 따로 보내야한다.
        recyclerReview.setAdapter(new PageAdapter(getContext()));
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

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
