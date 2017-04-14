package goriproject.ykjw.com.myapplication.Interfaces;

import java.util.List;

import goriproject.ykjw.com.myapplication.domain.List_All;
import goriproject.ykjw.com.myapplication.domain.Results;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Younkyu on 2017-04-06.
 */

public interface Main_List_Interface {

    @GET("talent/list")
    Call<List_All> getList();
}
