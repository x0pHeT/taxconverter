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
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional("springTransactionManager")
    public int getInt() {
        return template.query("select 666 from dual", new ResultSetExtractor<Integer>() {
            @Override
            public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getInt(1);
            }
        });
    }

    @Transactional(value = "hibernateTransactionManager", readOnly = true)
    public List getExecutors() {
        /*Criteria c = getSession().createCriteria(Executor.class);
        return c.list();*/
        return getSession().createQuery("from Executor").list();
    }

    @Transactional("hibernateTransactionManager")
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

    @Transactional("hibernateTransactionManager")
    public List<Complaint> getComplaintsList() {
        return getSession().createQuery("from Complaint").list();
    }
}
