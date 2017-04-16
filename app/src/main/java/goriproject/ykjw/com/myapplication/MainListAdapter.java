package goriproject.ykjw.com.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import goriproject.ykjw.com.myapplication.Interfaces.Review_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.StopProgressDialog;
import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import goriproject.ykjw.com.myapplication.domain.review.ReviewRetrieve;
import goriproject.ykjw.com.myapplication.domain_test.TalentAll;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Younkyu on 2017-03-23.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.CustomViewHolder> {


    List<Results> datas;
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디
    int itemLayout;
    //TalentDetail td;
    TalentAll td;
    Intent intent;
    int checkReview = 0;
    int checkTalent =0;

    Context context; // 클릭처리, 애니메이션 등을 위해 시스템자원 사용이 필요
    StopProgressDialog ctx_stopProgress = null;
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디디


    ReviewRetrieve reviewRetrieve;  // 리뷰 정보를 받아오기 위한 변수

    ProgressDialog  asyncDialog = null;
    long startTime;
    long stopTime;

    public MainListAdapter(List<Results> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
        ctx_stopProgress = (StopProgressDialog)context;
    }

    @Override
    public MainListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        CustomViewHolder cvh = new CustomViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(MainListAdapter.CustomViewHolder holder, int position) {

        holder.item = datas.get(position);

        //holder.ratingBar.setRating(item.getTutor_rating()/20);
//        int rating = (Integer.parseInt(holder.item.getAverage_rate()));
        long ratinglong = Math.round(Double.parseDouble(holder.item.getAverage_rate()));
        int rating = (int)ratinglong;
        holder.ratingBar.setRating(rating);
        //레이팅바의 색깔을 바꿔야 할 경우에 사용
        LayerDrawable stars = (LayerDrawable) holder.ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        holder.class_name.setText(holder.item.getTitle());
        holder.tutor_name.setText(holder.item.getTutor().getName());
        holder.id = Integer.parseInt(holder.item.getPk().trim());

//        if(tutors.getCampus().equals("고려대")) {
//            Glide.with(context).load(R.drawable.profile_dummy2).into(holder.imageView2);
//        } else {
            Glide.with(context).load(holder.item.getTutor().getProfile_image()).into(holder.imageView2);
//        }
        Glide.with(context).load(holder.item.getCover_image()).thumbnail(0.1f).into(new ViewTarget<ConstraintLayout, GlideDrawable>(holder.itemback) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation anim) {
                ConstraintLayout myView = this.view;
                // Set your resource on myView and/or start your animation here.
                myView.setBackground(resource);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView2;
        ConstraintLayout itemback;
        TextView class_name,tutor_name;
        RatingBar ratingBar;
        int id;
        Results item;

         CustomViewHolder(View itemView) {
            super(itemView);
             imageView2 = (ImageView)itemView.findViewById(R.id.iv_second_profile);
             imageView2.bringToFront();
             itemback = (ConstraintLayout)itemView.findViewById(R.id.itemback);
             tutor_name = (TextView)itemView.findViewById(R.id.tutor_name);
             class_name = (TextView)itemView.findViewById(R.id.class_name);
             ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

             itemback.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {


                     intent = new Intent(context, SecondActivity.class);
                     intent.putExtra("id",id);
                     intent.putExtra("item", item);

                     CheckTypesTask task = new CheckTypesTask();
                     task.setid(id);
                     task.execute();


                 }
             });

        }
    }



        private class CheckTypesTask extends AsyncTask<Void, Void, TalentAll> {

        int id = 0;

        public void setid(int id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {

//            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            asyncDialog.setMessage("데이터 로딩중..");
//            asyncDialog.show();  // show dialog
            asyncDialog = ProgressDialog.show(context, "데이터로딩중", "Downloading data",true);
            ctx_stopProgress.stopProgress(asyncDialog); // asyncDialog를 MainAtivity로 넘긴다.

           super.onPreExecute();
        }

        @Override
        protected TalentAll doInBackground(Void... arg0) {
            // 1. 레트로핏을 생성하고
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mozzi.co.kr/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Talent_All_Interface tdService = retrofit.create(Talent_All_Interface.class);

            final Call<TalentAll> tds = tdService.getTalentAll(String.valueOf(id));
            TalentAll td = null;
            try {
                td = tds.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            intent.putExtra("td",td);

            return td;
        }

        @Override
        protected void onPostExecute(TalentAll result) {
            context.startActivity(intent);
            super.onPostExecute(result);
        }
    }

    public interface Talent_All_Interface {
        @GET("talent/detail-all/{talent_pk}/")
        Call<TalentAll> getTalentAll(@Path("talent_pk") String talent_pk);
    }




}
