package jackal.objects.year2013;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * User: jackal
 * Date: 02.02.2009
 * Time: 15:36:09
 * $Rev: 169 $
 * $Author: jackal $
 * $Date: 2013-02-12 15:26:10 +0400 (Вт., 12 февр. 2013) $
 */
public class ComplaintInquiry implements Serializable {

    private static final long serialVersionUID = 5873819805678362593L;

    private Long id;
    private Complaint complaint;
    private String inquiryNum; //Реквизит №16.1
    private Date inquiryDate; //Реквизит №16.2
    private Set<ComplaintConclusion> conclusions = new HashSet<ComplaintConclusion>();

    public Long getId() {
        return id;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

    public String getInquiryNum() {
        return inquiryNum;
    }

    public void setInquiryNum(String inquiryNum) {
        this.inquiryNum = inquiryNum;
    }

    public Date getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(Date inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public Set<ComplaintConclusion> getConclusions() {
        return conclusions;
    }

    public void setConclusions(Set<ComplaintConclusion> conclusions) {
        this.conclusions = conclusions;
    }

    public void addConclusion(ComplaintConclusion conclusion) {
        conclusions.add(conclusion);
        conclusion.getInquiries().add(this);
    }

    public void removeConclusion(ComplaintConclusion conclusion) {
        Iterator<ComplaintConclusion> i = conclusions.iterator();
        boolean removed = false;
        while (i.hasNext()) {
            if(i.next().equals(conclusion)) {
                i.remove();
                removed=true;
                break;
            }
        }
        if(removed) {
            conclusion.removeInquiry(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplaintInquiry inquiry = (ComplaintInquiry) o;

        if (id != null ? !id.equals(inquiry.id) : inquiry.id != null) return false;
        if (inquiryDate != null ? !inquiryDate.equals(inquiry.inquiryDate) : inquiry.inquiryDate != null) return false;
        if (inquiryNum != null ? !inquiryNum.equals(inquiry.inquiryNum) : inquiry.inquiryNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (inquiryNum != null ? inquiryNum.hashCode() : 0);
        result = 31 * result + (inquiryDate != null ? inquiryDate.hashCode() : 0);
        return result;
    }
}
