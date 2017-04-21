package goriproject.ykjw.com.myapplication.domain_mypage_retrieve;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class Enrollment implements Serializable {

    private String message_to_tutor;

    private String student_level;

    private String is_verified;

    private String experience_length;

    private String joined_date;

    private Tutor_info tutor_info;

    private Talent talent;

    private String registered_location;

    private String pk;

    public String getMessage_to_tutor ()
    {
        return message_to_tutor;
    }

    public void setMessage_to_tutor (String message_to_tutor)
    {
        this.message_to_tutor = message_to_tutor;
    }

    public String getStudent_level ()
    {
        return student_level;
    }

    public void setStudent_level (String student_level)
    {
        this.student_level = student_level;
    }

    public String getIs_verified ()
    {
        return is_verified;
    }

    public void setIs_verified (String is_verified)
    {
        this.is_verified = is_verified;
    }

    public String getExperience_length ()
    {
        return experience_length;
    }

    public void setExperience_length (String experience_length)
    {
        this.experience_length = experience_length;
    }

    public String getJoined_date ()
    {
        return joined_date;
    }

    public void setJoined_date (String joined_date)
    {
        this.joined_date = joined_date;
    }

    public Tutor_info getTutor_info ()
    {
        return tutor_info;
    }

    public void setTutor_info (Tutor_info tutor_info)
    {
        this.tutor_info = tutor_info;
    }

    public Talent getTalent ()
    {
        return talent;
    }

    public void setTalent (Talent talent)
    {
        this.talent = talent;
    }

    public String getRegistered_location ()
    {
        return registered_location;
    }

    public void setRegistered_location (String registered_location)
    {
        this.registered_location = registered_location;
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
        return "ClassPojo [message_to_tutor = "+message_to_tutor+", student_level = "+student_level+", is_verified = "+is_verified+", experience_length = "+experience_length+", joined_date = "+joined_date+", tutor_info = "+tutor_info+", talent = "+talent+", registered_location = "+registered_location+", pk = "+pk+"]";
    }
}
