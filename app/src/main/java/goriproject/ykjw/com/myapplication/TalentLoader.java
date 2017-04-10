package goriproject.ykjw.com.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-03.
 */

public class TalentLoader {

    public static List<Talent> talent_datas = new ArrayList<>();

    public static void loadData() {
        //hj
        Talent Talent1 = new Talent();
        Talent1.addTalent_location("고려대");
        Talent1.addTalent_location("잠실");
        Talent1.setTalent_category("외국어");
        Talent1.setTutor_name("김지홍");
        Talent1.setTalent_name("외국어사용설명서");
        Talent1.setTalent__rating(10);
        Talent1.setTalent_id(1);
        Talent1.addTalent_time("10시-12시");
        Talent1.addTalent_time("15시-17시");
        Talent1.addTalent_day("월");
        Talent1.addTalent_day("화");
        Talent1.addTalent_day("수");
        Talent1.addTalent_day("목");
        Talent1.setTalent_place("스터디룸");
        Talent1.setTalent_plusprice("없음");
        Talent1.setTalent_price("30,000원");
        Talent1.setTimeperlesson("2시간/회");
        Talent1.setMaxman("1~4명");
        Talent1.setNumoftuty(150);
        talent_datas.add(Talent1);

        Talent Talent2 = new Talent();
        Talent2.addTalent_location("연세대");
        Talent2.addTalent_location("신촌");
        Talent2.setTalent_category("컴퓨터");
        Talent2.setTutor_name("구영재");
        Talent2.setTalent_name("컴퓨터사용설명서");
        Talent2.setTalent__rating(30);
        Talent2.setTalent_id(2);
        Talent2.addTalent_time("12시-14시");
        Talent2.addTalent_time("15시-17시");
        Talent2.addTalent_day("월");
        Talent2.addTalent_day("화");
        Talent2.addTalent_day("수");
        Talent2.addTalent_day("금");
        Talent2.setTalent_place("카페");
        Talent2.setTalent_plusprice("없음");
        Talent2.setTalent_price("20,000원");
        Talent2.setTimeperlesson("3시간/회");
        Talent2.setMaxman("1~3명");
        Talent2.setNumoftuty(150);
        talent_datas.add(Talent2);

        Talent Talent3 = new Talent();
        Talent3.addTalent_location("서울대");
        Talent3.addTalent_location("강남");
        Talent3.setTalent_category("헬스/뷰티");
        Talent3.setTutor_name("장한솔");
        Talent3.setTalent_name("필라테스사용설명서");
        Talent3.setTalent__rating(50);
        Talent3.setTalent_id(3);
        Talent3.addTalent_time("12시-14시");
        Talent3.addTalent_time("19시-20시");
        Talent3.addTalent_time("20시-22시");
        Talent3.addTalent_day("화");
        Talent3.addTalent_day("수");
        Talent3.addTalent_day("토");
        Talent3.addTalent_day("일");
        Talent3.setTalent_place("스터디룸");
        Talent3.setTalent_plusprice("스터디룸예약비");
        Talent3.setTalent_price("30,000원");
        Talent3.setTimeperlesson("2시간/회");
        Talent3.setMaxman("1~4명");
        Talent3.setNumoftuty(20);
        talent_datas.add(Talent3);

        Talent Talent4 = new Talent();
        Talent4.addTalent_location("홍익대");
        Talent4.addTalent_location("사당");
        Talent4.setTalent_category("외국어");
        Talent4.setTutor_name("강선미");
        Talent4.setTalent_name("태국어똠양꿍");
        Talent4.setTalent__rating(70);
        Talent4.setTalent_id(4);
        Talent4.addTalent_time("12시-14시");
        Talent4.addTalent_time("20시-22시");
        Talent4.addTalent_day("화");
        Talent4.addTalent_day("수");
        Talent4.addTalent_day("토");
        Talent4.addTalent_day("일");
        Talent4.setTalent_place("스터디룸");
        Talent4.setTalent_plusprice("스터디룸예약비");
        Talent4.setTalent_price("30,000원");
        Talent4.setTimeperlesson("3시간/회");
        Talent4.setMaxman("1~2명");
        Talent4.setNumoftuty(50);
        talent_datas.add(Talent4);

        Talent Talent5 = new Talent();
        Talent5.addTalent_location("홍익대");
        Talent5.addTalent_location("잠실");
        Talent5.setTalent_category("컴퓨터");
        Talent5.setTutor_name("박지언");
        Talent5.setTalent_name("C언어사용설명서");
        Talent5.setTalent__rating(80);
        Talent5.setTalent_id(5);
        Talent5.addTalent_time("12시-14시");
        Talent5.addTalent_time("20시-22시");
        Talent5.addTalent_time("22시-24시");
        Talent5.addTalent_day("월");
        Talent5.addTalent_day("수");
        Talent5.addTalent_day("토");
        Talent5.addTalent_day("일");
        Talent5.setTalent_place("스터디룸");
        Talent5.setTalent_plusprice("스터디룸예약비");
        Talent5.setTalent_price("30,000원");
        Talent5.setTimeperlesson("2시간/회");
        Talent5.setMaxman("1~4명");
        Talent5.setNumoftuty(110);
        talent_datas.add(Talent5);

        Talent Talent6 = new Talent();
        Talent6.addTalent_location("서울대");
        Talent6.addTalent_location("사당");
        Talent6.setTalent_category("헬스/뷰티");
        Talent6.setTutor_name("김다영");
        Talent6.setTalent_name("스쿠버다이빙");
        Talent6.setTalent__rating(10);
        Talent6.setTalent_id(6);
        Talent6.addTalent_time("20시-22시");
        Talent6.addTalent_time("22시-24시");
        Talent6.addTalent_day("월");
        Talent6.addTalent_day("수");
        Talent6.addTalent_day("토");
        Talent6.setTalent_place("교내카페");
        Talent6.setTalent_plusprice("커피값");
        Talent6.setTalent_price("60,000원");
        Talent6.setTimeperlesson("2시간/회");
        Talent6.setMaxman("1~4명");
        Talent6.setNumoftuty(250);
        talent_datas.add(Talent6);

        Talent Talent7 = new Talent();
        Talent7.addTalent_location("연세대");
        Talent7.addTalent_location("강남");
        Talent7.setTalent_category("음악/미술");
        Talent7.setTutor_name("전주은");
        Talent7.setTalent_name("단소로 발바닥때리기");
        Talent7.setTalent__rating(90);
        Talent7.setTalent_id(7);
        Talent7.addTalent_time("10시-22시");
        Talent7.addTalent_time("14시-18시");
        Talent7.addTalent_time("22시-24시");
        Talent7.addTalent_day("월");//
        Talent7.addTalent_day("수");
        Talent7.addTalent_day("목");
        Talent7.addTalent_day("토");
        Talent7.setTalent_place("교내카페");
        Talent7.setTalent_plusprice("커피값");
        Talent7.setTalent_price("10,000원");
        Talent7.setTimeperlesson("2시간/회");
        Talent7.setMaxman("1~4명");
        Talent7.setNumoftuty(0);
        talent_datas.add(Talent7);

        Talent Talent8 = new Talent();
        Talent8.addTalent_location("고려대");
        Talent8.addTalent_location("사당");
        Talent8.setTalent_category("외국어");
        Talent8.setTutor_name("장재광");
        Talent8.setTalent_name("스페인어초급부터 완벽하게");
        Talent8.setTalent__rating(100);
        Talent8.setTalent_id(8);
        Talent8.addTalent_time("10시-12시");
        Talent8.addTalent_time("14시-18시");
        Talent8.addTalent_time("22시-24시");
        Talent8.addTalent_day("월");
        Talent8.addTalent_day("수");
        Talent8.addTalent_day("목");
        Talent8.setTalent_place("교내카페");
        Talent8.setTalent_plusprice("커피값");
        Talent8.setTalent_price("15,000원");
        Talent8.setTimeperlesson("2시간/회");
        Talent8.setMaxman("1~4명");
        Talent8.setNumoftuty(150);
        talent_datas.add(Talent8);


        Talent Talent9 = new Talent();
        Talent9.addTalent_location("서울대");
        Talent9.addTalent_location("신촌");
        Talent9.setTalent_category("컴퓨터");
        Talent9.setTutor_name("김태호");
        Talent9.setTalent_name("나사실컴터못함");
        Talent9.setTalent__rating(100);
        Talent9.setTalent_id(9);
        Talent9.addTalent_time("10시-16시");
        Talent9.addTalent_time("14시-18시");
        Talent9.addTalent_day("수");
        Talent9.addTalent_day("목");
        Talent9.setTalent_place("교내카페");
        Talent9.setTalent_plusprice("커피값");
        Talent9.setTalent_price("30,000원");
        Talent9.setTimeperlesson("5시간/회");
        Talent9.setMaxman("1~2명");
        Talent9.setNumoftuty(150);
        talent_datas.add(Talent9);

        Talent Talent10 = new Talent();
        Talent10.addTalent_location("연세대");
        Talent10.addTalent_location("신촌");
        Talent10.setTalent_category("헬스/뷰티");
        Talent10.setTutor_name("김환희");
        Talent10.setTalent_name("나는예쁘니>_<");
        Talent10.setTalent__rating(95);
        Talent10.setTalent_id(10);
        Talent10.addTalent_time("10시-12시");
        Talent10.addTalent_time("14시-18시");
        Talent10.addTalent_day("월");
        Talent10.addTalent_day("수");
        Talent10.addTalent_day("목");
        Talent10.addTalent_day("금");
        Talent10.addTalent_day("토");
        Talent10.addTalent_day("일");
        Talent10.setTalent_place("교내카페");
        Talent10.setTalent_plusprice("커피값");
        Talent10.setTalent_price("30,000원");
        Talent10.setTimeperlesson("1시간/회");
        Talent10.setMaxman("1~2명");
        Talent10.setNumoftuty(150);
        talent_datas.add(Talent10);

    }

}
