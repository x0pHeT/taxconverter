package jackal.objects.year2013;

/**
 * User: Jackal
 * Date: 09.11.2008
 * Time: 21:39:43
 * $Rev: 193 $
 * $Author: jackal $
 * $Date: 2013-04-18 09:27:12 +0400 (Чт, 18 апр 2013) $
 */
public class PhysicalPerson extends Declarant implements DeclarantIsHuman {
    private static final long serialVersionUID = -4630133384419509006L;

    private String firstname = ""; //Реквизит №9.2.2
    private String flSurname = ""; //Реквизит №9.2.1
    private String patronymic = ""; //Реквизит №9.2.3

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        fireChange("firstname",this.firstname, firstname);
        this.firstname = firstname;
    }

    @Override
    public void setSurname(String surname) {
        setFlSurname(surname);
    }

    public String getFlSurname() {
        return flSurname;
    }

    public void setFlSurname(String surname) {
        fireChange("surname",this.flSurname, surname);
        this.flSurname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        fireChange("patronymic",this.patronymic, patronymic);
        this.patronymic = patronymic;
    }

    public String getFIO() {
        return new StringBuilder(flSurname)
                .append(' ').append(firstname)
                .append(' ').append(patronymic==null?"":patronymic).toString();
    }

    @Override
    public String toString() {
        return getFIO();
    }

    @Override
    public String getFullContentAsString() {
        StringBuilder sb = new StringBuilder(flSurname);
        sb.append(" ").append(firstname).append(" ").append(patronymic).append(" ");
        sb.append(INN!=null ? ("ИНН "+INN) : "");
        return sb.toString();
    }
}
