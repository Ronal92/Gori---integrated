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

public class Apply_4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TalentDetail td;
    ApplyActivity activity;
    Button btn_next_5;
    TextView tv_apply4_class,tv_apply4_payment,tv_apply4_tutorname;

    public Apply_4Fragment() {
        // Required empty public constructor
    }

    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }

    public static Apply_4Fragment newInstance(String param1, String param2) {
        Apply_4Fragment fragment = new Apply_4Fragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_apply_4, container, false);
        td = activity.td;
        btn_next_5 = (Button)view.findViewById(R.id.btn_next5);
        btn_next_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.goAp5();
            }
        });

        tv_apply4_class = (TextView)view.findViewById(R.id.tv_apply4_class);
        tv_apply4_payment = (TextView)view.findViewById(R.id.tv_apply4_payment);
        tv_apply4_tutorname = (TextView)view.findViewById(R.id.tv_apply4_tutorname);

        tv_apply4_class.setText(td.getTitle());
        tv_apply4_payment.setText(td.getPrice_per_hour()+"원");
        tv_apply4_tutorname.setText(td.getTutor().getName());

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
