package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by JINWOO on 2017-04-20.
 */

public interface User_Detail_Interface {

    @GET("member/profile/user/")
    Call<UserDetail> getUserRetrieve(@Header("Authorization") String token);
}
