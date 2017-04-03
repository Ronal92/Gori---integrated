package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class Apply_3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ApplyActivity activity;
    Button btn_next_4;
    ImageView img;

    public Apply_3Fragment() {

    }

    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }


    public static Apply_3Fragment newInstance(String param1, String param2) {
        Apply_3Fragment fragment = new Apply_3Fragment();
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
        View view =inflater.inflate(R.layout.fragment_apply_3, container, false);
        btn_next_4 = (Button)view.findViewById(R.id.btn_next4);
        btn_next_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.goAp4();
            }
        });
        img = (ImageView)view.findViewById(R.id.img_apply_profile4);
        Glide.with(getContext()).load(R.drawable.profile_dummy).into(img);
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
