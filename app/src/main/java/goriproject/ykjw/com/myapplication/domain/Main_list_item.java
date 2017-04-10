package goriproject.ykjw.com.myapplication.domain;

import java.util.List;

/**
 * Created by Younkyu on 2017-04-06.
 */

public class Main_list_item
{
    private String created_date;

    private String cover_image;

    private String hours_per_class;

    private String review_count;

    private String is_soldout;

    private String price_per_hour;

    private String title;

    private String number_of_class;

    private String category_name;

    private List<String> locations;

    private Tutor tutor;

    private String type_name;

    private String pk;

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
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

    public String getNumber_of_class ()
    {
        return number_of_class;
    }

    public void setNumber_of_class (String number_of_class)
    {
        this.number_of_class = number_of_class;
    }

    public String getCategory_name ()
    {
        return category_name;
    }

    public void setCategory_name (String category_name)
    {
        this.category_name = category_name;
    }

    public List<String> getLocations ()
    {
        return locations;
    }

    public void setLocations (List<String> locations)
    {
        this.locations = locations;
    }

    public Tutor getTutor ()
    {
        return tutor;
    }

    public void setTutor (Tutor tutor)
    {
        this.tutor = tutor;
    }

    public String getType_name ()
    {
        return type_name;
    }

    public void setType_name (String type_name)
    {
        this.type_name = type_name;
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
        return "ClassPojo [created_date = "+created_date+", cover_image = "+cover_image+", hours_per_class = "+hours_per_class+", review_count = "+review_count+", is_soldout = "+is_soldout+", price_per_hour = "+price_per_hour+", title = "+title+", number_of_class = "+number_of_class+", category_name = "+category_name+", locations = "+locations+", tutor = "+tutor+", type_name = "+type_name+", pk = "+pk+"]";
    }
}