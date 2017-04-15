package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain.Result2;
import goriproject.ykjw.com.myapplication.domain.review.ReviewRetrieve;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-13.
 */

public interface Review_Detail_Interface {
    @GET("talent/detail/{talent_pk}/review/")
    Call<ReviewRetrieve> getReviewRetrieve(@Path("talent_pk") String talent_pk);

    @FormUrlEncoded
    @POST("talent/add/review/")
    Call<String> setReviewRetrieve(@Header("Authorization") String token,
                        @Field("talent_pk") String talent_pk,
                        @Field("curriculum") String curriculum,
                        @Field("readiness") String readiness,
                        @Field("timeliness") String timeliness,
                        @Field("delivery") String delivery,
                        @Field("friendliness") String friendliness,
                        @Field("comment") String comment  );

}
