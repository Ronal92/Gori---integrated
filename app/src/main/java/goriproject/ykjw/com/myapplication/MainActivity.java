package goriproject.ykjw.com.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tsengvn.typekit.TypekitContextWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.Interfaces.Mypage_Detail_Interface;
import goriproject.ykjw.com.myapplication.Interfaces.User_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Results;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_mypage_retrieve.MyPage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.datas;
import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, NavigationView.OnNavigationItemSelectedListener {

    int location_menu_count = 0;
    int category_menu_count = 0;
    ImageButton img,img2;
    ImageView mainimg;
    static List<Results> datas2 = new ArrayList<>();
    static MainListAdapter rca;
    EditText editText;
    RecyclerView rv;
    ConstraintLayout location_menu, category_menu;
    TextView tv_location, tv_category;

    DrawerLayout drawer;
    NavigationView navigationView;

    @ViewById
    Button btn_location_all, btn_campus_all, btn_campus_korea, btn_campus_yeonse,btn_campus_seoul,btn_campus_hongik,
            btn_location_jamsil,btn_location_sadang,btn_location_sinchon,btn_location_gangnam, btn_category_all, btn_campus_kunkuk, btn_campus_busan,btn_campus_ihwa,btn_campus_hanyang,
            btn_campus_jungang, btn_location_jongro,btn_location_habjung,btn_location_yongsan,btn_location_hehwa, btn_location_mokdong, btn_category_music, btn_category_helth,btn_category_other,btn_campus_other,btn_location_other,btn_category_language,
            btn_category_cumputer, btn_category_sports, btn_category_major;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        if(pref.getString("token", "") != null && pref.getString("token", "").length() > 0) {
            key = pref.getString("token", "");
        }
        // Log.e("kkkkknjjjjjjjjjjjk",key);
        if(key != null && key.length() > 0) {
            is_signin = true;
        }

        if(datas2.size() == 0) {
            CheckTypesTask task = new CheckTypesTask();
            task.execute();
            //Toast.makeText(this, TutorLoader.datasRealy.size(), Toast.LENGTH_SHORT).show();
        }
        if(TalentLoader.talent_datas.size() ==0) {
            TalentLoader.loadData();
        }

        rca.notifyDataSetChanged();

        if(is_signin) {
            navigationView = (NavigationView) findViewById(R.id.nav_view);
            // get menu from navigationView
            Menu menu = navigationView.getMenu();
            // find MenuItem you want to change
            MenuItem logoutitem = menu.findItem(R.id.menu_signinout);
            logoutitem.setTitle(R.string.logoutitem);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @AfterViews
    protected void onCreate() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView  = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        location_menu = (ConstraintLayout)findViewById(R.id.location_menu);
        category_menu = (ConstraintLayout)findViewById(R.id.category_menu);
        editText = (EditText)findViewById(R.id.editText);
        tv_location = (TextView)findViewById(R.id.tv_location);
        tv_category = (TextView)findViewById(R.id.tv_category);

        editText.addTextChangedListener(this);

        //1. recycler 뷰 가져오기
        rv = (RecyclerView) findViewById(R.id.rv);

        //2. 아답터생성하기
        rca = new MainListAdapter(datas2, R.layout.main_list_item,this);

        //3. 리사이클러 뷰에 아답터 세팅하기
        rv.setAdapter(rca);

        rv.setNestedScrollingEnabled(false);

        //4. 리사이클러 뷰 매니저 등록하기(뷰의 모양을 결정 : 그리드, 일반리스트, 비대칭그리드)
        rv.setLayoutManager(new LinearLayoutManager(this));

        img = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        mainimg = (ImageView)findViewById(R.id.mainImage);

        Glide.with(this).load(R.drawable.main_img).thumbnail(0.1f).into(mainimg);

        button_connect();
    }


    public void button_connect() {

        btn_location_all.setOnClickListener(this);
        btn_campus_all.setOnClickListener(this);
        btn_campus_seoul.setOnClickListener(this);
        btn_campus_korea.setOnClickListener(this);
        btn_campus_yeonse.setOnClickListener(this);
        btn_campus_hongik.setOnClickListener(this);
        btn_campus_kunkuk.setOnClickListener(this);
        btn_campus_busan.setOnClickListener(this);
        btn_campus_ihwa.setOnClickListener(this);
        btn_campus_jungang.setOnClickListener(this);
        btn_campus_hanyang.setOnClickListener(this);
        btn_campus_other.setOnClickListener(this);

        btn_location_gangnam.setOnClickListener(this);
        btn_location_sadang.setOnClickListener(this);
        btn_location_sinchon.setOnClickListener(this);
        btn_location_jamsil.setOnClickListener(this);
        btn_location_jongro.setOnClickListener(this);
        btn_location_habjung.setOnClickListener(this);
        btn_location_hehwa.setOnClickListener(this);
        btn_location_yongsan.setOnClickListener(this);
        btn_location_mokdong.setOnClickListener(this);
        btn_location_other.setOnClickListener(this);

        btn_category_all.setOnClickListener(this);
        btn_category_major.setOnClickListener(this);
        btn_category_music.setOnClickListener(this);
        btn_category_cumputer.setOnClickListener(this);
        btn_category_language.setOnClickListener(this);
        btn_category_sports.setOnClickListener(this);
        btn_category_helth.setOnClickListener(this);
        btn_category_other.setOnClickListener(this);

    }


    public void location_click(View view) {

        if(category_menu_count%2 == 1) {
            category_click(view);
        }

        if(location_menu_count%2 == 0) {
            // 이동 애니메이션
            Animation animation = new TranslateAnimation(0,0,-100,0);
            // 나타났다 사라짐 애니메이션
            Animation animation1 = new AlphaAnimation(0,1);
            //속도조절
            animation.setDuration(300);
            animation1.setDuration(300);
            location_menu.setAnimation(animation);
            location_menu.setAnimation(animation1);
            location_menu.setVisibility(view.VISIBLE);
            img.setImageResource(R.drawable.arrow_down_select);
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            location_menu_count++;
        } else {
            location_menu.setVisibility(view.GONE);
            img.setImageResource(R.drawable.arrow_down);
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            location_menu_count++;
        }
    }


    public void category_click(View view) {

        if(location_menu_count%2 == 1) {
            location_click(view);
        }

        if (category_menu_count % 2 == 0) {
            // 이동 애니메이션
            Animation animation = new TranslateAnimation(0, 0, -100, 0);
            // 나타났다 사라짐 애니메이션
            Animation animation1 = new AlphaAnimation(0, 1);
            //속도조절
            animation.setDuration(300);
            animation1.setDuration(300);
            category_menu.setAnimation(animation);
            category_menu.setAnimation(animation1);
            category_menu.setVisibility(view.VISIBLE);
            img2.setImageResource(R.drawable.arrow_down_select);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            category_menu_count++;
        } else {
            category_menu.setVisibility(view.GONE);
            img2.setImageResource(R.drawable.arrow_down);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            category_menu_count++;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Sign_inout) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        String keword = "";

        switch (v.getId()) {
            case R.id.btn_campus_all :
                tv_location.setText("전체 지역");
                button_search(1);
                break;
            case R.id.btn_campus_seoul :
                tv_location.setText("서울대");
                button_search(1);
                break;
            case R.id.btn_campus_hongik :
                tv_location.setText("홍익대");
                button_search(1);
                break;
            case R.id.btn_campus_yeonse :
                tv_location.setText("연세대");
                button_search(1);
                break;
            case R.id.btn_campus_korea :
                tv_location.setText("고려대");
                button_search(1);
                break;
            case R.id.btn_campus_hanyang :
                tv_location.setText("한양대");
                button_search(1);
                break;
            case R.id.btn_campus_jungang :
                tv_location.setText("중앙대");
                button_search(1);
                break;
            case R.id.btn_campus_ihwa :
                tv_location.setText("이화여대");
                button_search(1);
                break;
            case R.id.btn_campus_kunkuk :
                tv_location.setText("건국대");
                button_search(1);
                break;
            case R.id.btn_campus_busan :
                tv_location.setText("부산대");
                button_search(1);
                break;
            case R.id.btn_campus_other :
                tv_location.setText("기타");
                button_search(1);
                break;
            case R.id.btn_location_all :
                tv_location.setText("전체 지역");
                button_search(1);
                break;
            case R.id.btn_location_jamsil :
                tv_location.setText("잠실");
                button_search(1);
                break;
            case R.id.btn_location_gangnam :
                tv_location.setText("강남");
                button_search(1);
                break;
            case R.id.btn_location_sinchon :
                tv_location.setText("신촌");
                button_search(1);
                break;
            case R.id.btn_location_hehwa :
                tv_location.setText("혜화");
                button_search(1);
                break;
            case R.id.btn_location_habjung :
                tv_location.setText("합정");
                button_search(1);
                break;
            case R.id.btn_location_yongsan:
                tv_location.setText("용산");
                button_search(1);
                break;
            case R.id.btn_location_jongro :
                tv_location.setText("종로");
                button_search(1);
                break;
            case R.id.btn_location_mokdong :
                tv_location.setText("목동");
                button_search(1);
                break;
            case R.id.btn_location_sadang :
                tv_location.setText("사당");
                button_search(1);
                break;
            case R.id.btn_location_other :
                tv_location.setText("기타");
                button_search(1);
                break;
            case R.id.btn_category_all :
                tv_category.setText("전체 카테고리");
                button_search(2);
                break;
            case R.id.btn_category_music :
                tv_category.setText("음악/미술");
                button_search(2);
                break;
            case R.id.btn_category_major :
                tv_category.setText("전공/취업");
                button_search(2);
                break;
            case R.id.btn_category_sports :
                tv_category.setText("스포츠");
                button_search(2);
                break;
            case R.id.btn_category_language :
                tv_category.setText("외국어");
                button_search(2);
                break;
            case R.id.btn_category_cumputer :
                tv_category.setText("컴퓨터");
                button_search(2);
                break;
            case R.id.btn_category_helth :
                tv_category.setText("헬스/뷰티");
                button_search(2);
                break;
            case R.id.btn_category_other :
                tv_category.setText("이색취미");
                button_search(2);
                break;
        }
    }


    public void button_search(int searchtype) {

        datas2.clear();

        String location, category;

        location = tv_location.getText().toString();
        category = tv_category.getText().toString();
        if(tv_location.getText().toString().equals("전체 지역")) {
            location = "";
        }
        if(tv_category.getText().toString().equals("전체 카테고리")) {
            category = "";
        }


        if(!location.equals("") ||  !category.equals("")) {
            List<Results> temp = new ArrayList<>();

            for (Results temp2 : datas) {
                if (temp2.getRegions().size() != 0) {
                    for (String temp3 : temp2.getRegions()) {
                        if (temp3.contains(location)) {
                            temp.add(temp2);
                            break;
                        }
                    }
                }
            }



            for (Results it : temp) {
                String t = it.getCategory().replaceAll("\\p{Z}", "");
                if (t.contains(category)) {
                    datas2.add(it);
                }
            }

            temp.clear();

            if(searchtype == 1) {
                if(datas2.size() == 0) {
                    Toast.makeText(MainActivity.this, "아직 조건에 맞는 클래스가 없습니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    tv_location.setText("전체 지역");
                    tv_category.setText("전체 카테고리");
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                    datas2.addAll(datas);
                } else {
                    Toast.makeText(MainActivity.this, "조건에 맞는 강의는 " + datas2.size() +"개입니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                }
            } else if (searchtype == 2) {
                if(datas2.size() == 0) {
                    Toast.makeText(MainActivity.this, "아직 조건에 맞는 클래스가 없습니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    tv_location.setText("전체 지역");
                    tv_category.setText("전체 카테고리");
                    img2.setImageResource(R.drawable.arrow_down);
                    category_menu_count++;
                    datas2.addAll(datas);
                } else {
                    Toast.makeText(MainActivity.this, "조건에 맞는 강의는 " + datas2.size() +"개입니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img2.setImageResource(R.drawable.arrow_down);
                    category_menu_count++;
                }
            }

        } else {
            if(searchtype == 1) {
                    Toast.makeText(MainActivity.this, "전체 목록입니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                    datas2.addAll(datas);

            } else if (searchtype == 2) {
                    Toast.makeText(MainActivity.this, "전체 목록입니다.", Toast.LENGTH_SHORT).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img2.setImageResource(R.drawable.arrow_down);
                    category_menu_count++;
                    datas2.addAll(datas);
            }
        }

        rca.notifyDataSetChanged();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String searchText = editText.getText().toString();
        text_search(searchText);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void text_search(String searchText) {
        datas2.clear();

        String location, category;
        List<Results> temp4 = new ArrayList<>();

        location = tv_location.getText().toString();
        category = tv_category.getText().toString();
        if(tv_location.getText().toString().equals("전체 지역")) {
            location = "";
        }
        if(tv_category.getText().toString().equals("전체 카테고리")) {
            category = "";
        }


        if(!location.equals("") ||  !category.equals("")) {
            List<Results> temp = new ArrayList<>();

            for (Results temp2 : datas) {
                if (temp2.getRegions().size() != 0) {
                    for (String temp3 : temp2.getRegions()) {
                        if (temp3.contains(location)) {
                            temp.add(temp2);
                            break;
                        }
                    }
                }
            }
            for (Results it : temp) {
                if (it.getCategory().contains(category)) {
                    temp4.add(it);
                }
            }
            temp.clear();
        } else {
            temp4.addAll(datas);
        }

        for(Results temp5 : temp4) {
            if(temp5.getTitle().contains(searchText)) {
                datas2.add(temp5);
            } else if(temp5.getTutor().getName().contains(searchText)) {
                datas2.add(temp5);
            }
        }

        temp4.clear();

        rca.notifyDataSetChanged();
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Intent intent;
        if (id == R.id.menu_introduce_gori) {
            intent= new Intent(MainActivity.this, IntroduceGoriActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        } else if (id == R.id.menu_signinout) {
            if(is_signin) {
                key = "";
                is_signin = false;
                item.setTitle("로그인");
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("token", "");
                editor.commit();
                Toast.makeText(MainActivity.this, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }else {
                intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
            }

        } else if (id == R.id.menu_mypage) {
            if(is_signin){
                createRetrofitGET_MYPAGE();
            } else {
                intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
        } else if (id == R.id.menu_tutor_go) {
            // 아직 구현할 생각 없음
            Toast.makeText(MainActivity.this, "튜터등록은 웹사이트에서 해주세요!", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public static void rcanoti() {
        rca.notifyDataSetChanged();
    }


    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                MainActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("로딩중입니다..");
            asyncDialog.setCanceledOnTouchOutside(false);

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            TutorLoader.loadData();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            rcanoti();
            super.onPostExecute(result);
        }
    }

    public void createRetrofitGET_MYPAGE() {
        Log.i("RAPSTAR","======================== This is createRetrofitGET_Mypage()");

        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Mypage_Detail_Interface mypage_detail_interface = retrofit.create(Mypage_Detail_Interface.class);

        Log.i("RAPSTAR","========================token : " + key);

        Call<MyPage> myPageCalla = mypage_detail_interface.getMyPageRetrieve("Token " + key);
        myPageCalla.enqueue(new Callback<MyPage>() {
            @Override
            public void onResponse(Call<MyPage> call, Response<MyPage> response) {
                MyPage myPageFromServer = response.body();
                createRetrofitUserGet(myPageFromServer);


            }
            @Override
            public void onFailure(Call<MyPage> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public void createRetrofitUserGet(final MyPage myPageFromServer){
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
                UserDetail userDetail = response.body();   // 현재 사용하는 유저 정보를 먼저 불러온다.
                Intent intent = new Intent(MainActivity.this, MyPageActivity.class);
                intent.putExtra("mypage",myPageFromServer);
                intent.putExtra("userInformation", userDetail);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {

            }
        });

    }



}

