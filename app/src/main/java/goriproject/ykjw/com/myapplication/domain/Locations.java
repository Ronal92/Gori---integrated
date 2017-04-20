package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-11.
 */

public class Locations implements Serializable
{
    private String region;

    private List<Results> results;

    private String count;

    public String getRegion ()
    {
        return region;
    }

    public void setRegion (String region)
    {
        this.region = region;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [region = "+region+", results = "+results+", count = "+count+"]";
    }
}