package jackal.objects.year2014;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: jackal
 * Date: 02.02.2009
 * Time: 15:29:58
 * $Rev: 201 $
 * $Author: jackal $
 * $Date: 2013-07-22 10:31:14 +0400 (Пн, 22 июл 2013) $
 */
public class ComplaintAppealDoc implements Serializable {

    private static final long serialVersionUID = -1356503568030830300L;

    private Long id;
    private Integer sortIdx = 1;

    private DictionaryItem complControlType; //Реквизит №10.1
    private DictionaryItem complAppealDocType; //Реквизит №10.2
    private String appealDocNum; //Реквизит №10.3
    private Date appealDocDate; //Реквизит №10.4
    private Boolean violationMark; //Реквизит №10.5
    private BigDecimal FLD_12;  //Реквизит №12 //сумма всех FLD_12_*
    private BigDecimal FLD_12_1; //Реквизит №12.1
    private BigDecimal FLD_12_2; //Реквизит №12.2
    private BigDecimal FLD_12_3; //Реквизит №12.3
    private BigDecimal FLD_12_4; //Реквизит №12.4
    private BigDecimal FLD_12_5; //Реквизит №12.5
    private BigDecimal FLD_12_6; //Реквизит №12.6
    private BigDecimal FLD_12_7; //Реквизит №12.7
    private BigDecimal FLD_12_8; //Реквизит №12.8
    private BigDecimal FLD_12_9; //Реквизит №12.9
    private BigDecimal FLD_12_10; //Реквизит №12.10
    private BigDecimal FLD_12_11; //Реквизит №12.11
    private BigDecimal FLD_12_12; //Реквизит №12.12

    protected Map<String,Object> originalValues = new HashMap<String, Object>();
    protected Map<String,Object> modifiedValues = new HashMap<String, Object>();

    public String getChangesAsString() {
        if(modifiedValues.size()==0) return null;
        StringBuilder sb = new StringBuilder("Обжалуемый документ: ");
        for(String key : modifiedValues.keySet()) {
            Object originalObject1 = originalValues.get(key);
            String o1 = originalObject1!=null ? originalObject1.toString() : "empty";
            Object originalObject2 = modifiedValues.get(key);
            String o2 = originalObject2!=null ? originalObject2.toString() : "empty";

            sb.append(key).append(":")
                    .append(o1).append("->").append(o2).append("; \n");
        }
        return sb.toString();
    }

    public ComplaintAppealDoc() {}

    @Override
    public String toString() {
        return "ComplaintAppealDoc{" +
                "id=" + id +
                ", complControlType=" + complControlType +
                ", complAppealDocType=" + complAppealDocType +
                ", appealDocNum='" + appealDocNum + '\'' +
                ", appealDocDate=" + appealDocDate +
                ", FLD_12=" + FLD_12 +
                ", FLD_12_1=" + FLD_12_1 +
                ", FLD_12_2=" + FLD_12_2 +
                ", FLD_12_3=" + FLD_12_3 +
                ", FLD_12_4=" + FLD_12_4 +
                ", FLD_12_5=" + FLD_12_5 +
                ", FLD_12_6=" + FLD_12_6 +
                ", FLD_12_7=" + FLD_12_7 +
                ", FLD_12_8=" + FLD_12_8 +
                ", FLD_12_9=" + FLD_12_9 +
                ", FLD_12_10=" + FLD_12_10 +
                ", FLD_12_11=" + FLD_12_11 +
                ", FLD_12_12=" + FLD_12_12 +
                '}';
    }

    public void clone(ComplaintAppealDoc doc) {
        this.complControlType = doc.getComplControlType();
        this.complAppealDocType = doc.getComplAppealDocType();
        this.appealDocNum = doc.getAppealDocNum();
        this.appealDocDate = doc.getAppealDocDate();
        this.FLD_12 = doc.getFLD_12();
        this.FLD_12_1 = doc.getFLD_12_1();
        this.FLD_12_2 = doc.getFLD_12_2();
        this.FLD_12_3 = doc.getFLD_12_3();
        this.FLD_12_4 = doc.getFLD_12_4();
        this.FLD_12_5 = doc.getFLD_12_5();
        this.FLD_12_6 = doc.getFLD_12_6();
        this.FLD_12_7= doc.getFLD_12_7();
        this.FLD_12_8 = doc.getFLD_12_8();
        this.FLD_12_9 = doc.getFLD_12_9();
        this.FLD_12_10 = doc.getFLD_12_10();
        this.FLD_12_11 = doc.getFLD_12_11();
        this.FLD_12_12 = doc.getFLD_12_12();
    }

    public void copyFrom(ComplaintAppealDoc doc, boolean useSetters) {
        if(useSetters) {
            setComplControlType(doc.getComplControlType());
            setComplAppealDocType(doc.getComplAppealDocType());
            setAppealDocNum(doc.getAppealDocNum());
            setAppealDocDate(doc.getAppealDocDate());
            this.FLD_12 = doc.getFLD_12();
            setFLD_12_1(doc.getFLD_12_1());
            setFLD_12_2(doc.getFLD_12_2());
            setFLD_12_3(doc.getFLD_12_3());
            setFLD_12_4(doc.getFLD_12_4());
            setFLD_12_5(doc.getFLD_12_5());
            setFLD_12_6(doc.getFLD_12_6());
            setFLD_12_7(doc.getFLD_12_7());
            setFLD_12_8(doc.getFLD_12_8());
            setFLD_12_9(doc.getFLD_12_9());
            setFLD_12_10(doc.getFLD_12_10());
            setFLD_12_11(doc.getFLD_12_11());
            setFLD_12_12(doc.getFLD_12_12());
            return;
        }
        this.complControlType = doc.getComplControlType();
        this.complAppealDocType = doc.getComplAppealDocType();
        this.appealDocNum = doc.getAppealDocNum();
        this.appealDocDate = doc.getAppealDocDate();
        this.FLD_12 = doc.getFLD_12();
        this.FLD_12_1 = doc.getFLD_12_1();
        this.FLD_12_2 = doc.getFLD_12_2();
        this.FLD_12_3 = doc.getFLD_12_3();
        this.FLD_12_4 = doc.getFLD_12_4();
        this.FLD_12_5 = doc.getFLD_12_5();
        this.FLD_12_6 = doc.getFLD_12_6();
        this.FLD_12_7= doc.getFLD_12_7();
        this.FLD_12_8 = doc.getFLD_12_8();
        this.FLD_12_9 = doc.getFLD_12_9();
        this.FLD_12_10 = doc.getFLD_12_10();
        this.FLD_12_11 = doc.getFLD_12_11();
        this.FLD_12_12 = doc.getFLD_12_12();
    }

    public Long getId() {
        return id;
    }

    public Integer getSortIdx() {
        return sortIdx;
    }

    public void setSortIdx(Integer sortIdx) {
        this.sortIdx = sortIdx;
    }

    public DictionaryItem getComplControlType() {
        return complControlType;
    }

    public void setComplControlType(DictionaryItem complControlType) {
        this.complControlType = complControlType;
    }

    public DictionaryItem getComplAppealDocType() {
        return complAppealDocType;
    }

    public void setComplAppealDocType(DictionaryItem complAppealDocType) {
        this.complAppealDocType = complAppealDocType;
    }

    public String getAppealDocNum() {
        return appealDocNum;
    }

    public void setAppealDocNum(String appealDocNum) {
        this.appealDocNum = appealDocNum;
    }

    public Date getAppealDocDate() {
        return appealDocDate;
    }

    public void setAppealDocDate(Date appealDocDate) {
        this.appealDocDate = appealDocDate;
    }

    public Boolean getViolationMark() {
        return violationMark;
    }

    public void setViolationMark(Boolean violationMark) {
        this.violationMark = violationMark;
    }

    public BigDecimal getFLD_12() {
        return FLD_12;
    }

    public void FLD_12_Recalculate() {
        if(FLD_12_1!=null || FLD_12_2!=null || FLD_12_3!=null || FLD_12_4!=null || FLD_12_5!=null || FLD_12_6!=null
                || FLD_12_7!=null || FLD_12_8!=null || FLD_12_9!=null || FLD_12_10!=null || FLD_12_11!=null || FLD_12_12!=null) {
            FLD_12 = BigDecimal.valueOf(0l);
            FLD_12 = FLD_12.add(FLD_12_1!=null ? FLD_12_1 : new BigDecimal(0))
                    .add(FLD_12_2!=null ? FLD_12_2 : new BigDecimal(0))
                    .add(FLD_12_3!=null ? FLD_12_3 : new BigDecimal(0))
                    .add(FLD_12_4!=null ? FLD_12_4 : new BigDecimal(0))
                    .add(FLD_12_5!=null ? FLD_12_5 : new BigDecimal(0))
                    .add(FLD_12_6!=null ? FLD_12_6 : new BigDecimal(0))
                    .add(FLD_12_7!=null ? FLD_12_7 : new BigDecimal(0))
                    .add(FLD_12_8!=null ? FLD_12_8 : new BigDecimal(0))
                    .add(FLD_12_9!=null ? FLD_12_9 : new BigDecimal(0))
                    .add(FLD_12_10!=null ? FLD_12_10 : new BigDecimal(0))
                    .add(FLD_12_11!=null ? FLD_12_11 : new BigDecimal(0))
                    .add(FLD_12_12!=null ? FLD_12_12 : new BigDecimal(0));
        } else {
            FLD_12 = null;
        }
    }

    public BigDecimal getFLD_12_1() {
        return FLD_12_1;
    }

    public void setFLD_12_1(BigDecimal FLD_12_1) {
        this.FLD_12_1 = FLD_12_1;
    }

    public BigDecimal getFLD_12_2() {
        return FLD_12_2;
    }

    public void setFLD_12_2(BigDecimal FLD_12_2) {
        this.FLD_12_2 = FLD_12_2;
    }

    public BigDecimal getFLD_12_3() {
        return FLD_12_3;
    }

    public void setFLD_12_3(BigDecimal FLD_12_3) {
        this.FLD_12_3 = FLD_12_3;
    }

    public BigDecimal getFLD_12_4() {
        return FLD_12_4;
    }

    public void setFLD_12_4(BigDecimal FLD_12_4) {
        this.FLD_12_4 = FLD_12_4;
    }

    public BigDecimal getFLD_12_5() {
        return FLD_12_5;
    }

    public void setFLD_12_5(BigDecimal FLD_12_5) {
        this.FLD_12_5 = FLD_12_5;
    }

    public BigDecimal getFLD_12_6() {
        return FLD_12_6;
    }

    public void setFLD_12_6(BigDecimal FLD_12_6) {
        this.FLD_12_6 = FLD_12_6;
    }

    public BigDecimal getFLD_12_7() {
        return FLD_12_7;
    }

    public void setFLD_12_7(BigDecimal FLD_12_7) {
        this.FLD_12_7 = FLD_12_7;
    }

    public BigDecimal getFLD_12_8() {
        return FLD_12_8;
    }

    public void setFLD_12_8(BigDecimal FLD_12_8) {
        this.FLD_12_8 = FLD_12_8;
    }

    public BigDecimal getFLD_12_9() {
        return FLD_12_9;
    }

    public void setFLD_12_9(BigDecimal FLD_12_9) {
        this.FLD_12_9 = FLD_12_9;
    }

    public BigDecimal getFLD_12_10() {
        return FLD_12_10;
    }

    public void setFLD_12_10(BigDecimal FLD_12_10) {
        this.FLD_12_10 = FLD_12_10;
    }

    public BigDecimal getFLD_12_11() {
        return FLD_12_11;
    }

    public void setFLD_12_11(BigDecimal FLD_12_11) {
        this.FLD_12_11 = FLD_12_11;
    }

    public BigDecimal getFLD_12_12() {
        return FLD_12_12;
    }

    public void setFLD_12_12(BigDecimal FLD_12_12) {
        this.FLD_12_12 = FLD_12_12;
    }
}
