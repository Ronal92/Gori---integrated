package goriproject.ykjw.com.myapplication.domain_mypage_retrieve;

import java.io.Serializable;

/**
 * Created by JINWOO on 2017-04-20.
 */

public class Results implements Serializable {
    private Applicants[] applicants;

    private Talents[] talents;

    private Wishlist[] wishlist;

    private Registrations[] registrations;

    private Enrollment[] enrollment;

    public Applicants[] getApplicants ()
    {
        return applicants;
    }

    public void setApplicants (Applicants[] applicants)
    {
        this.applicants = applicants;
    }

    public Talents[] getTalents ()
    {
        return talents;
    }

    public void setTalents (Talents[] talents)
    {
        this.talents = talents;
    }

    public Wishlist[] getWishlist ()
    {
        return wishlist;
    }

    public void setWishlist (Wishlist[] wishlist)
    {
        this.wishlist = wishlist;
    }

    public Registrations[] getRegistrations ()
    {
        return registrations;
    }

    public void setRegistrations (Registrations[] registrations)
    {
        this.registrations = registrations;
    }

    public Enrollment[] getEnrollment ()
    {
        return enrollment;
    }

    public void setEnrollment (Enrollment[] enrollment)
    {
        this.enrollment = enrollment;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [applicants = "+applicants+", talents = "+talents+", wishlist = "+wishlist+", registrations = "+registrations+", enrollment = "+enrollment+"]";
    }
}
