package jackal.converter.tests;

import jackal.objects.year2013.Complaint;
import jackal.services.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DBService dbService;

    @Test
    /*@Rollback(true)*/
    @Transactional("hibernateTransactionManager")
    public void getComplaintsList() {
        List<Complaint> result = dbService.getComplaintsList();
    }
}
