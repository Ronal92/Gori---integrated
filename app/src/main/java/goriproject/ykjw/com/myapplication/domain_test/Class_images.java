package goriproject.ykjw.com.myapplication.domain_test;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-16.
 */


public class Class_images implements Serializable
{
    private String talent_pk;

    private String image;

    private String pk;

    public String getTalent_pk ()
    {
        return talent_pk;
    }

    public void setTalent_pk (String talent_pk)
    {
        this.talent_pk = talent_pk;
    }

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
        return "ClassPojo [talent_pk = "+talent_pk+", image = "+image+", pk = "+pk+"]";
    }
}
