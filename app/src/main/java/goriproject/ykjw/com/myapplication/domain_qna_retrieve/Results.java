package goriproject.ykjw.com.myapplication.domain_qna_retrieve;

/**
 * Created by JINWOO on 2017-04-22.
 */

public class Results {
    private String content;

    private String created_date;

    private String[] replies;

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

    public String[] getReplies ()
    {
        return replies;
    }

    public void setReplies (String[] replies)
    {
        this.replies = replies;
    }

    public String getUserName ()
    {
        return user;
    }

    public void setUserName (String user)
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
