package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import goriproject.ykjw.com.myapplication.domain.TalentDetail;


public class Apply_2Fragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn_apply_starter, btn_apply_center, btn_apply_sang;
    int starter = 0;
    int center = 0;
    int sang = 0;
    EditText et_msg, explength;
    TalentDetail td;


    ApplyActivity activity;
    Button btn_next3;
    ImageView img_apply2_profile;
    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }



    public Apply_2Fragment() {
        // Required empty public constructor
    }

    public static Apply_2Fragment newInstance(String param1, String param2) {
        Apply_2Fragment fragment = new Apply_2Fragment();
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
        View view =inflater.inflate(R.layout.fragment_apply_2, container, false);
        btn_apply_starter = (Button)view.findViewById(R.id.btn_apply_starter);
        btn_apply_center = (Button)view.findViewById(R.id.btn_apply_center);
        btn_apply_sang = (Button)view.findViewById(R.id.btn_apply_sang);
        btn_apply_starter.setOnClickListener(this);
        btn_apply_center.setOnClickListener(this);
        btn_apply_sang.setOnClickListener(this);
        td = activity.td;
        btn_next3 = (Button)view.findViewById(R.id.btn_next3);
        btn_next3.setOnClickListener(v -> {
            if(et_msg.getText().toString().length() == 0) {
                Toast.makeText(getContext(), "튜터에게 남길 말을 입력해주세요", Toast.LENGTH_SHORT).show();
            } else {
                activity.experience_length = Integer.parseInt(explength.getText().toString().trim());
                activity.tutor_msg = et_msg.getText().toString();
                activity.goAp3();
            }
        });

        explength = (EditText)view.findViewById(R.id.et_almana);
        et_msg = (EditText)view.findViewById(R.id.et_apply_sendmessage);
        img_apply2_profile = (ImageView)view.findViewById(R.id.img_apply2_profile);
        Glide.with(activity).load(td.getTutor().getProfile_image()).into(img_apply2_profile);
        btn_apply_starter.setBackgroundResource(R.drawable.custom_button7);
        btn_apply_starter.setTextColor(Color.WHITE);
        activity.student_level = 1;

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apply_starter :
                if(starter == 0) {
                    starter = 1;
                    center = 0;
                    sang = 0;
                    btn_apply_starter.setBackgroundResource(R.drawable.custom_button7);
                    btn_apply_starter.setTextColor(Color.WHITE);
                    btn_apply_center.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_center.setTextColor(getResources().getColor(R.color.customgrey));
                    btn_apply_sang.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_sang.setTextColor(getResources().getColor(R.color.customgrey));
                    activity.student_level = 1;
                }
                break;
            case R.id.btn_apply_center :
                if(center == 0) {
                    starter = 0;
                    center = 1;
                    sang = 0;
                    btn_apply_starter.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_starter.setTextColor(getResources().getColor(R.color.customgrey));
                    btn_apply_center.setBackgroundResource(R.drawable.custom_button7);
                    btn_apply_center.setTextColor(Color.WHITE);
                    btn_apply_sang.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_sang.setTextColor(getResources().getColor(R.color.customgrey));
                    activity.student_level = 2;
                }
                break;
            case R.id.btn_apply_sang :
                if(sang == 0) {
                    starter = 0;
                    center = 0;
                    sang = 1;
                    btn_apply_starter.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_starter.setTextColor(getResources().getColor(R.color.customgrey));
                    btn_apply_center.setBackgroundResource(R.drawable.custom_button8);
                    btn_apply_center.setTextColor(getResources().getColor(R.color.customgrey));
                    btn_apply_sang.setBackgroundResource(R.drawable.custom_button7);
                    btn_apply_sang.setTextColor(Color.WHITE);
                    activity.student_level = 3;
                }
                break;
        }
    }
}
