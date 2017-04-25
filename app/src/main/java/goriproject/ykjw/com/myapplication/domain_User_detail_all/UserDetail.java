package goriproject.ykjw.com.myapplication.domain_User_detail_all;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class UserDetail implements Serializable {

    private String sent_registrations;

    private String received_registrations;

    private String wish_list;

    private String joined_date;

    private String is_active;

    private String is_staff;

    private String user_type;

    private String nickname;

    private String profile_image;

    private String cellphone;

    private String name;

    private String user_id;

    private String is_tutor;

    private String last_login;

    private String pk;

    public String getSent_registrations ()
    {
        return sent_registrations;
    }

    public void setSent_registrations (String sent_registrations)
    {
        this.sent_registrations = sent_registrations;
    }

    public String getReceived_registrations ()
    {
        return received_registrations;
    }

    public void setReceived_registrations (String received_registrations)
    {
        this.received_registrations = received_registrations;
    }

    public String getWish_list ()
    {
        return wish_list;
    }

    public void setWish_list (String wish_list)
    {
        this.wish_list = wish_list;
    }

    public String getJoined_date ()
    {
        return joined_date;
    }

    public void setJoined_date (String joined_date)
    {
        this.joined_date = joined_date;
    }

    public String getIs_active ()
    {
        return is_active;
    }

    public void setIs_active (String is_active)
    {
        this.is_active = is_active;
    }

    public String getIs_staff ()
    {
        return is_staff;
    }

    public void setIs_staff (String is_staff)
    {
        this.is_staff = is_staff;
    }

    public String getUser_type ()
    {
        return user_type;
    }

    public void setUser_type (String user_type)
    {
        this.user_type = user_type;
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

    public String getIs_tutor ()
    {
        return is_tutor;
    }

    public void setIs_tutor (String is_tutor)
    {
        this.is_tutor = is_tutor;
    }

    public String getLast_login ()
    {
        return last_login;
    }

    public void setLast_login (String last_login)
    {
        this.last_login = last_login;
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
        return "ClassPojo [sent_registrations = "+sent_registrations+", received_registrations = "+received_registrations+", wish_list = "+wish_list+", joined_date = "+joined_date+", is_active = "+is_active+", is_staff = "+is_staff+", user_type = "+user_type+", nickname = "+nickname+", profile_image = "+profile_image+", cellphone = "+cellphone+", name = "+name+", user_id = "+user_id+", is_tutor = "+is_tutor+", last_login = "+last_login+", pk = "+pk+"]";
    }

}
