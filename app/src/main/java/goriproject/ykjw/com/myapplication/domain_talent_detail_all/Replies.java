package goriproject.ykjw.com.myapplication.domain_talent_detail_all;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-16.
 */

public class Replies implements Serializable
{
    private String content;

    private String created_date;

    private String tutor;

    private String tutor_image;

    private String pk;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
    }

    public String getTutor ()
    {
        return tutor;
    }

    public void setTutor (String tutor)
    {
        this.tutor = tutor;
    }

    public String getTutor_image ()
    {
        return tutor_image;
    }

    public void setTutor_image (String tutor_image)
    {
        this.tutor_image = tutor_image;
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
        return "ClassPojo [content = "+content+", created_date = "+created_date+", tutor = "+tutor+", tutor_image = "+tutor_image+", pk = "+pk+"]";
    }
}