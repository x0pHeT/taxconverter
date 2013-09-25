package jackal.support;

import jackal.objects.year2013.Complaint;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: Sergey
 * Date: 28.04.2009
 * Time: 13:25:12
 * $Rev: 158 $
 * $Author: jackal $
 * $Date: 2013-01-24 10:00:25 +0400 (Чт., 24 янв. 2013) $
 */
public class ComplaintStatusType extends SmallIntEnumUserType<Complaint.ComplaintStatus>{

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    public ComplaintStatusType() {
        super(Complaint.ComplaintStatus.class, Complaint.ComplaintStatus.values());
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
        //if(LOG.isDebugEnabled()) LOG.debug("binding '"+((Enum)value).ordinal()+"' to parameter: "+index);
        super.nullSafeSet(preparedStatement, value, index);
    }
}
