package jackal.support;

import jackal.services.DBService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: jackal
 * Date: 20.09.13
 * Time: 14:34
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class ContextHolder {

    private static ApplicationContext ctx = null;
    private static DBService dbService = null;

    public static ApplicationContext getContext() {
        if(ctx==null)
            ctx = new ClassPathXmlApplicationContext("appContext.xml");
        return ctx;
    }

    public static DBService getDBService() {
        if(ctx==null) getContext();
        if(dbService==null) dbService = (DBService)ContextHolder.getContext().getBean("dbService");
        return dbService;
    }
}
