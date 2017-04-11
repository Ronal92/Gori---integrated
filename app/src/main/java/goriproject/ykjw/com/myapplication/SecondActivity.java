package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.tsengvn.typekit.TypekitContextWrapper;

import goriproject.ykjw.com.myapplication.Custom.CustomPager;
import goriproject.ykjw.com.myapplication.Custom.CustomScrollView;
import goriproject.ykjw.com.myapplication.Custom.RadiusImageView;
import goriproject.ykjw.com.myapplication.Custom.RectangleView;
import goriproject.ykjw.com.myapplication.domain.Main_list_item;

import static goriproject.ykjw.com.myapplication.Statics.useremail;
import static goriproject.ykjw.com.myapplication.Statics.userid;
import static goriproject.ykjw.com.myapplication.Statics.username;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "RAPSTAR";
    private static final String[] LIST_MENU = { "탈잉 소개", "MY PAGE", "수업신청서", "LOG OUT", "튜터 등록" };

    Second_OneFragment one;                        // 프래그먼트
    Second_TwoFragment two;
    Second_ThreeFragment three;
    Second_FourFragment four;
    RatingBar rating_second;
    FrameLayout frameLayout;

    ImageView imageView1;                   // 이미지뷰
    RadiusImageView imageView2;

    CheckBox checkbox_wishList;             // 체크박스

    Button btn_second_apply;                   // 버튼 드로어
    RelativeLayout drawer_relativeLayout;
    ImageButton btnDrawerMenu;
    TextView subTitle, txtTitle;
    TabLayout tab, subTab;
    CustomScrollView scrollView;

    DrawerLayout drawer;
    NavigationView navigationView;

    Talent talent;

    float txtTitle_y_position = 0;
    float tab_y_position = 0;

    private TabLayout mTabLayout;


/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        txtTitle_y_position = txtTitle.getTop();
        tab_y_position = tab.getTop();
        Log.i(TAG,"=========================txtTitle_y_position : " + txtTitle_y_position + ", tab_y_position : " + tab_y_position);
        scrollView.setTxtTitleForY(txtTitle_y_position + 180);
        scrollView.setTabForY(tab_y_position + 120);
        super.onWindowFocusChanged(hasFocus);
    }
*/


    @Override
    protected void onResume() {
        super.onResume();

        if(userid != null) {
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            // get menu from navigationView
            Menu menu = navigationView.getMenu();
            // find MenuItem you want to change
            MenuItem logoutitem = menu.findItem(R.id.menu_signinout);
            logoutitem.setTitle(R.string.logoutitem);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_view);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Intent intent = getIntent();
        final int id = intent.getExtras().getInt("id");
        Main_list_item item = (Main_list_item)intent.getSerializableExtra("item");

        // 탭 레이아웃 & 뷰페이저 초기화
        ViewPager viewPager_second_activity = (ViewPager)findViewById(R.id.viewPager_second_activity);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        if(viewPager_second_activity != null) viewPager_second_activity.setAdapter(adapter);
        mTabLayout = (TabLayout) findViewById(R.id.tab_second_activity);
        mTabLayout.addTab(mTabLayout.newTab().setText("수업 소개"));
        mTabLayout.addTab(mTabLayout.newTab().setText("장소/시간"));
        mTabLayout.addTab(mTabLayout.newTab().setText("리뷰"));
        mTabLayout.addTab(mTabLayout.newTab().setText("문의"));
        mTabLayout.bringToFront();
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager_second_activity));
        viewPager_second_activity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));



        //드로어레이아웃
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView  = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // 프래그먼트 초기화
        one = new Second_OneFragment();
        two = new Second_TwoFragment();
        three = new Second_ThreeFragment();
        four = new Second_FourFragment();
        one.setTalent(talent);
        two.setTalent(talent);
        three.setTalent(talent);
        four.setTalent(talent);
        one.setActivity(this);


        // 버튼 초기화
        Button btnApplySecondTemp = (Button)findViewById(R.id.btnApplySecondTemp);
        btnApplySecondTemp.bringToFront();
        btnApplySecondTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ApplyActivity.class);

                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        /*
        for(Talent data : TalentLoader.talent_datas) {
            if(data.getTalent_id() == id) {
                talent = data;
                Log.e("dddddddddddddddddd", talent.getTalent_name());
                break;
            }
        }

        btn_second_apply = (Button)findViewById(R.id.btn_second_apply);
        btn_second_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ApplyActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });




        // 체크 박스
        checkbox_wishList = (CheckBox)findViewById(R.id.checkbox_wishList);
        checkbox_wishList.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showMessage(isChecked);
            }
        });

        // 텍스트뷰
        subTitle = (TextView)findViewById(R.id.subTitle);
        txtTitle = (TextView)findViewById(R.id.txt_second_Title);





        // 탭 레이아웃 & 뷰페이저 초기화
        final CustomPager viewPager = (CustomPager)findViewById(R.id.viewPager);
        tab = (TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("수업 소개"));
        tab.addTab(tab.newTab().setText("장소/시간"));
        tab.addTab(tab.newTab().setText("리뷰"));
        tab.addTab(tab.newTab().setText("문의"));
        subTab = (TabLayout)findViewById(R.id.subTab);
        subTab.addTab(subTab.newTab().setText("수업 소개"));
        subTab.addTab(subTab.newTab().setText("장소/시간"));
        subTab.addTab(subTab.newTab().setText("리뷰"));
        subTab.addTab(subTab.newTab().setText("문의"));

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        subTab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(subTab));




        //스크롤 뷰
        scrollView = (CustomScrollView)findViewById(R.id.scrollView);

        scrollView.setOnAppearListener(new CustomScrollView.OnAppearListener() {
            @Override
            public void onAppearReached() {
                subTitle.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setOnDestroyListener(new CustomScrollView.OnDestroyListener(){
            @Override
            public void onDestroyReached() {
                subTitle.setVisibility(View.GONE);
            }
        });
        scrollView.setTabAppearListener(new CustomScrollView.OnTabAppearListener(){
            @Override
            public void onTabAppearReached() {
                subTab.setVisibility(View.VISIBLE);
            }
        });
        scrollView.setTabDestroyListener(new CustomScrollView.OnTabDestroyListener(){
            @Override
            public void onTabDestroyReached(){
                subTab.setVisibility(View.GONE);
            }
        });

        rating_second = (RatingBar)findViewById(R.id.rating_second);
        TextView tv_second_new = (TextView)findViewById(R.id.tv_second_new);
        if(Integer.parseInt(item.getReview_count().trim()) != 0) {
            long ratinglong = Math.round(Double.parseDouble(item.getAverage_rate()));
            int rating = (int)ratinglong;
            rating_second.setRating(rating);
            //레이팅바의 색깔을 바꿔야 할 경우에 사용
            LayerDrawable stars = (LayerDrawable) rating_second.getProgressDrawable();
            stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            tv_second_new.setVisibility(View.GONE);
        } else {
            rating_second.setVisibility(View.GONE);
        }

        ImageView iv_second_profile = (ImageView)findViewById(R.id.iv_second_profile);
        ImageView iv_second_cover = (ImageView)findViewById(R.id.iv_second_cover);
        Button btn_second_numoftuty = (Button)findViewById(R.id.btn_second_numoftuty);
        TextView txt_name = (TextView)findViewById(R.id.txt_second_name);
        TextView txt_title = (TextView)findViewById(R.id.txt_second_Title);
        TextView tv_location = (TextView)findViewById(R.id.tv_second_location);
        TextView tv_price = (TextView)findViewById(R.id.tv_second_timeper);
        TextView tv_maxman = (TextView)findViewById(R.id.tv_second_maxman);
        TextView tv_schedule = (TextView)findViewById(R.id.tv_second_schedule);
        txt_name.setText(item.getTutor().getName()+"/"+item.getTutor().getNickname());
        txt_title.setText(item.getTitle());
        if(item.getRegions() != null) {
            String location = "";
            for(String i : item.getRegions()) {
                location = location + ", " + i;
            }
            if(location.length() > 2) {
                location = location.substring(2,location.length());
                tv_location.setText(location);
            } else {
                tv_location.setText("없음");
            }
        } else {

        }

        Glide.with(this).load(item.getCover_image()).thumbnail(0.1f).into(iv_second_cover);
        Glide.with(this).load(item.getTutor().getProfile_image()).into(iv_second_profile);

        tv_price.setText(item.getPrice_per_hour()+"원");
        tv_maxman.setText("최대"+item.getNumber_of_class()+"명");
        tv_schedule.setText(item.getHours_per_class()+"시간/회");
        btn_second_numoftuty.setText("누적참여자"+item.getReview_count()+"명");
        */
    }

    // 위시리스트 결과를 보여주는 대화상자
    public void showMessage(boolean isChecked){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(isChecked) {
            builder.setTitle("위시 리스트 등록 여부");
            builder.setMessage("위리리스트에 등록 되었습니다.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO : 위시 리스트에 등록 하는 연산 추가하기
                }
            });
        } else  {
            builder.setMessage("위리리스트에 등록이 취소 되었습니다.");
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.menu_introduce_gori) {
            //TODO 고리소개 페이지로드
        } else if (id == R.id.menu_signinout) {
            if(userid != null) {
                userid = null;
                username = null;
                useremail = null;
                item.setTitle("로그인");
                Toast.makeText(SecondActivity.this, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(SecondActivity.this, SignInActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.menu_mypage) {
            //TODO 마이페이지 고
            Intent intent = new Intent(SecondActivity.this, MyPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_tutor_go) {
            // 아직 구현할 생각 없음
            Toast.makeText(SecondActivity.this, "튜터등록은 웹사이트에서 해주세요!", Toast.LENGTH_LONG).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        final int PAGE_COUNT = 4;
        private int mCurrentPosition = -1;


        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0 : fragment = one;
                    break;
                case 1 : fragment = two;
                    break;
                case 2 : fragment = three;
                    break;
                case 3 : fragment = four;
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void shareFacebook(View view)
    {
        ShareLinkContent content = new ShareLinkContent.Builder()
                //링크의 콘텐츠 제목
                .setContentTitle("페이스북 공유 링크입니다.")

                //게시물에 표시될 썸네일 이미지의 URL
                .setImageUrl(Uri.parse("https://lh3.googleusercontent.com/hmVeH1KmKDy1ozUlrjtYMHpzSDrBv9NSbZ0DPLzR8HdBip9kx3wn_sXmHr3wepCHXA=rw"))

                //공유될 링크
                .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.handykim.nbit.everytimerfree"))

                //게일반적으로 2~4개의 문장으로 구성된 콘텐츠 설명
                .setContentDescription("문장1, 문장2, 문장3, 문장4")
                .build();

        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(content, ShareDialog.Mode.FEED);   //AUTOMATIC, FEED, NATIVE, WEB 등이 있으며 이는 다이얼로그 형식을 말합니다.
    }

}
