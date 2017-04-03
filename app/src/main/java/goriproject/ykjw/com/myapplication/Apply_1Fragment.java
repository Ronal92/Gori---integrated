package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class Apply_1Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button btn_next2;
    ImageView img_apply1_profile;

    LinearLayout btn1layout, btn2layout, btn3layout;

    TextView tv_apply1_plusinfo;
    ApplyActivity activity;

    public void setActivity(ApplyActivity activity){
        this.activity = activity;
    }


    public Apply_1Fragment() {

    }



    public static Apply_1Fragment newInstance(String param1, String param2) {
        Apply_1Fragment fragment = new Apply_1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_apply_1, container, false);

        btn_next2 = (Button)view.findViewById(R.id.btn_next2);
        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.goAp2();
            }
        });
        tv_apply1_plusinfo = (TextView)view.findViewById(R.id.tv_apply1_plusinfo);
        tv_apply1_plusinfo.setText("장소 : "+activity.talent.getTalent_place() + "\n" + "추가비용 : "+activity.talent.getTalent_plusprice());
        img_apply1_profile = (ImageView)view.findViewById(R.id.img_apply1_profile);
        Glide.with(activity).load(R.drawable.profile_dummy).into(img_apply1_profile);

        btn1layout = (LinearLayout)view.findViewById(R.id.li_apply1_btnlayout1);
        btn2layout = (LinearLayout)view.findViewById(R.id.li_apply1_btnlayout2);
        btn3layout = (LinearLayout)view.findViewById(R.id.li_apply1_btnlayout3);

        List<String> canday = activity.talent.getTalent_day();
        List<String> cantime = activity.talent.getTalent_time();
        List<String> location = activity.talent.getTalent_location();
        final List<Button> locationbtnlist = new ArrayList<>();
        final List<Button> cantimebtnlist = new ArrayList<>();
        final List<Button> candaybtnlist = new ArrayList<>();


        for(String datas : location) {
            final Button btn = new Button(getContext());
            btn.setText(datas);
            btn.setWidth(70);
            btn.setHeight(30);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(120, 80);
            p.weight = 0;
            p.leftMargin = 10;
            btn.setLayoutParams(p);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn.setTag("close");
            locationbtnlist.add(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btn.getTag().equals("close")) {
                        for(Button btn2 : locationbtnlist) {
                            btn2.setBackgroundResource(R.drawable.custom_button6);
                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                            btn2.setTag("close");
                        }
                        btn.setBackgroundResource(R.drawable.custom_button5);
                        btn.setTextColor(Color.WHITE);
                        btn.setTag("open");
                    } else {
                        btn.setBackgroundResource(R.drawable.custom_button6);
                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                        btn.setTag("close");
                    }

                }
            });
            btn1layout.addView(btn);
        }

        for(String datas : canday) {
            final Button btn = new Button(getContext());
            btn.setText(datas);
            btn.setWidth(15);
            btn.setHeight(15);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(80, 80);
            p.weight = 0;
            p.leftMargin = 10;
            btn.setLayoutParams(p);
            btn.setTag("close");
            candaybtnlist.add(btn);
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btn.getTag().equals("close")) {
                        for(Button btn2 : candaybtnlist) {
                            btn2.setBackgroundResource(R.drawable.custom_button6);
                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                            btn2.setTag("close");
                        }
                        btn.setBackgroundResource(R.drawable.custom_button5);
                        btn.setTextColor(Color.WHITE);
                        btn.setTag("open");
                    } else {
                        btn.setBackgroundResource(R.drawable.custom_button6);
                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                        btn.setTag("close");
                    }

                }
            });
            btn2layout.addView(btn);
        }

        for(String datas : cantime) {
            final Button btn = new Button(getContext());
            btn.setText(datas);
            btn.setWidth(70);
            btn.setHeight(30);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(150, 80);
            p.weight = 0;
            p.leftMargin = 10;
            btn.setLayoutParams(p);
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn.setTag("close");
            cantimebtnlist.add(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btn.getTag().equals("close")) {
                        for(Button btn2 : cantimebtnlist) {
                            btn2.setBackgroundResource(R.drawable.custom_button6);
                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                            btn2.setTag("close");
                        }
                        btn.setBackgroundResource(R.drawable.custom_button5);
                        btn.setTextColor(Color.WHITE);
                        btn.setTag("open");
                    } else {
                        btn.setBackgroundResource(R.drawable.custom_button6);
                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                        btn.setTag("close");
                    }

                }
            });
            btn3layout.addView(btn);
        }

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
