package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain.List_All;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Younkyu on 2017-04-14.
 */

public interface Apply_Doc_Interface {

    @Multipart
    @POST("talent/add/registration/")
    Call<ResponseBody> postApply(@Header("Authorization") String Token, @Part("location_pk") int pk,
                                 @Part("message_to_tutor") String msg, @Part("student_level") int level,
                                 @Part("experience_length") int exl);

}
