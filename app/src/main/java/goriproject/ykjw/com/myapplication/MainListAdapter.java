package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
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

import java.util.List;

import goriproject.ykjw.com.myapplication.domain.Main_list_item;

/**
 * Created by Younkyu on 2017-03-23.
 */

public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.CustomViewHolder> {

    List<Main_list_item> datas;
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디
    int itemLayout;

    Context context; // 클릭처리, 애니메이션 등을 위해 시스템자원 사용이 필요
    // 리스트 각 행에서 사용되는 레이아웃 xml의 아이디디

    public MainListAdapter(List<Main_list_item> datas, int itemLayout, Context context) {
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
        Main_list_item item;

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
                     Intent intent = new Intent(context, SecondActivity.class);
                     intent.putExtra("id",id);
                     intent.putExtra("item", item);
                     context.startActivity(intent);
                 }
             });

        }
    }
}
