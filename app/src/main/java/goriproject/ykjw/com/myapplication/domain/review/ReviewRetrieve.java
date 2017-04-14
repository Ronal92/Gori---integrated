package goriproject.ykjw.com.myapplication.domain.review;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-13.
 */

public class ReviewRetrieve implements Serializable
{
    private Reviews[] reviews;

    private String category;

    private String title;

    private String review_count;

    private Average_rates average_rates;

    private String type;

    private String pk;

    public Reviews[] getReviews ()
    {
        return reviews;
    }

    public void setReviews (Reviews[] reviews)
    {
        this.reviews = reviews;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getReview_count ()
    {
        return review_count;
    }

    public void setReview_count (String review_count)
    {
        this.review_count = review_count;
    }

    public Average_rates getAverage_rates ()
    {
        return average_rates;
    }

    public void setAverage_rates (Average_rates average_rates)
    {
        this.average_rates = average_rates;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
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
        return "ClassPojo [reviews = "+reviews+", category = "+category+", title = "+title+", review_count = "+review_count+", average_rates = "+average_rates+", type = "+type+", pk = "+pk+"]";
    }
}
