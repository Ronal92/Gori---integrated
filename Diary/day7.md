[ Date : 2017. 04. 02(일) ]

#Done

 - 페이저에 라디오 버튼 동적 할당.

## 위젯 동적 할당하는 법

<CustomFragment.java> : onCreateView -> showLocationConfirm() -> makeRadioButton() 

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_two, container, false);

        showLocationConfirm();

        return view;
    }

    public void showLocationConfirm(){
        // 라디오 그룹 생성 (라디오 버튼이 들어갈 공간)
        RadioGroup dynamic_radioarea_loc = (RadioGroup)view.findViewById(R.id.dynamic_radioarea_loc);
        dynamic_radioarea_loc.setOrientation(RadioGroup.HORIZONTAL);

        // 패럼 생성 : 라디오 버튼
        RadioGroup.LayoutParams params_loc = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        params_loc.setMargins(10,20,0,0);

        // 라디오 버튼 동적 생성.
        String[] location_title = {"이화여대", "강남", "신촌"};
        makeRadioButton(params_loc, dynamic_radioarea_loc, location_title);
    }

	public void makeRadioButton(RadioGroup.LayoutParams params, final RadioGroup dynamic_radioarea,  String[] content){
        // 동적으로 라디오 버튼 생성
        for( int j =  0; j<content.length; j++) {
			// 라디오 버튼 속성 설정하기
            final RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(j);
            radioButton.setText(content[j]);
            radioButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            radioButton.setLayoutParams(params);
            radioButton.setBackgroundResource(R.drawable.custom_button_selector);
            radioButton.setButtonDrawable(new StateListDrawable());
            radioButton.setPaddingRelative(60,0,60,0);
            radioButton.setHeight(130);
            radioButton.setTag(j);
            radioButton.setTextColor(Color.BLACK);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 버튼 동작 코딩
                }
            });

            dynamic_radioarea.addView(radioButton);
        }
    }

<customFragment.xml>

	<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RadioGroup
    android:id="@+id/dynamic_radioarea_loc"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />

	</RelativeLayout>
#Todo

 - MyPage를 아직 시작 못함.........