package goriproject.ykjw.com.myapplication;

import android.content.Context;
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

import goriproject.ykjw.com.myapplication.Custom.CircleImageView;
import goriproject.ykjw.com.myapplication.domain_mypage_retrieve.MyPage;

/**
 * 실질적으로 MyPage 데이터들을 뿌려주는 곳.
 * 여기서 각 탭별로 구분하여 다른  CardView를 보여준다.
 */

public class MyPageAdapter extends RecyclerView.Adapter<MyPageAdapter.SimpleViewHolder> {

    private static final String TAG = "RAPSTAR";

    private Context context = null;
    MyPage mypageFromServer = null;
    private String typeFlag;

    // 탭에 보여줄 xml 식별자
    private int tap_layout_id = 0;

    public MyPageAdapter(Context context, MyPage mypageFromServer, String typeFlag){
        this.context = context;
        this.mypageFromServer = mypageFromServer;
        this.typeFlag = typeFlag;
        switch(typeFlag){
            case MyPageTuteeOfListFragment.TYPE_APPLICATION:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_one_carditem;
                break;
            case MyPageTuteeOfListFragment.TYPE_CLASSLIST:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_two_carditem;
                break;
            case MyPageTuteeOfListFragment.TYPE_WISHLIST:
                tap_layout_id = R.layout.fragment_mypage_tutee_fragment_three_carditem;
                break;
            case MyPageTutorOfListFragment.TYPE_RESUME:
                tap_layout_id = R.layout.fragment_mypage_tutor_fragment_one_carditem;
                break;
            case MyPageTutorOfListFragment.TYPE_MYCLASS:
                tap_layout_id = R.layout.fragment_mypage_tutor_fragment_two_carditem;
                break;
        }

    }


    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(tap_layout_id, parent, false);
        SimpleViewHolder svh = new SimpleViewHolder(view, typeFlag);
        return svh;
    }


    /*
     탭마다 다른 그림이 들어가게 설정하였다.
     */
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {

        switch(typeFlag){
            case MyPageTuteeOfListFragment.TYPE_APPLICATION:
                holder.txtWriter_tutee_fragment_one.setText(mypageFromServer.getResults().getRegistrations()[position].getTutor_info().getName());
                holder.txtTitle_tutee_fragment_one.setText(mypageFromServer.getResults().getRegistrations()[position].getTalent().getTitle());
                String date = mypageFromServer.getResults().getRegistrations()[position].getJoined_date();
                date = date.substring(0,date.indexOf("."));
                holder.txtDate_tutee_fragment_one.setText(date.replace("T"," "));
                Glide.with(context).load(mypageFromServer.getResults().getRegistrations()[position].getTalent().getCover_image()).into(holder.img_tutee_fragment_one);
                break;

            case MyPageTuteeOfListFragment.TYPE_CLASSLIST:
                holder.img_tutee_profile_fragment_two.bringToFront();
                Glide.with(context).load(mypageFromServer.getResults().getEnrollment()[position].getTutor_info().getProfile_image()).into(holder.img_tutee_profile_fragment_two);
                Glide.with(context).load(mypageFromServer.getResults().getEnrollment()[position].getTalent().getCover_image()).thumbnail(0.1f).into(new ViewTarget<ConstraintLayout, GlideDrawable>(holder.img_tutee_cover_fragment_two) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation anim) {
                        ConstraintLayout myView = this.view;
                        // Set your resource on myView and/or start your animation here.
                        myView.setBackground(resource);
                    }
                });
                holder.txtName_tutee_fragment_two.setText(mypageFromServer.getResults().getEnrollment()[position].getTutor_info().getName());
                holder.txtTitler_tutee_fragment_two.setText(mypageFromServer.getResults().getEnrollment()[position].getTalent().getTitle());
                long ratinglong_enrollment = Math.round(Double.parseDouble(mypageFromServer.getResults().getEnrollment()[position].getTalent().getAverage_rate()));
                int rating = (int)ratinglong_enrollment;
                holder.ratingBar_tutee_fragment_two.setRating(rating);

                break;

            case MyPageTuteeOfListFragment.TYPE_WISHLIST:
                Glide.with(context).load(mypageFromServer.getResults().getWishlist()[position].getCover_image()).into(holder.img_mypage_tutee_three);
                holder.txtTitle_mypage_tutee_three.setText(mypageFromServer.getResults().getWishlist()[position].getTitle());
                String registration_cnt = mypageFromServer.getResults().getWishlist()[position].getRegistration_count();
                holder.txtRegistrationCnt_mypage_tutee_three.setText(registration_cnt + "명");
                holder.txtPrice_mypage_tutee_three.setText(mypageFromServer.getResults().getWishlist()[position].getPrice_per_hour() + "\u20a9" );
                holder.txtRegions_mypage_tutee_three.setText(mypageFromServer.getResults().getWishlist()[position].getRegions()[0]); // 대표적으로 지역 하나만 출력
                holder.txtType_mypage_tutee_three.setText(mypageFromServer.getResults().getWishlist()[position].getType());
                long ratinglong_wishlist = Math.round(Double.parseDouble(mypageFromServer.getResults().getWishlist()[position].getAverage_rate()));
                holder.rb_mypage_tutee_three.setRating((int)ratinglong_wishlist);
                break;

            case MyPageTutorOfListFragment.TYPE_RESUME:
                Glide.with(context).load(R.drawable.test).into(holder.img_tutee_fragment_one);
                break;

            case MyPageTutorOfListFragment.TYPE_MYCLASS:
                Glide.with(context).load(R.drawable.test2).into(holder.img_tutee_fragment_one);
                break;

        }
    }



    @Override
    public int getItemCount() {
        int returnValue = 0;
        switch(typeFlag){
            case MyPageTuteeOfListFragment.TYPE_APPLICATION:
                returnValue = mypageFromServer.getResults().getRegistrations().length;
                break;
            case MyPageTuteeOfListFragment.TYPE_CLASSLIST:
                returnValue = mypageFromServer.getResults().getEnrollment().length;
                break;
            case MyPageTuteeOfListFragment.TYPE_WISHLIST:
                returnValue = mypageFromServer.getResults().getWishlist().length;
                break;

            case MyPageTutorOfListFragment.TYPE_RESUME:
                returnValue = mypageFromServer.getResults().getRegistrations().length;
                break;

            case MyPageTutorOfListFragment.TYPE_MYCLASS:
                returnValue = mypageFromServer.getResults().getRegistrations().length;
                break;

        }

        return returnValue;
    }

    /*
    위젯 아이디는 xml 모두 똑같아야 onBindViewHolder에서 같이 지정할 수 있다.
     */
    public class SimpleViewHolder extends RecyclerView.ViewHolder{

        // TYPE : registration
        CircleImageView img_tutee_fragment_one;
        TextView txtWriter_tutee_fragment_one, txtDate_tutee_fragment_one, txtTitle_tutee_fragment_one;

        // TYPE : enrollment
        ConstraintLayout img_tutee_cover_fragment_two;
        CircleImageView img_tutee_profile_fragment_two;
        RatingBar ratingBar_tutee_fragment_two;
        TextView txtTitler_tutee_fragment_two;
        TextView txtName_tutee_fragment_two;

        // TYPE : wishList
        ImageView img_mypage_tutee_three;
        TextView txtTitle_mypage_tutee_three, txtPrice_mypage_tutee_three, txtType_mypage_tutee_three, txtRegions_mypage_tutee_three, txtRegistrationCnt_mypage_tutee_three;
        RatingBar rb_mypage_tutee_three;

        // TYPE : applicants


        public SimpleViewHolder(View itemView, String typeflag_viewHolder) {
            super(itemView);
            switch (typeflag_viewHolder){
                case MyPageTuteeOfListFragment.TYPE_APPLICATION :
                    // TYPE : registration
                    img_tutee_fragment_one = (CircleImageView)itemView.findViewById(R.id.img_wishList_one);
                    txtWriter_tutee_fragment_one = (TextView)itemView.findViewById(R.id.txtWriter_tutee_fragment_one);
                    txtDate_tutee_fragment_one = (TextView)itemView.findViewById(R.id.txtDate_tutee_fragment_one);
                    txtTitle_tutee_fragment_one = (TextView)itemView.findViewById(R.id.txtTitle_tutee_fragment_one);
                    break;
                case MyPageTuteeOfListFragment.TYPE_CLASSLIST :
                    // TYPE : enrollment
                    img_tutee_cover_fragment_two = (ConstraintLayout)itemView.findViewById(R.id.img_tutee_cover_fragment_two);
                    img_tutee_profile_fragment_two = (CircleImageView)itemView.findViewById(R.id.img_tutee_profile_fragment_two);
                    ratingBar_tutee_fragment_two = (RatingBar)itemView.findViewById(R.id.ratingBar_tutee_fragment_two);
                    txtTitler_tutee_fragment_two = (TextView)itemView.findViewById(R.id.txtTitler_tutee_fragment_two);
                    txtName_tutee_fragment_two = (TextView)itemView.findViewById(R.id.txtName_tutee_fragment_two);
                    break;
                case MyPageTuteeOfListFragment.TYPE_WISHLIST :
                    // TYPE : wishList
                    img_mypage_tutee_three = (ImageView)itemView.findViewById(R.id.img_mypage_tutor_two);
                    txtTitle_mypage_tutee_three = (TextView)itemView.findViewById(R.id.txtTitle_mypage_tutee_three);
                    txtPrice_mypage_tutee_three = (TextView)itemView.findViewById(R.id.txtPrice_mypage_tutee_three);
                    txtType_mypage_tutee_three = (TextView)itemView.findViewById(R.id.txtType_mypage_tutee_three);
                    txtRegions_mypage_tutee_three = (TextView)itemView.findViewById(R.id.txtRegions_mypage_tutee_three);
                    txtRegistrationCnt_mypage_tutee_three = (TextView)itemView.findViewById(R.id.txtRegistrationCnt_mypage_tutee_three);
                    rb_mypage_tutee_three = (RatingBar) itemView.findViewById(R.id.rb_mypage_tutor_two);
                    break;
            }


        }

    }
}
