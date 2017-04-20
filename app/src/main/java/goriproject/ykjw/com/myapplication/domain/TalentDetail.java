package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class TalentDetail implements Serializable
{
    private String cover_image;

    public Average_rates getAverage_rates() {
        return average_rates;
    }

    public void setAverage_rates(Average_rates average_rates) {
        this.average_rates = average_rates;
    }

    private Average_rates average_rates;

    private String class_info;

    private String hours_per_class;

    private String review_count;

    private String is_soldout;

    private String tutor_info;

    private String type;

    private String video2;

    private String video1;

    private String price_per_hour;

    private List<Class_images> class_images;

    private String number_of_class;

    private String title;

    private String category;

    private String average_rate;

    private List<Locations> locations;

    private List<Curriculums> curriculums;

    public String getRegistration_count() {
        return registration_count;
    }

    public void setRegistration_count(String registration_count) {
        this.registration_count = registration_count;
    }

    private String registration_count;

    private Tutor tutor;

    private String pk;

    public String getCover_image ()
    {
        return cover_image;
    }

    public void setCover_image (String cover_image)
    {
        this.cover_image = cover_image;
    }

    public String getClass_info ()
    {
        return class_info;
    }

    public void setClass_info (String class_info)
    {
        this.class_info = class_info;
    }

    public String getHours_per_class ()
    {
        return hours_per_class;
    }

    public void setHours_per_class (String hours_per_class)
    {
        this.hours_per_class = hours_per_class;
    }

    public String getReview_count ()
    {
        return review_count;
    }

    public void setReview_count (String review_count)
    {
        this.review_count = review_count;
    }

    public String getIs_soldout ()
    {
        return is_soldout;
    }

    public void setIs_soldout (String is_soldout)
    {
        this.is_soldout = is_soldout;
    }

    public String getTutor_info ()
    {
        return tutor_info;
    }

    public void setTutor_info (String tutor_info)
    {
        this.tutor_info = tutor_info;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getVideo2 ()
    {
        return video2;
    }

    public void setVideo2 (String video2)
    {
        this.video2 = video2;
    }

    public String getVideo1 ()
    {
        return video1;
    }

    public void setVideo1 (String video1)
    {
        this.video1 = video1;
    }

    public String getPrice_per_hour ()
    {
        return price_per_hour;
    }

    public void setPrice_per_hour (String price_per_hour)
    {
        this.price_per_hour = price_per_hour;
    }


    public String getNumber_of_class ()
    {
        return number_of_class;
    }

    public void setNumber_of_class (String number_of_class)
    {
        this.number_of_class = number_of_class;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getAverage_rate ()
    {
        return average_rate;
    }

    public void setAverage_rate (String average_rate)
    {
        this.average_rate = average_rate;
    }



    public Tutor getTutor ()
    {
        return tutor;
    }

    public void setTutor (Tutor tutor)
    {
        this.tutor = tutor;
    }

    public String getPk ()
    {
        return pk;
    }

    public void setPk (String pk)
    {
        this.pk = pk;
    }

    public List<Class_images> getClass_images() {
        return class_images;
    }

    public void setClass_images(List<Class_images> class_images) {
        this.class_images = class_images;
    }

    public List<Locations> getLocations() {
        return locations;
    }

    public void setLocations(List<Locations> locations) {
        this.locations = locations;
    }

    public List<Curriculums> getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(List<Curriculums> curriculums) {
        this.curriculums = curriculums;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cover_image = "+cover_image+", class_info = "+class_info+", hours_per_class = "+hours_per_class+", review_count = "+review_count+", is_soldout = "+is_soldout+", tutor_info = "+tutor_info+", type = "+type+", video2 = "+video2+", video1 = "+video1+", price_per_hour = "+price_per_hour+", class_images = "+class_images+", number_of_class = "+number_of_class+", title = "+title+", category = "+category+", average_rate = "+average_rate+", locations = "+locations+", curriculums = "+curriculums+", tutor = "+tutor+", pk = "+pk+"]";
    }
}