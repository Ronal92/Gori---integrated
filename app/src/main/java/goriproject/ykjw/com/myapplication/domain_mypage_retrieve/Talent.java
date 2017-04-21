package goriproject.ykjw.com.myapplication.domain_mypage_retrieve;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class Talent implements Serializable {

    private String created_date;

    private String cover_image;

    private String is_verified;

    private String review_count;

    private String is_soldout;

    private String[] regions;

    private String type;

    private String price_per_hour;

    private String title;

    private String category;

    private String average_rate;

    private String wishlist_count;

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

    public String getCover_image ()
    {
        return cover_image;
    }

    public void setCover_image (String cover_image)
    {
        this.cover_image = cover_image;
    }

    public String getIs_verified ()
    {
        return is_verified;
    }

    public void setIs_verified (String is_verified)
    {
        this.is_verified = is_verified;
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

    public String[] getRegions ()
    {
        return regions;
    }

    public void setRegions (String[] regions)
    {
        this.regions = regions;
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

    public String getAverage_rate ()
    {
        return average_rate;
    }

    public void setAverage_rate (String average_rate)
    {
        this.average_rate = average_rate;
    }

    public String getWishlist_count ()
    {
        return wishlist_count;
    }

    public void setWishlist_count (String wishlist_count)
    {
        this.wishlist_count = wishlist_count;
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
        return "ClassPojo [created_date = "+created_date+", cover_image = "+cover_image+", is_verified = "+is_verified+", review_count = "+review_count+", is_soldout = "+is_soldout+", regions = "+regions+", type = "+type+", price_per_hour = "+price_per_hour+", title = "+title+", category = "+category+", average_rate = "+average_rate+", wishlist_count = "+wishlist_count+", registration_count = "+registration_count+", pk = "+pk+"]";
    }
}
