package jackal.objects.year2014;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User: jackal
 * Date: 01.11.2008
 * Time: 11:25:16
 * $Rev: 198 $
 * $Author: jackal $
 * $Date: 2013-05-22 09:36:12 +0400 (Ср, 22 май 2013) $
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4067248117041925822L;

    public final static byte ROLE_SYS = 2;
    public final static byte ROLE_ADM = 1;
    public final static byte ROLE_USER = 0;

    private final static String[] ROLES = {"Пользователь","Администратор","SYS"};

    private Long id;
    private String surname;
    private String firstname;
    private String patronimyc;
    private String username;
    private String password;
    private byte role = ROLE_USER;
    private boolean enabled;
    private Date created;
    private boolean deleted;

    public User() {
        created = new Date();
    }

    public User(User source) {
        id = source.id;
        surname = source.getSurname();
        firstname = source.getFirstname();
        patronimyc = source.getPatronimyc();
        username = source.getUsername();
        password = source.getPassword();
        role = source.getRole();
        enabled = source.isEnabled();
        created = source.getCreated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStringRole() {
        return ROLES[this.role];
    }

    public static List<String> getStringRoles() {
        List<String> result = new ArrayList<String>();
        result.addAll(Arrays.asList(ROLES));
        return result;
    }

    public String getFIO() {
        return new StringBuilder(surname)
                .append(' ').append(firstname)
                .append(' ').append(patronimyc).toString();
    }

    @Override
    public String toString() {
        return getFIO();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
