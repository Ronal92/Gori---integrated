package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain.review.ReviewRetrieve;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-13.
 */

public interface Review_Detail_Interface {
    @GET("talent/detail/{talent_pk}/review/")
    Call<ReviewRetrieve> getReviewRetrieve(@Path("talent_pk") String talent_pk);
}
