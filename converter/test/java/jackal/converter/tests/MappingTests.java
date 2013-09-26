package jackal.converter.tests;

import jackal.objects.year2013.Complaint;
import jackal.services.DBService;
import org.apache.log4j.Logger;
import org.hibernate.dialect.Oracle10gDialect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: jackal
 * Date: 25.09.13
 * Time: 11:14
 * $Rev:$
 * $Author:$
 * $Date:$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@TransactionConfiguration
public class MappingTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static Logger LOG = Logger.getLogger(MappingTests.class);

    @Autowired
    private DBService dbService;

    @Autowired
    ApplicationContext context;

    @Test
    /*@Rollback(true)*/
    @Transactional("hibernateTransactionManager2013")
    public void getComplaintsList() {
        List<Complaint> result = dbService.getComplaintsList();
    }

    @Test
    @Transactional("hibernateTransactionManager2014")
    public void testCreateSQlFromHibernateConfiguration() {
        LOG.debug("context=[" + context+"]");
        String[] script = ((LocalSessionFactoryBean)context.getBean("&hibernateSessionFactory2014"))
                .getConfiguration().generateSchemaCreationScript(new Oracle10gDialect());
        for(String s : script) {
            LOG.debug(s);
        }
    }
}
