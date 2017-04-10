package goriproject.ykjw.com.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-03.
 */

public class Talent {


    String tutor_name;
    String talent_name;
    int talent_id;
    String talent_category;
    int talent__rating;
    List<String> talent_time = new ArrayList<>();
    List<String> talent_location = new ArrayList<>();
    List<String> talent_day = new ArrayList<>();
    String talent_price;
    String talent_place;
    String talent_plusprice;
    String timeperlesson;
    String maxman;
    int numoftuty;

    public int getNumoftuty() {
        return numoftuty;
    }

    public void setNumoftuty(int numoftuty) {
        this.numoftuty = numoftuty;
    }

    public String getTimeperlesson() {
        return timeperlesson;
    }

    public void setTimeperlesson(String timeperlesson) {
        this.timeperlesson = timeperlesson;
    }

    public String getMaxman() {
        return maxman;
    }

    public void setMaxman(String maxman) {
        this.maxman = maxman;
    }

    public void setTalent_time(List<String> talent_time) {
        this.talent_time = talent_time;
    }

    public void setTalent_location(List<String> talent_location) {
        this.talent_location = talent_location;
    }

    public void setTalent_day(List<String> talent_day) {
        this.talent_day = talent_day;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public String getTalent_name() {
        return talent_name;
    }

    public void setTalent_name(String talent_name) {
        this.talent_name = talent_name;
    }

    public int getTalent_id() {
        return talent_id;
    }

    public void setTalent_id(int talent_id) {
        this.talent_id = talent_id;
    }

    public String getTalent_category() {
        return talent_category;
    }

    public void setTalent_category(String talent_category) {
        this.talent_category = talent_category;
    }

    public int getTalent__rating() {
        return talent__rating;
    }

    public void setTalent__rating(int talent__rating) {
        this.talent__rating = talent__rating;
    }

    public List<String> getTalent_time() {
        return talent_time;
    }

    public void addTalent_time(String time) {
        talent_time.add(time);
    }

    public List<String> getTalent_location() {
        return talent_location;
    }

    public void addTalent_location(String location) {
        talent_location.add(location);
    }

    public List<String> getTalent_day() {
        return talent_day;
    }

    public void addTalent_day(String day) {
        talent_day.add(day);
    }

    public String getTalent_price() {
        return talent_price;
    }

    public void setTalent_price(String talent_price) {
        this.talent_price = talent_price;
    }

    public String getTalent_place() {
        return talent_place;
    }

    public void setTalent_place(String talent_place) {
        this.talent_place = talent_place;
    }

    public String getTalent_plusprice() {
        return talent_plusprice;
    }

    public void setTalent_plusprice(String talent_plusprice) {
        this.talent_plusprice = talent_plusprice;
    }
}
