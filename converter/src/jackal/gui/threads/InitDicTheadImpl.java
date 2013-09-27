package jackal.gui.threads;

import jackal.gui.UICallback;
import jackal.services.DBService;
import jackal.support.ContextHolder;
import org.hibernate.HibernateException;

/**
 * User: jackal
 * Date: 27.09.13
 * Time: 8:49
 * $Rev:$
 * $Author:$
 * $Date:$
 */
public class InitDicTheadImpl implements Runnable,LongOperations {

    private UICallback ui;
    private boolean cancaled = false;
    private boolean executed = false;

    public InitDicTheadImpl(UICallback ui) {
        this.ui = ui;
    }

    private synchronized boolean isCancaled() {
        return cancaled;
    }

    @Override
    public synchronized void execute() {
        if (executed) {
            throw new IllegalStateException("Thread already executed");
        } else {
            executed = true;
            ui.disableButtons();
            Thread t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        DBService service = ContextHolder.getDBService();
        try {
        /*
        Признак жалобы
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Признак жалобы\"");
            service.saveDictionaryItem("ComplaintDiscrimina!te","1","Жалоба на решения, вынесенные налоговыми органами в порядке статьи 101 Кодекса");
            service.saveDictionaryItem("ComplaintDiscriminate","2","Жалоба на решения, вынесенные налоговыми органами в порядке статьи 101.4 Кодекса");
            service.saveDictionaryItem("ComplaintDiscriminate","3","Жалоба (обращение) на действия и (или) бездействие должностных лиц налоговых органов (налоговые споры)");
            service.saveDictionaryItem("ComplaintDiscriminate","4","Апелляционная жалоба");
            service.saveDictionaryItem("ComplaintDiscriminate","5","Жалоба на постановление по делу об административном правонарушении");
            service.saveDictionaryItem("ComplaintDiscriminate","6","Жалоба на нарушение порядка предоставления государственных услуг");
            service.saveDictionaryItem("ComplaintDiscriminate","7","Жалоба (обращение) на иные акты налоговых органов");
        /*
        Нормативно-правовой акт, регламентирующий срок рассмотрения жалобы
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"НПА\"");
            service.saveDictionaryItem("ComplaintLegalAct","1","Налоговый кодекс Российской Федерации");
            service.saveDictionaryItem("ComplaintLegalAct","2","Федеральный закон № 59-ФЗ");
            service.saveDictionaryItem("ComplaintLegalAct","3","Кодекс об административных правонарушениях");
            service.saveDictionaryItem("ComplaintLegalAct","4","Федеральный закон №210-ФЗ (Постановление Правительства РФ №840)");
        /*
        Вид контроля
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Вид контроля\"");
            service.saveDictionaryItem("ComplControlType","1","Камеральные налоговые проверки (КНП)");
            service.saveDictionaryItem("ComplControlType","2","КНП налоговых деклараций по НДС или по акцизу, в которых заявлено право на возмещение налога или акциза");
            service.saveDictionaryItem("ComplControlType","3","КНП правомерности предоставления налоговых вычетов по НДФЛ");
            service.saveDictionaryItem("ComplControlType","4","Выездные налоговые проверки (ВНП)");
            service.saveDictionaryItem("ComplControlType","4.1","Повторные выездные налоговые проверки (ПВНП)");
            service.saveDictionaryItem("ComplControlType","5","Обнаружение фактов, свидетельствующих о налоговых правонарушениях (за исключением правонарушений, предусмотренных статьями 120, 122 и 123 Кодекса)");
            service.saveDictionaryItem("ComplControlType","6","Дела об административных правонарушениях");
            service.saveDictionaryItem("ComplControlType","7","Иные формы налогового контроля");
            service.saveDictionaryItem("ComplControlType","8","Иные формы неналогового контроля");
        /*
        Вид обжалуемого документа (в том числе действия, бездействие)
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Вид обжалуемого документа\"");
            service.saveDictionaryItem("ComplAppealDocType","1","Решение о привлечении к ответственности за совершение налогового правонарушения");
            service.saveDictionaryItem("ComplAppealDocType","2","Решение об отказе в привлечении к ответственности за совершение налогового правонарушения");
            service.saveDictionaryItem("ComplAppealDocType","3","Решение об отказе в возмещении (полностью или частично) суммы НДС, заявленной к возмещению");
            service.saveDictionaryItem("ComplAppealDocType","4","Решение об отказе в возмещении (полностью или частично) суммы акциза, заявленной к возмещению");
            service.saveDictionaryItem("ComplAppealDocType","5","Решение о привлечении лица к ответственности за налоговое правонарушение");
            service.saveDictionaryItem("ComplAppealDocType","6","Решение об отказе в привлечении лица к ответственности за налоговое правонарушение");
            service.saveDictionaryItem("ComplAppealDocType","7","Постановление по делу об административном правонарушении");
            service.saveDictionaryItem("ComplAppealDocType","8","Иные документы");
            service.saveDictionaryItem("ComplAppealDocType","9","Акт о предоставлении (отказе в предоставлении) государственных услуг");
            service.saveDictionaryItem("ComplAppealDocType","10","Действия (бездействие) должностных лиц");
        /*
        Налоговая и административная ответственность
        */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Налоговая отвественность\"");
            service.saveDictionaryItem("ComplResponsibility","116","Нарушение  порядка постановки на учет в налоговом органе");
            service.saveDictionaryItem("ComplResponsibility","118","Нарушение срока представления сведений об открытии и закрытии счета в банке");
            service.saveDictionaryItem("ComplResponsibility","119","Непредставление налоговой декларации");
            service.saveDictionaryItem("ComplResponsibility","119.1","Нарушение установленного способа представления налоговой декларации (расчета)");
            service.saveDictionaryItem("ComplResponsibility","120","Грубое нарушение правил учета доходов и расходов и объектов налогообложения");
            service.saveDictionaryItem("ComplResponsibility","122","Неуплата или неполная уплата сумм налога (сбора)");
            service.saveDictionaryItem("ComplResponsibility","122.1","Сообщение участником консолидированной группы налогоплательщиков ответственному участнику этой группы недостоверных данных (несообщение данных)");
            service.saveDictionaryItem("ComplResponsibility","123","Невыполнение налоговым агентом обязанности по удержанию и (или) перечислению налогов");
            service.saveDictionaryItem("ComplResponsibility","125","Несоблюдение порядка владения, пользования и (или) распоряжения имуществом, на которое наложен арест или в отношении которого налоговым органом приняты обеспечительные меры в виде залога");
            service.saveDictionaryItem("ComplResponsibility","126","Непредставление налоговому органу сведений, необходимых для осуществления налогового контроля");
            service.saveDictionaryItem("ComplResponsibility","128","Ответственность свидетеля");
            service.saveDictionaryItem("ComplResponsibility","129","Отказ эксперта, переводчика или специалиста от участия в проведении налоговой проверки, дача заведомо ложного заключения или осуществление заведомо ложного перевода");
            service.saveDictionaryItem("ComplResponsibility","129.1","Неправомерное несообщение сведений налоговому органу");
            service.saveDictionaryItem("ComplResponsibility","129.2","Нарушение порядка регистрации объектов игорного бизнеса");
            service.saveDictionaryItem("ComplResponsibility","129.3","Неуплата или неполная уплата сумм налога в результате применения в целях налогообложения в контролируемых сделках коммерческих и (или) финансовых условий, не сопоставимых с коммерческими и (или) финансовыми условиями сделок между лицами, не являющимися взаимозависимыми");
            service.saveDictionaryItem("ComplResponsibility","129.4","Неправомерное непредставление уведомления о контролируемых сделках или представление налогоплательщиком в налоговый орган уведомления о контролируемых сделках, содержащего недостоверные сведения");
            service.saveDictionaryItem("ComplResponsibility","132","Нарушение банком порядка открытия счета налогоплательщику");
            service.saveDictionaryItem("ComplResponsibility","133","Нарушение срока исполнения поручения о перечислении налога, авансового платежа, сбора, пеней, штрафа");
            service.saveDictionaryItem("ComplResponsibility","134","Неисполнение банком решения налогового органа о приостановлении операций по счетам налогоплательщика, плательщика сбора или налогового агента");
            service.saveDictionaryItem("ComplResponsibility","135","Неисполнение банком поручения налогового органа о перечислении налога; авансового платежа, сбора, пеней, штрафа");
            service.saveDictionaryItem("ComplResponsibility","135.1","Непредставление банком справок (выписок) по операциям и счетам в налоговый орган");
            service.saveDictionaryItem("ComplResponsibility","135.2","Нарушение банком обязанностей, связанных с электронными денежными средствами");
            service.saveDictionaryItem("ComplResponsibility","136","Порядок взыскания с банков штрафов и пеней");
            //service.saveDictionaryItem("ComplResponsibility","2","Кодекс об административных правонарушениях Российской Федерации");
        /*
        Результат по жалобе
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Результат по жалобе\"");
            service.saveDictionaryItem("ComplResult","1","Рассмотрено");
            service.saveDictionaryItem("ComplResult","2","Оставлено без рассмотрения в соответствии со статьей 139³ Кодекса");
            service.saveDictionaryItem("ComplResult","3","Направлено письмо по иным причинам, в том числе предусмотренным  Федеральным законом № 59-ФЗ");
            service.saveDictionaryItem("ComplResult","4","Жалоба направлена на рассмотрение по подведомственности");
        /*
        Результат рассмотрения жалобы
         */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Результат рассмотрения\"");
            service.saveDictionaryItem("ComplResultOfConsideration","1","Оставлено без удовлетворения");
            service.saveDictionaryItem("ComplResultOfConsideration","2","Удовлетворено полностью");
            service.saveDictionaryItem("ComplResultOfConsideration","3","Удовлетворено частично");
        /*
        Вид обжалуемого налога (сбора)
        */
            ui.addUserMessageFromOuterMethod("Инициализация справочника \"Вид обжалуемого налога\"");
            service.saveDictionaryItem("ComplAppealTaxType","1","Налог на добавленную стоимость");
            service.saveDictionaryItem("ComplAppealTaxType","2","Акцизы");
            service.saveDictionaryItem("ComplAppealTaxType","3","Налог на доходы физических лиц");
            service.saveDictionaryItem("ComplAppealTaxType","4","Единый социальный налог");
            service.saveDictionaryItem("ComplAppealTaxType","5","Налог на прибыль организаций");
            service.saveDictionaryItem("ComplAppealTaxType","6","Налог на добычу полезных ископаемых");
            service.saveDictionaryItem("ComplAppealTaxType","7","Водный налог");
            service.saveDictionaryItem("ComplAppealTaxType","8","Сборы за пользование объектами животного мира и за пользование объектами водных биологических ресурсов");
            service.saveDictionaryItem("ComplAppealTaxType","9","Государственная пошлина");
            service.saveDictionaryItem("ComplAppealTaxType","10","Налог на имущество организаций");
            service.saveDictionaryItem("ComplAppealTaxType","11","Налог на игорный бизнес");
            service.saveDictionaryItem("ComplAppealTaxType","12","Транспортный налог");
            service.saveDictionaryItem("ComplAppealTaxType","13","Земельный налог");
            service.saveDictionaryItem("ComplAppealTaxType","14","Налог на имущество физических лиц");
            service.saveDictionaryItem("ComplAppealTaxType","15","Единый сельскохозяйственный налог");
            service.saveDictionaryItem("ComplAppealTaxType","16","Единый налог уплачиваемый при применении упрощенной системы налогообложения");
            service.saveDictionaryItem("ComplAppealTaxType","17","Единый налог на вмененный доход");
            service.saveDictionaryItem("ComplAppealTaxType","17","Иные");
        } catch (HibernateException e) {
            ui.addUserMessageFromOuterMethod("Ошибка: "+e.getMessage());
            ui.addUserMessageFromOuterMethod("Импорт справочников остановлен");
            ui.enableButtons();
            return;
        }
        ui.addUserMessageFromOuterMethod("Инициализация справочников завершена.");
        ui.enableButtons();
    }

    @Override
    public synchronized void cancel() {
        cancaled = true;
    }
}
