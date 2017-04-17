package goriproject.ykjw.com.myapplication;

import android.util.Log;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import goriproject.ykjw.com.myapplication.Interfaces.Main_List_Interface;
import goriproject.ykjw.com.myapplication.domain.List_All;
import goriproject.ykjw.com.myapplication.domain.Results;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static goriproject.ykjw.com.myapplication.Statics.datas;

/**
 * Created by Younkyu on 2017-03-27.
 */

public class TutorLoader {




    public static void loadData() {


        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. 사용할 인터페이스를 설정한다.
        Main_List_Interface service = retrofit.create(Main_List_Interface.class);

        // 3. 데이터를 가져온다.
        Call<List_All> result = service.getList();

        try {
            List_All list_all = new List_All();
            list_all = result.execute().body();

            datas = list_all.getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Statics.maxsize = datas.size();



        if(MainActivity.datas2.size() == 0) {
            MainActivity.datas2.addAll(datas);
        }


//        // 4. 데이터를 가져오는 부분은 네트웍을 통해서 오기 때문에 비동기 처리된다.
//        result.enqueue(new Callback<List<Results>>() {
//            @Override
//            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
//                if(response.isSuccessful()) {
//                    Log.e("Retrofit---------------",response.body().toString());
//                    datas = response.body();
//                    Log.e("Retrofit---------------", String.valueOf(datas.size()));
//
//                    //sortTop(datas);
//
//                    Statics.maxsize = datas.size();
//
//                    if(MainActivity.datas2.size() == 0) {
//                        MainActivity.datas2.addAll(datas);
//                    }
//
//                    Log.e("Retrofit---------------", "adapternoti");
//                    MainActivity.rcanoti();
//
//                } else {
//                    Log.e("Retrofit",response.message()); // 정상적이지 않을 경우 message에 오류내용이 담겨 온다.
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Results>> call, Throwable t) {
//
//            }
//        });

    }

    // 단순한 String,int리스트가 아닌 객체에 대한 정렬을 해야할 경우에 사용
    public static void sortTop(List<Results> datas) {
        Collections.sort(datas, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //사용하려는 객체로 파싱해줌
                Results no1 = (Results)o1;
                Results no2 = (Results)o2;

                //-1과 1의 위치를 조정하면 오름차순/내림차순을 조절할 수 있다.
                if(Integer.parseInt(no1.getAverage_rate().trim()) >Integer.parseInt(no2.getAverage_rate().trim())) {
                    return -1;
                } else if (Integer.parseInt(no1.getAverage_rate().trim()) == Integer.parseInt(no2.getAverage_rate().trim())){
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }


}
