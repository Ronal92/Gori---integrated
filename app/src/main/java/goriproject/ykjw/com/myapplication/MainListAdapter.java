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

import goriproject.ykjw.com.myapplication.Interfaces.Talent_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Younkyu on 2017-03-23.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.CustomViewHolder> {

    List<Results> datas;
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디
    int itemLayout;
    TalentDetail td;
    Intent intent;
    Context context; // 클릭처리, 애니메이션 등을 위해 시스템자원 사용이 필요
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디디

    public MainListAdapter(List<Results> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
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

//        holder.ratingBar.setRating(item.getTutor_rating()/20);
//        int rating = (Integer.parseInt(holder.item.getAverage_rate()));

        long ratinglong = Math.round(Double.parseDouble(holder.item.getAverage_rate()));
        int rating = (int)ratinglong;
        holder.ratingBar.setRating(rating);

        if(holder.item.getIs_soldout().equals("true")) {
            holder.soldout.setVisibility(View.VISIBLE);
            holder.soldout.bringToFront();
            holder.tv_soldout.setVisibility(View.VISIBLE);
            holder.tv_soldout.bringToFront();
            holder.itemback.setClickable(false);
        }

        //레이팅바의 색깔을 바꿔야 할 경우에 사용
        LayerDrawable stars = (LayerDrawable) holder.ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.argb(255,238,83,78), PorterDuff.Mode.SRC_ATOP);
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
        TextView class_name,tutor_name,tv_soldout;
        RatingBar ratingBar;
        View soldout;
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
             tv_soldout = (TextView)itemView.findViewById(R.id.tv_soldout);
             soldout = (View)itemView.findViewById(R.id.view_sold_out);

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

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                context);

        int id = 0;

        public void setid(int id) {
            this.id = id;
        }

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("데이터 로딩중..");
            asyncDialog.setCanceledOnTouchOutside(false);
            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // 1. 레트로핏을 생성하고
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mozzi.co.kr/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Talent_Detail_Interface tdService = retrofit.create(Talent_Detail_Interface.class);

            final Call<TalentDetail> tds = tdService.getTalentDetail(String.valueOf(id));

            try {
                td = tds.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            intent.putExtra("td",td);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            context.startActivity(intent);
            super.onPostExecute(result);
        }
    }



}
