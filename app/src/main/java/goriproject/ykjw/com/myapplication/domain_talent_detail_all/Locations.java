package goriproject.ykjw.com.myapplication.domain_talent_detail_all;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-15.
 */

public class Locations implements Serializable
{
    private String region;

    private String extra_fee_amount;

    private String[] time;

    private String talent_pk;

    private String specific_location;

    private String day;

    private String extra_fee;

    private String pk;

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

    public String[] getTime ()
    {
        return time;
    }

    public void setTime (String[] time)
    {
        this.time = time;
    }

    public String getTalent_pk ()
    {
        return talent_pk;
    }

    public void setTalent_pk (String talent_pk)
    {
        this.talent_pk = talent_pk;
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
        return "ClassPojo [region = "+region+", extra_fee_amount = "+extra_fee_amount+", time = "+time+", talent_pk = "+talent_pk+", specific_location = "+specific_location+", day = "+day+", extra_fee = "+extra_fee+", pk = "+pk+"]";
    }
}