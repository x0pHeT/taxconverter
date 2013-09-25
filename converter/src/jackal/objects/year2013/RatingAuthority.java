package jackal.objects.year2013;

import java.io.Serializable;
import java.util.Date;

/**
 * User: jackal
 * Date: 11.11.2008
 * Time: 15:09:39
 * $Rev: 169 $
 * $Author: jackal $
 * $Date: 2013-02-12 15:26:10 +0400 (Вт., 12 февр. 2013) $
 */
public class RatingAuthority implements Serializable {

    private static final long serialVersionUID = 1707592861856499428L;

    private Long id;
    private String code; //Реквизит №14.2
    private String nameShort; //Реквизит №14.1
    private String nameFull; //Реквизит №14.1
    private Date dateFrom;
    private Date dateTo;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getNameShort() {
        return nameShort;
    }

    public String getNameFull() {
        return nameFull;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return "("+code+")"+nameFull;
    }
}
