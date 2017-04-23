package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain_qna_retrieve.QnaDetail;
import goriproject.ykjw.com.myapplication.domain_qna_retrieve.QnaResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-22.
 */

public interface Qna_Detail_Interface {
    @GET("talent/detail/{talent_pk}/qna/")
    Call<QnaDetail> getQnaRetrieve(@Path("talent_pk") String talent_pk);


    @Multipart
    @POST("talent/add/question/")
    Call<QnaResponse> setQnaRetrieve(
            @Header("Authorization") String token,
            @Part("talent_pk") int talent_pk,
            @Part("content") String content
    );

    @DELETE("talent/delete/question/{question_pk}/")
    Call<Void> deleteQna(@Header("Authorization") String token, @Path("question_pk") String question_pk);

}
