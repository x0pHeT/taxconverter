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
public class TablesCreationThreadImpl extends CommonImpotThreadImpl {

    private String[] scripts;

    public TablesCreationThreadImpl(UICallback ui, String[] script) {
        super(ui);
        this.scripts = script;
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
            try {
                service.createTable(scripts[i]);
            } catch (Exception e) {
                ui.addUserMessageFromOuterMethod("Ошибка выполнения скрипта");
                ui.addUserMessageFromOuterMethod(e.getMessage());
            }
        }
        ui.addUserMessageFromOuterMethod("Все скрипты выполнены");
        ui.enableButtons();
    }
}
