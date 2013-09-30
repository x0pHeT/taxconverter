package jackal.gui.threads;

import jackal.gui.UICallback;
import jackal.objects.year2013.Holiday;
import jackal.services.DBService;
import jackal.support.ContextHolder;

import java.util.List;

/**
 * User: jackal
 * Date: 30.09.13
 * Time: 14:56
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class ImportHolidaysThreadImpl extends CommonImpotThreadImpl {

    public ImportHolidaysThreadImpl(UICallback ui) {
        super(ui);
    }

    @Override
    public void run() {
        DBService service = ContextHolder.getDBService();

        List<Holiday> holidayList = service.getHolidaysFromOldDatabase();
        for(Holiday h : holidayList) {
            if(isCanceled()) {
                ui.addUserMessageFromOuterMethod("Операция прервана");
                ui.enableButtons();
                return;
            }
            jackal.objects.year2014.Holiday holiday = convert(h);
            service.saveHolidayNewDatabase(holiday);
        }
        ui.addUserMessageFromOuterMethod("Импорт праздников завершен");
        ui.enableButtons();
    }

    private jackal.objects.year2014.Holiday convert(Holiday h) {
        jackal.objects.year2014.Holiday holiday = new jackal.objects.year2014.Holiday();
        holiday.setDateOfHoliday(h.getDateOfHoliday());
        holiday.setComment(h.getComment());
        return holiday;
    }

}
