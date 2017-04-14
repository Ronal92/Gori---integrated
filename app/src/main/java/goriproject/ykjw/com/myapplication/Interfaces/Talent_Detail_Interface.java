package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain.TalentDetail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Younkyu on 2017-04-11.
 */

public interface Talent_Detail_Interface {

    @GET("talent/detail-all/{talent_pk}/")
    Call<TalentDetail> getTalentDetail(@Path("talent_pk") String talent_pk);

}
