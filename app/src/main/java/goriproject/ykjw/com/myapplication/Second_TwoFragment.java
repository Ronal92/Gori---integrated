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


/**
 * A simple {@link Fragment} subclass.
 */
public class Second_TwoFragment extends Fragment {

    Context context = null;
    private Talent talent;

    private View view;

    public Second_TwoFragment() {
        // Required empty public constructor
    }

    public void setTalent(Talent talenta) {
        // Required empty public constructor
        talent = talenta;
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

    public void showLocationConfirm() {
        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_loc = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_loc);
        dynamic_radioarea_loc.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_loc = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        params_loc.setMargins(10, 20, 0, 0);

        // 라디오 버튼 동적 생성.
        String[] location_title = {"이화여대", "강남", "신촌"};
        makeRadioButtonLocation(params_loc, dynamic_radioarea_loc, location_title);
    }

    public void makeRadioButtonLocation(RadioGroup.LayoutParams params, final RadioGroup dynamic_radioarea, String[] content) {
        // 동적으로 라디오 버튼 생성
        for (int j = 0; j < content.length; j++) {
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(j);
            radioButton.setText(content[j]);
            radioButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
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
                    showPossibleDay(radioButton.getText().toString());
                }
            });

            dynamic_radioarea.addView(radioButton);
        }
    }

    public void showPossibleDay(String radioButtonLoc_txt) {


        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_day = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_day);
        dynamic_radioarea_day.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_day = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
        params_day.setMargins(10, 20, 0, 0);

        dynamic_radioarea_day.removeAllViews();
        if (radioButtonLoc_txt.equals("이화여대")) {
            String[] day_title = {"월", "화", "수"};
            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
        } else if (radioButtonLoc_txt.equals("강남")) {
            String[] day_title = {"월", "화", "수", "목", "금", "토", "일"};
            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
        } else if (radioButtonLoc_txt.equals("신촌")) {
            String[] day_title = {"토", "일"};
            makeRadioButtonDay(params_day, dynamic_radioarea_day, day_title);
        }

    }


    public void makeRadioButtonDay(RadioGroup.LayoutParams params, RadioGroup dynamic_radioarea, String[] content) {
        // 동적으로 라디오 버튼 생성
        for (int j = 0; j < content.length; j++) {
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(j);
            radioButton.setText(content[j]);
            radioButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
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
                    showPossibleTime(radioButton.getText().toString());
                }
            });

            dynamic_radioarea.addView(radioButton);
        }
    }

    public void showPossibleTime(String radioButtonLoc_txt) {

        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_time = (RadioGroup) view.findViewById(R.id.dynamic_radiogroup_time);
        dynamic_radioarea_time.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_day = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
        params_day.setMargins(10, 20, 0, 0);

        dynamic_radioarea_time.removeAllViews();

        if (radioButtonLoc_txt.equals("월")) {
            String[] time_title = {"06~08시", "12~14시"};
            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
        } else if (radioButtonLoc_txt.equals("화")) {
            String[] time_title = {"10~12시", "15~17시", "19~21시"};
            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
        } else if (radioButtonLoc_txt.equals("수")) {
            String[] time_title = {"15~17시"};
            makeRadioButtonTime(params_day, dynamic_radioarea_time, time_title);
        }

    }

    public void makeRadioButtonTime(RadioGroup.LayoutParams params, RadioGroup dynamic_radioarea, String[] content) {
        // 동적으로 라디오 버튼 생성
        for (int j = 0; j < content.length; j++) {
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(j);
            radioButton.setText(content[j]);
            radioButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
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




