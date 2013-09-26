package jackal.services;

import jackal.objects.year2013.Complaint;
import jackal.objects.year2013.Executor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: jackal
 * Date: 23.09.13
 * Time: 11:11
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class DBService {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private SessionFactory hibernateSessionFactory2013;

    private Session getSession() {
        return hibernateSessionFactory2013.getCurrentSession();
    }

    @Transactional()
    public void createTable(String createSql) {
        template.execute(createSql);
    }

    @Transactional(value = "hibernateTransactionManager2013", readOnly = true)
    public List getExecutors() {
        return getSession().createQuery("from Executor").list();
    }

    @Transactional("hibernateTransactionManager2013")
    public void addExecutors(Executor executor) {
        if(executor.getSortIdx()==null) {
            Integer idx = (Integer)getSession().createCriteria(Executor.class).setProjection(Projections.max("sortIdx")).uniqueResult();
            if(idx!=null && idx>=0) {
                executor.setSortIdx(idx+1);
            } else {
                executor.setSortIdx(0);
            }
        }

        getSession().save(executor);
    }

    @Transactional("hibernateTransactionManager2013")
    public List<Complaint> getComplaintsList() {
        return getSession().createQuery("from Complaint").list();
    }
}
