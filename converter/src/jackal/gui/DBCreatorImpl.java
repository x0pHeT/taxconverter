package jackal.gui;

import jackal.services.DBService;
import jackal.support.ContextHolder;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

/**
 * User: jackal
 * Date: 26.09.13
 * Time: 14:51
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class DBCreatorImpl implements Runnable, DBCreator{

    private boolean executed = false;
    private boolean canceled = false;
    private UICallback ui;
    private String[] scripts;

    public DBCreatorImpl(UICallback ui, String[] script) {
        this.ui = ui;
        this.scripts = script;
    }

    @Override
    public synchronized void createTables() {
        if(executed) {
            throw new IllegalStateException("Creator is already executed");
        }
        else {
            ui.disableCreateButton();
            executed = true;
            Thread t = new Thread(this,"DBCreator thread");
            t.start();
        }
    }

    @Override
    public synchronized void cancel() {
        canceled = true;
    }

    private synchronized boolean isCanceled() {
        return canceled;
    }

    @Override
    public void run() {
        ui.addUserMessageFromOuterMethod("Выполняем скрипты создания таблиц");
        DBService service = ContextHolder.getDBService();
        for(int i=0; i<scripts.length; i++) {
            if(isCanceled()) {
                ui.addUserMessageFromOuterMethod("Создание прервано");
                ui.addUserMessageFromOuterMethod("Последний выполенный скрипт ["+scripts[i-1]+"]");
                ui.enableCreateButton();
                return;
            }
            ui.addUserMessageFromOuterMethod("Выполняем скрипт ["+scripts[i]+"]");
            //service.createTable(scripts[i]);
        }
        ui.addUserMessageFromOuterMethod("Все таблицы созданы");
        ui.enableCreateButton();
    }
}
