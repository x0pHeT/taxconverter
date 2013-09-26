package jackal.objects.year2014;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Jackal
 * Date: 19.01.2010
 * Time: 11:24:19
 * $Rev: 42 $
 * $Author: jackal $
 * $Date: 2011-08-04 19:20:25 +0400 (Чт., 04 авг. 2011) $
 */
public class Executor implements Serializable {

    private Long id;
    private Date created;
    private String surname;
    private String firstname;
    private String patronimyc;
    private boolean deleted;
    private Integer sortIdx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPatronimyc() {
        return patronimyc;
    }

    public void setPatronimyc(String patronimyc) {
        this.patronimyc = patronimyc;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getSortIdx() {
        return sortIdx;
    }

    public void setSortIdx(Integer sortIdx) {
        this.sortIdx = sortIdx;
    }

    public String getFIO() {
        return surname+" "+firstname+" "+patronimyc;
    }

    @Override
    public String toString() {
        return getFIO();
    }
}
