package goriproject.ykjw.com.myapplication.Interfaces;

import goriproject.ykjw.com.myapplication.domain_User_detail_all.UserDetail;
import goriproject.ykjw.com.myapplication.domain_wishlist.WishList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by JINWOO on 2017-04-22.
 */

public interface WishList_Toggle_Interface {

    @GET("talent/{talent_pk}/wish-list/toggle/")
    Call<WishList> getWishList(@Header("Authorization") String token, @Path("talent_pk") String talent_pk);
}
