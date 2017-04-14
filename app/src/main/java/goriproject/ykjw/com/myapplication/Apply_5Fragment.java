package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import goriproject.ykjw.com.myapplication.domain.TalentDetail;


public class Apply_5Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    TalentDetail td;
    Button btn_applycomplete;
    ApplyActivity activity;
    TextView tv_apply5_tutorinfo, tv_apply5_tutorname, tv_apply5_tv1;

    public Apply_5Fragment() {

    }

    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }

    public static Apply_5Fragment newInstance(String param1, String param2) {
        Apply_5Fragment fragment = new Apply_5Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_apply_5, container, false);

        btn_applycomplete = (Button)view.findViewById(R.id.btn_matchcomplete);
        btn_applycomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.goPc();
            }
        });

        tv_apply5_tutorinfo = (TextView)view.findViewById(R.id.tv_apply5_tutorinfo);
        tv_apply5_tutorname = (TextView)view.findViewById(R.id.tv_apply5_tutorname);
        tv_apply5_tv1 = (TextView)view.findViewById(R.id.tv_apply5_tv1);

        tv_apply5_tutorname.setText(activity.talent.getTutor_name());
        tv_apply5_tutorinfo.setText(activity.talent.getTalent_name());
        tv_apply5_tv1.setText("위의 계좌로 \n"+ activity.talent.getTalent_price()+"을 입금해주세요 \n \n 입금 확인 후, 튜터분과 즉시 연결됩니다.");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }



}
