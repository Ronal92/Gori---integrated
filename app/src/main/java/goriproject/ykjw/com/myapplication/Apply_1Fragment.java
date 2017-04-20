package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.Util.DipCal;
import goriproject.ykjw.com.myapplication.domain.Locations;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;


public class Apply_1Fragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button btn_next2;
    ImageView img_apply1_profile;
    TalentDetail td;

    LinearLayout  btn2layout;

    GridLayout btn3layout, btn1layout;
    TextView tv_apply1_plusinfo;
    ApplyActivity activity;
    List<Button> locationbtnlist = new ArrayList<>();
    List<Button> locationbtnlist2 = new ArrayList<>();

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
        td = activity.td;

        btn_next2 = (Button)view.findViewById(R.id.btn_next2);
        btn_next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.goAp2();
            }
        });
        tv_apply1_plusinfo = (TextView)view.findViewById(R.id.tv_apply1_plusinfo);

        img_apply1_profile = (ImageView)view.findViewById(R.id.img_apply1_profile);
        Glide.with(activity).load(td.getTutor().getProfile_image()).into(img_apply1_profile);

        btn1layout = (GridLayout)view.findViewById(R.id.li_apply1_btnlayout1);
        btn2layout = (LinearLayout)view.findViewById(R.id.li_apply1_btnlayout2);
        btn3layout = (GridLayout) view.findViewById(R.id.li_apply1_btnlayout3);





        int tag = 0;
        for(Locations lc : td.getLocations()) {
            final Button btn = new Button(getContext());
            btn.setText(lc.getRegion());

            final int width = DipCal.convertPixelsToDp(70,getContext());
            final int height = DipCal.convertPixelsToDp(40,getContext());
            btn.setWidth(width);
            btn.setHeight(height);
            GridLayout.LayoutParams p = new GridLayout.LayoutParams();
            p.setMargins(10,0,0,10);
            p.width= width;
            p.height = height;
            btn.setLayoutParams(p);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn.setTag(tag);
            tag = tag +1;
            locationbtnlist.add(btn);
            btn.setOnClickListener(this);
            btn.setClickable(true);
            locationbtnlist.add(btn);
            btn1layout.addView(btn);
        }

        locationbtnlist.get(0).setBackgroundResource(R.drawable.custom_button5);
        locationbtnlist.get(0).setTextColor(Color.WHITE);

        setLocation(0);
        setTime(0, 0);



//        for(String datas : location) {
//            final Button btn = new Button(getContext());
//            btn.setText(datas);
//            final int width = DipCal.convertPixelsToDp(70,getContext());
//            final int height = DipCal.convertPixelsToDp(40,getContext());
//            btn.setWidth(width);
//            btn.setHeight(height);
//            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width, height);
//            p.weight = 0;
//            p.leftMargin = 10;
//            btn.setLayoutParams(p);
//            btn.setTextSize(14);
//            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//            btn.setBackgroundResource(R.drawable.custom_button6);
//            btn.setTag("close");
//            locationbtnlist.add(btn);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(btn.getTag().equals("close")) {
//                        for(Button btn2 : locationbtnlist) {
//                            btn2.setBackgroundResource(R.drawable.custom_button6);
//                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                            btn2.setTag("close");
//                        }
//                        btn.setBackgroundResource(R.drawable.custom_button5);
//                        btn.setTextColor(Color.WHITE);
//                        btn.setTag("open");
//                    } else {
//                        btn.setBackgroundResource(R.drawable.custom_button6);
//                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                        btn.setTag("close");
//                    }
//
//                }
//            });
//            btn1layout.addView(btn);
//        }
//
//        for(String datas : canday) {
//            final Button btn = new Button(getContext());
//            btn.setText(datas);
//            final int width = DipCal.convertPixelsToDp(40,getContext());
//            final int height = DipCal.convertPixelsToDp(40,getContext());
//            btn.setWidth(width);
//            btn.setHeight(height);
//            btn.setTextSize(14);
//            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width, height);
//            p.weight = 0;
//            p.leftMargin = 10;
//            btn.setLayoutParams(p);
//            btn.setTag("close");
//            candaybtnlist.add(btn);
//            btn.setBackgroundResource(R.drawable.custom_button6);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(btn.getTag().equals("close")) {
//                        for(Button btn2 : candaybtnlist) {
//                            btn2.setBackgroundResource(R.drawable.custom_button6);
//                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                            btn2.setTag("close");
//                        }
//                        btn.setBackgroundResource(R.drawable.custom_button5);
//                        btn.setTextColor(Color.WHITE);
//                        btn.setTag("open");
//                    } else {
//                        btn.setBackgroundResource(R.drawable.custom_button6);
//                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                        btn.setTag("close");
//                    }
//
//                }
//            });
//            btn2layout.addView(btn);
//        }
//
//        for(String datas : cantime) {
//            final Button btn = new Button(getContext());
//            btn.setText(datas);
//            final int width = DipCal.convertPixelsToDp(90,getContext());
//            final int height = DipCal.convertPixelsToDp(40,getContext());
//            btn.setWidth(width);
//            btn.setHeight(height);
//            btn.setTextSize(14);
//            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width, height);
//            p.weight = 0;
//            p.leftMargin = 10;
//            btn.setLayoutParams(p);
//            btn.setBackgroundResource(R.drawable.custom_button6);
//            btn.setTag("close");
//            cantimebtnlist.add(btn);
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(btn.getTag().equals("close")) {
//                        for(Button btn2 : cantimebtnlist) {
//                            btn2.setBackgroundResource(R.drawable.custom_button6);
//                            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                            btn2.setTag("close");
//                        }
//                        btn.setBackgroundResource(R.drawable.custom_button5);
//                        btn.setTextColor(Color.WHITE);
//                        btn.setTag("open");
//                    } else {
//                        btn.setBackgroundResource(R.drawable.custom_button6);
//                        btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
//                        btn.setTag("close");
//                    }
//
//                }
//            });
//            btn3layout.addView(btn);
//        }

        return view;
    }

    public void setLocation(int tag){
        int tag2 = 0;
        btn2layout.removeAllViews();
        locationbtnlist2.clear();
        for(Results rt : td.getLocations().get(tag).getResults()) {
            final Button btn2 = new Button(getContext());
            btn2.setText(rt.getDay());
            final int width = DipCal.convertPixelsToDp(40,getContext());
            final int height = DipCal.convertPixelsToDp(40,getContext());
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(width, height);
            p.weight = 0;
            p.leftMargin = 10;
            btn2.setLayoutParams(p);
            btn2.setTextSize(14);
            btn2.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            btn2.setBackgroundResource(R.drawable.custom_button6);
            btn2.setTag(tag2 + tag*10);
            tag2 = tag2 +1 ;
            locationbtnlist2.add(btn2);
            btn2.setOnClickListener(btn2li);
            btn2layout.addView(btn2);
        }

        locationbtnlist2.get(0).setBackgroundResource(R.drawable.custom_button5);
        locationbtnlist2.get(0).setTextColor(Color.WHITE);


    }

    public void setText(int tag) {
        for(Button btn : locationbtnlist) {
        }
    }

    public void setTime(int tag, int tag2) {
        btn3layout.removeAllViews();
        for(String btntime : td.getLocations().get(tag).getResults().get(tag2%10).getTime()) {
            final Button btn = new Button(getContext());
            btn.setText(btntime);
            final int width2 = DipCal.convertPixelsToDp(90,getContext());
            final int height2 = DipCal.convertPixelsToDp(40,getContext());
            btn.setWidth(width2);
            btn.setHeight(height2);
            btn.setTextSize(14);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            GridLayout.LayoutParams p1 = new GridLayout.LayoutParams();
            p1.setMargins(10,0,0,10);
            p1.width= width2;
            p1.height = height2;
            btn.setLayoutParams(p1);
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn3layout.addView(btn);
        }

        activity.Location_pk = Integer.parseInt(td.getLocations().get(tag).getResults().get(tag2%10).getPk().trim());

        if(td.getLocations().get(tag).getResults().get(tag2%10).getExtra_fee().equals("N")) {
            tv_apply1_plusinfo.setText("추가비용 없음 ");
        }else {
            tv_apply1_plusinfo.setText("추가비용 : " + td.getLocations().get(tag).getResults().get(tag2%10).getExtra_fee_amount());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    View.OnClickListener btn2li = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for(Button btn : locationbtnlist2) {
                btn.setBackgroundResource(R.drawable.custom_button6);
                btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
                if(v.getTag() == btn.getTag()) {
                    btn.setBackgroundResource(R.drawable.custom_button5);
                    btn.setTextColor(Color.WHITE);
                }
            }
        setTime(((int)v.getTag()/10),((int)v.getTag()%10));
        }

    };


    @Override
    public void onClick(View v) {

        for(Button btn : locationbtnlist) {
            btn.setBackgroundResource(R.drawable.custom_button6);
            btn.setTextColor(getResources().getColor(R.color.cardview_dark_background));
            if(v.getTag() == btn.getTag()) {
                btn.setBackgroundResource(R.drawable.custom_button5);
                btn.setTextColor(Color.WHITE);
            }
        }
        setLocation((int)v.getTag());
        setTime((int)v.getTag(), 0);
    }
}
