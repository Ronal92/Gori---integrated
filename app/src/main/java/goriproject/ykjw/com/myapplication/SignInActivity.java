package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.Arrays;

import goriproject.ykjw.com.myapplication.Interfaces.User_Detail_Interface;
import goriproject.ykjw.com.myapplication.domain.Result;
import goriproject.ykjw.com.myapplication.domain.Result2;
import goriproject.ykjw.com.myapplication.Interfaces.SignUpInterface;
import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.is_signin;
import static goriproject.ykjw.com.myapplication.Statics.key;
import static goriproject.ykjw.com.myapplication.Statics.user_name;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signup;
    EditText email,password;
    private CallbackManager callbackManager;
    CheckBox cb_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signup = (TextView)findViewById(R.id.singupgo);
        signup.setOnClickListener(this);
        email = (EditText)findViewById(R.id.login_id);
        password = (EditText)findViewById(R.id.login_password);
        cb_login = (CheckBox)findViewById(R.id.autologin_check);


    }

    public void signin(View view) {
        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SignUpInterface service = retrofit.create(SignUpInterface.class);

        String id,pass;

        id = email.getText().toString();
        pass = password.getText().toString();

        // 토큰 받아오기
        Call<Result2> loginData = service.login(id, pass);

        loginData.enqueue(new Callback<Result2>() {
            @Override
            public void onResponse(Call<Result2> call, Response<Result2> response) {
                if (response.code() == 200) {
                    Toast.makeText(SignInActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    key = response.body().getKey();

                    if(cb_login.isChecked()) {
                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", key);
                        editor.commit();
                    } else {
                        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("token", null);
                        editor.commit();
                    }

                    is_signin = true;
                    createRetrofitUserGet();
                    // goriproject.ykjw.com.myapplication.domain.Result@42ca77a0
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    finish();
                    overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_top);
                } else {
                    Toast.makeText(SignInActivity.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result2> call, Throwable t) {

            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singupgo :
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);
                finish();
                break;
        }
    }

    public void facebooklogin(View view) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logInWithReadPermissions(SignInActivity.this,
                Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {

                // 1. 레트로핏을 생성하고
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://mozzi.co.kr/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                SignUpInterface service = retrofit.create(SignUpInterface.class);

                // 토큰 받아오기
                Call<Result> loginData = service.facebooklogin(result.getAccessToken().getToken());

                loginData.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        Log.e("ddddddddddddd", String.valueOf(response.code()));
                        if (response.code() == 201) {
                            Toast.makeText(SignInActivity.this, "페이스북 로그인 성공", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignInActivity.this, "페이스북 로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }

                });
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                //finish();
            }

            @Override
            public void onCancel() {
                //finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void createRetrofitUserGet(){
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
                user_name = userDetail.getName();
                Log.i("RAPSTAR", "=============================  user_name  : " + user_name);
                finish();
            }
            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {

            }
        });

    }

    public void finishsignin(View view ) {
        finish();
    }
}
