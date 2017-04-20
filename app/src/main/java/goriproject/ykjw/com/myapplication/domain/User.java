package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-19.
 */

public class User implements Serializable
{
    private String profile_image;

    private String name;

    private String pk;

    public String getProfile_image ()
{
    return profile_image;
}

    public void setProfile_image (String profile_image)
    {
        this.profile_image = profile_image;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
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
        return "ClassPojo [profile_image = "+profile_image+", name = "+name+", pk = "+pk+"]";
    }
}
