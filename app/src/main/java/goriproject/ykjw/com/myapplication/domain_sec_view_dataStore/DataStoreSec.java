package goriproject.ykjw.com.myapplication.domain_sec_view_dataStore;

import java.util.ArrayList;
import java.util.List;

import goriproject.ykjw.com.myapplication.domain_sec_view.Class;

/**
 *  API 통해 받아온 데이터를 SingleTon으로 저장한다.
 */

public class DataStoreSec {

    private static DataStoreSec singleInstance = null;
    private List<Class> secondViewDatas;

    private DataStoreSec(){
        secondViewDatas = new ArrayList<>();
    }

    public static DataStoreSec getSingleInstance(){
        if(singleInstance == null){
            singleInstance = new DataStoreSec();
        }
        return singleInstance;
    }

    public List<Class> getSecondViewDatas() {
        return secondViewDatas;
    }

    public void setSecondViewDatas(List<Class> secondViewDatas) {
        this.secondViewDatas = secondViewDatas;
    }

    public void addSecondViewDatas(Class data){
        secondViewDatas.add(data);
    }
}
