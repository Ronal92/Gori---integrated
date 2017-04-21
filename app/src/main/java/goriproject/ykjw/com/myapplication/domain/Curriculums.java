package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class Curriculums implements Serializable
{
    private String information;

    private String talent_pk;

    private String image;

    public String getInformation ()
    {
        return information;
    }

    public void setInformation (String information)
    {
        this.information = information;
    }

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

    @Override
    public String toString()
    {
        return "ClassPojo [information = "+information+", talent_pk = "+talent_pk+", image = "+image+"]";
    }
}
