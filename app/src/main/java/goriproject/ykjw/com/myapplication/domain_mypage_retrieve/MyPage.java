package goriproject.ykjw.com.myapplication.domain_mypage_retrieve;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class MyPage implements Serializable {
    private Results results;

    private String joined_date;

    private String nickname;

    private String profile_image;

    private String cellphone;

    private String name;

    private String user_id;

    private String pk;

    public Results getResults ()
    {
        return results;
    }

    public void setResults (Results results)
    {
        this.results = results;
    }

    public String getJoined_date ()
    {
        return joined_date;
    }

    public void setJoined_date (String joined_date)
    {
        this.joined_date = joined_date;
    }

    public String getNickname ()
    {
        return nickname;
    }

    public void setNickname (String nickname)
    {
        this.nickname = nickname;
    }

    public String getProfile_image ()
    {
        return profile_image;
    }

    public void setProfile_image (String profile_image)
    {
        this.profile_image = profile_image;
    }

    public String getCellphone ()
    {
        return cellphone;
    }

    public void setCellphone (String cellphone)
    {
        this.cellphone = cellphone;
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
        return "ClassPojo [results = "+results+", joined_date = "+joined_date+", nickname = "+nickname+", profile_image = "+profile_image+", cellphone = "+cellphone+", name = "+name+", user_id = "+user_id+", pk = "+pk+"]";
    }
}
