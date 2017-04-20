package goriproject.ykjw.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.Arrays;

import goriproject.ykjw.com.myapplication.domain.Result;
import goriproject.ykjw.com.myapplication.Interfaces.SignUpInterface;
import goriproject.ykjw.com.myapplication.Util.SignUtil;
import goriproject.ykjw.com.myapplication.domain.SignUpModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button signup_facebook;
    private CallbackManager callbackManager;
    EditText etemail,etname,etpass,etpass2;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_facebook = (Button)findViewById(R.id.facebooksignup);
        signup_facebook.setOnClickListener(this);

        etname = (EditText)findViewById(R.id.signup_name);
        etemail = (EditText)findViewById(R.id.signup_email);
        etpass = (EditText)findViewById(R.id.signup_password);
        etpass2 = (EditText)findViewById(R.id.signup_password2);

        cb = (CheckBox)findViewById(R.id.signup_check);






    }

    public void emailsignup(View view) {

        if(!cb.isChecked()) {
            Toast.makeText(SignUpActivity.this, "이용약관을 체크하셨나요?", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SignUpInterface service = retrofit.create(SignUpInterface.class);

        SignUpModel model = new SignUpModel();
        String email = etemail.getText().toString();
        String pass = etpass.getText().toString();
        String passCon = etpass2.getText().toString();
        String name = etname.getText().toString();

        // 이메일 형식 아이디 검사
        if(!SignUtil.validateEmail(email)) {
            Toast.makeText(SignUpActivity.this, "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 비밀번호 형식 검사
        if(!SignUtil.validatePassword(pass)) {
            Toast.makeText(SignUpActivity.this, "비밀번호 형식이 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
            Toast.makeText(SignUpActivity.this, "8글자 이상, 단순한 비밀번호는 사용하지 말아주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        // 비밀번호 확인 검사
        if(!SignUtil.checkTwoPasswords(pass, passCon)) {
            Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        model.setName(name);
        model.setPassword1(pass);
        model.setPassword2(passCon);
        model.setUsername(email);

        Call<ResponseBody> remoteData = service.createUser(model);

        remoteData.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("create user response", response.toString());
                try {
                    ResponseBody rsp = response.body();
                    Log.e(response.code() + "", "eee");
                    if(response.code()==201) {
                        Log.e("create user response", "SignUp 성공");
                        Toast.makeText(SignUpActivity.this,"회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                     Log.e(String.valueOf(response.code()), String.valueOf(response.body()));
                    }
                } catch(Exception e) {
                    Log.e("SignUp Error", "SignUp Error Occured!!!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebooksignup :
                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();

                LoginManager.getInstance().logInWithReadPermissions(SignUpActivity.this,
                        Arrays.asList("public_profile", "email"));
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
                        Call<Result> loginData = service.facebooklogin(result.getAccessToken().toString());

                        loginData.enqueue(new Callback<Result>() {
                            @Override
                            public void onResponse(Call<Result> call, Response<Result> response) {
                                Log.e("ddddddddddddd", String.valueOf(response.code()));
                                if (response.code() == 201) {
                                    Toast.makeText(SignUpActivity.this, "페이스북 회원가입 성공", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "페이스북 회원가입 실패", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Result> call, Throwable t) {

                            }

                        });





//                        GraphRequest request;
//                        request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//
//                            @Override
//                            public void onCompleted(JSONObject user, GraphResponse response) {
//                                if (response.getError() != null) {
//
//                                } else {
//
//
//                                    Log.i("TAG", "user: " + user.toString());
//                                    Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
//                                    setResult(RESULT_OK);
//
//                                    String userinfo = user.toString();
//                                    //user: {"id":"1243048255816444","gender":"male","email":"gtv15234@naver.com","name":"이윤규"}
//                                    String[] userinfos = userinfo.split("\"");
//
//                                    userid = userinfos[3];
//                                    username = userinfos[15];
//                                    useremail = userinfos[11];
//                                    Log.i("TAG", "userinfos " + userid);
//                                    Log.i("TAG", "userinfos " + username);
//                                    Log.i("TAG", "userinfos " + useremail);
////                                    Toast.makeText(SignUpActivity.this, "환영합니다! " + username+ "님 ^_^", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(SignUpActivity.this, "환영합니다! " + "이윤규"+ "님 ^_^", Toast.LENGTH_SHORT).show();
//                                    Intent i = new Intent(SignUpActivity.this, MainActivity.class);
//                                    startActivity(i);
//                                    finish();
//                                }
//                            }
//                        });
//                        Bundle parameters = new Bundle();
//                        parameters.putString("fields", "id,name,email,gender,birthday");
//                        request.setParameters(parameters);
//                        request.executeAsync();
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
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    public void signingo(View view ) {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void finishsignup(View view ) {
        finish();
    }
}
