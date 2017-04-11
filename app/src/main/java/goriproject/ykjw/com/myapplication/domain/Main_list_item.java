package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-06.
 */

public class Main_list_item implements Serializable
{
    private String created_date;

    private String is_school;

    private String cover_image;

    private String hours_per_class;

    private String review_count;

    private String is_soldout;

    private List<String> regions;

    public List<String> getRegions() {
        return regions;
    }

    public void setRegions(List<String> regions) {
        this.regions = regions;
    }

    private String type;

    private String price_per_hour;

    private String title;

    private String category;

    private String number_of_class;

    private String average_rate;

    private Tutor tutor;

    private String registration_count;

    private String pk;

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
    }

    public String getIs_school ()
    {
        return is_school;
    }

    public void setIs_school (String is_school)
    {
        this.is_school = is_school;
    }

    public String getCover_image ()
    {
        return cover_image;
    }

    public void setCover_image (String cover_image)
    {
        this.cover_image = cover_image;
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

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getPrice_per_hour ()
    {
        return price_per_hour;
    }

    public void setPrice_per_hour (String price_per_hour)
    {
        this.price_per_hour = price_per_hour;
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

    public String getNumber_of_class ()
    {
        return number_of_class;
    }

    public void setNumber_of_class (String number_of_class)
    {
        this.number_of_class = number_of_class;
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

    public String getRegistration_count ()
    {
        return registration_count;
    }

    public void setRegistration_count (String registration_count)
    {
        this.registration_count = registration_count;
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
        return "ClassPojo [created_date = "+created_date+", is_school = "+is_school+", cover_image = "+cover_image+", hours_per_class = "+hours_per_class+", review_count = "+review_count+", is_soldout = "+is_soldout+", regions = "+regions+", type = "+type+", price_per_hour = "+price_per_hour+", title = "+title+", category = "+category+", number_of_class = "+number_of_class+", average_rate = "+average_rate+", tutor = "+tutor+", registration_count = "+registration_count+", pk = "+pk+"]";
    }
}