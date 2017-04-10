package goriproject.ykjw.com.myapplication;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.Interfaces.Main_List_Interface;
import goriproject.ykjw.com.myapplication.domain.Main_list_item;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Younkyu on 2017-03-27.
 */

public class TutorLoader {

    public static List<tutor> datas = new ArrayList<>();
    public static List<tutor> datasRealy = new ArrayList<>();
    public static List<Main_list_item> datasReal = new ArrayList<>();

    public static void loadRealData() {

        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. 사용할 인터페이스를 설정한다.
        Main_List_Interface service = retrofit.create(Main_List_Interface.class);

        // 3. 데이터를 가져온다.
        Call<List<Main_list_item>> result = service.getList();

        // 4. 데이터를 가져오는 부분은 네트웍을 통해서 오기 때문에 비동기 처리된다.
        result.enqueue(new Callback<List<Main_list_item>>() {
            @Override
            public void onResponse(Call<List<Main_list_item>> call, Response<List<Main_list_item>> response) {
                if(response.isSuccessful()) {
                    Log.e("Retrofit---------------",response.body().toString());
                    List<Main_list_item> data = response.body();
                    Log.e("Retrofit---------------", String.valueOf(data.size()));

                    for(Main_list_item tt : data) {
                        Log.e("Retrofit---------------", tt.getCategory_name());
                        tutor tut = new tutor();
                        tut.setTutor_name(tt.getTutor().getName());
                        tut.setClass_name(tt.getTitle());
                        tut.setTutor_rating(80);
                        tut.setCampus("고려대");
                        tut.setLocation(tt.getLocations().get(0));
                        tut.setCategory(tt.getCategory_name());
                        tut.setTutor_id(Integer.parseInt(tt.getPk()));
                        datas.add(tut);
                    }

//                    List<Main_list_item> data = response.body(); // 원래 반환값이 jsonString이 Data 클래스로 변환되어 리턴된다.
//                    Log.e("Retrofit---------------",response.body().toString());
//                    for(Main_list_item tt : data) {
//                        tutor tut = new tutor();
//                        tut.setTutor_name(tt.getTutor().getName());
//                        tut.setClass_name(tt.getTitle());
//                        tut.setTutor_rating(0);
//                        tut.setLocation("신촌");
//                        tut.setCategory(tt.getCategory_name());
//                        tut.setTutor_id(Integer.parseInt(tt.getPk()));
//                        datasRealy.add(tut);
//                    }

                } else {
                    Log.e("Retrofit",response.message()); // 정상적이지 않을 경우 message에 오류내용이 담겨 온다.
                }
            }

            @Override
            public void onFailure(Call<List<Main_list_item>> call, Throwable t) {

            }
        });

    }




    public static void loadData() {

//        tutor tutor1 = new tutor();
//        tutor1.setCampus("고려대");
//        tutor1.setCategory("외국어");
//        tutor1.setLocation("잠실");
//        tutor1.setTutor_name("김지홍");
//        tutor1.setClass_name("외국어사용설명서");
//        tutor1.setTutor_rating(10);
//        tutor1.setTutor_id(1);
//        datas.add(tutor1);
//
//        tutor tutor2 = new tutor();
//        tutor2.setCampus("연세대");
//        tutor2.setCategory("컴퓨터");
//        tutor2.setLocation("신촌");
//        tutor2.setTutor_name("구영재");
//        tutor2.setClass_name("컴퓨터사용설명서");
//        tutor2.setTutor_rating(30);
//        tutor2.setTutor_id(2);
//        datas.add(tutor2);
//
//        tutor tutor3 = new tutor();
//        tutor3.setCampus("서울대");
//        tutor3.setCategory("헬스/뷰티");
//        tutor3.setLocation("강남");
//        tutor3.setTutor_name("장한솔");
//        tutor3.setClass_name("필라테스사용설명서");
//        tutor3.setTutor_rating(50);
//        tutor3.setTutor_id(3);
//        datas.add(tutor3);
//
//        tutor tutor4 = new tutor();
//        tutor4.setCampus("홍익대");
//        tutor4.setCategory("외국어");
//        tutor4.setLocation("사당");
//        tutor4.setTutor_name("강선미");
//        tutor4.setClass_name("태국어똠양꿍");
//        tutor4.setTutor_rating(70);
//        tutor4.setTutor_id(4);
//        datas.add(tutor4);
//
//        tutor tutor5 = new tutor();
//        tutor5.setCampus("홍익대");
//        tutor5.setCategory("컴퓨터");
//        tutor5.setLocation("잠실");
//        tutor5.setTutor_name("박지언");
//        tutor5.setClass_name("C언어사용설명서");
//        tutor5.setTutor_rating(80);
//        tutor5.setTutor_id(5);
//        datas.add(tutor5);
//
//
//        tutor tutor6 = new tutor();
//        tutor6.setCampus("서울대");
//        tutor6.setCategory("헬스/뷰티");
//        tutor6.setLocation("사당");
//        tutor6.setTutor_name("김다영");
//        tutor6.setClass_name("스쿠버다이빙지랄");
//        tutor6.setTutor_rating(10);
//        tutor6.setTutor_id(6);
//        datas.add(tutor6);
//
//        tutor tutor7 = new tutor();
//        tutor7.setCampus("연세대");
//        tutor7.setCategory("음악/미술");
//        tutor7.setLocation("강남");
//        tutor7.setTutor_name("전주은");
//        tutor7.setClass_name("단소로 발바닥때리기");
//        tutor7.setTutor_rating(90);
//        tutor7.setTutor_id(7);
//        datas.add(tutor7);
//
//        tutor tutor8 = new tutor();
//        tutor8.setCampus("고려대");
//        tutor8.setCategory("외국어");
//        tutor8.setLocation("사당");
//        tutor8.setTutor_name("장재광");
//        tutor8.setClass_name("스페인어초급부터 완벽하게");
//        tutor8.setTutor_rating(100);
//        tutor8.setTutor_id(8);
//        datas.add(tutor8);
//
//        tutor tutor9 = new tutor();
//        tutor9.setCampus("서울대");
//        tutor9.setCategory("컴퓨터");
//        tutor9.setLocation("신촌");
//        tutor9.setTutor_name("김태호");
//        tutor9.setClass_name("나사실컴터못함");
//        tutor9.setTutor_rating(100);
//        tutor9.setTutor_id(9);
//        datas.add(tutor9);
//
//        tutor tutor10 = new tutor();
//        tutor10.setCampus("연세대");
//        tutor10.setCategory("헬스/뷰티");
//        tutor10.setLocation("신촌");
//        tutor10.setTutor_name("김환희");
//        tutor10.setClass_name("나는예쁘니>~<");
//        tutor10.setTutor_rating(95);
//        tutor10.setTutor_id(10);
//        datas.add(tutor10);

        // 1. 레트로핏을 생성하고
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mozzi.co.kr/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. 사용할 인터페이스를 설정한다.
        Main_List_Interface service = retrofit.create(Main_List_Interface.class);

        // 3. 데이터를 가져온다.
        Call<List<Main_list_item>> result = service.getList();

//        try {
//            List<Main_list_item> data = new ArrayList<>();
//            data.addAll(result.execute().body());
//            for(Main_list_item tt : data) {
//                Log.e("Retrofit---------------", tt.getCategory_name());
//                tutor tut = new tutor();
//                tut.setTutor_name(tt.getTutor().getName());
//                tut.setClass_name(tt.getTitle());
//                tut.setTutor_rating(80);
//                tut.setCampus("고려대");
//                //
//                if(tt.getLocations().size() !=0 ) {
//                    tut.setLocation(tt.getLocations().get(0));
//                } else {
//                    tut.setLocation("신촌");
//                }
//                tut.setCategory(tt.getCategory_name());
//                tut.setTutor_id(Integer.parseInt(tt.getPk()));
//                tut.setImgUrl(tt.getCover_image());
//                tut.setProfileurl(tt.getTutor().getProfile_image());
//                datas.add(tut);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 4. 데이터를 가져오는 부분은 네트웍을 통해서 오기 때문에 비동기 처리된다.
        result.enqueue(new Callback<List<Main_list_item>>() {
            @Override
            public void onResponse(Call<List<Main_list_item>> call, Response<List<Main_list_item>> response) {
                if(response.isSuccessful()) {
                    Log.e("Retrofit---------------",response.body().toString());
                    List<Main_list_item> data = response.body();
                    Log.e("Retrofit---------------", String.valueOf(data.size()));

                    for(Main_list_item tt : data) {
                        Log.e("Retrofit---------------", tt.getCategory_name());
                        tutor tut = new tutor();
                        tut.setTutor_name(tt.getTutor().getName());
                        tut.setClass_name(tt.getTitle());
                        tut.setTutor_rating(80);
                        tut.setCampus("고려대");
                        //
                        if(tt.getLocations().size() !=0 ) {
                            tut.setLocation(tt.getLocations().get(0));
                        } else {
                            tut.setLocation("신촌");
                        }
                        tut.setCategory(tt.getCategory_name());
                        tut.setTutor_id(Integer.parseInt(tt.getPk()));
                        tut.setImgUrl(tt.getCover_image());
                        tut.setProfileurl(tt.getTutor().getProfile_image());
                        datas.add(tut);
                    }

                    MainActivity.datas2.addAll(datas);
                    Log.e("Retrofit---------------", "adapternoti");
                    MainActivity.rcanoti();


//                    List<Main_list_item> data = response.body(); // 원래 반환값이 jsonString이 Data 클래스로 변환되어 리턴된다.
//                    Log.e("Retrofit---------------",response.body().toString());
//                    for(Main_list_item tt : data) {
//                        tutor tut = new tutor();
//                        tut.setTutor_name(tt.getTutor().getName());
//                        tut.setClass_name(tt.getTitle());
//                        tut.setTutor_rating(0);
//                        tut.setLocation("신촌");
//                        tut.setCategory(tt.getCategory_name());
//                        tut.setTutor_id(Integer.parseInt(tt.getPk()));
//                        datasRealy.add(tut);
//                    }

                } else {
                    Log.e("Retrofit",response.message()); // 정상적이지 않을 경우 message에 오류내용이 담겨 온다.
                }
            }

            @Override
            public void onFailure(Call<List<Main_list_item>> call, Throwable t) {

            }
        });

    }


}
