package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-19.
 */

public class Reviews implements Serializable
{
    private String created_date;

    private String friendliness;

    private String talent;

    private String curriculum;

    private String comment;

    private String delivery;

    private String readiness;

    private User user;

    private String timeliness;

    private String pk;

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
    }

    public String getFriendliness ()
    {
        return friendliness;
    }

    public void setFriendliness (String friendliness)
    {
        this.friendliness = friendliness;
    }

    public String getTalent ()
    {
        return talent;
    }

    public void setTalent (String talent)
    {
        this.talent = talent;
    }

    public String getCurriculum ()
    {
        return curriculum;
    }

    public void setCurriculum (String curriculum)
    {
        this.curriculum = curriculum;
    }

    public String getComment ()
    {
        return comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public String getDelivery ()
    {
        return delivery;
    }

    public void setDelivery (String delivery)
    {
        this.delivery = delivery;
    }

    public String getReadiness ()
    {
        return readiness;
    }

    public void setReadiness (String readiness)
    {
        this.readiness = readiness;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public String getTimeliness ()
    {
        return timeliness;
    }

    public void setTimeliness (String timeliness)
    {
        this.timeliness = timeliness;
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
        return "ClassPojo [created_date = "+created_date+", friendliness = "+friendliness+", talent = "+talent+", curriculum = "+curriculum+", comment = "+comment+", delivery = "+delivery+", readiness = "+readiness+", user = "+user+", timeliness = "+timeliness+", pk = "+pk+"]";
    }
}