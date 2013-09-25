package jackal.objects.year2013;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Jackal
 * Date: 09.11.2008
 * Time: 21:19:09
 * $Rev: 193 $
 * $Author: jackal $
 * $Date: 2013-04-18 09:27:12 +0400 (Чт, 18 апр 2013) $
 */
public abstract class Declarant implements Serializable {

    private static final long serialVersionUID = -1819083812170280947L;

    private Long id;
    protected String INN; //Реквизит №9.3

    public Long getId() {
        return id;
    }

    /*public void setId(Long id) {
        this.id = id;
    }*/

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        fireChange("INN",this.INN, INN);
        this.INN = INN;
    }

    public String getFullContentAsString() {
        return "";
    }

    protected Map<String,Object> originalValues = new HashMap<String, Object>();
    protected Map<String,Object> modifiedValues = new HashMap<String, Object>();

    protected void fireChange(String propertyName, Object oldValue, Object newValue) {
        if (id==null) return;
    }

    public String getChangesAsString() {
        if(modifiedValues.size()==0) return null;
        StringBuilder sb = new StringBuilder("Изменения в заявителе: ");
        for(String key : modifiedValues.keySet()) {
            Object originalObject1 = originalValues.get(key);
            String o1 = originalObject1.toString();
            Object originalObject2 = modifiedValues.get(key);
            String o2 = originalObject2.toString();

            sb.append(key).append(":")
                    .append(o1).append("->").append(o2).append("; \n");
        }
        return sb.toString();
    }
}
