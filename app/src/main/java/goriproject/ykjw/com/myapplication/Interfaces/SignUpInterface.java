package goriproject.ykjw.com.myapplication.Interfaces;

import org.json.JSONObject;

import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface SignUpInterface {

    @FormUrlEncoded
    @POST("member/signup")
    public void signup(@Field("username") String username, @Field("password1") String password1, @Field("password2") String password2,
                       @Field("name") String name, Callback<JSONObject> callback);
}
