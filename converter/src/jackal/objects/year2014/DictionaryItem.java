package jackal.objects.year2014;

import java.io.Serializable;

/**
 * User: jackal
 * Date: 10.11.2008
 * Time: 14:17:37
 * $Rev: 122 $
 * $Author: jackal $
 * $Date: 2012-04-03 18:54:07 +0400 (Вт., 03 апр. 2012) $
 */
public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 5762692321361395719L;

    private Long id;
    private String code;
    private String content;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public DictionaryItem() {
    }

    public DictionaryItem(String code,String content) {
        this.code=code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
