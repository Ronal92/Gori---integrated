package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class Locations implements Serializable
{
    private String region;

    private String extra_fee_amount;

    private List<String> time;

    private String talent;

    private String specific_location;

    private String day;

    private String extra_fee;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public String getExtra_fee_amount ()
    {
        return extra_fee_amount;
    }

    public void setExtra_fee_amount (String extra_fee_amount)
    {
        this.extra_fee_amount = extra_fee_amount;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public String getTalent ()
    {
        return talent;
    }

    public void setTalent (String talent)
    {
        this.talent = talent;
    }

    public String getSpecific_location ()
    {
        return specific_location;
    }

    public void setSpecific_location (String specific_location)
    {
        this.specific_location = specific_location;
    }

    public String getDay ()
    {
        return day;
    }

    public void setDay (String day)
    {
        this.day = day;
    }

    public String getExtra_fee ()
    {
        return extra_fee;
    }

    public void setExtra_fee (String extra_fee)
    {
        this.extra_fee = extra_fee;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", extra_fee_amount = "+extra_fee_amount+", time = "+time+", talent = "+talent+", specific_location = "+specific_location+", day = "+day+", extra_fee = "+extra_fee+"]";
    }
}