package goriproject.ykjw.com.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import goriproject.ykjw.com.myapplication.Interfaces.Apply_Doc_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.key;

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
                CheckTypesTask task = new CheckTypesTask();
                task.execute();
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

    private class CheckTypesTask extends AsyncTask<Void, Void, String> {

        ProgressDialog asyncDialog = new ProgressDialog(
                getContext());

        int id = 0;

        public void setid(int id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("데이터 로딩중..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... arg0) {
            // 1. 레트로핏을 생성하고
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mozzi.co.kr/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Apply_Doc_Interface adService = retrofit.create(Apply_Doc_Interface.class);

            Call<ResponseBody> tds = adService.postApply("Token "+key,activity.Location_pk,activity.tutor_msg,activity.student_level, activity.experience_length);

            try {
                if(tds.execute().code() == 201) {
//                    Log.e("responsecode = " , String.valueOf(tds.execute().code()));
                    return "ok";

                }else {

                    return "no";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            asyncDialog.dismiss();
            if(result.equals("ok")) {
                Log.e("key = ",key);
                Log.e("studentlevel : ", String.valueOf(activity.student_level));
                Log.e("tutor_msg : ", activity.tutor_msg);
                Log.e("Locationpk = ,", String.valueOf(activity.Location_pk));
                activity.goAp5();
            }else {
                Log.e("key = ",key);
                Log.e("studentlevel : ", String.valueOf(activity.student_level));
                Log.e("tutor_msg : ", activity.tutor_msg);
                Log.e("Locationpk = ,", String.valueOf(activity.Location_pk));
                Log.e("LENGTH = ,", String.valueOf(activity.experience_length));
                Toast.makeText(getContext(),"등록에 실패했습니다.",Toast.LENGTH_LONG).show();
                activity.finish();
            }
            super.onPostExecute(result);
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

}
