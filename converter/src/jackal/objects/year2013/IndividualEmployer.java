package jackal.objects.year2013;

/**
 * User: Jackal
 * Date: 09.11.2008
 * Time: 21:45:04
 * $Rev: 193 $
 * $Author: jackal $
 * $Date: 2013-04-18 09:27:12 +0400 (Чт, 18 апр 2013) $
 */
public class IndividualEmployer extends Declarant implements DeclarantIsHuman {
    private static final long serialVersionUID = 963528122973879935L;

    private String firstname = ""; //Реквизит №9.2.2
    private String ipSurname = ""; //Реквизит №9.2.1
    private String patronymic = ""; //Реквизит №9.2.3

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        fireChange("firstname",this.firstname, firstname);
        this.firstname = firstname;
    }

    public String getIpSurname() {
        return ipSurname;
    }

    @Override
    public void setSurname(String surname) {
        setIpSurname(surname);
    }

    public void setIpSurname(String ipSurname) {
        fireChange("surname",this.ipSurname, ipSurname);
        this.ipSurname = ipSurname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        fireChange("patronymic",this.patronymic, patronymic);
        this.patronymic = patronymic;
    }

    public String getFIO() {
        return new StringBuilder(ipSurname)
                .append(' ').append(firstname)
                .append(' ').append(patronymic==null?"":patronymic).toString();
    }

    @Override
    public String toString() {
        return getFIO();
    }

    @Override
    public String getFullContentAsString() {
        StringBuilder sb = new StringBuilder(ipSurname);
        sb.append(" ").append(firstname).append(" ").append(patronymic).append(" ");
        sb.append(getINN() != null ? ("ИНН " + INN) : "");
        return sb.toString();
    }
}
