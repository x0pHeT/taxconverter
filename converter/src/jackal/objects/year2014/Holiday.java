package jackal.objects.year2014;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jackal
 * Date: 11.11.2008
 * Time: 14:41:22
 * $Rev: 24 $
 * $Author: jackal $
 * $Date: 2011-03-18 15:11:29 +0300 (Пт., 18 марта 2011) $
 */
public class Holiday implements Serializable {

    private Long id;
    private Date dateOfHoliday;
    private String comment;

    public Long getId() {
        return id;
    }

    public Date getDateOfHoliday() {
        return dateOfHoliday;
    }

    public void setDateOfHoliday(Date dateOfHoliday) {
        this.dateOfHoliday = dateOfHoliday;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
