package jackal.objects.year2013;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: jackal
 * Date: 11.11.2008
 * Time: 14:52:57
 * $Rev: 198 $
 * $Author: jackal $
 * $Date: 2013-05-22 09:36:12 +0400 (Ср, 22 май 2013) $
 */
public class Complaint implements Serializable {

    private static final long serialVersionUID = 2733626999929380091L;

    public enum ComplaintStatus {
        csInUse, csPerformed, csPerformedWithTheLate, csExpiredExecution;

        @Override
        public String toString() {
            switch (this) {
                case csInUse: return "В работе";
                case csPerformed: return "Исполнена";
                case csPerformedWithTheLate: return "Исполнена с нарушением сроков";
                default: csExpiredExecution: return "Просрочена";
            }
        }
    }

    private Long id;

    /*Заголовок жалобы*/

    private Long journalNumber; //Реквизит №1
    private Date createDate; //Реквизит №2
    private User creator; //Реквизит №4.2
    private User lastModifier; //Реквизит №4.3
    private Executor executor; //Реквизит №4.1
    private ComplaintStatus status; //Сурогатный признак, вычисляется исходя из совокупности реквизитов

    /*Основные данные жалобы*/

    private String outDocNum; //Реквизит №3.1
    private Date outDocDate; //Реквизит №3.2
    private String inDocNum; //Реквизит №3.3
    private Date inDocDate; //Реквизит №3.4
    private String summary; //Реквизит №3.5


    private DictionaryItem complaintDiscriminate; //Реквизит №5
    private DictionaryItem legalAct; //Реквизит №6
    private boolean secondPetition; //Реквизит №9.1.2
    private String nameOfAuthority; //Реквизит №9.1.3
    private Date deadline1; //Реквизит №6.1
    private Date deadline2; //Реквизит №6.2
    private Date deadline3; //Реквизит №8
    private Date deadline4; //Реквизит №8.1
    private Date prolongationDate; //Реквизит №7.1
    private String prolongationNum; //Реквизит №7.2
    private Declarant declarant; //Реквизит №9

    /*Данные по жалобе*/

    /*@OnDelete(action= OnDeleteAction.CASCADE)*/
    private List<ComplaintAppealDoc> appealDocs = new ArrayList<ComplaintAppealDoc>(); //Реквизит №10
    private BigDecimal FLD_13; //Реквизит №13
    private Collection<DictionaryItem> responsibilities = new ArrayList<DictionaryItem>(); //Реквизит №11
    private Set<RatingAuthority> taxAuthorities = new HashSet<RatingAuthority>(); //Реквизит №14
    private String underTaxAuthorityInDocNum; //Реквизит №15.1
    private Date underTaxAuthorityInDocDate; //Реквизит №15.2
    private Set<ComplaintInquiry> inquires = new HashSet<ComplaintInquiry>(); //Реквизит №16
    private Set<ComplaintConclusion> conclusions = new HashSet<ComplaintConclusion>(); //Реквизит №17
    private String telephoneMessageNum; //Реквизит №18.1
    private Date telephoneMessageDate; //Реквизит №18.2
    private String whoTookFIO; //Реквизит №18.3
    private String revocationNum; //Реквизит №19.1
    private Date revocationDate; //Реквизит №19.2

    private DictionaryItem result; //Реквизит №20
    private RatingAuthority jurisdiction; //Реквизит №20.1  //по подведомственности
    private String underAuthorityDecisionNum; //Реквизит №20.2.1
    private Date underAuthorityDecisionDate; //Реквизит №20.2.2
    private DictionaryItem resultOfConsideration; //Реквизит №21
    private String decisionNum; //Реквизит №22.1
    private Date decisionDate; //Реквизит №22.2

    private BigDecimal FLD_23; //Реквизит №23
    private BigDecimal FLD_23_1; //Реквизит №23.1
    private BigDecimal FLD_23_2; //Реквизит №23.2
    private BigDecimal FLD_23_3; //Реквизит №23.3
    private BigDecimal FLD_23_4; //Реквизит №23.4
    private BigDecimal FLD_23_5; //Реквизит №23.5
    private BigDecimal FLD_23_6; //Реквизит №23.6
    private BigDecimal FLD_23_7; //Реквизит №23.7
    private BigDecimal FLD_23_8; //Реквизит №23.8
    private BigDecimal FLD_23_9; //Реквизит №23.9
    private BigDecimal FLD_23_10; //Реквизит №23.10
    private BigDecimal FLD_23_11; //Реквизит №23.11
    private BigDecimal FLD_23_12; //Реквизит №23.12
    private BigDecimal FLD_24; //Реквизит №24
    private BigDecimal FLD_25; //Реквизит №25
    private BigDecimal FLD_25_1; //Реквизит №25.1
    private BigDecimal FLD_25_2; //Реквизит №25.2
    private BigDecimal FLD_25_3; //Реквизит №25.3
    private BigDecimal FLD_25_4; //Реквизит №25.4
    private BigDecimal FLD_25_5; //Реквизит №25.5
    private BigDecimal FLD_25_6; //Реквизит №25.6
    private BigDecimal FLD_25_7; //Реквизит №25.7
    private BigDecimal FLD_25_8; //Реквизит №25.8
    private Set<DictionaryItem> appealTaxType = new HashSet<DictionaryItem>(); //Реквизит №26
    private String notice; //Реквизит №27

    /*change writer*/

    private Map<String,Object> originalValues = new HashMap<String, Object>();
    private Map<String,Object> modifiedValues = new HashMap<String, Object>();
    private final String NO_VALUE = "empty";

    private void fireChangeEvent(String propertyName, Object oldValue, Object newValue) {
        if(this.id==null) return;
        if(newValue instanceof String || newValue instanceof Date || newValue instanceof BigDecimal || newValue instanceof Boolean || newValue==null) {
            //firePrimitivePropertyChangeEvent(propertyName, oldValue, newValue);
        } else if(newValue instanceof DictionaryItem){
            //fireDictionaryPropertyChangeEvent(propertyName, (DictionaryItem)oldValue, (DictionaryItem)newValue);
        } else if(newValue instanceof Executor) {
            fireExecutorPropertyChangeEvent(propertyName, (Executor)oldValue, (Executor)newValue);
        } else if(newValue instanceof Declarant) {
            fireDeclarantPropertyChangeEvent(propertyName, (Declarant)oldValue, (Declarant)newValue);
        }
    }

    public void fireCollectionPropertyChangeEvent(String propertyName, String operation, String newValue) {
        if(id==null) return;
        String key = propertyName+operation;
        Object originalValue = originalValues.get(key);
        if(originalValue==null) {
            originalValues.put(key,NO_VALUE);
            modifiedValues.put(key,newValue);
        } else {
            modifiedValues.put(key,modifiedValues.get(key)+" -> "+newValue);
        }
    }

    public void fireExecutorPropertyChangeEvent(String propertyName, Executor oldValue, Executor newValue) {
        if(id==null) return;
        if(oldValue!=null) {
            if(oldValue.getId().equals(newValue.getId())) return;

            Executor originalValue = (Executor)originalValues.get(propertyName);
            if(originalValue==null) {
                originalValues.put(propertyName,oldValue);
                modifiedValues.put(propertyName,newValue);
            } else if(originalValue.getId().equals(newValue.getId())) {
                originalValues.remove(propertyName);
                modifiedValues.remove(propertyName);
            } else {
                modifiedValues.put(propertyName,newValue);
            }
        } else if(newValue!=null){
            originalValues.put(propertyName,NO_VALUE);
            modifiedValues.put(propertyName,newValue);
        }
    }

    public void fireDeclarantPropertyChangeEvent(String propertyName, Declarant oldValue, Declarant newValue) {
        if(id==null) return;
        if(oldValue!=null) {
            if(oldValue.getId().equals(newValue.getId())) return;

            Declarant originalValue = (Declarant)originalValues.get(propertyName);
            if(originalValue==null) {
                originalValues.put(propertyName,oldValue);
                modifiedValues.put(propertyName,newValue);
            } else if(originalValue.getId().equals(newValue.getId())) {
                originalValues.remove(propertyName);
                modifiedValues.remove(propertyName);
            } else {
                modifiedValues.put(propertyName,newValue);
            }
        } else if(newValue!=null){
            originalValues.put(propertyName,NO_VALUE);
            modifiedValues.put(propertyName,newValue);
        }
    }

    private String convertToStringFromObject(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String o1;
        if(o!=null)
            if(!(o instanceof Declarant))
                o1 = o instanceof Date ? sdf.format(o) : o.toString();
            else {
                o1 = ((Declarant)o).getFullContentAsString();
            }
        else
            o1 = "empty";
        return o1;
    }

    public String getChangesAsString(String userWhoSaveChanges) {
        String declChange = declarant.getChangesAsString();
        boolean appDocChanged = false;
        String appDocChanges = "";
        for(ComplaintAppealDoc doc : appealDocs) {
            if(doc.getChangesAsString()!=null) {
                appDocChanged = true;
                appDocChanges = appDocChanges+doc.getChangesAsString()+"\t";
                break;
            }
        }
        if(modifiedValues.size()==0 && declChange==null && !appDocChanged) return "Жалоба ["+id+"] сохранена без изменений";
        StringBuilder sb = new StringBuilder("В жалобу ID["+id+"] внесены изменения: ");
        for(String key : modifiedValues.keySet()) {
            Object originalObject1 = originalValues.get(key);
            String o1 = convertToStringFromObject(originalObject1);
            Object originalObject2 = modifiedValues.get(key);
            String o2 = convertToStringFromObject(originalObject2);

            sb.append(key).append(":")
                    .append(o1).append("->").append(o2).append("; \n");
        }
        if(declChange!=null) sb.append(declChange).append("; \n");
        if(appDocChanged) sb.append(appDocChanges).append("; \n");
        if(userWhoSaveChanges!=null)
            sb.append("Изменения внёс: ").append(userWhoSaveChanges);
        //ComplaintChangesHolder.addChange(sb.toString());
        return sb.toString();
    }

    /*getters and setters*/

    public Long getId() {
        return id;
    }

    public Long getJournalNumber() {
        return journalNumber;
    }

    public void setJournalNumber(Long journalNumber) {
        this.journalNumber = journalNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(User lastModifier) {
        this.lastModifier = lastModifier;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        fireChangeEvent("executor", this.executor, executor);
        this.executor = executor;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getOutDocNum() {
        return outDocNum;
    }

    public void setOutDocNum(String outDocNum) {
        fireChangeEvent("outDocNum", this.outDocNum, outDocNum);
        this.outDocNum = outDocNum;
    }

    public Date getOutDocDate() {
        return outDocDate;
    }

    public void setOutDocDate(Date outDocDate) {
        fireChangeEvent("outDocDate", this.outDocDate, outDocDate);
        this.outDocDate = outDocDate;
    }

    public String getInDocNum() {
        return inDocNum;
    }

    public void setInDocNum(String inDocNum) {
        fireChangeEvent("inDocNum", this.inDocNum, inDocNum);
        this.inDocNum = inDocNum;
    }

    public Date getInDocDate() {
        return inDocDate;
    }

    public void setInDocDate(Date inDocDate) {
        fireChangeEvent("inDocDate", this.inDocDate, inDocDate);
        this.inDocDate = inDocDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        fireChangeEvent("summary", this.summary, summary);
        this.summary = summary;
    }

    public boolean isSecondPetition() {
        return secondPetition;
    }

    public void setSecondPetition(boolean secondPetition) {
        fireChangeEvent("secondPetition", this.secondPetition, secondPetition);
        this.secondPetition = secondPetition;
    }

    public String getNameOfAuthority() {
        return nameOfAuthority;
    }

    public void setNameOfAuthority(String nameOfAuthority) {
        fireChangeEvent("nameOfAuthority", this.nameOfAuthority, nameOfAuthority);
        this.nameOfAuthority = nameOfAuthority;
    }

    public DictionaryItem getComplaintDiscriminate() {
        return complaintDiscriminate;
    }

    public void setComplaintDiscriminate(DictionaryItem complaintDiscriminate) {
        fireChangeEvent("complaintDiscriminate", this.complaintDiscriminate, complaintDiscriminate);
        this.complaintDiscriminate = complaintDiscriminate;
    }

    public DictionaryItem getLegalAct() {
        return legalAct;
    }

    public void setLegalAct(DictionaryItem legalAct) {
        fireChangeEvent("legalAct", this.legalAct, legalAct);
        this.legalAct = legalAct;
    }

    public Date getDeadline1() {
        return deadline1;
    }

    public void setDeadline1(Date deadline1) {
        fireChangeEvent("deadline1", this.deadline1, deadline1);
        this.deadline1 = deadline1;
    }

    public Date getDeadline2() {
        return deadline2;
    }

    public void setDeadline2(Date deadline2) {
        fireChangeEvent("deadline2", this.deadline2, deadline2);
        this.deadline2 = deadline2;
    }

    public Date getDeadline4() {
        return deadline4;
    }

    public void setDeadline4(Date deadline4) {
        fireChangeEvent("deadline4", this.deadline4, deadline4);
        this.deadline4 = deadline4;
    }

    public Date getProlongationDate() {
        return prolongationDate;
    }

    public void setProlongationDate(Date prolongationDate) {
        fireChangeEvent("prolongationDate", this.prolongationDate, prolongationDate);
        this.prolongationDate = prolongationDate;
    }

    public String getProlongationNum() {
        return prolongationNum;
    }

    public void setProlongationNum(String prolongationNum) {
        fireChangeEvent("prolongationNum", this.prolongationNum, prolongationNum);
        this.prolongationNum = prolongationNum;
    }

    public Date getDeadline3() {
        return deadline3;
    }

    public void setDeadline3(Date deadline3) {
        fireChangeEvent("deadline3", this.deadline3, deadline3);
        this.deadline3 = deadline3;
    }

    public Declarant getDeclarant() {
        return declarant;
    }

    public void setDeclarant(Declarant declarant) {
        fireChangeEvent("declarant", this.declarant, declarant);
        this.declarant = declarant;
    }

    public Collection<ComplaintAppealDoc> getAppealDocs() {
        return Collections.unmodifiableCollection(appealDocs);
    }

    /*private void setAppealDocs(List<ComplaintAppealDoc> appealDocs) {
        this.appealDocs = appealDocs;
    }*/

    public void addAppealDoc(ComplaintAppealDoc appealDoc) {
        fireCollectionPropertyChangeEvent("appealDoc", "_Add", appealDoc.toString());
        appealDocs.add(appealDoc);
        FLD_13_Recalculate();
        //fireChangeEvent("appealDoc"+new Random(951L).nextInt(9999),"","Добавлен обжалуемый документ "+appealDoc);
    }

    public void removeAppealDoc(ComplaintAppealDoc appealDoc) {
        fireCollectionPropertyChangeEvent("appealDoc", "_Remove", appealDoc.toString());
        appealDocs.remove(appealDoc);
        //fireChangeEvent("appealDoc"+new Random(753L).nextInt(9999),"","Удален обжалуемый документ "+appealDoc);
    }

    public BigDecimal getFLD_13() {
        return FLD_13;
    }

    public BigDecimal FLD_13_Recalculate() {
        boolean noneSum = true;
        BigDecimal fld = new BigDecimal(0);
        for(ComplaintAppealDoc doc : appealDocs) {
            if(doc.getFLD_12()!=null) {
                fld = fld.add(doc.getFLD_12());
                noneSum=false;
            }
        }
        if(!noneSum) FLD_13 = fld;
        else FLD_13 = null;
        return FLD_13;
    }

    public Collection<DictionaryItem> getResponsibilities() {
        return Collections.unmodifiableCollection(responsibilities);
    }

    public void addResponsibility(DictionaryItem responsibility) {
        fireCollectionPropertyChangeEvent("responsibility", "_Add", responsibility.toString());
        responsibilities.add(responsibility);
    }

    public void removeResponsibility(DictionaryItem responsibility) {
        fireCollectionPropertyChangeEvent("responsibility", "_Remove", responsibility.toString());
        responsibilities.remove(responsibility);
    }

    /*public void setResponsibilities(Collection<DictionaryItem> responsibilities) {
        this.responsibilities = responsibilities;
    }*/

    public Set<RatingAuthority> getTaxAuthorities() {
        return Collections.unmodifiableSet(taxAuthorities);
    }

    public void addTaxAuthority(RatingAuthority ratingAuthority) {
        fireCollectionPropertyChangeEvent("ratingAuthority", "_Add", ratingAuthority.toString());
        taxAuthorities.add(ratingAuthority);
    }

    public void removeTaxAuthority(RatingAuthority ratingAuthority) {
        fireCollectionPropertyChangeEvent("ratingAuthority", "_Remove", ratingAuthority.toString());
        taxAuthorities.remove(ratingAuthority);
    }

    /*public void setTaxAuthorities(Set<RatingAuthority> taxAuthorities) {
        this.taxAuthorities = taxAuthorities;
    }*/

    public String getUnderTaxAuthorityInDocNum() {
        return underTaxAuthorityInDocNum;
    }

    public void setUnderTaxAuthorityInDocNum(String underTaxAuthorityInDocNum) {
        fireChangeEvent("underTaxAuthorityInDocNum", this.underTaxAuthorityInDocNum, underTaxAuthorityInDocNum);
        this.underTaxAuthorityInDocNum = underTaxAuthorityInDocNum;
    }

    public Date getUnderTaxAuthorityInDocDate() {
        return underTaxAuthorityInDocDate;
    }

    public void setUnderTaxAuthorityInDocDate(Date underTaxAuthorityInDocDate) {
        fireChangeEvent("underTaxAuthorityInDocDate", this.underTaxAuthorityInDocDate, underTaxAuthorityInDocDate);
        this.underTaxAuthorityInDocDate = underTaxAuthorityInDocDate;
    }

    public Set<ComplaintInquiry> getInquires() {
        return Collections.unmodifiableSet(inquires);
    }

    /*public void setInquires(Set<ComplaintInquiry> inquires) {
        this.inquires = inquires;
    }*/

    public void addInquires(ComplaintInquiry inquire) {
        inquires.add(inquire);
        inquire.setComplaint(this);
    }

    public void removeInquiry(ComplaintInquiry inquiry) {
        inquires.remove(inquiry);
        inquiry.setComplaint(null);
    }

    public Set<ComplaintConclusion> getConclusions() {
        return conclusions;
    }

    public void setConclusions(Set<ComplaintConclusion> conclusions) {
        this.conclusions = conclusions;
    }

    public void addConclusion(ComplaintConclusion conclusion) {
        conclusions.add(conclusion);
        conclusion.setComplaint(this);
    }

    public void removeConclusion(ComplaintConclusion conclusion) {
        conclusions.remove(conclusion);
        conclusion.setComplaint(null);
    }

    public String getTelephoneMessageNum() {
        return telephoneMessageNum;
    }

    public void setTelephoneMessageNum(String telephoneMessageNum) {
        fireChangeEvent("telephoneMessageNum", this.telephoneMessageNum, telephoneMessageNum);
        this.telephoneMessageNum = telephoneMessageNum;
    }

    public Date getTelephoneMessageDate() {
        return telephoneMessageDate;
    }

    public void setTelephoneMessageDate(Date telephoneMessageDate) {
        fireChangeEvent("telephoneMessageDate", this.telephoneMessageDate, telephoneMessageDate);
        this.telephoneMessageDate = telephoneMessageDate;
    }

    public String getWhoTookFIO() {
        return whoTookFIO;
    }

    public void setWhoTookFIO(String whoTookFIO) {
        fireChangeEvent("whoTookFIO", this.whoTookFIO, whoTookFIO);
        this.whoTookFIO = whoTookFIO;
    }

    public String getRevocationNum() {
        return revocationNum;
    }

    public void setRevocationNum(String revocationNum) {
        fireChangeEvent("revocationNum", this.revocationNum, revocationNum);
        this.revocationNum = revocationNum;
    }

    public Date getRevocationDate() {
        return revocationDate;
    }

    public void setRevocationDate(Date revocationDate) {
        fireChangeEvent("revocationDate", this.revocationDate, revocationDate);
        this.revocationDate = revocationDate;
    }

    public DictionaryItem getResult() {
        return result;
    }

    public void setResult(DictionaryItem result) {
        fireChangeEvent("result", this.result, result);
        this.result = result;
    }

    public RatingAuthority getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(RatingAuthority jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public DictionaryItem getResultOfConsideration() {
        return resultOfConsideration;
    }

    public void setResultOfConsideration(DictionaryItem resultOfConsideration) {
        fireChangeEvent("resultOfConsideration", this.resultOfConsideration, resultOfConsideration);
        this.resultOfConsideration = resultOfConsideration;
    }

    public String getUnderAuthorityDecisionNum() {
        return underAuthorityDecisionNum;
    }

    public void setUnderAuthorityDecisionNum(String underAuthorityDecisionNum) {
        fireChangeEvent("underAuthorityDecisionNum", this.underAuthorityDecisionNum, underAuthorityDecisionNum);
        this.underAuthorityDecisionNum = underAuthorityDecisionNum;
    }

    public Date getUnderAuthorityDecisionDate() {
        return underAuthorityDecisionDate;
    }

    public void setUnderAuthorityDecisionDate(Date underAuthorityDecisionDate) {
        fireChangeEvent("underAuthorityDecisionDate", this.underAuthorityDecisionDate, underAuthorityDecisionDate);
        this.underAuthorityDecisionDate = underAuthorityDecisionDate;
    }

    public String getDecisionNum() {
        return decisionNum;
    }

    public void setDecisionNum(String decisionNum) {
        fireChangeEvent("decisionNum", this.decisionNum, decisionNum);
        this.decisionNum = decisionNum;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        fireChangeEvent("decisionDate", this.decisionDate, decisionDate);
        this.decisionDate = decisionDate;
    }

    public BigDecimal getFLD_23() {
        return FLD_23;
    }

    public void FLD_23_Recalculate() {
        if(FLD_23_1!=null || FLD_23_2!=null || FLD_23_3!=null || FLD_23_4!=null || FLD_23_5!=null || FLD_23_6!=null
                || FLD_23_7!=null || FLD_23_8!=null || FLD_23_9!=null || FLD_23_10!=null || FLD_23_11!=null
                || FLD_23_12!=null) {
            FLD_23 = BigDecimal.valueOf(0L);
            FLD_23 = FLD_23
                    .add(FLD_23_1!=null ? FLD_23_1 : new BigDecimal(0))
                    .add(FLD_23_2 != null ? FLD_23_2 : new BigDecimal(0))
                    .add(FLD_23_3 != null ? FLD_23_3 : new BigDecimal(0))
                    .add(FLD_23_4 != null ? FLD_23_4 : new BigDecimal(0))
                    .add(FLD_23_5 != null ? FLD_23_5 : new BigDecimal(0))
                    .add(FLD_23_6 != null ? FLD_23_6 : new BigDecimal(0))
                    .add(FLD_23_7 != null ? FLD_23_7 : new BigDecimal(0))
                    .add(FLD_23_8!=null ? FLD_23_8 : new BigDecimal(0))
                    .add(FLD_23_9!=null ? FLD_23_9 : new BigDecimal(0))
                    .add(FLD_23_10!=null ? FLD_23_10 : new BigDecimal(0))
                    .add(FLD_23_11!=null ? FLD_23_11 : new BigDecimal(0))
                    .add(FLD_23_12 != null ? FLD_23_12 : new BigDecimal(0));
        }
    }

    public BigDecimal getFLD_23_1() {
        return FLD_23_1;
    }

    public void setFLD_23_1(BigDecimal FLD_23_1) {
        fireChangeEvent("FLD_23_1", this.FLD_23_1, FLD_23_1);
        this.FLD_23_1 = FLD_23_1;
    }

    public BigDecimal getFLD_23_2() {
        return FLD_23_2;
    }

    public void setFLD_23_2(BigDecimal FLD_23_2) {
        fireChangeEvent("FLD_23_2", this.FLD_23_2, FLD_23_2);
        this.FLD_23_2 = FLD_23_2;
    }

    public BigDecimal getFLD_23_3() {
        return FLD_23_3;
    }

    public void setFLD_23_3(BigDecimal FLD_23_3) {
        fireChangeEvent("FLD_23_3", this.FLD_23_3, FLD_23_3);
        this.FLD_23_3 = FLD_23_3;
    }

    public BigDecimal getFLD_23_4() {
        return FLD_23_4;
    }

    public void setFLD_23_4(BigDecimal FLD_23_4) {
        fireChangeEvent("FLD_23_4", this.FLD_23_4, FLD_23_4);
        this.FLD_23_4 = FLD_23_4;
    }

    public BigDecimal getFLD_23_5() {
        return FLD_23_5;
    }

    public void setFLD_23_5(BigDecimal FLD_23_5) {
        fireChangeEvent("FLD_23_5", this.FLD_23_5, FLD_23_5);
        this.FLD_23_5 = FLD_23_5;
    }

    public BigDecimal getFLD_23_6() {
        return FLD_23_6;
    }

    public void setFLD_23_6(BigDecimal FLD_23_6) {
        fireChangeEvent("FLD_23_6", this.FLD_23_6, FLD_23_6);
        this.FLD_23_6 = FLD_23_6;
    }

    public BigDecimal getFLD_23_7() {
        return FLD_23_7;
    }

    public void setFLD_23_7(BigDecimal FLD_23_7) {
        fireChangeEvent("FLD_23_7", this.FLD_23_7, FLD_23_7);
        this.FLD_23_7 = FLD_23_7;
    }

    public BigDecimal getFLD_23_8() {
        return FLD_23_8;
    }

    public void setFLD_23_8(BigDecimal FLD_23_8) {
        fireChangeEvent("FLD_23_8", this.FLD_23_8, FLD_23_8);
        this.FLD_23_8 = FLD_23_8;
    }

    public BigDecimal getFLD_23_9() {
        return FLD_23_9;
    }

    public void setFLD_23_9(BigDecimal FLD_23_9) {
        fireChangeEvent("FLD_23_9", this.FLD_23_9, FLD_23_9);
        this.FLD_23_9 = FLD_23_9;
    }

    public BigDecimal getFLD_23_10() {
        return FLD_23_10;
    }

    public void setFLD_23_10(BigDecimal FLD_23_10) {
        fireChangeEvent("FLD_23_10", this.FLD_23_10, FLD_23_10);
        this.FLD_23_10 = FLD_23_10;
    }

    public BigDecimal getFLD_23_11() {
        return FLD_23_11;
    }

    public void setFLD_23_11(BigDecimal FLD_23_11) {
        fireChangeEvent("FLD_23_11", this.FLD_23_11, FLD_23_11);
        this.FLD_23_11 = FLD_23_11;
    }

    public BigDecimal getFLD_23_12() {
        return FLD_23_12;
    }

    public void setFLD_23_12(BigDecimal FLD_23_12) {
        fireChangeEvent("FLD_23_12", this.FLD_23_12, FLD_23_12);
        this.FLD_23_12 = FLD_23_12;
    }

    public BigDecimal getFLD_24() {
        return FLD_24;
    }

    public void setFLD_24(BigDecimal FLD_24) {
        fireChangeEvent("FLD_24", this.FLD_24, FLD_24);
        this.FLD_24 = FLD_24;
    }

    public BigDecimal getFLD_25() {
        return FLD_25;
    }

    public void FLD_25_Recalculate() {
        if(FLD_25_1!=null || FLD_25_2!=null || FLD_25_3!=null || FLD_25_4!=null || FLD_25_5!=null || FLD_25_6!=null
                || FLD_25_7!=null || FLD_25_8!=null) {
            FLD_25 = BigDecimal.valueOf(0L);
            FLD_25 = FLD_25
                    .add(FLD_25_1 != null ? FLD_25_1 : new BigDecimal(0))
                    .add(FLD_25_2 != null ? FLD_25_2 : new BigDecimal(0))
                    .add(FLD_25_3 != null ? FLD_25_3 : new BigDecimal(0))
                    .add(FLD_25_4 != null ? FLD_25_4 : new BigDecimal(0))
                    .add(FLD_25_5 != null ? FLD_25_5 : new BigDecimal(0))
                    .add(FLD_25_6 != null ? FLD_25_6 : new BigDecimal(0))
                    .add(FLD_25_7 != null ? FLD_25_7 : new BigDecimal(0))
                    .add(FLD_25_8 != null ? FLD_25_8 : new BigDecimal(0));
        }
    }

    public BigDecimal getFLD_25_1() {
        return FLD_25_1;
    }

    public void setFLD_25_1(BigDecimal FLD_25_1) {
        fireChangeEvent("FLD_25_1", this.FLD_25_1, FLD_25_1);
        this.FLD_25_1 = FLD_25_1;
    }

    public BigDecimal getFLD_25_2() {
        return FLD_25_2;
    }

    public void setFLD_25_2(BigDecimal FLD_25_2) {
        fireChangeEvent("FLD_25_2", this.FLD_25_2, FLD_25_2);
        this.FLD_25_2 = FLD_25_2;
    }

    public BigDecimal getFLD_25_3() {
        return FLD_25_3;
    }

    public void setFLD_25_3(BigDecimal FLD_25_3) {
        fireChangeEvent("FLD_25_3", this.FLD_25_3, FLD_25_3);
        this.FLD_25_3 = FLD_25_3;
    }

    public BigDecimal getFLD_25_4() {
        return FLD_25_4;
    }

    public void setFLD_25_4(BigDecimal FLD_25_4) {
        fireChangeEvent("FLD_25_4", this.FLD_25_4, FLD_25_4);
        this.FLD_25_4 = FLD_25_4;
    }

    public BigDecimal getFLD_25_5() {
        return FLD_25_5;
    }

    public void setFLD_25_5(BigDecimal FLD_25_5) {
        fireChangeEvent("FLD_25_5", this.FLD_25_5, FLD_25_5);
        this.FLD_25_5 = FLD_25_5;
    }

    public BigDecimal getFLD_25_6() {
        return FLD_25_6;
    }

    public void setFLD_25_6(BigDecimal FLD_25_6) {
        fireChangeEvent("FLD_25_6", this.FLD_25_6, FLD_25_6);
        this.FLD_25_6 = FLD_25_6;
    }

    public BigDecimal getFLD_25_7() {
        return FLD_25_7;
    }

    public void setFLD_25_7(BigDecimal FLD_25_7) {
        fireChangeEvent("FLD_25_7", this.FLD_25_7, FLD_25_7);
        this.FLD_25_7 = FLD_25_7;
    }

    public BigDecimal getFLD_25_8() {
        return FLD_25_8;
    }

    public void setFLD_25_8(BigDecimal FLD_25_8) {
        fireChangeEvent("FLD_25_8", this.FLD_25_8, FLD_25_8);
        this.FLD_25_8 = FLD_25_8;
    }

    public Set<DictionaryItem> getAppealTaxType() {
        return appealTaxType;
    }

    public void setAppealTaxType(Set<DictionaryItem> appealTaxType) {
        this.appealTaxType = appealTaxType;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        fireChangeEvent("notice", this.notice, notice);
        this.notice = notice;
    }

    public String getComplaintStatusAsString() {
        switch (getStatus()) {
            case csInUse : return "В работе";
            case csPerformed : return "Исполнено";
            case csPerformedWithTheLate : return "Исполнено с нарушением сроков";
            case csExpiredExecution : return "Истек срок исполнения";
            default: return "Неопределено";
        }
    }

    public void calculateStatus() {
        DateTime decisionDate = null;
        if(this.decisionDate !=null) {
            decisionDate = new DateMidnight(this.decisionDate).toDateTime();
        }
        DateTime deadline;
        if(prolongationDate==null) {
            if(deadline1.after(deadline2)) {
                deadline =  new DateMidnight(deadline2).toDateTime();
            } else {
                deadline =  new DateMidnight(deadline1).toDateTime();
            }
        } else {
            if(deadline3.after(deadline4)) {
                deadline =  new DateMidnight(deadline4).toDateTime();
            } else {
                deadline =  new DateMidnight(deadline3).toDateTime();
            }

        }
        DateTime revocDate = new DateMidnight(revocationDate).toDateTime();
        if(decisionDate!=null) {
            if(decisionDate.isBefore(deadline) || decisionDate.equals(deadline)) {
                status = Complaint.ComplaintStatus.csPerformed;
                return;
            } else {
                status = Complaint.ComplaintStatus.csPerformedWithTheLate;
                return;
            }
        } else {
            if(deadline.isAfterNow() && revocDate==null) {
                status = Complaint.ComplaintStatus.csExpiredExecution;
                return;
            } else {
                if(deadline.isBeforeNow() && revocDate.isAfter(deadline)) {
                    status = Complaint.ComplaintStatus.csExpiredExecution;
                    return;
                } else {
                    status = ComplaintStatus.csInUse;
                    return;
                }
            }
        }
    }
}

