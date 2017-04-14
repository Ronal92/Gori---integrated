package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class Class_images implements Serializable
{
    private String image;

    private String pk;

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
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
        return "ClassPojo [image = "+image+", pk = "+pk+"]";
    }
}