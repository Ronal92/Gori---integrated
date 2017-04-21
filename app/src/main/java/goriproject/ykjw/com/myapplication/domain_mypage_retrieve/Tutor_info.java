package goriproject.ykjw.com.myapplication.domain_mypage_retrieve;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class Tutor_info implements Serializable {

    private String profile_image;

    private String name;

    private String user_id;

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

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
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
        return "ClassPojo [profile_image = "+profile_image+", name = "+name+", user_id = "+user_id+", pk = "+pk+"]";
    }
}
