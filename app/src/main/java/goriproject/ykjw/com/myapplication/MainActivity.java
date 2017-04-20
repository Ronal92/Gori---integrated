package goriproject.ykjw.com.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.domain.Results;

import static goriproject.ykjw.com.myapplication.Statics.datas;
import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;

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
    Button btn_location_all, btn_campus_all, btn_campus_korea, btn_campus_yeonse,btn_campus_seoul,btn_campus_hongik,
            btn_location_jamsil,btn_location_sadang,btn_location_sinchon,btn_location_gangnam;
    Button btn_category_all, btn_campus_kunkuk, btn_campus_busan,btn_campus_ihwa,btn_campus_hanyang,
            btn_campus_jungang, btn_location_jongro,btn_location_habjung,btn_location_yongsan,btn_location_hehwa, btn_location_mokdong;
    Button btn_category_music, btn_category_helth,btn_category_other,btn_campus_other,btn_location_other,btn_category_language,
            btn_category_cumputer, btn_category_sports, btn_category_major;
    TextView tv_location, tv_category;

    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        key = pref.getString("token", "");
        Log.e("kkkkknjjjjjjjjjjjk",key);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        btn_location_all = (Button)findViewById(R.id.btn_location_all);
        btn_campus_all = (Button)findViewById(R.id.btn_campus_all);
        btn_campus_seoul = (Button)findViewById(R.id.btn_campus_seoul);
        btn_campus_korea = (Button)findViewById(R.id.btn_campus_korea);
        btn_campus_yeonse = (Button)findViewById(R.id.btn_campus_yeonse);
        btn_campus_hongik = (Button)findViewById(R.id.btn_campus_hongik);
        btn_campus_kunkuk = (Button)findViewById(R.id.btn_campus_kunkuk);
        btn_campus_busan = (Button)findViewById(R.id.btn_campus_busan);
        btn_campus_ihwa = (Button)findViewById(R.id.btn_campus_ihwa);
        btn_campus_jungang = (Button)findViewById(R.id.btn_campus_jungang);
        btn_campus_hanyang = (Button)findViewById(R.id.btn_campus_hanyang);
        btn_campus_other = (Button)findViewById(R.id.btn_campus_others);

        btn_location_gangnam = (Button)findViewById(R.id.btn_location_gangnam);
        btn_location_sadang = (Button)findViewById(R.id.btn_location_sadang);
        btn_location_sinchon = (Button)findViewById(R.id.btn_location_sinchon);
        btn_location_jamsil = (Button)findViewById(R.id.btn_location_jamsil);
        btn_location_jongro = (Button)findViewById(R.id.btn_location_jongro);
        btn_location_habjung = (Button)findViewById(R.id.btn_location_hapjung);
        btn_location_hehwa = (Button)findViewById(R.id.btn_location_hehwa);
        btn_location_yongsan = (Button)findViewById(R.id.btn_location_youngsan);
        btn_location_mokdong = (Button)findViewById(R.id.btn_location_mokdong);
        btn_location_other = (Button)findViewById(R.id.btn_location_other);

        btn_category_all = (Button)findViewById(R.id.btn_category_all);
        btn_category_major = (Button)findViewById(R.id.btn_category_major);
        btn_category_music = (Button)findViewById(R.id.btn_category_music);
        btn_category_cumputer = (Button)findViewById(R.id.btn_category_computer);
        btn_category_language = (Button)findViewById(R.id.btn_category_language);
        btn_category_sports = (Button)findViewById(R.id.btn_category_sports);
        btn_category_helth = (Button)findViewById(R.id.btn_category_helth);
        btn_category_other = (Button)findViewById(R.id.btn_category_other);


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
            case R.id.btn_campus_others :
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
            case R.id.btn_location_hapjung :
                tv_location.setText("합정");
                button_search(1);
                break;
            case R.id.btn_location_youngsan :
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
            case R.id.btn_category_computer :
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
                    Toast.makeText(MainActivity.this, "아직 조건에 맞는 클래스가 없습니다.", Toast.LENGTH_LONG).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    tv_location.setText("전체 지역");
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                    datas2.addAll(datas);
                } else {
                    Toast.makeText(MainActivity.this, "조건에 맞는 강의는 " + datas2.size() +"개입니다.", Toast.LENGTH_LONG).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                }
            } else if (searchtype == 2) {
                if(datas2.size() == 0) {
                    Toast.makeText(MainActivity.this, "아직 조건에 맞는 클래스가 없습니다.", Toast.LENGTH_LONG).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    tv_category.setText("전체 카테고리");
                    img2.setImageResource(R.drawable.arrow_down);
                    category_menu_count++;
                    datas2.addAll(datas);
                } else {
                    Toast.makeText(MainActivity.this, "조건에 맞는 강의는 " + datas2.size() +"개입니다.", Toast.LENGTH_LONG).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img2.setImageResource(R.drawable.arrow_down);
                    category_menu_count++;
                }
            }

        } else {
            if(searchtype == 1) {
                    Toast.makeText(MainActivity.this, "전체 목록입니다.", Toast.LENGTH_LONG).show();
                    location_menu.setVisibility(View.GONE);
                    category_menu.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.arrow_down);
                    location_menu_count++;
                    datas2.addAll(datas);

            } else if (searchtype == 2) {
                    Toast.makeText(MainActivity.this, "전체 목록입니다.", Toast.LENGTH_LONG).show();
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


    // 여기는 소트 부분인데 지금은 나중에 rating이 들어오면 수정할 예정


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Intent intent;
        if (id == R.id.menu_introduce_gori) {
            intent= new Intent(MainActivity.this, IntroduceGoriActivity.class);
            startActivity(intent);
            //TODO 고리소개 페이지로드
        } else if (id == R.id.menu_signinout) {
            if(is_signin) {
                key = null;
                is_signin = false;
                item.setTitle("로그인");
                SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("autologin", null);
                editor.commit();
                Toast.makeText(MainActivity.this, "정상적으로 로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }else {
                intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }

        } else if (id == R.id.menu_mypage) {
            //TODO 마이페이지 고
            intent = new Intent(MainActivity.this, MyPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_tutor_go) {
            // 아직 구현할 생각 없음
            Toast.makeText(MainActivity.this, "튜터등록은 웹사이트에서 해주세요!", Toast.LENGTH_LONG).show();
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



}

