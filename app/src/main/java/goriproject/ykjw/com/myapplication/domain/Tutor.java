package goriproject.ykjw.com.myapplication.domain;

/**
 * Created by Younkyu on 2017-04-06.
 */



public class Tutor
{
    private String is_verified;

    private String nickname;

    private String cellphone;

    private String profile_image;

    private String name;

    private String user_id;

    private String pk;

    public String getIs_verified ()
    {
        return is_verified;
    }

    public void setIs_verified (String is_verified)
    {
        this.is_verified = is_verified;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getCellphone ()
    {
        return cellphone;
    }

    public void setCellphone (String cellphone)
    {
        this.cellphone = cellphone;
    }

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
        return "ClassPojo [is_verified = "+is_verified+", nickname = "+nickname+", cellphone = "+cellphone+", profile_image = "+profile_image+", name = "+name+", user_id = "+user_id+", pk = "+pk+"]";
    }
}