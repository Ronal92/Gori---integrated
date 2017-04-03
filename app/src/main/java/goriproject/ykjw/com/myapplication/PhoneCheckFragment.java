package goriproject.ykjw.com.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneCheckFragment extends Fragment {

    ApplyActivity activity;

    ImageView phone_profile;


    public PhoneCheckFragment() {
        // Required empty public constructor
    }

    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_check, container, false);
        phone_profile = (ImageView)view.findViewById(R.id.phone_profile);
        Glide.with(getContext()).load(R.drawable.profile_dummy).into(phone_profile);
        return view;
    }

}
