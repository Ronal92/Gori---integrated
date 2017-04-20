package goriproject.ykjw.com.myapplication.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Younkyu on 2017-04-19.
 */

public class Qna implements Serializable
{
    private String content;

    private String created_date;

    private List<String> replies;

    private String user;

    private String user_image;

    private String pk;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getCreated_date ()
    {
        return created_date;
    }

    public void setCreated_date (String created_date)
    {
        this.created_date = created_date;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getUser_image ()
    {
        return user_image;
    }

    public void setUser_image (String user_image)
    {
        this.user_image = user_image;
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
        return "ClassPojo [content = "+content+", created_date = "+created_date+", replies = "+replies+", user = "+user+", user_image = "+user_image+", pk = "+pk+"]";
    }
}
