package jackal.gui.threads;

import jackal.gui.UICallback;
import jackal.services.DBService;
import jackal.support.ContextHolder;

/**
 * User: jackal
 * Date: 26.09.13
 * Time: 14:51
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class TablesCreationThreadImpl implements Runnable, LongOperations {

    private boolean executed = false;
    private boolean canceled = false;
    private UICallback ui;
    private String[] scripts;

    public TablesCreationThreadImpl(UICallback ui, String[] script) {
        this.ui = ui;
        this.scripts = script;
    }

    @Override
    public synchronized void cancel() {
        canceled = true;
    }

    private synchronized boolean isCanceled() {
        return canceled;
    }

    @Override
    public synchronized void execute() {
        if(executed) {
            throw new IllegalStateException("Thread is already executed");
        }
        else {
            ui.disableButtons();
            executed = true;
            Thread t = new Thread(this,"LongOperations thread");
            t.start();
        }
    }

    @Override
    public void run() {
        ui.addUserMessageFromOuterMethod("Выполняем скрипты DDL");
        DBService service = ContextHolder.getDBService();
        for(int i=0; i<scripts.length; i++) {
            if(isCanceled()) {
                ui.addUserMessageFromOuterMethod("Выполнение прервано пользователем");
                ui.addUserMessageFromOuterMethod("Последний выполенный скрипт ["+scripts[i-1]+"]");
                ui.enableButtons();
                return;
            }
            ui.addUserMessageFromOuterMethod("Выполняем скрипт ["+scripts[i]+"]");
            service.createTable(scripts[i]);
        }
        ui.addUserMessageFromOuterMethod("Все скрипты выполнены");
        ui.enableButtons();
    }
}
