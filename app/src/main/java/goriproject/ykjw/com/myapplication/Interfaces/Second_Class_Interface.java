package goriproject.ykjw.com.myapplication.Interfaces;


import java.util.List;

import goriproject.ykjw.com.myapplication.domain_sec_view.Class;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by JINWOO on 2017-04-07.
 */

public interface Second_Class_Interface {
    //@Headers({"Accept: application/json"})
    @GET("talent/list/")
    Call<List<Class>> getSecondViewData();

}
