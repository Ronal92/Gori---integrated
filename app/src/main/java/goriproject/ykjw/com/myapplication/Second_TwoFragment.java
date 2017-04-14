package goriproject.ykjw.com.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_TwoFragment extends Fragment {

    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL = "twoFragmentTL";

    Context context = null;
    private TalentDetail talentDetail = null;

    private View view;

    public Second_TwoFragment() {
        // Required empty public constructor
    }

    //
    public static Second_TwoFragment newInstance(TalentDetail talentDetail){
        Second_TwoFragment secondTwoFragment = new Second_TwoFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL, talentDetail);
        secondTwoFragment.setArguments(args);
        return secondTwoFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            this.talentDetail = (TalentDetail)getArguments().getSerializable(KEY_FOR_TALENTDETAIL);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_second_two, container, false);



        showLocationConfirm();

        return view;
    }

    // 위치 버튼
    public void showLocationConfirm() {
        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_loc = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_loc);
        dynamic_radioarea_loc.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_loc = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        params_loc.setMargins(10, 20, 0, 0);

        // 라디오 버튼 동적 생성.
        List<String> location = new ArrayList<>();
        for(int i = 0; i < talentDetail.getLocations().size(); i++){
            location.add(talentDetail.getLocations().get(i).getRegion());
        }
        //String[] location_title = {"이화여대", "강남", "신촌"};
        makeRadioButtonLocation(params_loc, dynamic_radioarea_loc, location);
    }


    public void makeRadioButtonLocation(RadioGroup.LayoutParams params, final RadioGroup dynamic_radioarea, final List<String> location) {
        // 동적으로 라디오 버튼 생성
        for (int j = 0; j < location.size(); j++) {
            final RadioButton radioButton_loc = new RadioButton(getContext());
            radioButton_loc.setId(j);
            radioButton_loc.setText(location.get(j));
            radioButton_loc.setLayoutParams(params);
            radioButton_loc.setBackgroundResource(R.drawable.custom_button_selector);
            radioButton_loc.setButtonDrawable(new StateListDrawable());
            radioButton_loc.setPaddingRelative(60, 0, 60, 0);
            radioButton_loc.setHeight(130);
            radioButton_loc.setTag(j);
            radioButton_loc.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.custom_button_text_selector));

            final int loc_index = j;
            radioButton_loc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPossibleDay(radioButton_loc.getText().toString(), loc_index);
                }
            });

            dynamic_radioarea.addView(radioButton_loc);
        }
    }


    // 가능 요일
    public void showPossibleDay(String radioButtonLoc_txt, int loc_index) {

        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_day = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_day);
        dynamic_radioarea_day.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_day = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
        params_day.setMargins(10, 20, 0, 0);

        dynamic_radioarea_day.removeAllViews();

        makeRadioButtonDay(params_day, dynamic_radioarea_day, loc_index);

//        if (radioButtonLoc_txt.equals(location.get(0))) {
//            String[] day_title = {"월", "화", "수"};
//            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
//        } else if (radioButtonLoc_txt.equals("강남")) {
//            String[] day_title = {"월", "화", "수", "목", "금", "토", "일"};
//            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
//        } else if (radioButtonLoc_txt.equals("신촌")) {
//            String[] day_title = {"토", "일"};
//            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
//        }

    }


    public void makeRadioButtonDay(RadioGroup.LayoutParams params, RadioGroup dynamic_radioarea, final int loc_index) {
        // 동적으로 라디오 버튼 생성
        //for (int j = 0; j < talentDetail.getLocations(); j++) {
        final RadioButton radioButton = new RadioButton(getContext());
        //    radioButton.setId(j);
        radioButton.setText(talentDetail.getLocations().get(loc_index).getDay());
        radioButton.setLayoutParams(params);
        radioButton.setBackgroundResource(R.drawable.custom_button_selector);
        radioButton.setButtonDrawable(new StateListDrawable());
        radioButton.setPaddingRelative(60, 0, 60, 0);
        radioButton.setHeight(130);
        //    radioButton.setTag(j);
        radioButton.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.custom_button_text_selector));


        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPossibleTime(radioButton.getText().toString(), loc_index);
            }
        });

        dynamic_radioarea.addView(radioButton);
        //}
    }

    public void showPossibleTime(String radioButtonLoc_txt, int loc_index) {

        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_time = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_time);
        dynamic_radioarea_time.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_day = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
        params_day.setMargins(10, 20, 0, 0);

        dynamic_radioarea_time.removeAllViews();

        // 각 시간별로 버튼을 만들어줘야 한다.
        makeRadioButtonTime(params_day, dynamic_radioarea_time, talentDetail.getLocations().get(loc_index).getTime());



//        if (radioButtonLoc_txt.equals("월")) {
//            String[] time_title = {"06~08시", "12~14시"};
//            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
//        } else if (radioButtonLoc_txt.equals("화")) {
//            String[] time_title = {"10~12시", "15~17시", "19~21시"};
//            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
//        } else if (radioButtonLoc_txt.equals("수")) {
//            String[] time_title = {"15~17시"};
//            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
//        }

    }

    public void makeRadioButtonTime(RadioGroup.LayoutParams params, RadioGroup dynamic_radioarea, List<String> content) {
        // 동적으로 라디오 버튼 생성
        for (int j = 0; j < content.size(); j++) {
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(j);
            radioButton.setText(content.get(j));
            radioButton.setLayoutParams(params);
            radioButton.setBackgroundResource(R.drawable.custom_button_selector);
            radioButton.setButtonDrawable(new StateListDrawable());
            radioButton.setPaddingRelative(60, 0, 60, 0);
            radioButton.setHeight(130);
            radioButton.setTag(j);
            radioButton.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.custom_button_text_selector));

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO ??
                }
            });

            dynamic_radioarea.addView(radioButton);
        }

    }
}



