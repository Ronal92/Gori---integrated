package goriproject.ykjw.com.myapplication.Interfaces;

import java.util.List;

import goriproject.ykjw.com.myapplication.domain_review_retrieve.ReviewsSecThreeFrag;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-13.
 */

public interface Review_Detail_Interface {
    @GET("talent/detail/{talent_pk}/review/")
    Call<ReviewsSecThreeFrag> getReviewRetrieve(@Path("talent_pk") String talent_pk);


    //    @FormUrlEncoded
//    @Headers("Content-Type:multipart/form-data")
    @Multipart
    @POST("talent/add/review/")
    Call<String> setReviewRetrieve(
            @Header("Authorization") String token,
            @Part("talent_pk") int talent_pk,
            @Part("curriculum") int curriculum,
            @Part("readiness") int readiness,
            @Part("timeliness") int timeliness,
            @Part("delivery") int delivery,
            @Part("friendliness") int friendliness,
            @Part("comment") String comment
    );

    @DELETE("talent/delete/review/{review_pk}/")
    Call<Void> deleteReview(@Header("Authorization") String token, @Path("review_pk") String review_pk);

}