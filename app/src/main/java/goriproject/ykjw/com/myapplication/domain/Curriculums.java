package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class Curriculums implements Serializable
{
    private String information;

    private String image;

    private String talent;

    public String getInformation ()
    {
        return information;
    }

    public void setInformation (String information)
    {
        this.information = information;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getTalent ()
    {
        return talent;
    }

    public void setTalent (String talent)
    {
        this.talent = talent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [information = "+information+", image = "+image+", talent = "+talent+"]";
    }
}
