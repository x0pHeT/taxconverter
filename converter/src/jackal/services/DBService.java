package jackal.services;

import jackal.objects.year2013.Complaint;
import jackal.objects.year2014.DictionaryItem;
import jackal.objects.year2014.Executor;
import jackal.objects.year2014.Holiday;
import jackal.objects.year2014.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    private SessionFactory hibernateSessionFactory2014;

    @Autowired
    private SessionFactory hibernateSessionFactory2013;

    private Session getSession2014() {
        return hibernateSessionFactory2014.getCurrentSession();
    }

    private Session getSession2013() {
        return hibernateSessionFactory2013.getCurrentSession();
    }

    @Transactional
    public void createTable(String createSql) {
        template.execute(createSql);
    }

    @Transactional("hibernateTransactionManager2014")
    public void saveDictionaryItem(String entityName, String code, String text) {
        DictionaryItem item = new DictionaryItem(code,text);
        getSession2014().save(entityName, item);
    }

    @Transactional(readOnly = true)
    public boolean isTablesYear2014Exist() {
        return template.query("SELECT count(1) FROM ALL_TABLES WHERE OWNER='TAX2014' AND TABLE_NAME = 'COMPL_APPEAL_DOC'", new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                rs.next();
                int count = rs.getInt(1);
                return count==1;
            }
        });
    }

    @Transactional(value = "hibernateTransactionManager2013", readOnly = true)
    public List<jackal.objects.year2013.User> getUsersFromOldDatabase() {
        return getSession2013().createQuery("from User").list();
    }

    @Transactional("hibernateTransactionManager2014")
    public void saveUserNewDatabase(User user) {
        getSession2014().save(user);
    }

    @Transactional(value = "hibernateTransactionManager2013", readOnly = true)
    public List<jackal.objects.year2013.Executor> getExecutorsFromOldDatabase() {
        return getSession2013().createQuery("from Executor").list();
    }

    @Transactional("hibernateTransactionManager2014")
    public void saveExecutorNewDatabase(Executor executor) {
        getSession2014().save(executor);
    }

    @Transactional(value = "hibernateTransactionManager2013", readOnly = true)
    public List<jackal.objects.year2013.Holiday> getHolidaysFromOldDatabase() {
        return getSession2013().createQuery("from Holiday").list();
    }

    @Transactional("hibernateTransactionManager2014")
    public void saveHolidayNewDatabase(Holiday holiday) {
        getSession2014().save(holiday);
    }
}
