package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-06.
 */

public class Results implements Serializable
{


    private String hours_per_class;

    private String is_verified;

    private String review_count;

    private String tutor_info;

    private String is_soldout;

    private List<Qna> qna;

    private String type;

    private String price_per_hour;

    private String location_message;

    public String getHours_per_class() {
        return hours_per_class;
    }

    public void setHours_per_class(String hours_per_class) {
        this.hours_per_class = hours_per_class;
    }

    public String getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(String is_verified) {
        this.is_verified = is_verified;
    }

    public String getReview_count() {
        return review_count;
    }

    public void setReview_count(String review_count) {
        this.review_count = review_count;
    }

    public String getTutor_info() {
        return tutor_info;
    }

    public void setTutor_info(String tutor_info) {
        this.tutor_info = tutor_info;
    }

    public String getIs_soldout() {
        return is_soldout;
    }

    public void setIs_soldout(String is_soldout) {
        this.is_soldout = is_soldout;
    }

    public List<Qna> getQna() {
        return qna;
    }

    public void setQna(List<Qna> qna) {
        this.qna = qna;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice_per_hour() {
        return price_per_hour;
    }

    public void setPrice_per_hour(String price_per_hour) {
        this.price_per_hour = price_per_hour;
    }

    public String getLocation_message() {
        return location_message;
    }

    public void setLocation_message(String location_message) {
        this.location_message = location_message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Locations[] getLocations() {
        return locations;
    }

    public void setLocations(Locations[] locations) {
        this.locations = locations;
    }

    public String getTutor_message() {
        return tutor_message;
    }

    public void setTutor_message(String tutor_message) {
        this.tutor_message = tutor_message;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public String getClass_info() {
        return class_info;
    }

    public void setClass_info(String class_info) {
        this.class_info = class_info;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getVideo2() {
        return video2;
    }

    public void setVideo2(String video2) {
        this.video2 = video2;
    }

    public String getVideo1() {
        return video1;
    }

    public void setVideo1(String video1) {
        this.video1 = video1;
    }

    public String getMin_number_student() {
        return min_number_student;
    }

    public void setMin_number_student(String min_number_student) {
        this.min_number_student = min_number_student;
    }

    public String getMax_number_student() {
        return max_number_student;
    }

    public void setMax_number_student(String max_number_student) {
        this.max_number_student = max_number_student;
    }

    public List<Class_images> getClass_images() {
        return class_images;
    }

    public void setClass_images(List<Class_images> class_images) {
        this.class_images = class_images;
    }
    private String average_rate;

    public String getAverage_rate() {
        return average_rate;
    }

    public void setAverage_rate(String average_rate) {
        this.average_rate = average_rate;
    }

    public String getNumber_of_class() {
        return number_of_class;
    }

    public void setNumber_of_class(String number_of_class) {
        this.number_of_class = number_of_class;
    }

    public List<Curriculums> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(List<Curriculums> curriculums) {
        this.curriculums = curriculums;
    }

    public String getRegistration_count() {
        return registration_count;
    }

    public void setRegistration_count(String registration_count) {
        this.registration_count = registration_count;
    }

    public Average_rates getAverage_rates() {
        return average_rates;
    }

    public void setAverage_rates(Average_rates average_rates) {
        this.average_rates = average_rates;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String title;

    private Locations[] locations;

    private String tutor_message;

    private Tutor tutor;

    private List<Reviews> reviews;

    private String class_info;

    private String cover_image;

    private String video2;

    private String video1;

    private String min_number_student;

    private String max_number_student;

    private List<Class_images> class_images;


    private String number_of_class;

    private List<Curriculums> curriculums;

    private String registration_count;

    private Average_rates average_rates;

    private String user;


    private List<String> regions;

    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    private String region;


    private String extra_fee_amount;

    private List<String> time;

    private String talent_pk;

    private String specific_location;

    private String day;

    private String location_info;

    private String extra_fee;

    private String pk;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getExtra_fee_amount ()
    {
        return extra_fee_amount;
    }

    public void setExtra_fee_amount (String extra_fee_amount)
    {
        this.extra_fee_amount = extra_fee_amount;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getTalent_pk ()
    {
        return talent_pk;
    }

    public void setTalent_pk (String talent_pk)
    {
        this.talent_pk = talent_pk;
    }

    public String getSpecific_location ()
    {
        return specific_location;
    }

    public void setSpecific_location (String specific_location)
    {
        this.specific_location = specific_location;
    }

    public String getDay ()
    {
        return day;
    }

    public void setDay (String day)
    {
        this.day = day;
    }

    public String getLocation_info ()
    {
        return location_info;
    }

    public void setLocation_info (String location_info)
    {
        this.location_info = location_info;
    }

    public String getExtra_fee ()
    {
        return extra_fee;
    }

    public void setExtra_fee (String extra_fee)
    {
        this.extra_fee = extra_fee;
    }

    public String getPk ()
    {
        return pk;
    }

    public void setPk (String pk)
    {
        this.pk = pk;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", extra_fee_amount = "+extra_fee_amount+", time = "+time+", talent_pk = "+talent_pk+", specific_location = "+specific_location+", day = "+day+", location_info = "+location_info+", extra_fee = "+extra_fee+", pk = "+pk+"]";
    }
}