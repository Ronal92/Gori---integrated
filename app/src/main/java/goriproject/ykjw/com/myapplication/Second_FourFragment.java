package goriproject.ykjw.com.myapplication;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.Interfaces.Qna_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.User_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Qna;

import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_qna_retrieve.QnaDetail;
import goriproject.ykjw.com.myapplication.domain_qna_retrieve.QnaResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;
import static goriproject.ykjw.com.myapplication.Statics.user_name;


/**
 *  Talent detail all(API : qna 구조 <-> Qna(API) : results 구조
 */
public class Second_FourFragment extends Fragment {
    private static final String TAG = "RAPSTAR";
    private static final String KEY_FOR_TALENTDETAIL_FOUR = "threeFragmentTL";

    Context context = null;
    View view;
    TalentDetail td = null;
    UserDetail userDetail = null;

    List<Qna> qna = null; // 내부 데이터 저장소

    RecyclerView recyclerReview = null;
    PagerAdapter adapter = null;



    public Second_FourFragment() {
        // Required empty public constructor
    }

    public static Second_FourFragment newInstance(TalentDetail td ){
        Second_FourFragment secondFourFragment = new Second_FourFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FOR_TALENTDETAIL_FOUR, td);
        secondFourFragment.setArguments(args);
        return secondFourFragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) { // Heap 영역에 올라감.(객체생성하면)
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.td = (TalentDetail)getArguments().getSerializable(KEY_FOR_TALENTDETAIL_FOUR);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null) {
            return view;
        }

        view = inflater.inflate(R.layout.fragment_second_four, container, false);
        context = view.getContext();



        final EditText edTxt_secondfour_content = (EditText)view.findViewById(R.id.edTxt_secondfour_content);
        Button btn_secondfour_qna = (Button)view.findViewById(R.id.btn_secondfour_qna);
        btn_secondfour_qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if(로그인)
                if(is_signin) {
                    createRetrofitPOST_Qna(edTxt_secondfour_content.getText().toString());
                } else {
                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
            }
        });

        // 리사이클러뷰 설정
        recyclerReview = (RecyclerView) view.findViewById(R.id.rv_secondfour);
        adapter = new PagerAdapter(context);
        qna = new ArrayList<>();  // Talent_detail_all 내부 저장소
        Log.i("REALMADRID", "=====================td : " + td);
        for(int i = 0; i<td.getQna().size(); i++){
            qna.add(td.getQna().get(i));
        }
        adapter.setData(qna);
        recyclerReview.setAdapter(adapter);
        recyclerReview.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    public void createRetrofitPOST_Qna(final String content){
        // 2. POST 통신
        // 2.1 통신 로그 확인
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        // 프로그레스 다이얼로그
        final ProgressDialog asyncDialog = new ProgressDialog(getContext());
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        asyncDialog.show();

        // 2.2 레트로핏을 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Qna_Detail_Interface service = retrofit.create(Qna_Detail_Interface.class);

        // 2.3 데이터 받아오기
        Call<QnaResponse> qnaData = service.setQnaRetrieve("Token " + key ,
                Integer.valueOf(td.getPk()),
                content
        );
        qnaData.enqueue(new Callback<QnaResponse>() {
            @Override
            public void onResponse(Call<QnaResponse> call, Response<QnaResponse> response) {

                createRetrofitGetQna(content, asyncDialog);

            }
            @Override
            public void onFailure(Call<QnaResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void createRetrofitUserGet(final String content){
        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User_Detail_Interface tdService = retrofit.create(User_Detail_Interface.class);

        Call<UserDetail> tds = tdService.getUserRetrieve("Token " + key);

        tds.enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                userDetail = response.body();   // 현재 사용하는 유저 정보를 먼저 불러온다.

//                // POST 이후 데이터를 따로 데이터 내부 저장소에 저장한다..
//                Qna qnaData_after_post = new Qna();
//                qnaData_after_post.setUserName(userDetail.getName());
//                qnaData_after_post.setUser_image(userDetail.getProfile_image());
//                qnaData_after_post.setCreated_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())) + "T");
//                qnaData_after_post.setContent(content);
//                qna.add(qnaData_after_post);              // 데이터 추가
//                adapter.setData(qna);                     // 데이터 갱신
//                adapter.notifyDataSetChanged();                //  어뎁터 갱신


            }
            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {

            }
        });

    }

    public void createRetrofitGetQna(final String content, final ProgressDialog dialog_qna){
        // GET으로 해당하는 코멘트의 PK를 먼저 구해야 한다.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Qna_Detail_Interface tdService = retrofit.create(Qna_Detail_Interface.class);

        Call<QnaDetail> tds = tdService.getQnaRetrieve(td.getPk());
        tds.enqueue(new Callback<QnaDetail>() {
            @Override
            public void onResponse(Call<QnaDetail> call, Response<QnaDetail> response) {
                QnaDetail qna_post = response.body();
                qna.clear();
                for(int i = 0; i<qna_post.getResults().size(); i++) {
                    Qna temp = new Qna();
                    temp.setContent(qna_post.getResults().get(i).getContent());
                    temp.setCreated_date(qna_post.getResults().get(i).getCreated_date());
                    temp.setUserName(qna_post.getResults().get(i).getUserName());
                    temp.setUser_image(qna_post.getResults().get(i).getUser_image());
                    temp.setPk(qna_post.getResults().get(i).getPk());
                    temp.setReplies(qna_post.getResults().get(i).getReplies());
                    qna.add(temp);
                }
                adapter.setData(qna);
                recyclerReview.setAdapter(adapter);
                recyclerReview.setLayoutManager(new LinearLayoutManager(context));


                if(dialog_qna != null || dialog_qna.isShowing()) {
                    dialog_qna.dismiss();
                }
            }

            @Override
            public void onFailure(Call<QnaDetail> call, Throwable t) {

            }
        });
    }

    //    public void createRetrofitDelete(String qna_pk, final List<Qna> qnaList, final int qna_position, final ProgressDialog  dialog_qna_delete){
    public void createRetrofitDelete(String qna_pk){

        // 1 .delete 통신
        final ProgressDialog dialog_qna_delete = new ProgressDialog(context);
        dialog_qna_delete.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog_qna_delete.setMessage("로딩중입니다..");
        dialog_qna_delete.show();

        // 2.1 통신 로그 확인
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        // 2.2 레트로핏을 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        Qna_Detail_Interface service = retrofit.create(Qna_Detail_Interface.class);

        // 2.3 데이터 받아오기
        Call<Void> reviewDelete = service.deleteQna("Token " + key, qna_pk);
        reviewDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(getContext(), "성공", Toast.LENGTH_SHORT).show();
//                    // 데이터 제거 및 어뎁터 업데이트
//                    qnaList.remove(qna_position);
//                    adapter.setData(qnaList);
//                    adapter.notifyDataSetChanged();
//
//                    if(dialog_qna_delete != null || dialog_qna_delete.isShowing()) {
//                        dialog_qna_delete.dismiss();
//                    }
                    createRetrofitGetQna(null, dialog_qna_delete);

                } else  {
                    Toast.makeText(getContext(), response.code() + "", Toast.LENGTH_SHORT).show();
                    try {
                        Log.e(TAG, "========================response.body" + response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG, "========================delete 통신 실패!!");
                t.printStackTrace();
            }
        });
    }

    // Adapter : 리뷰를 리스트로 보여줄 어뎁터
    public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

        private Context context = null;
        private List<Qna> qnaList= null;

        public PagerAdapter(Context context){
            this.context = context;

        }

        public void setData(List<Qna> qna){
            qnaList = qna;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_second_four_item, parent, false);
            ViewHolder svh = new ViewHolder(view);
            return svh;
        }

        @Override
        public int getItemCount() {
            return qnaList.size();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.txtComment_tutee_fragment_qna.setText(qnaList.get(position).getContent());
            holder.txName_tutee_fragment_qna.setText(qnaList.get(position).getUserName());
            String date = qnaList.get(position).getCreated_date();
            holder.txtDate_tutee_fragment_qna.setText(date.substring(0, date.indexOf("T")));
            Glide.with(context).load(qnaList.get(position).getUser_image()).placeholder(R.mipmap.ic_launcher).into(holder.img_tutee_fragment_qna); // 이미지 표시



            if (is_signin ) {
                if ( user_name.equals(holder.txName_tutee_fragment_qna.getText())) {
                    final int qna_position = position;
                    holder.btnDelete_fragmentFour_qna.setVisibility(View.VISIBLE);
                    holder.btnDelete_fragmentFour_qna.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            createRetrofitDelete(qnaList.get(qna_position).getPk());

//                            // GET으로 해당하는 코멘트의 PK를 먼저 구해야 한다.
//                            Retrofit retrofit = new Retrofit.Builder()
//                                    .baseUrl("https://mozzi.co.kr/api/")
//                                    .addConverterFactory(GsonConverterFactory.create())
//                                    .build();
//
//                            final ProgressDialog dialog_qna_delete = new ProgressDialog(context);
//                            dialog_qna_delete.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                            dialog_qna_delete.setMessage("로딩중입니다..");
//                            dialog_qna_delete.show();
//
//                            Qna_Detail_Interface tdService = retrofit.create(Qna_Detail_Interface.class);
//
//                            Call<QnaDetail> tds = tdService.getQnaRetrieve(td.getPk());
//                            tds.enqueue(new Callback<QnaDetail>() {
//                                @Override
//                                public void onResponse(Call<QnaDetail> call, Response<QnaDetail> response) {
//                                    QnaDetail qnaDetail = response.body();
//                                    Log.i(TAG,"=======================GET 통신 성공");
//                                    // Delete 통신
//                                    String pkForQnaDelete = findPK(qna_position, qnaList, qnaDetail);
//
//
//                                    createRetrofitDelete(pkForQnaDelete, qnaList, qna_position, dialog_qna_delete);
//
//
//                                }
//
//                                @Override
//                                public void onFailure(Call<QnaDetail> call, Throwable t) {
//
//                                }
//                            });
                        }
                    });
                } else {
                    holder.btnDelete_fragmentFour_qna.setVisibility(View.GONE);
                }

            }
        }
/*
        public String findPK(int qna_position, List<Qna> qnaList, QnaDetail qnaDetail){
            String foundPK = "";





            for(int indexOfPk = 0; indexOfPk < qnaDetail.getResults().size(); indexOfPk++){
                if(user_name.equals(qnaDetail.getResults().get(indexOfPk).getUserName() )
                       // && qnaList.get(qna_position).getCreated_date().substring(0,qnaList.get(qna_position).getCreated_date().indexOf("T")).equals(qnaDetail.getResults().get(indexOfPk).getCreated_date().substring(0,qnaDetail.getResults().get(indexOfPk).getCreated_date().indexOf("T")))
                        && qnaDetail.getResults().get(indexOfPk).getContent().contains(qnaList.get(qna_position).getContent()))
                {
                    Log.i(TAG, "========  FOUND! : " + qnaDetail.getResults().get(indexOfPk).getPk());
                    foundPK = qnaDetail.getResults().get(indexOfPk).getPk();
                }


            }
            return foundPK;
        }
           */


        public class ViewHolder extends RecyclerView.ViewHolder{
            CircleImageView img_tutee_fragment_qna;
            TextView txtDate_tutee_fragment_qna;
            TextView txName_tutee_fragment_qna;
            TextView txtComment_tutee_fragment_qna;
            Button btnResponse_tutee_fragment_qna;
            Button btnDelete_fragmentFour_qna;

            public ViewHolder(View itemView) {
                super(itemView);

                img_tutee_fragment_qna = (CircleImageView)itemView.findViewById(R.id.img_tutee_fragment_qna);
                txtDate_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txtDate_tutee_fragment_qna);
                txName_tutee_fragment_qna = (TextView)itemView.findViewById(R.id.txName_tutee_fragment_qna);
                txtComment_tutee_fragment_qna = (TextView) itemView.findViewById(R.id.txtComment_tutee_fragment_qna);
                btnResponse_tutee_fragment_qna = (Button) itemView.findViewById(R.id.btnResponse_tutee_fragment_qna);
                btnDelete_fragmentFour_qna = (Button) itemView.findViewById(R.id.btnDelete_fragmentFour_qna);



            }

        }
    }

}