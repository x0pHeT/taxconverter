package jackal.gui.threads;

import jackal.gui.UICallback;
import jackal.objects.year2013.User;
import jackal.services.DBService;
import jackal.support.ContextHolder;

import java.util.List;

/**
 * User: jackal
 * Date: 30.09.13
 * Time: 12:09
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class ImportUsersThreadImpl extends CommonImpotThreadImpl {

    public ImportUsersThreadImpl(UICallback ui) {
        super(ui);
    }

    @Override
    public void run() {
        DBService service = ContextHolder.getDBService();

        List<User> users = service.getUsersFromOldDatabase();
        for(User u : users) {
            if(isCanceled()) {
                ui.addUserMessageFromOuterMethod("Операция прервана");
                ui.enableButtons();
                return;
            }
            jackal.objects.year2014.User user = convertUser(u);
            service.saveUserNewDatabase(user);
            ui.addUserMessageFromOuterMethod("Импортирован пользователь "+user.getFIO());
        }
        ui.addUserMessageFromOuterMethod("Импорт пользователей завершен");
        ui.enableButtons();
    }

    private jackal.objects.year2014.User convertUser(User u) {
        jackal.objects.year2014.User user = new jackal.objects.year2014.User();
        user.setSurname(u.getSurname());
        user.setFirstname(u.getFirstname());
        user.setPatronimyc(u.getPatronimyc());
        user.setCreated(u.getCreated());
        user.setUsername(u.getUsername());
        user.setPassword(u.getPassword());
        user.setRole(u.getRole());
        user.setEnabled(u.isEnabled());
        user.setDeleted(u.isDeleted());
        return user;
    }


}
