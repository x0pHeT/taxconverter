package jackal.objects.year2014;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * User: jackal
 * Date: 02.02.2009
 * Time: 15:55:17
 * $Rev: 169 $
 * $Author: jackal $
 * $Date: 2013-02-12 15:26:10 +0400 (Вт., 12 февр. 2013) $
 */
public class ComplaintConclusion implements Serializable {

    private static final long serialVersionUID = -1064092374276242546L;

    private Long id;
    private Complaint complaint;
    private String outNum; //Реквизит №17.1
    private Date outDate; //Реквизит №17.2
    private Date inDate; //Реквизит №17.3
    private String inNum; //Реквизит №17.4
    private Set<ComplaintInquiry> inquiries = new HashSet<ComplaintInquiry>();

    public Long getId() {
        return id;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public String getOutNum() {
        return outNum;
    }

    public void setOutNum(String outNum) {
        this.outNum = outNum;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public String getInNum() {
        return inNum;
    }

    public void setInNum(String inNum) {
        this.inNum = inNum;
    }

    public Set<ComplaintInquiry> getInquiries() {
        return inquiries;
    }

    public void setInquiries(Set<ComplaintInquiry> inquiries) {
        this.inquiries = inquiries;
    }

    public void addInquiry(ComplaintInquiry inquiry) {
        inquiries.add(inquiry);
        inquiry.getConclusions().add(this);
    }

    public void removeInquiry(ComplaintInquiry inquiry) {
        Iterator<ComplaintInquiry> i = inquiries.iterator();
        boolean removed = false;
        while (i.hasNext()) {
            if(i.next().equals(inquiry)) {
                i.remove();
                removed=true;
                break;
            }
        }
        if(removed) {
            inquiry.removeConclusion(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintConclusion that = (ComplaintConclusion) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (outDate != null ? !outDate.equals(that.outDate) : that.outDate != null) return false;
        if (outNum != null ? !outNum.equals(that.outNum) : that.outNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (outNum != null ? outNum.hashCode() : 0);
        result = 31 * result + (outDate != null ? outDate.hashCode() : 0);
        return result;
    }
}
