package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain_mypage_retrieve.MyPage;
import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-20.
 */

public interface Mypage_Detail_Interface {

        @GET("member/my-page/")
        Call<MyPage> getMyPageRetrieve(@Header("Authorization") String token);
}
