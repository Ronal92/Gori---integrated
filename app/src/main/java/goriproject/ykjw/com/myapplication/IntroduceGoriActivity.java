package goriproject.ykjw.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

public class IntroduceGoriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_gori);

        LinearLayout li_introduce = (LinearLayout)findViewById(R.id.li_introduce);
        ImageView iv = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i1.jpg").into(iv);
        ImageView iv2 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i2.jpg").into(iv2);
        ImageView iv3 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i3.jpg").into(iv3);
        ImageView iv4 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i4.jpg").into(iv4);
        ImageView iv5 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i5.jpg").into(iv5);
        ImageView iv6 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i6.jpg").into(iv6);
        ImageView iv7 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i7.jpg").into(iv7);
        ImageView iv8 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i8.jpg").into(iv8);
        ImageView iv9 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i9.jpg").into(iv9);
        ImageView iv10 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i10.jpg").into(iv10);
        ImageView iv11 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i11.jpg").into(iv11);
        ImageView iv12 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i12.jpg").into(iv12);
        ImageView iv13 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i13.jpg").into(iv13);
        ImageView iv14 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i14.jpg").into(iv14);
        ImageView iv15 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i15.jpg").into(iv15);
        ImageView iv16 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i16.jpg").into(iv16);
        ImageView iv17 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i17.jpg").into(iv17);
        ImageView iv18 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i18.jpg").into(iv18);
        ImageView iv19 = new ImageView(this);
        Glide.with(this).load("http://taling.me/Content/Images/Intro/i19.jpg").into(iv19);




        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        iv.setScaleType(ImageView.ScaleType.MATRIX);
        iv.setLayoutParams(p);
        iv2.setScaleType(ImageView.ScaleType.MATRIX);
        iv2.setLayoutParams(p);
        iv3.setScaleType(ImageView.ScaleType.MATRIX);
        iv3.setLayoutParams(p);
        iv4.setScaleType(ImageView.ScaleType.MATRIX);
        iv4.setLayoutParams(p);
        iv5.setScaleType(ImageView.ScaleType.MATRIX);
        iv5.setLayoutParams(p);
        iv6.setScaleType(ImageView.ScaleType.MATRIX);
        iv6.setLayoutParams(p);
        iv7.setScaleType(ImageView.ScaleType.MATRIX);
        iv7.setLayoutParams(p);
        iv8.setScaleType(ImageView.ScaleType.MATRIX);
        iv8.setLayoutParams(p);
        iv9.setScaleType(ImageView.ScaleType.MATRIX);
        iv9.setLayoutParams(p);
        iv10.setScaleType(ImageView.ScaleType.MATRIX);
        iv10.setLayoutParams(p);
        iv11.setScaleType(ImageView.ScaleType.MATRIX);
        iv11.setLayoutParams(p);
        iv12.setScaleType(ImageView.ScaleType.MATRIX);
        iv12.setLayoutParams(p);
        iv13.setScaleType(ImageView.ScaleType.MATRIX);
        iv13.setLayoutParams(p);
        iv14.setScaleType(ImageView.ScaleType.MATRIX);
        iv14.setLayoutParams(p);
        iv15.setScaleType(ImageView.ScaleType.MATRIX);
        iv15.setLayoutParams(p);
        iv16.setScaleType(ImageView.ScaleType.MATRIX);
        iv16.setLayoutParams(p);
        iv17.setScaleType(ImageView.ScaleType.MATRIX);
        iv17.setLayoutParams(p);
        iv18.setScaleType(ImageView.ScaleType.MATRIX);
        iv18.setLayoutParams(p);
        iv19.setScaleType(ImageView.ScaleType.MATRIX);
        iv19.setLayoutParams(p);

        li_introduce.addView(iv);
        li_introduce.addView(iv2);
        li_introduce.addView(iv3);
        li_introduce.addView(iv4);
        li_introduce.addView(iv5);
        li_introduce.addView(iv6);
        li_introduce.addView(iv7);
        li_introduce.addView(iv8);
        li_introduce.addView(iv9);
        li_introduce.addView(iv10);
        li_introduce.addView(iv11);
        li_introduce.addView(iv12);
        li_introduce.addView(iv13);
        li_introduce.addView(iv14);
        li_introduce.addView(iv15);
        li_introduce.addView(iv16);
        li_introduce.addView(iv17);
        li_introduce.addView(iv18);
        li_introduce.addView(iv19);
    }
}
