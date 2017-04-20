package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;

/**
 * Created by Younkyu on 2017-04-19.
 */

public class Average_rates implements Serializable
{
    private String total;

    private String friendliness;

    private String curriculum;

    private String delivery;

    private String readiness;

    private String timeliness;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getFriendliness ()
    {
        return friendliness;
    }

    public void setFriendliness (String friendliness)
    {
        this.friendliness = friendliness;
    }

    public String getCurriculum ()
    {
        return curriculum;
    }

    public void setCurriculum (String curriculum)
    {
        this.curriculum = curriculum;
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

    public String getTimeliness ()
    {
        return timeliness;
    }

    public void setTimeliness (String timeliness)
    {
        this.timeliness = timeliness;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", friendliness = "+friendliness+", curriculum = "+curriculum+", delivery = "+delivery+", readiness = "+readiness+", timeliness = "+timeliness+"]";
    }
}