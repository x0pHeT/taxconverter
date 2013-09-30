package jackal.gui.threads;

import jackal.gui.UICallback;
import jackal.objects.year2013.Executor;
import jackal.services.DBService;
import jackal.support.ContextHolder;

import java.util.List;

/**
 * User: jackal
 * Date: 30.09.13
 * Time: 12:29
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class ImportExecutorsThreadImpl extends CommonImpotThreadImpl {

    public ImportExecutorsThreadImpl(UICallback ui) {
        super(ui);
    }
    @Override
    public void run() {
        DBService service = ContextHolder.getDBService();

        List<Executor> executors = service.getExecutorsFromOldDatabase();
        for(Executor exec : executors) {
            if(isCanceled()) {
                ui.addUserMessageFromOuterMethod("Операция прервана");
                ui.enableButtons();
                return;
            }
            jackal.objects.year2014.Executor executor = convert(exec);
            service.saveExecutorNewDatabase(executor);
            ui.addUserMessageFromOuterMethod("Импортирован исполнитель "+executor.getFIO());
        }
        ui.addUserMessageFromOuterMethod("Импорт исполнителей завершен");
        ui.enableButtons();
    }

    private jackal.objects.year2014.Executor convert(Executor exec) {
        jackal.objects.year2014.Executor executor = new jackal.objects.year2014.Executor();
        executor.setSurname(exec.getSurname());
        executor.setFirstname(exec.getFirstname());
        executor.setPatronimyc(exec.getPatronimyc());
        executor.setCreated(exec.getCreated());
        executor.setDeleted(exec.isDeleted());
        executor.setSortIdx(exec.getSortIdx());
        return executor;
    }
}
